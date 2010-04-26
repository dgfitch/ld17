import pulpcore.animation.event.SceneChangeEvent;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Sprite;
import pulpcore.Stage;
import pulpcore.sound.Sound;
import pulpcore.sound.Playback;

public class TitleScene extends Scene2D {
    
    Button playButton;
    Button optionsButton;
    Group componentLayer;
    Sound noise;
    Playback music;
    
    @Override
    public void load() {
        noise = Sound.load("menu.ogg");
        music = noise.loop();
        
        Label title = new Label(CoreFont.load("hello.font.png"), "Crowned in Doubt", 320, 170);
        title.setAnchor(0.5, 0.5);
        playButton = Button.createLabeledButton("Play", 320, 320);
        playButton.setAnchor(0.5, 0.5);
        optionsButton = Button.createLabeledButton("Options", 320, 370);
        optionsButton.setAnchor(0.5, 0.5);
        
        componentLayer = new Group();
        componentLayer.add(playButton);
        componentLayer.add(optionsButton);
        
        add(new ImageSprite("title.png", 0, 0));
        add(title);
        addLayer(componentLayer);
    }
    
    @Override 
    public void update(int elapsedTime) {
        if (optionsButton.isClicked()) {
            // Pushes the scene onto the stack (doesn't unload this Scene)
            Stage.pushScene(new OptionScene());
        }
        else if (playButton.isClicked()) {
            Stage.setScene(new Level(1, new Player()));
        }
    }

    @Override 
    public void unload() {
        music.stop();
    }
}
