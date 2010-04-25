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

public class LevelOverScene extends Scene2D {
    
    Player player;
    int time = 0;
    int levelNumber;

    public LevelOverScene(Player p) {
        super();
        player = p;
        levelNumber = player.getLevelNumber();
    }
    
    @Override
    public void load() {
        
        Label title = new Label(CoreFont.load("hello.font.png"), "GET READY", 320, 240);
        title.setAnchor(0.5, 0.5);
        title.scale(title.width.get() / 2, title.height.get() / 2, title.width.get(), title.height.get(), 4000, pulpcore.animation.Easing.ELASTIC_IN);
        title.alpha.animate(0, 255, 10000);
        title.y.animate(240, 180, 10000, pulpcore.animation.Easing.ELASTIC_OUT);

        Label complete = new Label(CoreFont.load("hello.font.png"), "Level " + levelNumber + " complete.", 320, 100);
        complete.setAnchor(0.5, 0.5);
        complete.scaleTo(complete.width.get() / 2, complete.height.get() / 2, 4000);
        complete.alpha.animateTo(150, 10000);

        add(new FilledSprite(BLACK));
        add(title);
        add(complete);
    }
    
    @Override 
    public void update(int elapsedTime) {
        time += elapsedTime;
        if (time > 5000) {
            Stage.replaceScene(new Level(levelNumber + 1, player));
        }
    }
}

