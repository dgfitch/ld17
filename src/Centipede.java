import pulpcore.CoreSystem;
import pulpcore.math.CoreMath;
import pulpcore.sprite.ImageSprite;

public class Centipede extends Enemy {
    public Centipede(Player p, Level l, int x, int y) {
        super(p, l, "enemy_centipede.png", x, y);
        speed = CoreMath.rand(0.8, 1.0) + (p.getLevelNumber() / 10);
        health = p.getLevelNumber() + CoreMath.rand(2.0, 5.0);
        points = (int)health;
        int size = CoreMath.rand(20, 30);
        setSize(size, size);
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        if (stunned <= 0.0) {
            // TODO: Different logic
            facePlayer();
            approachPlayer();
        }
    }
}


