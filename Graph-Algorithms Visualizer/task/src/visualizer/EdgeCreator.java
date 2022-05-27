package visualizer;

import javax.swing.*;

public class EdgeCreator {
    private Vertex edgeStart;

    private EdgeCreator() {
    }

    static Vertex createEdge(Graph graph, int x, int y) {
        String input = JOptionPane.showInputDialog(graph, "Enter the Vertex Id (should be 1 char", "Vertex", JOptionPane.QUESTION_MESSAGE);
        if (isValidWeight(input)) {
            return new Vertex(graph,x, y, input.charAt(0));
        }
        return createEdge(graph, x, y);
    }

    private static boolean isValidWeight(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
