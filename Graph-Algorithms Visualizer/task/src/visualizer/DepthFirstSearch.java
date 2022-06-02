package visualizer;

import java.util.HashSet;
import java.util.Set;

public class DepthFirstSearch extends Algorithm {

    private Set<Vertex> searched;
    private StringBuilder ret;

    public DepthFirstSearch(Vertex startPoint) {
        super.startPoint = startPoint;
    }

    @Override
    String search() {
        ret = new StringBuilder();
        searched = new HashSet<>();
        ret.append(" ").append(startPoint.getId()).append(" ");
        searched.add(startPoint);
        dfSearch(startPoint);
        return "DFS : " + ret.toString().trim().replaceAll("\\s+", " -> ");
    }

    void dfSearch(Vertex vertex) {
        if (!searched.contains(vertex) && vertex.getConnectedVertices().isEmpty()) {
            searched.add(vertex);
            ret.append(" ").append(vertex.getId()).append(" ");
        }


        for (var vert : vertex.getConnectedVertices().values()) {
            if (!searched.contains(vert)) {
                searched.add(vert);
                ret.append(" ").append(vert.getId()).append(" ");
                dfSearch(vert);
            }
        }
    }
}