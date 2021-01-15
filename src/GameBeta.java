import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class GameBeta extends Game {

    protected Stage mainStage;
    protected Stage uiStage;

    public void create() {
        mainStage = new Stage();
        uiStage = new Stage();
        initialize();
    }

    protected abstract void initialize();


    /** Gameloop stages:
    // (1) process input
    // (2) update state of game world
    // (3) render the graphics */
    public void render() {

        float dt = Gdx.graphics.getDeltaTime();
        mainStage.act(dt);
        uiStage.act(dt);
        update(dt);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainStage.draw();
        uiStage.draw();
    }

    protected abstract void update(float dt);
}
