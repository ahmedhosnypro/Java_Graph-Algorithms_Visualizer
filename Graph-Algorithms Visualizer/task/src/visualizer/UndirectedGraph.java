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

    final MainFrame mainFrame;

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

    protected void paintChildren(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(6));
        g2.setColor(Color.WHITE);
        for (var edge : edges) {
            drawEdge(g2, edge);
        }

        super.paintChildren(g);
    }

    private void setupLabelPosition(UndirectedEdge edge) {
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

    private void drawEdge(Graphics2D g2, UndirectedEdge edge) {
        var nodes = edge.getNodes();
        int x1 = nodes[0].getCenterX();
        int y1 = nodes[0].getCenterY();
        int x2 = nodes[1].getCenterX();
        int y2 = nodes[1].getCenterY();

        g2.drawLine(x1, y1, x2, y2);
    }

    void addNewEdge(UndirectedEdge newEdge, UndirectedEdge stupidStageLogic) {
        if (!isDuplicateEdge(newEdge)) {
            edges.add(newEdge);
            add(newEdge);


            int x1 = newEdge.getNodes()[0].getCenterX();
            int y1 = newEdge.getNodes()[0].getCenterY();
            int x2 = newEdge.getNodes()[1].getCenterX();
            int y2 = newEdge.getNodes()[1].getCenterY();
            int centerX = Math.min(x1, x2) + Math.abs(x1 - x2) / 2;
            int centerY = Math.min(y1, y2) + Math.abs(y1 - y2) / 2;
            newEdge.setBounds(centerX - 3, centerY - 3, 50, 50);
            stupidStageLogic.setBounds(centerX, centerY, 50, 50);

            edges.add(stupidStageLogic);
            add(stupidStageLogic);

            setupLabelPosition(newEdge);
            weightLabels.add(newEdge.getWeightLabel());
            add(newEdge.getWeightLabel());

            whiteVertices();

            mainFrame.repaint();
        }
    }

    void removeEdge(UndirectedEdge edge) {
        var optEdge = edges
                .stream()
                .filter(it -> it.getNodes()[0] == edge.getNodes()[1] && it.getNodes()[1] == edge.getNodes()[0])
                .findFirst();

        UndirectedEdge oppositeEdge = null;
        if (optEdge.isPresent()) {
            oppositeEdge = optEdge.get();
        }

        remove(edge);
        edges.remove(edge);
        remove(oppositeEdge);
        edges.remove(oppositeEdge);

        remove(edge.getWeightLabel());
        weightLabels.remove(edge.getWeightLabel());

        if (oppositeEdge != null) {
            remove(oppositeEdge.getWeightLabel());
            weightLabels.remove(oppositeEdge.getWeightLabel());
        }

        edge.getNodes()[0].disconnectVertex(edge.getNodes()[1]);
        edge.getNodes()[1].disconnectVertex(edge.getNodes()[0]);

        mainFrame.repaint();
    }

    void removeVertex(Vertex vertex) {
        remove(vertex);
        vertices.remove(vertex);
        edges.stream().filter(e -> e.getNodes()[0] == vertex || e.getNodes()[1] == vertex).forEach(this::removeEdge);
        edges.removeIf(e -> e.getNodes()[0] == vertex || e.getNodes()[1] == vertex);
        mainFrame.repaint();
    }

    void reset() {
        for (var vertex : vertices) {
            remove(vertex);
        }
        vertices.clear();

        for (var edge : edges) {
            remove(edge);
        }
        edges.clear();

        for (var label : weightLabels) {
            remove(label);
        }
        weightLabels.clear();

        mainFrame.setMode(Mode.ADD_VERTEX);

        mainFrame.repaint();
    }


    private boolean isDuplicateEdge(UndirectedEdge newEdge) {
        return edges.stream()
                .anyMatch(e -> e.getNodes()[0] == newEdge.getNodes()[0] && e.getNodes()[1] == newEdge.getNodes()[1]);
    }

    // getters
    Set<UndirectedEdge> getEdges() {
        return edges;
    }

    public Set<JLabel> getWeightLabels() {
        return weightLabels;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }
}