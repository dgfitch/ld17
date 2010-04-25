import pulpcore.CoreSystem;
import pulpcore.Input;
import pulpcore.sprite.ImageSprite;
import pulpcore.Stage;

public class Player extends ImageSprite {
    Flashlight flashlight;
    double speed = 2.0;
    int time = 0;
    int score = 0;
    int levelNumber;
    double maxHealth = 100.0;
    double health = 100.0;

    public Player() {
        super("player.png", 0, 0);
        setAnchor(0.5, 0.5);
        setSize(32, 32);
    }

    public void setFlashlight(Flashlight f) {
        flashlight = f;
        flashlight.BindPlayer(this);
    }

    public Flashlight getFlashlight() { return flashlight; }
    public int getScore() { return score; }
    public int getTime() { return time; }
    public double getMaxHealth() { return maxHealth; }
    public double getHealth() { return health; }
    public int getLevelNumber() { return levelNumber; }
    public void setLevelNumber(int x) { levelNumber = x; }
    public void resetTime() { time = 0; }

    public int addScore(int x) {
        score += x;
        return score;
    }

    public int addTime(int x) {
        time += x;
        return time;
    }


    public void doDamage(double multiplier, int elapsedTime) {
        health -= multiplier * elapsedTime;
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        addTime(elapsedTime);

        if (health <= 0.0) {
            // GAME OVER MAN
            Stage.setScene(new GameOverScene(this));
        }

        int vx = 0;
        int vy = 0;
        if (Input.isDown(Input.KEY_UP)    || Input.isDown(Input.KEY_W)) vy -= 1;
        if (Input.isDown(Input.KEY_LEFT)  || Input.isDown(Input.KEY_A)) vx -= 1;
        if (Input.isDown(Input.KEY_DOWN)  || Input.isDown(Input.KEY_S)) vy += 1;
        if (Input.isDown(Input.KEY_RIGHT) || Input.isDown(Input.KEY_D)) vx += 1;

        double dx = speed * vx;
        double dy = speed * vy;
        x.set(x.get() + dx);
        y.set(y.get() + dy);

        // calculate angle stuff
        double a = Math.atan2(flashlight.focusX() - x.get(), y.get() - flashlight.focusY());
        angle.set(a);
    }
}

