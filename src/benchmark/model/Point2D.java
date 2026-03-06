package benchmark.model;

import java.util.Objects;

public class Point2D implements Comparable<Point2D> {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double length;

    public Point2D(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.length = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getLength() {
        return length;
    }

    @Override
    public int compareTo(Point2D other) {
        return Double.compare(this.length, other.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point2D)) {
            return false;
        }
        Point2D point2D = (Point2D) o;
        return Double.compare(x1, point2D.x1) == 0
                && Double.compare(y1, point2D.y1) == 0
                && Double.compare(x2, point2D.x2) == 0
                && Double.compare(y2, point2D.y2) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, y1, x2, y2);
    }

    @Override
    public String toString() {
        return "Point2D[(" + x1 + ", " + y1 + ") -> (" + x2 + ", " + y2 + "), length=" + length + "]";
    }
}
