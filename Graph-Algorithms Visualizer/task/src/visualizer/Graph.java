package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Graph extends JPanel {
    private static final int GRAPH_SIZE = 800;
    private static final float TEXT_SIZE_PERCENT = 7.6f;
    private GraphMode graphMode = GraphMode.ADD_VERTEX;
    private static final String CURRENT_MODE = "Current Mode -> ";
    private final JLabel modeLabel = new JLabel(CURRENT_MODE + graphMode.getMode());

    private final java.util.List<Edge> edges = new ArrayList<>();


    public Graph(LayoutManager layout, String name) {
        setLayout(layout);
        setName(name);
        initComponents();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (graphMode) {
                    case ADD_VERTEX -> createVertex(e);
                    case ADD_EDGE -> createEdge(e);
                    case NONE -> heiglightSelected(e);
                }
            }
        });
    }

    private void initComponents() {


        add(modeLabel);
        modeLabel.setForeground(Color.white);
        modeLabel.setBounds((int) (GRAPH_SIZE - modeLabel.getText().length() * TEXT_SIZE_PERCENT),
                0, (int) (modeLabel.getText().length() * TEXT_SIZE_PERCENT), 50);
        add(modeLabel);
    }

    private void createVertex(MouseEvent e) {
        Vertex vertex = VertexCreator.createVertex(Graph.this, e.getX(), e.getY());
        add(vertex);
        paintComponents(getGraphics());
        repaint();
    }

    private void createEdge(MouseEvent e) {

    }

    private void heiglightSelected(MouseEvent e) {

    }

    public void setGraphMode(GraphMode graphMode) {
        this.graphMode = graphMode;
        modeLabel.setText(CURRENT_MODE + graphMode.getMode());
        modeLabel.setBounds((int) (GRAPH_SIZE - modeLabel.getText().length() * TEXT_SIZE_PERCENT),
                0, (int) (modeLabel.getText().length() * TEXT_SIZE_PERCENT), 50);
        repaint();
    }
}