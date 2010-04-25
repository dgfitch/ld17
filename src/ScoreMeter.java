import pulpcore.CoreSystem;
import pulpcore.image.CoreFont;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;

public class ScoreMeter extends Group {
    Player player;
    Label levelLabel;
    Label scoreLabel;
    Label timeLabel;

    public ScoreMeter(Player p) {
        super();
        player = p;
        CoreFont font = CoreFont.load("uismall.font.png");
        levelLabel = new Label(font, "Level: " + p.getLevelNumber(), 30, 10);
        scoreLabel = new Label(font, "<score>", 30, 30);
        timeLabel  = new Label(font, "<time>", 30, 50);
        add(levelLabel);
        add(scoreLabel);
        add(timeLabel);
    }

    @Override
    public void update(int elapsedTime) {
        super.update(elapsedTime);

        scoreLabel.setText("Score: " + Integer.toString(player.getScore()));
        timeLabel.setText("Time: " + Integer.toString(player.getTime() / 1000) + "s");
    }
}
