package benchmark.model;

public enum OutputType {
    CONSOLE(1, "Console"),
    CSV(2, "CSV"),
    JSON(3, "JSON");

    private final int option;
    private final String label;

    OutputType(int option, String label) {
        this.option = option;
        this.label = label;
    }

    public int getOption() {
        return option;
    }

    public String getLabel() {
        return label;
    }

    public static OutputType fromOption(int option) {
        for (OutputType type : values()) {
            if (type.option == option) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid output option: " + option);
    }
}
