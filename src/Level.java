import pulpcore.CoreSystem;
import pulpcore.Build;
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
    Group helilightLayer;
    Group itemLayer;
    Group flaresLayer;
    Group backLayer;
    Group uiLayer;
    ArrayList<String> messages;

    ArrayList<Flare> flares;
    int flareDelay = 9000;
    int flareCount = 0;
    int flareAllowed = 4000;
    double flareDamage = 0.01;

    int messageDelay = 500;

    public Level(int n, Player p) {
        super();
        player = p;
        player.setLevelNumber(n);
        levelNumber = n;
        flares = new ArrayList<Flare>();
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
        flashlight = new Flashlight(this);
        player.setFlashlight(flashlight);
        maskLayer.add(flashlight);
        maskLayer.setBlendMode(BlendMode.Multiply());
        maskLayer.createBackBuffer();

        backLayer = new Group();
        backLayer.add(new ImageSprite("background.png", 0, 0));

        player.moveTo(320, 240, 0);
        itemLayer = new Group();
        itemLayer.add(player);

        flaresLayer = new Group();
        flaresLayer.setBlendMode(BlendMode.Add());
        flaresLayer.createBackBuffer();
        
        uiLayer = new Group();
        uiLayer.add(new Meter(player, flashlight));
        scoreMeter = new ScoreMeter(player);
        uiLayer.add(scoreMeter);
        messager = new UIMessage();
        uiLayer.add(messager);

        addLayer(backLayer);
        addLayer(itemLayer);
        addLayer(maskLayer);
        addLayer(flaresLayer);
        addLayer(uiLayer);
        
        //// TODO: Not working
        //Sound sound = Sound.load("sound.wav");
        //sound.play();
    }

    public Group getMaskLayer() { return maskLayer; }
    public Group getItemLayer() { return itemLayer; }

    private void addCrawler() {
        addEnemy(0);
    }

    private void addCentipede() {
        addEnemy(1);
    }

    private void addEnemy(int kind) {
        // Make it pop out of the edges only
        int cx = 0;
        int cy = 0;
        switch (CoreMath.rand(0,3)) {
            case 0: cx = CoreMath.rand(-20, 0);
                    cy = CoreMath.rand(0, 480);
                    break;
            case 1: cx = CoreMath.rand(640, 660);
                    cy = CoreMath.rand(0, 480);
                    break;
            case 2: cx = CoreMath.rand(0, 640);
                    cy = CoreMath.rand(-20, 0);
                    break;
            case 3: cx = CoreMath.rand(0, 640);
                    cy = CoreMath.rand(480, 500);
                    break;
        }

        // Switching on int = LOL [in a hurry]
        switch (kind) {
        case 0: 
            Crawler c = new Crawler(player, this, cx, cy);
            itemLayer.add(c);
            break;
        case 1:
            Centipede p = new Centipede(player, this, cx, cy);
            itemLayer.add(p);
            break;
        }
    }

    public void addFlare(int x, int y) {
        // Timer is to stop player from spamming
        if (flareCount < flareAllowed) {
            debug("level is launching flare to " + x + ", " + y);
            Sound sound = Sound.load("check.ogg");
            sound.play();
            Flare f = new Flare(this, x, y);
            flares.add(f);
            flaresLayer.add(f);
            flareCount += flareDelay;
        } else {
            switch (CoreMath.rand(0,9)) {
                case 0: messager.addMessage("I can't keep up with you, kid!",3000);
                        break;
                case 1: messager.addMessage("You're giving epilepsy up here...",2000);
                        break;
                case 2: messager.addMessage("Think I'm getting epilepsy up here...",3000);
                        break;
                case 3: messager.addMessage("We've got to conserve flares, not burn them all!",3500);
                        break;
                case 4: messager.addMessage("You're a pyro, gimme a moment here to light another one.", 4000);
                        break;
                case 5: messager.addMessage("Hey, it's not like I've got a freakin' flare machine gun up here!", 4000);
                        break;
                case 6: messager.addMessage("Okay, Blinky McBlinkerson, I'm working on it.", 3500);
                        break;
                case 7: messager.addMessage("Cripes, hang on, I'm not a machine...", 2000);
                        break;
                case 8: messager.addMessage("Sec, kid!", 1000);
                        break;
                case 9: messager.addMessage("Hang on!", 1000);
                        break;
                default: messager.addMessage("Sheesh, I'm on it!", 2000);
            }
        }
    }

    public boolean isFlareTouching(ImageSprite x) {
        for (Flare f : flares) {
            if (f.isLit() && f.getGlow().intersects(x))
                return true;
        }
        return false;
    }

    public double getFlareDamage() { return flareDamage; }

    public void removeFlare(Flare f) {
        if (flares.contains(f)) {
            flares.remove(flares.indexOf(f));
        }
    }

    public void debug(String s) {
        if (Build.DEBUG) CoreSystem.print(s);
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
            if (CoreMath.randChance(1)) {
                addCentipede();
            }
        }

        flareCount -= elapsedTime;

        if (time > levelDuration) {
            player.resetTime();
            Stage.replaceScene(new LevelOverScene(player));
        }

        if (Build.DEBUG && Input.isDown(Input.KEY_C)) addCrawler();
        if (Build.DEBUG && Input.isDown(Input.KEY_V)) addCentipede();

        // random encouragement from the heli
        if (messages.size() == 0 && CoreMath.rand(0.0, 1.0) > 0.98) {
            if (levelDuration - time < 5000) {
                switch (CoreMath.rand(0,6)) {
                    case 0: messages.add("Nice work!");
                            break;
                    case 1: messages.add("Most excellent.");
                            break;
                    case 2: messages.add("Charge that light, here comes another swarm!");
                            break;
                    case 3: messages.add("Sorry kid, but there's even more coming over that hill.");
                            break;
                    case 4: messages.add("Phew! Nice.");
                            break;
                    case 5: messages.add("I'm sweatin' for you up here, kiddo.");
                            break;
                    case 6: messages.add("Man oh man.");
                            break;
                    default: break;
                }
            } else {
                switch (CoreMath.rand(0,6)) {
                    case 0: messages.add("Go kid, go! You're doin' it!");
                            break;
                    case 1: messages.add("Watch out!");
                            break;
                    case 2: messages.add("Excellent...");
                            break;
                    case 3: messages.add("Don't give up.");
                            break;
                    case 4: messages.add("What are those things doing?");
                            break;
                    case 5: messages.add("Yikes.");
                            break;
                    case 6: messages.add("Crud, it's getting windy up here.");
                            break;
                    default: break;
                }
            }
        }
    }
}
