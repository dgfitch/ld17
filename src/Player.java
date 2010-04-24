import pulpcore.CoreSystem;
import pulpcore.Input;
import pulpcore.sprite.ImageSprite;

public class Player extends ImageSprite {
    Flashlight flashlight;
    double playerSpeed = 2.0;

    public Player(Flashlight f) {
        super("player.png", 0, 0);
        flashlight = f;
        flashlight.BindPlayer(this);
        setAnchor(0.5, 0.5);
        setSize(32, 32);
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        int vx = 0;
        int vy = 0;
        if (Input.isDown(Input.KEY_LEFT))  vx -= 1;
        if (Input.isDown(Input.KEY_RIGHT)) vx += 1;
        if (Input.isDown(Input.KEY_DOWN))  vy += 1;
        if (Input.isDown(Input.KEY_UP))    vy -= 1;

        double dx = playerSpeed * vx;
        double dy = playerSpeed * vy;
        x.set(x.get() + dx);
        y.set(y.get() + dy);

        // calculate angle stuff
        double a = Math.atan2(flashlight.focusX() - x.get(), y.get() - flashlight.focusY());
        angle.set(a);
    }
}

