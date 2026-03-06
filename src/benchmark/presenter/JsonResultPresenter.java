package benchmark.presenter;

import benchmark.model.BenchmarkConfig;
import benchmark.model.BenchmarkReport;
import benchmark.model.TestResult;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class JsonResultPresenter implements ResultPresenter {
    private static final DateTimeFormatter FILE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    @Override
    public void present(BenchmarkReport report) {
        String fileName = "collection-benchmark-" + report.getGeneratedAt().format(FILE_TIME_FORMAT) + ".json";
        Path path = Path.of(fileName);

        BenchmarkConfig config = report.getConfig();
        StringBuilder json = new StringBuilder();

        json.append("{\n");
        json.append("  \"generatedAt\": \"").append(report.getGeneratedAt()).append("\",\n");
        json.append("  \"config\": {\n");
        json.append("    \"dataType\": \"").append(config.getDataType().getLabel()).append("\",\n");
        json.append("    \"collectionType\": \"").append(config.getCollectionType().getLabel()).append("\",\n");
        json.append("    \"elementCount\": ").append(config.getElementCount()).append(",\n");
        json.append("    \"iterations\": ").append(config.getIterations()).append("\n");
        json.append("  },\n");
        json.append("  \"results\": [\n");

        for (int i = 0; i < report.getResults().size(); i++) {
            TestResult result = report.getResults().get(i);
            json.append("    {\n");
            json.append("      \"testType\": \"").append(result.getTestType().getLabel()).append("\",\n");
            json.append("      \"applicable\": ").append(result.isApplicable()).append(",\n");
            if (result.isApplicable()) {
                json.append("      \"averageMs\": ").append(result.getAverageNanos() / 1_000_000.0).append("\n");
            } else {
                json.append("      \"averageMs\": null\n");
            }
            json.append("    }");
            if (i < report.getResults().size() - 1) {
                json.append(',');
            }
            json.append("\n");
        }

        json.append("  ]\n");
        json.append("}\n");

        try {
            Files.write(path, json.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Results saved to: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save JSON: " + e.getMessage());
        }
    }
}
