import pulpcore.CoreSystem;
import pulpcore.math.CoreMath;
import pulpcore.sprite.ImageSprite;

public class Crawler extends Enemy {
    public Crawler(Player p, int x, int y) {
        super(p, "enemy_crawler.png", x, y);
        speed = CoreMath.rand(0.3, 0.8);
        health = p.getLevelNumber() + CoreMath.rand(3.0, 7.0);
        points = (int)health;
        int size = CoreMath.rand(20, 40);
        setSize(size, size);
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        if (stunned <= 0.0) {
            facePlayer();
            approachPlayer();
        }
    }
}

