package benchmark.model;

import java.time.LocalDateTime;
import java.util.List;

public class BenchmarkReport {
    private final BenchmarkConfig config;
    private final List<TestResult> results;
    private final LocalDateTime generatedAt;

    public BenchmarkReport(BenchmarkConfig config, List<TestResult> results) {
        this.config = config;
        this.results = results;
        this.generatedAt = LocalDateTime.now();
    }

    public BenchmarkConfig getConfig() {
        return config;
    }

    public List<TestResult> getResults() {
        return results;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
}
