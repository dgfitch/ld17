import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Sprite;
import pulpcore.math.CoreMath;
import pulpcore.Input;

public class Flashlight extends Group {
    ImageSprite cursor;
    ImageSprite cursorCone;
    Group mask;
    Player player;
    boolean on = true;
    double power = 100.0;
    double flicker = 0.0;
    double switched = 0.0;
    double damage = 0.005;
    double lastX = 320.0;
    double lastY = 240.0;
    double recharge = 0.02;
    double shakeRecharge = 0.0005;

    public Flashlight(Group maskLayer) {
        super();
        mask = maskLayer;

        cursor = new ImageSprite("light_glow_1.png", 0, 0);
        cursor.setAnchor(0.5, 0.5);
        add(cursor);
        cursorCone = new ImageSprite("light_cone_1.png", 0, 0);
        cursorCone.setAnchor(0.5, 1.0);
        add(cursorCone);
    }

    public void BindPlayer(Player p) {
        player = p;
        cursorCone.bindLocationTo(p);
    }

    public double focusX() { return cursor.x.get(); }
    public double focusY() { return cursor.y.get(); }
    public boolean isOn() { return on; }
    public double getPower() { return power; }
    public double getDamage() { return damage; }

    public boolean isTouching(Sprite other) {
        return (on && cursor.intersects(other));
        // TODO: also check light cone??
    }


    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        if (on) {
            if (Input.isMousePressed() || power <= 0.0) {
                on = false;
                cursor.visible.set(false);
                cursorCone.visible.set(false);
            }
        } else {
            if (Input.isMousePressed()) {
                on = true;
                cursor.visible.set(true);
                cursorCone.visible.set(true);
            }
        }

        cursor.setLocation(Input.getMouseX(), Input.getMouseY());
        double cx = cursor.x.get();
        double cy = cursor.y.get();

        // charge flashlight if the mouse is moving a lot
        double mouseMoved = Utils.dist(cx, cy, lastX, lastY);
        power += elapsedTime * mouseMoved * shakeRecharge;

        if (!on) {
            if (power < 100.0) 
                power += elapsedTime * recharge;
        } else { // is on
            if (power > 0.0) 
                power -= elapsedTime * 0.01;

            // calculate distance from player
            double distance = Utils.dist(cx, cy, player.x.get(), player.y.get());
            double beamWidth = 64.0 + distance / 3.0;
            double lensDilation = beamWidth * (1.0 + distance / 500.0);
            int lensIntensity = CoreMath.clamp((int)(500.0 - distance), 50, 255);
            cursorCone.scaleTo(beamWidth, distance, 0);
            cursor.scaleTo(beamWidth, lensDilation, 0);

            double a = player.angle.get();
            cursorCone.angle.set(a);
            cursor.angle.set(a);
            cursorCone.visible.set(distance > 20.0);

            if (flicker == 0.0) {
                if (CoreMath.randChance(CoreMath.clamp(50 / ((int)power + 1), 0, 8))) {
                    flicker = 1.0;
                    alpha.animateTo(lensIntensity / CoreMath.rand(2, 5), 50);
                } else {
                    alpha.animateTo(lensIntensity, CoreMath.rand(10, 50));
                }
            } else if (flicker < 0.0) {
                flicker = 0.0;
                alpha.animateTo(lensIntensity, CoreMath.rand(100, 500));
            } else {
                flicker -= (elapsedTime * 0.2);
            }
        }

        if (power > 100.0) power = 100.0;
        lastX = cx;
        lastY = cy;
    }
}
