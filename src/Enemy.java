import pulpcore.CoreSystem;
import pulpcore.math.CoreMath;
import pulpcore.sprite.ImageSprite;

public class Enemy extends ImageSprite {
    double speed;
    Player player;
    double health = 10.0;
    double stunned = 0.0;
    double stunRecoverySpeed = 0.02;
    double damage = 0.05;
    int points = 1;

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

    public void doStun() {
        stunned = 1.0;
        x.animateTo(x.get() + CoreMath.rand(-10, 10), 1000);
        y.animateTo(y.get() + CoreMath.rand(-10, 10), 1000);
        // TODO: spazz out the angle
    }

    public void doDamage(double multiplier, int elapsedTime) {
        health -= multiplier * elapsedTime;
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        // check if we should be stunned and/or damaged
        boolean touchingFlashlight = player.getFlashlight().isTouching(this);

        if (stunned > 0.0) {
            stunned -= elapsedTime * stunRecoverySpeed;
            if (touchingFlashlight) doDamage(player.getFlashlight().getDamage(), elapsedTime);
        } else {
            if (touchingFlashlight) {
                doStun();
            } else if (this.intersects(player)) {
                player.doDamage(damage, elapsedTime);
            }
        }

        if (health <= 0.0) {
            player.addScore(points);
            removeFromParent();
            // TODO: Other garbage collection?
            // TODO: blood spatter or some cool death effect?
        }
    }
}

