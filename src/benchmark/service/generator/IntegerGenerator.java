package benchmark.service.generator;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntegerGenerator implements DataGenerator<Integer> {
    private final Random random = new Random();

    @Override
    public List<Integer> generate(int count) {
        return Stream.generate(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        return random.nextInt();
                    }
                })
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Supplier<Integer> elementSupplier() {
        return new Supplier<Integer>() {
            @Override
            public Integer get() {
                return random.nextInt();
            }
        };
    }

    @Override
    public Comparator<Integer> comparator() {
        return null;
    }
}
