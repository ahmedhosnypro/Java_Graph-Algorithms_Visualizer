package visualizer;

import java.util.*;

public class PrimAlgorithm extends Algorithm {
    Set<Vertex> mstSelected;
    Map<Vertex, Integer> verticesWeight; // in mst tree
    Map<Character, Character> childParents;   // by id

    PrimAlgorithm(Vertex startPoint) {
        super.startPoint = startPoint;
    }

    @Override
    String search() {
        mstSelected = new HashSet<>();
        verticesWeight = new HashMap<>();
        childParents = new TreeMap<>();

        for (var vertex : startPoint.getMainFrame().getGraph().getVertices()) {
            verticesWeight.put(vertex, Integer.MAX_VALUE);
        }

        verticesWeight.put(startPoint, 0);
        mstSelected.add(startPoint);

        buildMST(startPoint);

        StringBuilder ret = new StringBuilder();
        childParents.forEach((c, p) -> ret.append(c).append("=").append(p).append(", "));
        return ret.deleteCharAt(ret.length() - 2).toString().trim();
    }

    void buildMST(Vertex vertex) {
        if (vertex.getConnectedVertices().isEmpty()) {
            return;
        }

        var connected = new LinkedHashMap<Vertex, Integer>();
        for (var entry : vertex.getConnectedVertices().entrySet()) {
            connected.put(entry.getValue(), entry.getKey());
        }

        for (var entry : connected.entrySet()) {
            if (entry.getKey() != startPoint && entry.getValue() < verticesWeight.get(entry.getKey())) {
                verticesWeight.put(entry.getKey(), entry.getValue());
                childParents.put(entry.getKey().getId(), vertex.getId());
            }
        }

        var optVertex = verticesWeight.keySet().stream()
                .filter(k -> !mstSelected.contains(k))
                .min((k1, k2) -> Integer.compare(verticesWeight.get(k1), verticesWeight.get(k2)));

        Vertex toSelect = null;
        if (optVertex.isPresent()) {
            toSelect = optVertex.get();
        }

        if (toSelect != null) {
            mstSelected.add(toSelect);
            buildMST(toSelect);
        }
    }
}
