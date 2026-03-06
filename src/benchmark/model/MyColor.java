package benchmark.model;

import java.util.Objects;

public class MyColor implements Comparable<MyColor> {
    private final int r;
    private final int g;
    private final int b;
    private final int sum;

    public MyColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.sum = r + g + b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public int compareTo(MyColor other) {
        return Integer.compare(this.sum, other.sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyColor)) {
            return false;
        }
        MyColor myColor = (MyColor) o;
        return r == myColor.r && g == myColor.g && b == myColor.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }

    @Override
    public String toString() {
        return "MyColor(r=" + r + ", g=" + g + ", b=" + b + ", sum=" + sum + ")";
    }
}
