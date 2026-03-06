package benchmark.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public enum CollectionType {
    ARRAY_LIST(1, "ArrayList"),
    LINKED_LIST(2, "LinkedList"),
    HASH_SET(3, "HashSet"),
    TREE_SET(4, "TreeSet");

    private final int option;
    private final String label;

    CollectionType(int option, String label) {
        this.option = option;
        this.label = label;
    }

    public int getOption() {
        return option;
    }

    public String getLabel() {
        return label;
    }

    public static CollectionType fromOption(int option) {
        for (CollectionType type : values()) {
            if (type.option == option) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid collection option: " + option);
    }

    public <T> Collection<T> createCollection(Comparator<T> comparator) {
        switch (this) {
            case ARRAY_LIST:
                return new ArrayList<T>();
            case LINKED_LIST:
                return new LinkedList<T>();
            case HASH_SET:
                return new HashSet<T>();
            case TREE_SET:
                return comparator == null ? new TreeSet<T>() : new TreeSet<T>(comparator);
            default:
                throw new IllegalArgumentException("Unsupported collection type: " + this);
        }
    }
}
