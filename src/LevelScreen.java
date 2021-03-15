import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.audio.Ogg;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class LevelScreen extends BaseScreen{

    private Turtle turtle;
    private boolean win;
    private Label starfishLabel;

    // TODO --> for scenes
    private DialogBox dialogBox;

    // TODO --> for music and sounds
    private float audioVolume;
    private Sound waterDrop;
    private Music instrumental;
    private Music oceanSurf;

    // TODO --> shark sound
    private Sound eaten;
    private Sound burp;

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
        new Rock(600,350, mainStage);
        new Rock(740,570, mainStage);
        new Rock(720,200, mainStage);
        new Rock(880,220, mainStage);
        new Rock(900,350, mainStage);
        new Rock(590,40, mainStage);
        new Rock(40,600, mainStage);
        new Rock(159,500, mainStage);
        new Rock(60,330, mainStage);
        // new Rock(70,400, mainStage);
        new Rock(200,150, mainStage);
        new Rock(100,300, mainStage);
        new Rock(300,350, mainStage);
        new Rock(450,200, mainStage);

        new Shark(400, 300, mainStage);
        new Shark(900, 700, mainStage);
        new Shark(670, 500, mainStage);
        new Shark(150, 800, mainStage);

        turtle = new Turtle(20,20, mainStage);

        Sign sign1 = new Sign(20,400, mainStage);
        sign1.setText("West Starfish Bay");

        Sign sign2 = new Sign(600,300, mainStage);
        sign2.setText("East Starfish Bay");

        win = false;

        // TODO --> User interface code

        starfishLabel = new Label("Starfish Left:", BaseGame.labelStyle);
        starfishLabel.setColor(Color.CYAN);
        // TODO --> method used before which set Position of starfishLabel
        // starfishLabel.setPosition(20, 520);
        // uiStage.addActor(starfishLabel);

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();

        Texture buttonTex = new Texture(Gdx.files.internal("assets/undo.png"));
        TextureRegion buttonRegion = new TextureRegion(buttonTex);
        buttonStyle.up = new TextureRegionDrawable(buttonRegion);

        Button restartButton = new Button(buttonStyle);
        restartButton.setColor(Color.CYAN);
        // TODO --> method used before which set Position of restartButton
        // restartButton.setPosition(720, 520);
        // uiStage.addActor(restartButton);

        restartButton.addListener(
                (Event e) ->
                {
                    // TODO --> code before added music and sound
                    /* if (!(e instanceof InputEvent) ||
                    !((InputEvent)e).getType().equals(InputEvent.Type.touchDown))
                        return false;

                    StarfishGame.setActiveScreen(new LevelScreen());
                    return false;

                     */

                    if (!isTouchDownEvent(e)){
                        return false;
                    }

                    instrumental.dispose();
                    oceanSurf.dispose();

                    StarfishGame.setActiveScreen(new LevelScreen());
                    return true;
                }
        );

        Button.ButtonStyle buttonStyle2 = new Button.ButtonStyle();

        Texture buttonTex2 = new Texture(Gdx.files.internal("assets/audio.png"));
        TextureRegion buttonRegion2 = new TextureRegion(buttonTex2);
        buttonStyle2.up = new TextureRegionDrawable(buttonRegion2);

        Button muteButton = new Button(buttonStyle2);
        muteButton.setColor(Color.CYAN);

        muteButton.addListener(
                (Event e) -> {
                    if (!isTouchDownEvent(e)) {
                        return false;
                    }

                    audioVolume = 1 - audioVolume;
                    instrumental.setVolume(audioVolume);
                    oceanSurf.setVolume(audioVolume);

                    return true;
                }
        );

        uiTable.pad(10);
        uiTable.add(starfishLabel).top();
        uiTable.add().expandX().expandY();
        uiTable.add(muteButton).top();
        uiTable.add(restartButton).top();

        dialogBox = new DialogBox(0,0, uiStage);
        dialogBox.setBackgroundColor( Color.TAN );
        dialogBox.setFontColor( Color.BROWN );
        dialogBox.setDialogSize(600, 100);
        dialogBox.setFontScale(0.80f);
        dialogBox.alignCenter();
        dialogBox.setVisible(false);

        uiTable.row();
        uiTable.add(dialogBox).colspan(4);

        waterDrop    = Gdx.audio.newSound(Gdx.files.internal("assets/Water_Drop.ogg"));
        eaten = Gdx.audio.newSound(Gdx.files.internal("assets/eating.ogg"));
        burp = Gdx.audio.newSound(Gdx.files.internal("assets/burp.ogg"));
        instrumental = Gdx.audio.newMusic(Gdx.files.internal("assets/Master_of_the_Feast.ogg"));
        oceanSurf    = Gdx.audio.newMusic(Gdx.files.internal("assets/Ocean_Waves.ogg"));

        audioVolume = 1.00f;
        instrumental.setLooping(true);
        instrumental.setVolume(audioVolume);
        instrumental.play();
        oceanSurf.setLooping(true);
        oceanSurf.setVolume(audioVolume);
        oceanSurf.play();


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
                waterDrop.play(audioVolume);
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
                long t= System.currentTimeMillis();
                long end = t+2000;

                eaten.play(audioVolume);
                turtle.addAction( Actions.fadeOut(1));
                burp.play(audioVolume);

                while(System.currentTimeMillis() < end) {
                    // do something
                    BaseActor GameOverMessage = new BaseActor(0, 0, uiStage);
                    GameOverMessage.loadTexture("assets/game-over.png");
                    GameOverMessage.centerAtPosition(400, 300);
                }

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

        for ( BaseActor signActor : BaseActor.getList(mainStage, "Sign") )
        {
            Sign sign = (Sign)signActor;

            turtle.preventOverlap(sign);
            boolean nearby = turtle.isWithinDistance(4, sign);

            if ( nearby && !sign.isViewing() )
            {
                dialogBox.setText( sign.getText() );
                dialogBox.setVisible( true );
                sign.setViewing( true );
            }

            if (sign.isViewing() && !nearby)
            {
                dialogBox.setText( " " );
                dialogBox.setVisible( false );
                sign.setViewing( false );
            }
        }
    }
}
