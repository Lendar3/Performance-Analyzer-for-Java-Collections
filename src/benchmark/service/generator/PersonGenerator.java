package benchmark.service.generator;

import benchmark.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonGenerator implements DataGenerator<Person> {
    private static final String[] NAMES = {
            "Vova", "Gracijan", "Jarik", "David", "Eve",
            "Oleg", "Oleksii", "Rafal", "Ivan", "Julia"
    };

    private final Random random = new Random();

    @Override
    public List<Person> generate(int count) {
        return Stream.generate(elementSupplier())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Supplier<Person> elementSupplier() {
        return new Supplier<Person>() {
            @Override
            public Person get() {
                String name = NAMES[random.nextInt(NAMES.length)] + random.nextInt(1000);
                int year = 1950 + random.nextInt(76);
                return new Person(name, year);
            }
        };
    }

    @Override
    public Comparator<Person> comparator() {
        return null;
    }
}
