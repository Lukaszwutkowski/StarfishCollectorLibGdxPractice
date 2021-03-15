import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen extends BaseScreen {

    @Override
    public void initialize() {
        BaseActor ocean = new BaseActor(0, 0, mainStage);
        ocean.loadTexture("assets/water.jpg");
        ocean.setSize(800, 600);

        BaseActor title = new BaseActor(0, 0, mainStage);
        title.loadTexture("assets/starfish-collector.png");
        title.centerAtPosition(400, 300);
        title.moveBy(0, 100);

        // TODO --> method used before which start game by pressing the button on keyboard
       /* BaseActor start = new BaseActor(0, 0, mainStage);
        start.loadTexture("assets/message-start.png");
        start.centerAtPosition(400, 300);
        start.moveBy(0, -100);
        */

        TextButton startButton = new TextButton("Start", BaseGame.textButtonStyle);
        // TODO --> method used before which set Position of startButton
        // startButton.setPosition(150, 150);
        // uiStage.addActor(startButton);

        startButton.addListener(
                (Event e) ->
                {
                    if (!(e instanceof InputEvent) ||
                            !((InputEvent) e).getType().equals(InputEvent.Type.touchDown))
                        return false;

                    // TODO --> ADDED CUTSCENE SCREEN (StoryScreen) to start after pressing start button
                    StarfishGame.setActiveScreen(new StoryScreen());
                    // StarfishGame.setActiveScreen(new LevelScreen());
                    return false;
                }
        );

        TextButton quitButton = new TextButton("Quit", BaseGame.textButtonStyle);
        // TODO --> method used before which set Position of quitButton
        // quitButton.setPosition(500, 150);
        // uiStage.addActor(quitButton);

        quitButton.addListener(
                (Event e) ->
                {
                    if (!(e instanceof InputEvent) ||
                            !((InputEvent) e).getType().equals(InputEvent.Type.touchDown))
                        return false;

                    Gdx.app.exit();
                    return false;
                }
        );

        uiTable.add(title).colspan(2);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.add(quitButton);
    }

    public boolean keyDown(int keyCode) {

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            StarfishGame.setActiveScreen(new LevelScreen());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        return false;
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            StarfishGame.setActiveScreen(new LevelScreen());
        }

    }
}
