package visualizer;

public enum Mode {
    ADD_VERTEX("Add a Vertex"),
    ADD_EDGE("Add an Edge"),
    NONE_MODE("None");

    private final String name;

    Mode(String mode) {
        this.name = mode;
    }

    public String getName() {
        return name;
    }
}
