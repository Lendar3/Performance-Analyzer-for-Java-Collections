package benchmark.presenter;

import benchmark.model.OutputType;

public class ResultPresenterFactory {
    public ResultPresenter forType(OutputType outputType) {
        switch (outputType) {
            case CONSOLE:
                return new ConsoleResultPresenter();
            case CSV:
                return new CsvResultPresenter();
            case JSON:
                return new JsonResultPresenter();
            default:
                throw new IllegalArgumentException("Unsupported output type: " + outputType);
        }
    }
}
