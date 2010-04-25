import pulpcore.CoreSystem;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.FilledSprite;
import pulpcore.sprite.Group;
import pulpcore.image.BlendMode;
import static pulpcore.image.Colors.*;

public class FlashlightMeter extends Group {
    Flashlight flashlight;
    FilledSprite black;
    FilledSprite meter;
    UIBox box;

    int mh = 182;
    int mw = 10;

    public FlashlightMeter(Flashlight f) {
        flashlight = f;
        box = new UIBox(mw+18, mh+18);
        black = new FilledSprite(BLACK);
        black.scaleTo(mw+8,mh+8,0);
        black.moveTo(5,5,0);
        meter = new FilledSprite(RED);
        meter.scaleTo(mw,mh,0);
        meter.moveTo(9,9,0);
        add(box);
        add(black);
        add(meter);
        moveTo(10,10,0);
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        int meterH;
        double power = flashlight.getPower();
        if (power <= 0.0) {
            meterH = 0;
        } else {
            meterH = (int)(mh * power / 100);
        }
        meter.scaleTo(mw,meterH,0);
        if (flashlight.isOn()) {
            alpha.set(200);
        } else {
            alpha.set(30);
        }
    }

}

