import pulpcore.CoreSystem;
import pulpcore.math.CoreMath;
import pulpcore.sprite.ImageSprite;

public class Crawler extends Enemy {
    public Crawler(Player p, Level l, int x, int y) {
        super(p, l, "enemy_crawler.png", x, y);
        speed = CoreMath.rand(0.4, 0.8) + (p.getLevelNumber() / 10);
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

