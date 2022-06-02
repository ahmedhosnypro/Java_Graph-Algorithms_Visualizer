package visualizer;

import java.util.*;

public class BreadthFirstSearch extends Algorithm {
    private Set<Vertex> searched;
    private StringBuilder ret;

    private Deque<Vertex> toSearch;

    public BreadthFirstSearch(Vertex startPoint) {
        super.startPoint = startPoint;
    }

    @Override
    String search() {
        ret = new StringBuilder();
        searched = new HashSet<>();
        toSearch = new LinkedList<>();
        searched.add(startPoint);
        ret.append(" ").append(startPoint.getId()).append(" ");
        bfSearch(startPoint);
        return "BFS : " + ret.toString().trim().replaceAll("\\s+", " -> ");
    }

    void bfSearch(Vertex vertex) {
        for (var vert : vertex.getConnectedVertices().values()) {
            if (!searched.contains(vert)) {
                toSearch.addLast(vert);
            }
        }
        doSearch();
    }

    void doSearch() {
        while (!toSearch.isEmpty()) {
            var vertex = toSearch.pollFirst();
            if (!searched.contains(vertex)) {
                ret.append(" ").append(vertex.getId()).append(" ");
                searched.add(vertex);
                bfSearch(vertex);
            }
        }
    }
}
