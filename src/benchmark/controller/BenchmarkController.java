package benchmark.controller;

import benchmark.model.BenchmarkConfig;
import benchmark.model.BenchmarkReport;
import benchmark.model.CollectionType;
import benchmark.model.DataType;
import benchmark.model.OutputType;
import benchmark.presenter.ResultPresenter;
import benchmark.presenter.ResultPresenterFactory;
import benchmark.service.CollectionBenchmarkService;
import benchmark.view.ConsoleView;

public class BenchmarkController {
    private final ConsoleView view;
    private final CollectionBenchmarkService benchmarkService;
    private final ResultPresenterFactory presenterFactory;

    public BenchmarkController(
            ConsoleView view,
            CollectionBenchmarkService benchmarkService,
            ResultPresenterFactory presenterFactory
    ) {
        this.view = view;
        this.benchmarkService = benchmarkService;
        this.presenterFactory = presenterFactory;
    }

    public void run() {
        view.showWelcome();

        boolean runAgain = true;
        while (runAgain) {
            try {
                DataType dataType = view.chooseDataType();
                int elementCount = view.chooseElementCount();
                int iterations = view.chooseIterations();
                CollectionType collectionType = view.chooseCollectionType();
                OutputType outputType = view.chooseOutputType();

                BenchmarkConfig config = new BenchmarkConfig(
                        dataType,
                        collectionType,
                        outputType,
                        elementCount,
                        iterations
                );

                BenchmarkReport report = benchmarkService.run(config);
                ResultPresenter presenter = presenterFactory.forType(outputType);
                presenter.present(report);
            } catch (IllegalArgumentException ex) {
                view.showError(ex.getMessage());
            } catch (Exception ex) {
                view.showError("Unexpected error: " + ex.getMessage());
            }

            runAgain = view.askRunAgain();
        }

        view.close();
    }
}
