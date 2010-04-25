import pulpcore.CoreSystem;
import pulpcore.Input;
import pulpcore.math.CoreMath;
import pulpcore.image.CoreFont;
import pulpcore.image.BlendMode;
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
    Player player;
    Flashlight flashlight;
    ScoreMeter scoreMeter;
    Group maskLayer;
    Group helilightLayer;
    Group itemLayer;
    Group backLayer;
    Group uiLayer;
    
    @Override
    public void load() {
        setCursor(Input.CURSOR_OFF);

        maskLayer = new Group();
        maskLayer.add(new FilledSprite(gray(18)));
        flashlight = new Flashlight(maskLayer);
        maskLayer.add(flashlight);
        maskLayer.setBlendMode(BlendMode.Multiply());
        maskLayer.createBackBuffer();

        backLayer = new Group();
        backLayer.add(new ImageSprite("background.png", 0, 0));

        itemLayer = new Group();
        itemLayer.add(new ImageSprite("tile_grass_1.png", 0, 0));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 64, 0));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 0, 64));
        itemLayer.add(new ImageSprite("tile_grass_1.png", 64, 64));

        player = new Player(flashlight);
        player.moveTo(320, 240, 0);
        itemLayer.add(player);
        
        CoreFont font = CoreFont.load("hello.font.png");
        theme = new Label(font, "HOORAY!!", 320, 50);
        theme.setAnchor(0.5, 0.5);
        itemLayer.add(theme);

        uiLayer = new Group();
        uiLayer.add(new Meter(player, flashlight));
        scoreMeter = new ScoreMeter(player);
        uiLayer.add(scoreMeter);

        addLayer(backLayer);
        addLayer(itemLayer);
        addLayer(maskLayer);
        addLayer(uiLayer);
        
        //// TODO: Not working
        //Sound sound = Sound.load("sound.wav");
        //sound.play();
    }

    private void addCrawler() {
        // TODO: Make it pop out of the edges only
        Crawler c = new Crawler(player, CoreMath.rand(0, 640), CoreMath.rand(0, 480));
        itemLayer.add(c);
    }
    
    @Override
    public void update(int elapsedTime) {
        theme.y.animateTo(640 - Input.getMouseY(), 1000);
        if (CoreMath.randChance(1)) {
            addCrawler();
        }
    }
}
