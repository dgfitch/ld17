import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Sprite;
import pulpcore.math.CoreMath;
import pulpcore.animation.Easing;
import pulpcore.animation.event.RemoveSpriteEvent;
import pulpcore.animation.Timeline;
import pulpcore.image.CoreImage;
import pulpcore.image.AnimatedImage;
import pulpcore.scene.Scene2D;
import static pulpcore.image.Colors.*;

public class Flare extends Group {
    int time = 0;
    int timeLit = 600;
    int duration = 6000;
    boolean fading = false;
    boolean lit = false;
    AnimatedImage glowA;
    ImageSprite glow;
    CoreImage[] glows;
    CoreImage[] sparks;
    Scene2D scene;

    public Flare(Scene2D sc, int sx, int sy) {
        super();
        scene = sc;
        glowA = new AnimatedImage(CoreImage.load("flare_glow_test.png"), 4, 1);
        glowA.setFrameDuration(50, true);
        glowA.setHotspot(32,32);
        glowA.start();
        glow = new ImageSprite(glowA, 0, 0);
        glow.alpha.set(0);
        glow.width.set(200);
        glow.height.set(200);
        glows = CoreImage.load("spark_glow.png").split(4, 1);
        sparks = CoreImage.load("spark.png").split(4, 1);
        x.set(sx);
        y.set(sy);
        add(glow);
    }

    public void addSpark() {
        CoreImage image = sparks[CoreMath.rand(sparks.length - 1)];
        addParticle(new ImageSprite(image, 0, 0), 3, CoreMath.rand(10, 20));
    }

    public void addGlowSpark() {
        CoreImage image = glows[CoreMath.rand(glows.length - 1)];
        addParticle(new ImageSprite(image, 0, 0), 2, CoreMath.rand(20, 48));
    }

    public void addParticle(ImageSprite s, int speed, int size) {
        Timeline t = new Timeline();

        int duration = (200 - size) * 10 / speed;
        int moveDistance = CoreMath.rand(4, 60 - size) + (speed * 10);
        double moveDirection = CoreMath.rand(0, 2*Math.PI);
        
        int startX = CoreMath.rand(-2, 2);
        int startY = CoreMath.rand(-2, 2);
        int goalX = startX + (int)(moveDistance * Math.cos(moveDirection));
        int goalY = startY + (int)(moveDistance * Math.sin(moveDirection));
        double startAngle = CoreMath.rand(0, 2*Math.PI);
        double endAngle = startAngle + CoreMath.rand(-2*Math.PI, 2*Math.PI);
        
        s.setAnchor(0.5, 0.5);
        s.setSize(size, size);
        add(s);
        
        t.animateTo(s.x, goalX, duration, Easing.REGULAR_OUT);
        t.animateTo(s.y, goalY, duration, Easing.REGULAR_OUT);
        t.animate(s.angle, startAngle, endAngle, duration);
        t.scaleTo(s, size / 3, size / 3, duration);
        t.at(100).animateTo(s.alpha, 0, duration - 100, Easing.REGULAR_OUT);
        t.add(new RemoveSpriteEvent(this, s, duration));

        scene.addTimeline(t);
    }

    @Override
    public void update(int elapsedTime) {
        time += elapsedTime;

        if (time > timeLit && !lit) {
            lit = true;
            glow.scaleTo(250, 250, 100);
        }
        if (time > duration - timeLit && !fading) {
            fading = true;
            glow.scaleTo(10, 10, 100);
        }

        if (fading) {
            glow.alpha.set(CoreMath.rand(0, 2));
        } else if (lit) {
            glow.alpha.set(CoreMath.rand(40, 60));
        } else {
            glow.alpha.set(CoreMath.rand(0, 4));
        }

        if (time < duration - timeLit) {
            double r = CoreMath.rand(0.0,1.0);
            if (r > 0.98)
                addSpark();
            if (time > timeLit && r > 0.96)
                addGlowSpark();
        }

        if (time > duration + timeLit)
            removeFromParent();
    }
}

