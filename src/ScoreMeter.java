import pulpcore.CoreSystem;
import pulpcore.image.CoreFont;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;

public class ScoreMeter extends Group {
    int score = 0;
    int time = 0;
    Label scoreLabel;
    Label timeLabel;

    public ScoreMeter() {
        super();
        CoreFont font = CoreFont.load("uismall.font.png");
        scoreLabel = new Label(font, "<score>", 50, 10);
        timeLabel  = new Label(font, "<time>", 50, 30);
        add(scoreLabel);
        add(timeLabel);
    }

    public int addScore(int x) {
        score += x;
        return score;
    }

    public int addTime(int x) {
        time += x;
        return time;
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);
        addTime(elapsedTime);

        scoreLabel.setText("Score: " + Integer.toString(score));
        timeLabel.setText("Time: " + Integer.toString(time / 1000) + "s");
    }
}
