package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class UndirectedGraph extends JPanel {
    private final Set<Vertex> vertices = new HashSet<>();
    private final Set<UndirectedEdge> edges = new HashSet<>();

    private final Set<JLabel> weightLabels = new HashSet<>();

    private final MainFrame mainFrame;

    public UndirectedGraph(MainFrame mainFrame, LayoutManager layout, String name) {
        this.mainFrame = mainFrame;
        setLayout(layout);
        setName(name);
        setForeground(Color.RED);
        setBackground(Color.BLACK);
        setBounds(0, 0, 800, 600);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (mainFrame.getMode()) {
                    case ADD_VERTEX -> createVertex(e);
                    case ADD_EDGE -> {
                        // To Do
                    }
                    case NONE_MODE -> whiteVertices();
                }
            }
        });
    }


    @Override
    protected void paintComponent(Graphics g) {
        setupLabelPosition();
        super.paintComponent(g);
    }

    @Override
    protected void paintChildren(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.WHITE);
        for (var edge : edges) {
            drawEdge(g2, edge);
        }

        super.paintChildren(g);
    }

    private void drawEdge(Graphics2D g2, UndirectedEdge edge) {
        var nodes = edge.getNodes();
        int x1 = nodes[0].getCenterX();
        int y1 = nodes[0].getCenterY();
        int x2 = nodes[1].getCenterX();
        int y2 = nodes[1].getCenterY();

        g2.drawLine(x1, y1, x2, y2);
    }

    private void setupLabelPosition() {
        for (var edge : edges) {
            var nodes = edge.getNodes();
            int x1 = nodes[0].getCenterX();
            int y1 = nodes[0].getCenterY();
            int x2 = nodes[1].getCenterX();
            int y2 = nodes[1].getCenterY();

            int centerX = Math.min(x1, x2) + Math.abs(x1 - x2) / 2;
            int centerY = Math.min(y1, y2) + Math.abs(y1 - y2) / 2;

            int dist = 25;
            int distSqr = dist * dist;
            if (x1 - x2 != 0) {
                float slope = (float) (y1 - y2) / (x2 - x1);
                double slopeSqr = slope * slope;
                double diffX = Math.sqrt(distSqr / (1 + slopeSqr));
                double diffY = Math.sqrt(distSqr - distSqr / (1 + slopeSqr));

                centerX = (int) (centerX + diffX);
                centerY = (int) (centerY + diffY);
            }
            edge.getWeightLabel().setBounds(centerX, centerY, 30, 30);
        }
    }

    private void createVertex(MouseEvent e) {
        whiteVertices();
        Vertex vertex = VertexCreator.createVertex(mainFrame, e.getX(), e.getY());
        if (vertex != null) {
            add(vertex);
            vertices.add(vertex);
            paintComponents(getGraphics());
            mainFrame.repaint();
        }
    }

    void whiteVertices() {
        vertices.forEach(Vertex::resetColor);
        mainFrame.repaint();
    }

    void addNewEdge(UndirectedEdge newEdge, UndirectedEdge stupidStageLogic) {
        if (!isDuplicateEdge(newEdge)) {
            edges.add(newEdge);
            add(newEdge);
            add(stupidStageLogic);

            weightLabels.add(newEdge.getWeightLabel());
            add(newEdge.getWeightLabel());

            whiteVertices();
            mainFrame.repaint();

        }
    }

    private boolean isDuplicateEdge(UndirectedEdge newEdge) {
        return edges.stream()
                .anyMatch(e -> e.getNodes()[0] == newEdge.getNodes()[0] && e.getNodes()[1] == newEdge.getNodes()[1]);
    }

    // getters
    Set<UndirectedEdge> getEdges() {
        return edges;
    }
}