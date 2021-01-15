import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LevelScreen extends BaseScreen{

    private Turtle turtle;
    private boolean win;
    private Label starfishLabel;

    @Override
    public void initialize()
    {
        BaseActor ocean = new BaseActor(0,0, mainStage);
        ocean.loadTexture( "assets/water-border.jpg" );
        ocean.setSize(1200,900);
        BaseActor.setWorldBounds(ocean);

        new Starfish(400,400, mainStage);
        new Starfish(500,100, mainStage);
        new Starfish(100,450, mainStage);
        new Starfish(200,250, mainStage);
        new Starfish(1000,250, mainStage);
        new Starfish(400,460, mainStage);
        new Starfish(600,780, mainStage);
        new Starfish(700,400, mainStage);
        new Starfish(600,120, mainStage);
        new Starfish(500,500, mainStage);
        new Starfish(800,450, mainStage);
        new Starfish(840,670, mainStage);
        new Starfish(920,100, mainStage);
        new Starfish(980,320, mainStage);
        new Starfish(1000,450, mainStage);
        new Starfish(690,100, mainStage);
        new Starfish(20,800, mainStage);
        new Starfish(69,700, mainStage);
        new Starfish(30,530, mainStage);
        new Starfish(90,600, mainStage);


        new Rock(800,50, mainStage);
        new Rock(300,160, mainStage);
        new Rock(400,580, mainStage);
        new Rock(500,400, mainStage);
        new Rock(380,120, mainStage);
        new Rock(500,400, mainStage);
        new Rock(600,350, mainStage);
        new Rock(740,570, mainStage);
        new Rock(720,200, mainStage);
        new Rock(880,220, mainStage);
        new Rock(900,350, mainStage);
        new Rock(590,40, mainStage);
        new Rock(40,600, mainStage);
        new Rock(159,500, mainStage);
        new Rock(60,330, mainStage);
        new Rock(70,400, mainStage);
        new Rock(200,150, mainStage);
        new Rock(100,300, mainStage);
        new Rock(300,350, mainStage);
        new Rock(450,200, mainStage);

        new Shark(400, 300, mainStage);
        new Shark(900, 700, mainStage);
        new Shark(670, 500, mainStage);
        new Shark(150, 800, mainStage);

        turtle = new Turtle(20,20, mainStage);

        win = false;

        starfishLabel = new Label("Starfish Left:", BaseGame.labelStyle);
        starfishLabel.setColor(Color.CYAN);
        starfishLabel.setPosition(20, 520);
        uiStage.addActor(starfishLabel);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();

        Texture buttonTex = new Texture(Gdx.files.internal("assets/undo.png"));
        TextureRegion buttonRegion = new TextureRegion(buttonTex);
        buttonStyle.up = new TextureRegionDrawable(buttonRegion);

        Button restartButton = new Button(buttonStyle);
        restartButton.setColor(Color.CYAN);
        restartButton.setPosition(720, 520);
        uiStage.addActor(restartButton);

        restartButton.addListener(
                (Event e) ->
                {
                    if (!(e instanceof InputEvent) ||
                    !((InputEvent)e).getType().equals(InputEvent.Type.touchDown))
                        return false;

                    StarfishGame.setActiveScreen(new LevelScreen());
                    return false;
                }
        );
    }

    @Override
    public void update(float dt)
    {
        for (BaseActor rockActor : BaseActor.getList(mainStage, "Rock"))
            turtle.preventOverlap(rockActor);

        for (BaseActor starfishActor : BaseActor.getList(mainStage, "Starfish"))
        {
            Starfish starfish = (Starfish)starfishActor;
            if ( turtle.overlaps(starfish) && !starfish.collected )
            {
                starfish.collected = true;
                starfish.clearActions();
                starfish.addAction( Actions.fadeOut(1) );
                starfish.addAction( Actions.after( Actions.removeActor() ) );

                Whirlpool whirl = new Whirlpool(0,0, mainStage);
                whirl.centerAtActor( starfish );
                whirl.setOpacity(0.25f);
            }
        }

        for (BaseActor sharkActor : BaseActor.getList(mainStage, "Shark")) {
            Shark shark = (Shark)sharkActor;
            if ( turtle.overlaps(shark))
            {
                BaseActor GameOverMessage = new BaseActor(0, 0, uiStage);
                GameOverMessage.loadTexture("assets/game-over.png");
                GameOverMessage.centerAtPosition(400, 300);
                turtle.addAction( Actions.fadeOut(1));
                turtle.addAction( Actions.after(Actions.removeActor()));
            }
        }

        if ( BaseActor.count(mainStage, "Starfish") == 0 && !win )
        {
            win = true;
            BaseActor youWinMessage = new BaseActor(0,0,uiStage);
            youWinMessage.loadTexture("assets/you-win.png");
            youWinMessage.centerAtPosition(400,300);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction( Actions.delay(1) );
            youWinMessage.addAction( Actions.after( Actions.fadeIn(1) ) );
        }

        starfishLabel.setText("Starfish Left: " + BaseActor.count(mainStage, "Starfish"));
    }
}
