package benchmark.service.generator;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoubleGenerator implements DataGenerator<Double> {
    private final Random random = new Random();

    @Override
    public List<Double> generate(int count) {
        return Stream.generate(new Supplier<Double>() {
                    @Override
                    public Double get() {
                        return random.nextDouble() * count * 10;
                    }
                })
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Supplier<Double> elementSupplier() {
        return new Supplier<Double>() {
            @Override
            public Double get() {
                return random.nextDouble() * 10000;
            }
        };
    }

    @Override
    public Comparator<Double> comparator() {
        return null;
    }
}
