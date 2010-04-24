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
    
    Label theme;
    ImageSprite tam;
    
    @Override
    public void load() {
        add(new ImageSprite("background.png", 0, 0));

        add(new ImageSprite("tile_grass_1.png", 0, 0));
        add(new ImageSprite("tile_grass_1.png", 64, 0));
        add(new ImageSprite("tile_grass_1.png", 0, 64));
        add(new ImageSprite("tile_grass_1.png", 64, 64));

        tam = new ImageSprite("tam.png", 0, 0);
        add(tam);
        
        CoreFont font = CoreFont.load("hello.font.png");
        theme = new Label(font, "HOORAY!!", 320, 50);
        theme.setAnchor(0.5, 0.5);
        add(theme);
        
        //// TODO: Not working
        //Sound sound = Sound.load("sound.wav");
        //sound.play();
    }
    
    @Override
    public void update(int elapsedTime) {
        theme.y.animateTo(640 - Input.getMouseY(), 1000);

        tam.x.animateTo(Input.getMouseX() - 320, 300);
        tam.y.animateTo(Input.getMouseY() - 240, 300);
    }
}
