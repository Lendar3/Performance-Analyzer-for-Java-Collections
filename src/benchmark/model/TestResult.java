package benchmark.model;

import java.util.Locale;

public class TestResult {
    private final TestType testType;
    private final long averageNanos;
    private final boolean applicable;

    private TestResult(TestType testType, long averageNanos, boolean applicable) {
        this.testType = testType;
        this.averageNanos = averageNanos;
        this.applicable = applicable;
    }

    public static TestResult success(TestType testType, long averageNanos) {
        return new TestResult(testType, averageNanos, true);
    }

    public static TestResult notApplicable(TestType testType) {
        return new TestResult(testType, -1L, false);
    }

    public TestType getTestType() {
        return testType;
    }

    public long getAverageNanos() {
        return averageNanos;
    }

    public boolean isApplicable() {
        return applicable;
    }

    public String toHumanString() {
        if (!applicable) {
            return testType.getLabel() + ": N/A";
        }
        return String.format(Locale.US, "%s: %.4f ms", testType.getLabel(), averageNanos / 1_000_000.0);
    }
}
