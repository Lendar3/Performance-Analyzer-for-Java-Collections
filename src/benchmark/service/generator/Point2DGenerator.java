package benchmark.service.generator;

import benchmark.model.Point2D;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Point2DGenerator implements DataGenerator<Point2D> {
    private final Random random = new Random();

    @Override
    public List<Point2D> generate(int count) {
        return Stream.generate(elementSupplier())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Supplier<Point2D> elementSupplier() {
        return new Supplier<Point2D>() {
            @Override
            public Point2D get() {
                return new Point2D(
                        random.nextDouble(),
                        random.nextDouble(),
                        random.nextDouble(),
                        random.nextDouble()
                );
            }
        };
    }

    @Override
    public Comparator<Point2D> comparator() {
        return null;
    }
}
