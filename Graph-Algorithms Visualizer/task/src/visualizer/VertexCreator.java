package visualizer;

import javax.swing.*;

public class VertexCreator {
    private static final String MESSAGE = "Enter the Vertex Id (should be 1 char";
    private static final String TITLE = "Vertex";

    private VertexCreator() {
    }


    static Vertex createVertex(MainFrame mainFrame, int x, int y) {
        String input = JOptionPane.showInputDialog(mainFrame, MESSAGE, TITLE, JOptionPane.QUESTION_MESSAGE);
        if (input == null) {
            return null;
        }
        if (isValidId(input)) {
            return new Vertex(mainFrame, x, y, input.charAt(0));
        }
        return createVertex(mainFrame, x, y);
    }


    private static boolean isValidId(String input) {
        if (input.length() == 1) {
            return input.matches("[A-Za-z\\d]");
        }
        return false;
    }
}
