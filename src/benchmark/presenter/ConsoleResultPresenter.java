package benchmark.presenter;

import benchmark.model.BenchmarkConfig;
import benchmark.model.BenchmarkReport;
import benchmark.model.TestResult;

import java.time.format.DateTimeFormatter;

public class ConsoleResultPresenter implements ResultPresenter {
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void present(BenchmarkReport report) {
        BenchmarkConfig config = report.getConfig();

        System.out.println();
        System.out.println("=== Collection Benchmark Results ===");
        System.out.println("Generated at: " + report.getGeneratedAt().format(TIME_FORMAT));
        System.out.println("Data type: " + config.getDataType().getLabel());
        System.out.println("Collection: " + config.getCollectionType().getLabel());
        System.out.println("Elements: " + config.getElementCount());
        System.out.println("Iterations per test: " + config.getIterations());
        System.out.println("------------------------------------");
        for (TestResult result : report.getResults()) {
            System.out.println(result.toHumanString());
        }
        System.out.println("====================================");
        System.out.println();
    }
}
