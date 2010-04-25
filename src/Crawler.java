import pulpcore.CoreSystem;
import pulpcore.math.CoreMath;
import pulpcore.sprite.ImageSprite;

public class Crawler extends Enemy {
    public Crawler(Player p, int x, int y) {
        super(p, "enemy_crawler.png", x, y);
        speed = CoreMath.rand(0.2, 0.6);
        setSize(32, 32);
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        facePlayer();
        approachPlayer();
    }
}

