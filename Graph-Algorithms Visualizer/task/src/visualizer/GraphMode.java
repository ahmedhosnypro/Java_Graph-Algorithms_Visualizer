package visualizer;

public enum GraphMode {
    ADD_VERTEX("Add a Vertex"),
    ADD_EDGE("Add an Edge"),
    NONE("None");

    private final String mode;

    GraphMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
