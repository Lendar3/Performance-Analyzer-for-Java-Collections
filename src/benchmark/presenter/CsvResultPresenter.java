package benchmark.presenter;

import benchmark.model.BenchmarkConfig;
import benchmark.model.BenchmarkReport;
import benchmark.model.TestResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class CsvResultPresenter implements ResultPresenter {
    private static final DateTimeFormatter FILE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    @Override
    public void present(BenchmarkReport report) {
        String fileName = "collection-benchmark-" + report.getGeneratedAt().format(FILE_TIME_FORMAT) + ".csv";
        Path path = Path.of(fileName);

        BenchmarkConfig config = report.getConfig();
        StringBuilder csv = new StringBuilder();
        csv.append("generated_at,data_type,collection,elements,iterations,test_type,applicable,average_ms")
                .append(System.lineSeparator());

        for (TestResult result : report.getResults()) {
            csv.append(report.getGeneratedAt()).append(',')
                    .append(config.getDataType().getLabel()).append(',')
                    .append(config.getCollectionType().getLabel()).append(',')
                    .append(config.getElementCount()).append(',')
                    .append(config.getIterations()).append(',')
                    .append(result.getTestType().getLabel()).append(',')
                    .append(result.isApplicable()).append(',');

            if (result.isApplicable()) {
                csv.append(result.getAverageNanos() / 1_000_000.0);
            } else {
                csv.append("N/A");
            }
            csv.append(System.lineSeparator());
        }

        try {
            Files.write(path, csv.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Results saved to: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save CSV: " + e.getMessage());
        }
    }
}
