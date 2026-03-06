package benchmark.model;

public enum DataType {
    INTEGER(1, "Integer"),
    DOUBLE(2, "Double"),
    PERSON(3, "Person"),
    MY_COLOR(4, "MyColor"),
    POINT_2D(5, "Point2D"),
    LONG(6, "Long");

    private final int option;
    private final String label;

    DataType(int option, String label) {
        this.option = option;
        this.label = label;
    }

    public int getOption() {
        return option;
    }

    public String getLabel() {
        return label;
    }

    public static DataType fromOption(int option) {
        for (DataType type : values()) {
            if (type.option == option) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid data type option: " + option);
    }
}
