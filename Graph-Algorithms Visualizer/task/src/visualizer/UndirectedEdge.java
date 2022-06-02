package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UndirectedEdge extends JComponent {
    private final Vertex[] nodes = new Vertex[2];
    private final int weight;
    private final JLabel weightLabel = new JLabel();
    private final UndirectedGraph graph;

    public UndirectedEdge(Vertex fstNode, Vertex sndNode, int weight, UndirectedGraph graph) {
        this.graph = graph;
        nodes[0] = fstNode;
        nodes[1] = sndNode;
        this.weight = weight;

        fstNode.connectVertex(sndNode, weight);

        setName(String.format("Edge <%c -> %c>", fstNode.getId(), sndNode.getId()));

        weightLabel.setFont(new Font("Arial", Font.BOLD, 20));
        weightLabel.setForeground(Color.RED);
        weightLabel.setText(String.valueOf(weight));
        weightLabel.setName(String.format("EdgeLabel <%c -> %c>", fstNode.getId(), sndNode.getId()));

        setBackground(Color.WHITE);
        addEventListeners();
    }

    void addEventListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (graph.mainFrame.getMode() == Mode.REMOVE_EDGE) {
                    graph.removeEdge(UndirectedEdge.this);
                }
            }
        });
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
