import pulpcore.CoreSystem;
import pulpcore.image.CoreFont;
import pulpcore.sprite.Group;
import pulpcore.animation.event.RemoveSpriteEvent;
import pulpcore.animation.Timeline;
import pulpcore.animation.Easing;
import pulpcore.sprite.Label;

public class UIMessage extends Group {
    CoreFont font;

    int x1 = 325;
    int y1 = 400;
    int x2 = 325;
    int y2 = 490;

    public UIMessage() {
        super();
        font = CoreFont.load("uismall.font.png");
    }

    public void addMessage(String s, int duration) {
        Label l = new Label(font, "\"" + s + "\"", 0, 0);
        l.setAnchor(0.5, 0.5);

        Timeline t = new Timeline();
        l.moveTo(x1, y1, 0);
        l.moveTo(x2, y2, 5000);
        l.alpha.animate(255, 100, 5000);

        t.animateTo(l.x, x2, duration, Easing.REGULAR_OUT);
        t.animateTo(l.y, y2, duration, Easing.REGULAR_OUT);
        t.at(duration/2).animateTo(l.alpha, 0, duration - 100, Easing.REGULAR_OUT);
        t.add(new RemoveSpriteEvent(this, l, duration));
        add(l);
    }
}
