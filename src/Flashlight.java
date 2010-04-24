import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.math.CoreMath;
import pulpcore.Input;

public class Flashlight extends Group {
    ImageSprite cursor;
    ImageSprite cursorCone;
    Group mask;
    Player player;
    boolean on = true;
    double power = 100.0;

    public Flashlight(Group maskLayer) {
        super();
        mask = maskLayer;

        cursor = new ImageSprite("light_glow_1.png", 0, 0);
        cursor.setAnchor(0.5, 0.5);
        add(cursor);
        // TODO: reduce size somewhat
        // TODO: reduce intensity with distance
        //cursor.setSize();
        cursorCone = new ImageSprite("light_cone_1.png", 0, 0);
        cursorCone.setAnchor(0.5, 1.0);
        add(cursorCone);
    }

    public void BindPlayer(Player p) {
        player = p;
        cursorCone.bindLocationTo(p);
    }

    public double focusX() {
        return cursor.x.get();
    }

    public double focusY() {
        return cursor.y.get();
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        cursor.setLocation(Input.getMouseX(), Input.getMouseY());
        cursorCone.angle.set(player.angle.get());
        //cursor.visible.set(Input.isMouseInside());

        // calculate distance
        double distx = Math.abs(cursor.x.get() - player.x.get());
        double disty = Math.abs(cursor.y.get() - player.y.get());
        double distance = Math.sqrt(distx * distx + disty * disty);
        cursorCone.scaleTo(cursorCone.width.get(), distance, 0);

        if (Input.isMousePressed()) {
            on = !on;
        }

        if (CoreMath.randChance(5)) {
          alpha.animateTo(CoreMath.rand(100), 50);
        } else {
          alpha.animateTo(255, CoreMath.rand(50, 200));
        }
    }
}
