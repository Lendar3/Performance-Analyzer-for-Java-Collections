package benchmark.service.generator;

import benchmark.model.DataType;

public class DataGeneratorFactory {
    public DataGenerator<?> forType(DataType dataType) {
        switch (dataType) {
            case INTEGER:
                return new IntegerGenerator();
            case DOUBLE:
                return new DoubleGenerator();
            case PERSON:
                return new PersonGenerator();
            case MY_COLOR:
                return new MyColorGenerator();
            case POINT_2D:
                return new Point2DGenerator();
            case LONG:
                return new LongGenerator();
            default:
                throw new IllegalArgumentException("Unsupported data type: " + dataType);
        }
    }
}
