package visualizer;

import javax.swing.*;
import java.awt.*;

public class Edge extends JComponent {
    private Vertex startVertex;
    private Vertex endVertex;
    private int weight;

    public Edge(Vertex startVertex, Vertex endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 5));
        g.drawLine(startVertex.getX(), startVertex.getY(), endVertex.getX(), endVertex.getY());
    }
}
