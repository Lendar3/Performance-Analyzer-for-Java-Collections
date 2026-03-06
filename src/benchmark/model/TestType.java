package benchmark.model;

public enum TestType {
    ADD("Add"),
    GET_BY_INDEX("GetByIndex"),
    SEARCH_ELEMENT("SearchElement"),
    CONTAINS("Contains"),
    REMOVE_ELEMENT("RemoveElement");

    private final String label;

    TestType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
