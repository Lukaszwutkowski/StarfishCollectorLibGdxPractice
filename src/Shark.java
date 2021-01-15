import com.badlogic.gdx.scenes.scene2d.Stage;

public class Shark extends BaseActor{
    public Shark(float x, float y, Stage s) {
        super(x, y, s);

        loadTexture("assets/sharky.png");
        setBoundaryPolygon(8);
    }
}
