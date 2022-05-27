package visualizer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Vertex extends JPanel {
    private static final int DIFF_X = -25;
    private static final int DIFF_Y = -25;
    private static final int SIZE = 50;

    private final Graph graph;

    private final JLabel label = new JLabel();

    private final java.util.List<Vertex> connectedVertices = new ArrayList<>();

    private int centerX;
    private int centerY;
    private char id;


    // private final int diffX = 734;  // for corner vertices apply x = (i % 2) * diffX
    // private final int diffY = 511;   // for corner vertices apply y = (i / 2) * diffY


    public Vertex(Graph graph, int x, int y, char id) {
        this.graph = graph;
        this.centerX = x + DIFF_X;
        this.centerY = y + DIFF_Y;
        this.id = id;

        initComponents();
    }

    private void initComponents() {
        setName("Vertex " + id);
        setLayout(new GridBagLayout());
        setBounds(centerX, centerY, SIZE, SIZE);

        label.setName("VertexLabel " + id);
        label.setText(String.valueOf(id));
        label.setFont(new Font("SansSerif", Font.BOLD, 40));
        label.setForeground(Color.BLACK);
        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, SIZE, SIZE);
    }

    void connectVertex(Vertex vertex) {
        connectedVertices.add(vertex);
    }

    void disconnectVertex(Vertex vertex) {
        connectedVertices.removeIf(v -> v == vertex);
    }

    // setters
    public void setX(int centerX) {
        this.centerX = centerX;
    }

    public void setY(int centerY) {
        this.centerY = centerY;
    }

    public void setId(char id) {
        this.id = id;
    }

    void setLabel(String labelText) {
        label.setText(labelText);
    }


    // getters
    char getId() {
        return id;
    }

    List<Vertex> getConnectedVertices() {
        return connectedVertices;
    }
}
