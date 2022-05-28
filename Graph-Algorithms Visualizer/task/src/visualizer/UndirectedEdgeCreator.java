package visualizer;

import javax.swing.*;

public class UndirectedEdgeCreator {
    private static Vertex fstNode = null;
    private static final String MESSAGE = "Enter Weight";
    private static final String TITLE = "Input";

    private UndirectedEdgeCreator() {
    }

    static void createUndirectedEdge(Vertex vertex) {
        if (fstNode == null) {
            fstNode = vertex;
        } else if (fstNode != vertex) {
            Integer weight = getWeight(vertex.getMainFrame().getGraph());
            if (weight == null) {
                fstNode.getMainFrame().getGraph().whiteVertices();
                fstNode = null;
                return;
            }
            var undirectedEdges = vertex.getMainFrame().getGraph().getEdges();
            if (!isDuplicateEdge(undirectedEdges, vertex)) {

                vertex.getMainFrame().getGraph()
                        .addNewEdge(new UndirectedEdge(fstNode, vertex, weight, vertex.getMainFrame().getGraph()),
                                new UndirectedEdge(vertex, fstNode, weight, vertex.getMainFrame().getGraph()));
                fstNode = null;
            }
        }
    }

    private static Integer getWeight(UndirectedGraph graph) {
        String input = JOptionPane.showInputDialog(graph, MESSAGE, TITLE, JOptionPane.QUESTION_MESSAGE);
        if (input == null) {
            return null;
        }
        if (isValidWeight(input)) {
            return Integer.parseInt(input);
        }
        return getWeight(graph);
    }


    private static boolean isValidWeight(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isDuplicateEdge(java.util.Set<UndirectedEdge> edges, Vertex sndNode) {
        return edges.stream()
                .anyMatch(edge -> edge.getNodes()[0] == fstNode && edge.getNodes()[1] == sndNode);
    }

    public static void reset() {
        fstNode = null;
    }
}
