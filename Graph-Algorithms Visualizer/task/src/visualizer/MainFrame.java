package visualizer;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final Graph graph = new Graph(null, "Graph");


    public MainFrame() {
        setTitle("Graph-Algorithms Visualizer");
        setName("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();

        setVisible(true);
    }

    void initComponents() {
        addGraph();
        addMenuBar();
    }

    void addGraph() {
        graph.setBackground(Color.BLACK);
        add(graph);
    }

    void addMenuBar() {
        new DefaultMenuBar(this, graph);
    }

    /*
    ** stage 1
    void createCornerVertices() {
        for (int i = 0; i < 4; i++) {
            Vertex vertex = new Vertex("Vertex " + i, "VertexLabel " + i, String.valueOf(i), i);
            graph.add(vertex);
        }
    }
    */
}