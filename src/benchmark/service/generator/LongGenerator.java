package benchmark.service.generator;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LongGenerator implements DataGenerator<Long> {
    private final Random random = new Random();

    @Override
    public List<Long> generate(int count) {
        return Stream.generate(new Supplier<Long>() {
                    @Override
                    public Long get() {
                        return random.nextLong();
                    }
                })
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Supplier<Long> elementSupplier() {
        return new Supplier<Long>() {
            @Override
            public Long get() {
                return random.nextLong();
            }
        };
    }

    @Override
    public Comparator<Long> comparator() {
        return null;
    }
}
