package visualizer;

import javax.swing.*;
import java.awt.*;

public class UndirectedEdge extends JComponent {
    private Vertex[] nodes = new Vertex[2];
    private int weight;
    private JLabel weightLabel = new JLabel();

    public UndirectedEdge( Vertex fstNode, Vertex sndNode, int weight) {
        nodes[0] = fstNode;
        nodes[1] = sndNode;
        this.weight = weight;

        setName(String.format("Edge <%c -> %c>", fstNode.getId(), sndNode.getId()));

        weightLabel.setFont(new Font("Arial", Font.BOLD, 20));
        weightLabel.setForeground(Color.RED);
        weightLabel.setText(String.valueOf(weight));
        weightLabel.setName(String.format("EdgeLabel <%c -> %c>", fstNode.getId(), sndNode.getId()));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 5));
        g.drawLine(nodes[0].getX(), nodes[0].getY(), nodes[1].getX(), nodes[1].getY());
    }

    // getters

    Vertex[] getNodes() {
        return nodes;
    }

    int getWeight() {
        return weight;
    }

    JLabel getWeightLabel() {
        return weightLabel;
    }
}
