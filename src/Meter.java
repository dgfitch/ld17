import pulpcore.CoreSystem;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.FilledSprite;
import pulpcore.sprite.Group;
import pulpcore.image.BlendMode;
import static pulpcore.image.Colors.*;

public class Meter extends Group {
    Flashlight flashlight;
    Player player;
    FilledSprite black;
    FilledSprite flashlightMeter;
    FilledSprite healthMeter;
    UIBox box;

    int mh = 182;
    int mw = 5;

    public Meter(Player p, Flashlight f) {
        player = p;
        flashlight = f;
        box = new UIBox(mw+23, mh+18);
        black = new FilledSprite(BLACK);
        black.scaleTo(mw+13,mh+8,0);
        black.moveTo(5,5,0);
        flashlightMeter = new FilledSprite(BLUE);
        flashlightMeter.scaleTo(mw,mh,0);
        flashlightMeter.moveTo(9,9,0);
        healthMeter = new FilledSprite(GREEN);
        healthMeter.scaleTo(mw,mh,0);
        healthMeter.moveTo(9+mw,9,0);
        add(box);
        add(black);
        add(flashlightMeter);
        add(healthMeter);
        moveTo(10,10,0);
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        double power = flashlight.getPower();
        int f = (int)(mh * power / 100);
        int h = (int)(mh * player.getHealth() / player.getMaxHealth());
        flashlightMeter.scaleTo(mw,f,0);
        healthMeter.scaleTo(mw,h,0);
        if (flashlight.isOn()) {
            alpha.set(200);
        } else {
            alpha.set(30);
        }
    }

}

