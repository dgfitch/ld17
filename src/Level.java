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
import pulpcore.Stage;
import static pulpcore.image.Colors.*;
import java.util.ArrayList;

public class Level extends Scene2D {
    
    int levelNumber;
    int levelDuration = 60000;
    Player player;
    Flashlight flashlight;
    ScoreMeter scoreMeter;
    UIMessage messager;
    Group maskLayer;
    Group flareLayer;
    Group helilightLayer;
    Group itemLayer;
    Group backLayer;
    Group uiLayer;
    ArrayList<String> messages;

    int messageDelay = 500;

    public Level(int n, Player p) {
        super();
        player = p;
        player.setLevelNumber(n);
        levelNumber = n;
        messages = new ArrayList<String>();
        if (levelNumber == 1) {
            messages.add("Oh my god, a light! There's someone still down there!");
            messages.add("Hey you, down there! Run with WASD or the arrow keys...");
            messages.add("Click the left button to turn your flashlight on and off.");
            messages.add("Stay in the light to survive, kid, they don't like the light!");
            messages.add("Shine your light at the doubts to stun them, dude!");
            messages.add("Flash your light at a spot twice, and I'll drop a flare there.");
            messages.add("You can shake your flashlight around to charge it faster");
        } else if (levelNumber == 2) {
            messages.add("Get a move on, kid!");
            messages.add("Damn! Something hit us, and took out the spotlight!");
            messages.add("Looks like it's going to be just your guts and our flares...");
            messages.add("Don't forget, you can shake your light to charge it up faster.");
        } else if (levelNumber == 3) {
            messages.add("I still can't land with all of those things down there.");
            messages.add("Get to somewhere safe and we'll pick you up.");
            messages.add("Remember, flash your light at a spot two times and I'll try to toss a flare close.");
        } else if (levelNumber == 4) {
            messages.add("Hop to it... they're coming!");
            messages.add("Damn it... I think they're getting used to the flashlight.");
        } else if (levelNumber == 5) {
            messages.add("Man, do I wish I had a ladder to drop down to you, kid...");
            messages.add("This is too brutal.");
        }
    }
    
    @Override
    public void load() {
        setCursor(Input.CURSOR_OFF);

        maskLayer = new Group();
        maskLayer.add(new FilledSprite(gray(18)));
        flashlight = new Flashlight(maskLayer);
        player.setFlashlight(flashlight);
        maskLayer.add(flashlight);
        maskLayer.setBlendMode(BlendMode.Multiply());
        maskLayer.createBackBuffer();

        flareLayer = new Group();
        flareLayer.setBlendMode(BlendMode.Add());
        flareLayer.createBackBuffer();

        backLayer = new Group();
        backLayer.add(new ImageSprite("background.png", 0, 0));

        player.moveTo(320, 240, 0);
        itemLayer = new Group();
        itemLayer.add(player);
        
        uiLayer = new Group();
        uiLayer.add(new Meter(player, flashlight));
        scoreMeter = new ScoreMeter(player);
        uiLayer.add(scoreMeter);
        messager = new UIMessage();
        uiLayer.add(messager);

        addLayer(backLayer);
        addLayer(itemLayer);
        addLayer(maskLayer);
        addLayer(flareLayer);
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
        if (!messages.isEmpty()) {
            messageDelay -= elapsedTime;
            if (messageDelay <= 0) {
                messager.addMessage(messages.get(0), CoreMath.rand(5000, 7000));
                messages.remove(0);
                messageDelay = CoreMath.rand(4000, 6000);
            }
        }

        int time = player.getTime();
        boolean canSpawn = time > 20000 || levelNumber > 1;
        boolean shouldSpawn = canSpawn && time < levelDuration - 10000;
        if (shouldSpawn) {
            if (CoreMath.randChance(1)) {
                addCrawler();
            }
        }

        if (time > levelDuration) {
            player.resetTime();
            Stage.replaceScene(new LevelOverScene(player));
        }

        if (Input.isDown(Input.KEY_F)) {
            Flare f = new Flare(this, 320, 240);
            flareLayer.add(f);
        }
    }
}
