import pulpcore.CoreSystem;
import pulpcore.math.CoreMath;
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
    ImageSprite player;
    ImageSprite cursor;
    ImageSprite cursorCone;
    Group maskLayer;
    Group itemLayer;
    Group backLayer;
    
    double playerSpeed = 2.0;

    @Override
    public void load() {
        setCursor(Input.CURSOR_OFF);
        cursor = new ImageSprite("light_glow_1.png", 0, 0);
        cursor.setAnchor(0.5, 0.5);
        //cursor.visible.set(false);
        cursorCone = new ImageSprite("light_cone_1.png", 0, 0);
        cursorCone.setAnchor(0.5, 1.0);

        maskLayer = new Group();
        maskLayer.add(new FilledSprite(gray(18)));
        maskLayer.add(cursor);
        maskLayer.add(cursorCone);
        maskLayer.setBlendMode(BlendMode.Multiply());
        maskLayer.createBackBuffer();

        backLayer = new Group();
        backLayer.add(new ImageSprite("background.png", 0, 0));

        itemLayer = new Group();
        itemLayer.add(new ImageSprite("tile_grass_1.png", 0, 0));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 64, 0));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 0, 64));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 64, 64));

        player = new ImageSprite("player.png", 0, 0);
        player.setAnchor(0.5, 0.5);
        player.moveTo(320, 240, 0);
        itemLayer.add(player);
        cursorCone.bindLocationTo(player);
        
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

        //cursor.visible.set(Input.isMouseInside());

        theme.y.animateTo(640 - Input.getMouseY(), 1000);

        int vx = 0;
        int vy = 0;
        if (Input.isDown(Input.KEY_LEFT))  vx -= 1;
        if (Input.isDown(Input.KEY_RIGHT)) vx += 1;
        if (Input.isDown(Input.KEY_DOWN))  vy += 1;
        if (Input.isDown(Input.KEY_UP))    vy -= 1;

        double dx = playerSpeed * vx;
        double dy = playerSpeed * vy;
        player.x.set(player.x.get() + dx);
        player.y.set(player.y.get() + dy);

        // calculate angle stuff
        double angle = Math.atan2(cursor.x.get() - player.x.get(), player.y.get() - cursor.y.get());
        player.angle.set(angle);
        cursorCone.angle.set(angle);
        double distx = Math.abs(cursor.x.get() - player.x.get());
        double disty = Math.abs(cursor.y.get() - player.y.get());
        double distance = Math.sqrt(distx * distx + disty * disty);
        cursorCone.scaleTo(cursorCone.width.get(), distance, 0);

        if (CoreMath.randChance(5)) {
          cursorCone.alpha.animateTo(50, 50);
          cursor.alpha.animateTo(50, 50);
        } else {
          cursorCone.alpha.animateTo(255, 100);
          cursor.alpha.animateTo(255, 100);
        }
    }
}
