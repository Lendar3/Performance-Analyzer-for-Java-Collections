package benchmark.model;

import java.util.Objects;

public class Person implements Comparable<Person> {
    private final String name;
    private final int yearOfBirth;

    public Person(String name, int yearOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    @Override
    public int compareTo(Person other) {
        int byYear = Integer.compare(this.yearOfBirth, other.yearOfBirth);
        if (byYear != 0) {
            return byYear;
        }
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return yearOfBirth == person.yearOfBirth && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfBirth);
    }

    @Override
    public String toString() {
        return name + " (" + yearOfBirth + ")";
    }
}
