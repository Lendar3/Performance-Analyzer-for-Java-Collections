package benchmark.service.generator;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public interface DataGenerator<T> {
    List<T> generate(int count);

    Supplier<T> elementSupplier();

    Comparator<T> comparator();
}
