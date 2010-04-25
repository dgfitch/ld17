import pulpcore.CoreSystem;
import pulpcore.math.CoreMath;
import pulpcore.sprite.ImageSprite;

public class Enemy extends ImageSprite {
    double speed;
    Player player;

    public Enemy(Player p, String image, int x, int y) {
        super(image, x, y);
        player = p;
        setAnchor(0.5, 0.5);
    }

    public void facePlayer() {
        angle.set(Math.atan2(player.x.get() - x.get(), y.get() - player.y.get()));
    }

    public void approachPlayer() {
        double dx = speed * Math.sin(angle.get());
        double dy = speed * Math.cos(angle.get());
        x.set(x.get() + dx);
        y.set(y.get() - dy);
    }
}

