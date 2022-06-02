package visualizer;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm extends Algorithm {

    private Map<Vertex, Integer> weights;

    public DijkstraAlgorithm(Vertex startPoint) {
        super.startPoint = startPoint;
    }

    @Override
    String search() {
        weights = new LinkedHashMap<>();
        weights.put(startPoint, 0);

        findShortestPath(startPoint, Set.of(startPoint));

        weights.remove(startPoint);

        StringBuilder ret = new StringBuilder();
        weights.forEach((k, v) -> ret.append(k.getId()).append("=").append(v).append(", "));
        return ret.deleteCharAt(ret.length() - 2).toString().trim();
    }

    void findShortestPath(Vertex vertex, Set<Vertex> parents) {
        var tmpMap = new LinkedHashMap<Vertex, Integer>();
        for (var entry : vertex.getConnectedVertices().entrySet()) {
            tmpMap.put(entry.getValue(), entry.getKey());
        }
        for (var entry : tmpMap.entrySet()) {
            if (entry.getKey() != startPoint && entry.getKey() != vertex && !parents.contains(entry.getKey())) {
                int vertWeight = weights.get(vertex) + entry.getValue();
                weights.put(entry.getKey(), Math.min(vertWeight, weights.getOrDefault(entry.getKey(), Integer.MAX_VALUE)));
            }
        }

        for (var entry : tmpMap.entrySet()) {
            if (entry.getKey() != startPoint && entry.getKey() != vertex && !parents.contains(entry.getKey())) {
                var tmpSet = new HashSet<>(parents);
                tmpSet.add(vertex);
                findShortestPath(entry.getKey(), tmpSet);
            }
        }
    }
}
