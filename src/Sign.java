import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sign extends BaseActor{

    // TODO --> The text to be displayed
    private String text;

    // TODO --> Determine if sign text is currently being displayed
    private boolean viewing;


    public Sign(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("assets/sign.png");
        text = " ";
        viewing = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String t) {
        text = t;
    }

    public boolean isViewing() {
        return viewing;
    }

    public void setViewing(boolean v) {
        viewing = v;
    }
}
