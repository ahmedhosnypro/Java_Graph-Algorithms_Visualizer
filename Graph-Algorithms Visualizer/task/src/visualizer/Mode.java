package visualizer;

public enum Mode {
    ADD_VERTEX("Add a Vertex"),
    ADD_EDGE("Add an Edge"),
    REMOVE_VERTEX("Remove a Vertex"),
    REMOVE_EDGE("Remove an Edge"),
    NONE_MODE("None"),
    DFS_ALGORITHM("Depth-First Search"),
    BFS_ALGORITHM("Breadth-First Search"),
    DIJKSTRA_ALGORITHM("Dijkstra's Algorithm"),
    PRIM_ALGORITHM("Prim's Algorithm");

    private final String name;

    Mode(String mode) {
        this.name = mode;
    }

    public String getName() {
        return name;
    }
}
