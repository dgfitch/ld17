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

public class GameOverScene extends Scene2D {
    
    Button continueButton;
    Button quitButton;
    Group componentLayer;
    int finalScore;

    public GameOverScene(int score) {
        super();
        finalScore = score;
    }
    
    @Override
    public void load() {
        
        Label title = new Label(CoreFont.load("hello.font.png"), "GAME OVER", 320, 240);
        title.setAnchor(0.5, 0.5);
        title.scale(title.width.get() / 2, title.height.get() / 2, title.width.get(), title.height.get(), 4000, pulpcore.animation.Easing.ELASTIC_IN);
        title.alpha.animate(0, 255, 10000);
        title.y.animate(240, 180, 10000, pulpcore.animation.Easing.ELASTIC_OUT);

        Label scoreWas = new Label(CoreFont.load("hello.font.png"), "Final score: " + Integer.toString(finalScore), 320, 100);
        scoreWas.setAnchor(0.5, 0.5);
        scoreWas.scaleTo(scoreWas.width.get() / 2, scoreWas.height.get() / 2, 4000);
        scoreWas.alpha.animateTo(150, 10000);

        continueButton = Button.createLabeledButton("OK", 320, 320);
        continueButton.setAnchor(0.5, 0.5);
        quitButton = Button.createLabeledButton("Quit", 320, 370);
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
            Stage.setScene(new LD17());
        }
        else if (quitButton.isClicked()) {
            Stage.setScene(new TitleScene());
        }
    }
}

