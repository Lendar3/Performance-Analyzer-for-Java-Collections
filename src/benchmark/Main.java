package benchmark;

import benchmark.controller.BenchmarkController;
import benchmark.presenter.ResultPresenterFactory;
import benchmark.service.CollectionBenchmarkService;
import benchmark.service.generator.DataGeneratorFactory;
import benchmark.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        BenchmarkController controller = new BenchmarkController(
                new ConsoleView(),
                new CollectionBenchmarkService(new DataGeneratorFactory()),
                new ResultPresenterFactory()
        );
        controller.run();
    }
}
