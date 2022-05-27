package visualizer;

import javax.swing.*;

public class VertexCreator {
    private VertexCreator() {
    }

    static Vertex createVertex(Graph graph, int x, int y) {
        String input = JOptionPane.showInputDialog(graph, "Enter the Vertex Id (should be 1 char", "Vertex", JOptionPane.QUESTION_MESSAGE);
        if (isValidId(input)) {
            return new Vertex(graph, x, y, input.charAt(0));
        }
        return createVertex(graph, x, y);
    }

    private static boolean isValidId(String input) {
        if (input.length() == 1) {
            return input.matches("[A-Za-z\\d]");
        }
        return false;
    }
}
