package benchmark.model;

public class BenchmarkConfig {
    private final DataType dataType;
    private final CollectionType collectionType;
    private final OutputType outputType;
    private final int elementCount;
    private final int iterations;

    public BenchmarkConfig(
            DataType dataType,
            CollectionType collectionType,
            OutputType outputType,
            int elementCount,
            int iterations
    ) {
        this.dataType = dataType;
        this.collectionType = collectionType;
        this.outputType = outputType;
        this.elementCount = elementCount;
        this.iterations = iterations;
    }

    public DataType getDataType() {
        return dataType;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    public OutputType getOutputType() {
        return outputType;
    }

    public int getElementCount() {
        return elementCount;
    }

    public int getIterations() {
        return iterations;
    }
}
