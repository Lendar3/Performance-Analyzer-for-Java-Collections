package benchmark.service;

import benchmark.model.BenchmarkConfig;
import benchmark.model.BenchmarkReport;
import benchmark.model.CollectionType;
import benchmark.model.TestResult;
import benchmark.model.TestType;
import benchmark.service.generator.DataGenerator;
import benchmark.service.generator.DataGeneratorFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class CollectionBenchmarkService {
    private final DataGeneratorFactory generatorFactory;

    public CollectionBenchmarkService(DataGeneratorFactory generatorFactory) {
        this.generatorFactory = generatorFactory;
    }

    public BenchmarkReport run(BenchmarkConfig config) {
        DataGenerator<?> generator = generatorFactory.forType(config.getDataType());
        return runWithTypedGenerator(config, generator);
    }

    private <T> BenchmarkReport runWithTypedGenerator(BenchmarkConfig config, DataGenerator<T> generator) {
        List<T> data = generator.generate(config.getElementCount());
        Supplier<T> elementSupplier = generator.elementSupplier();
        Comparator<T> comparator = generator.comparator();

        List<TestResult> results = new ArrayList<TestResult>();
        for (TestType testType : TestType.values()) {
            TestResult result = runSingleTest(
                    testType,
                    config.getCollectionType(),
                    config.getIterations(),
                    data,
                    elementSupplier,
                    comparator
            );
            results.add(result);
        }

        return new BenchmarkReport(config, results);
    }

    private <T> TestResult runSingleTest(
            TestType testType,
            CollectionType collectionType,
            int iterations,
            List<T> data,
            Supplier<T> elementSupplier,
            Comparator<T> comparator
    ) {
        long totalNanos = 0L;

        for (int i = 0; i < iterations; i++) {
            Collection<T> collection;
            try {
                collection = collectionType.createCollection(comparator);
            } catch (Exception e) {
                return TestResult.notApplicable(testType);
            }

            Long measured = measure(testType, collection, data, elementSupplier);
            if (measured == null) {
                return TestResult.notApplicable(testType);
            }
            totalNanos += measured;
        }

        return TestResult.success(testType, totalNanos / iterations);
    }

    private <T> Long measure(
            TestType testType,
            Collection<T> collection,
            List<T> data,
            Supplier<T> elementSupplier
    ) {
        try {
            long start;
            long end;

            switch (testType) {
                case ADD:
                    start = System.nanoTime();
                    collection.addAll(data);
                    end = System.nanoTime();
                    return end - start;

                case GET_BY_INDEX:
                    if (!(collection instanceof List)) {
                        return null;
                    }
                    List<T> list = (List<T>) collection;
                    list.addAll(data);
                    if (data.isEmpty()) {
                        return 0L;
                    }
                    int index = data.size() / 2;
                    start = System.nanoTime();
                    list.get(index);
                    end = System.nanoTime();
                    return end - start;

                case SEARCH_ELEMENT:
                    collection.addAll(data);
                    if (data.isEmpty()) {
                        return 0L;
                    }
                    T search = data.get(data.size() / 2);
                    start = System.nanoTime();
                    collection.contains(search);
                    end = System.nanoTime();
                    return end - start;

                case CONTAINS:
                    collection.addAll(data);
                    if (data.isEmpty()) {
                        return 0L;
                    }
                    T present = data.get(0);
                    T absent = elementSupplier.get();
                    int guard = 0;
                    while (collection.contains(absent) && guard < 1000) {
                        absent = elementSupplier.get();
                        guard++;
                    }
                    start = System.nanoTime();
                    collection.contains(present);
                    collection.contains(absent);
                    end = System.nanoTime();
                    return (end - start) / 2;

                case REMOVE_ELEMENT:
                    collection.addAll(data);
                    if (data.isEmpty()) {
                        return 0L;
                    }
                    T remove = data.get(0);
                    start = System.nanoTime();
                    collection.remove(remove);
                    end = System.nanoTime();
                    return end - start;

                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
