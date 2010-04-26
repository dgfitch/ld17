import pulpcore.animation.event.SceneChangeEvent;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Sprite;
import pulpcore.sprite.FilledSprite;
import static pulpcore.image.Colors.*;
import pulpcore.Stage;
import pulpcore.sound.Sound;

// normally I wouldn't copy and paste, but LESS THAN AN HOUR TO GO!
public class GameWinScene extends Scene2D {
    
    Button continueButton;
    Button quitButton;
    Group componentLayer;
    int finalScore;
    Player player;
    Sound theme;

    public GameWinScene(Player p) {
        super();
        finalScore = p.score;
        player = p;
        theme = Sound.load("gamewin.ogg");
    }
    
    @Override
    public void load() {
        theme.play();
        
        Label title = new Label(CoreFont.load("hello.font.png"), "You survived!", 320, 240);
        title.setAnchor(0.5, 0.5);
        title.scale(title.width.get() / 2, title.height.get() / 2, title.width.get(), title.height.get(), 4000, pulpcore.animation.Easing.ELASTIC_IN);
        title.alpha.animate(0, 255, 10000);
        title.y.animate(240, 180, 10000, pulpcore.animation.Easing.ELASTIC_OUT);

        Label scoreWas = new Label(CoreFont.load("hello.font.png"), "Final score: " + Integer.toString(finalScore), 320, 100);
        scoreWas.setAnchor(0.5, 0.5);
        scoreWas.scaleTo(scoreWas.width.get() / 2, scoreWas.height.get() / 2, 4000);
        scoreWas.alpha.animateTo(150, 10000);

        continueButton = Button.createLabeledButton("Play Again", 320, 320);
        continueButton.setAnchor(0.5, 0.5);
        quitButton = Button.createLabeledButton("I'm a Winner", 320, 370);
        quitButton.setAnchor(0.5, 0.5);
        
        componentLayer = new Group();
        componentLayer.add(continueButton);
        componentLayer.add(quitButton);
        
        add(new FilledSprite(BLACK));
        add(title);
        add(scoreWas);
        addLayer(componentLayer);
    }
    
    @Override 
    public void update(int elapsedTime) {
        if (continueButton.isClicked()) {
            Stage.setScene(new Level(1, new Player()));
        }
        else if (quitButton.isClicked()) {
            Stage.setScene(new TitleScene());
        }
    }
}


