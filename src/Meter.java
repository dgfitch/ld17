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

    int mh = 460;
    int mw = 5;
    int mx = 5;
    int my = 5;

    public Meter(Player p, Flashlight f) {
        player = p;
        flashlight = f;
        black = new FilledSprite(BLACK);
        black.scaleTo(mw*2+4,mh+4,0);
        black.moveTo(mx-2,my-2,0);
        flashlightMeter = new FilledSprite(WHITE);
        flashlightMeter.scaleTo(mw,mh,0);
        flashlightMeter.moveTo(mx+mw,my,0);
        healthMeter = new FilledSprite(GREEN);
        healthMeter.scaleTo(mw,mh,0);
        healthMeter.moveTo(mx,my,0);
        add(black);
        add(flashlightMeter);
        add(healthMeter);
        moveTo(mx,my,0);
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
            alpha.set(150);
        }
    }

}

