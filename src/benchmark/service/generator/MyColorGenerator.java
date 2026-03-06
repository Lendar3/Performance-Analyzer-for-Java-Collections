package benchmark.service.generator;

import benchmark.model.MyColor;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyColorGenerator implements DataGenerator<MyColor> {
    private final Random random = new Random();

    @Override
    public List<MyColor> generate(int count) {
        return Stream.generate(elementSupplier())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Supplier<MyColor> elementSupplier() {
        return new Supplier<MyColor>() {
            @Override
            public MyColor get() {
                return new MyColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            }
        };
    }

    @Override
    public Comparator<MyColor> comparator() {
        return null;
    }
}
