package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class Vertex extends JPanel {
    private static final int DIFF_X = 25;
    private static final int DIFF_Y = 25;
    private static final int SIZE = 50;

    private final MainFrame mainFrame;

    private final JLabel label = new JLabel();

    private final Set<Vertex> connectedVertices = new HashSet<>();

    private int centerX;
    private int centerY;
    private char id;
    private static final Color DEFAULT_COLOR = Color.WHITE;
    private Color color = DEFAULT_COLOR;


    // private final int diffX = 734;  // for corner vertices apply x = (i % 2) * diffX
    // private final int diffY = 511;   // for corner vertices apply y = (i / 2) * diffY


    public Vertex(MainFrame mainFrame, int x, int y, char id) {
        this.mainFrame = mainFrame;
        this.centerX = x - DIFF_X;
        this.centerY = y - DIFF_Y;
        this.id = id;

        initComponents();
        setActionEvents();
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

    private void setActionEvents() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (mainFrame.getMode()) {
                    case ADD_VERTEX, NONE_MODE, REMOVE_EDGE -> highlight();
                    case ADD_EDGE -> createEdge();
                    case REMOVE_VERTEX -> removeVertex();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillOval(0, 0, SIZE, SIZE);
    }

    private void createEdge() {
        highlight();
        UndirectedEdgeCreator.createUndirectedEdge(Vertex.this);
    }

    private void highlight() {
        mainFrame.getGraph().whiteVertices();
        color = Color.yellow;
        mainFrame.getGraph().repaint();
    }

    private void removeVertex() {
        mainFrame.getGraph().removeVertex(this);
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

    public void resetColor() {
        this.color = DEFAULT_COLOR;
    }

    // getters
    char getId() {
        return id;
    }

    Set<Vertex> getConnectedVertices() {
        return connectedVertices;
    }

    public int getCenterX() {
        return centerX + DIFF_X;
    }

    public int getCenterY() {
        return centerY + DIFF_Y;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }
}
