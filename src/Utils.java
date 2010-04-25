import pulpcore.math.CoreMath;

public class Utils {
    static double dist(double x1, double y1, double x2, double y2) {
        double distx = Math.abs(x1 - x2);
        double disty = Math.abs(y1 - y2);
        return Math.sqrt(distx * distx + disty * disty);
    }
}
