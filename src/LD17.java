import pulpcore.CoreSystem;
import pulpcore.image.CoreFont;
import pulpcore.Input;
import pulpcore.scene.Scene2D;
import pulpcore.sound.Sound;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Sprite;
import pulpcore.sprite.FilledSprite;

public class LD17 extends Scene2D {
    
    Label label;
    Label theme;
    
    @Override
    public void load() {
        add(new ImageSprite("background.png", 0, 0));

        add(new ImageSprite("craptest.png", 0, 0));
        
        CoreFont font = CoreFont.load("hello.font.png");
        label = new Label(font, "Ludum Dare 17", 320, 240);
        label.setAnchor(0.5, 0.5);
        add(label);

        theme = new Label(font, "ISLANDS", 320, 140);
        theme.setAnchor(0.5, 0.5);
        add(theme);
        
        //// TODO: Not working
        //Sound sound = Sound.load("sound.wav");
        //sound.play();
    }
    
    @Override
    public void update(int elapsedTime) {
        double angle = 0.006 * (Input.getMouseX() - 320);
        int duration = 100;
        label.angle.animateTo(angle, duration);
    }
}
