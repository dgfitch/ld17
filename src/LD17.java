import pulpcore.CoreSystem;
import pulpcore.image.CoreFont;
import pulpcore.image.BlendMode;
import pulpcore.Input;
import pulpcore.scene.Scene2D;
import pulpcore.sound.Sound;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Sprite;
import pulpcore.sprite.FilledSprite;
import pulpcore.sprite.Group;
import static pulpcore.image.Colors.*;

public class LD17 extends Scene2D {
    
    Label theme;
    ImageSprite tam;
    ImageSprite cursor;
    Group maskLayer;
    Group itemLayer;
    Group backLayer;
    
    @Override
    public void load() {
        setCursor(Input.CURSOR_OFF);
        cursor = new ImageSprite("light_glow_1.png", 0, 0);
        cursor.setAnchor(0.5, 0.5);
        cursor.visible.set(false);

        maskLayer = new Group();
        maskLayer.add(new FilledSprite(gray(18)));
        maskLayer.add(cursor);
        maskLayer.setBlendMode(BlendMode.Multiply());
        maskLayer.createBackBuffer();

        backLayer = new Group();
        backLayer.add(new ImageSprite("background.png", 0, 0));

        itemLayer = new Group();
        itemLayer.add(new ImageSprite("tile_grass_1.png", 0, 0));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 64, 0));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 0, 64));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 64, 64));

        tam = new ImageSprite("tam.png", 0, 0);
        itemLayer.add(tam);
        
        CoreFont font = CoreFont.load("hello.font.png");
        theme = new Label(font, "HOORAY!!", 320, 50);
        theme.setAnchor(0.5, 0.5);
        itemLayer.add(theme);

        addLayer(backLayer);
        addLayer(itemLayer);
        addLayer(maskLayer);
        
        //// TODO: Not working
        //Sound sound = Sound.load("sound.wav");
        //sound.play();
    }
    
    @Override
    public void update(int elapsedTime) {
        cursor.setLocation(Input.getMouseX(), Input.getMouseY());
        cursor.visible.set(Input.isMouseInside());

        theme.y.animateTo(640 - Input.getMouseY(), 1000);

        tam.x.animateTo(Input.getMouseX() - 320, 300);
        tam.y.animateTo(Input.getMouseY() - 240, 300);
    }
}
