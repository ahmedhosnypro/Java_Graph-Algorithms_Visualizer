package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Vertex extends JPanel {
    private static final int DIFF_X = 25;
    private static final int DIFF_Y = 25;
    private static final int SIZE = 50;

    private final MainFrame mainFrame;

    private final JLabel label = new JLabel();

    private final Map<Integer, Vertex> connectedVertices = new TreeMap<>();

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
                    case DFS_ALGORITHM -> search(new DepthFirstSearch(Vertex.this));
                    case BFS_ALGORITHM -> search(new BreadthFirstSearch(Vertex.this));
                    case DIJKSTRA_ALGORITHM -> search(new DijkstraAlgorithm(Vertex.this));
                    case PRIM_ALGORITHM -> search(new PrimAlgorithm(Vertex.this));
                }
            }
        });
    }

    private void search(Algorithm algorithm) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        redLight();
        SearchStrategy.setStrategy(algorithm);
        mainFrame.setResultLabelText("Please wait...");
        executor.submit(() -> {
            try {
                Thread.sleep(5000);
                mainFrame.setResultLabelText(SearchStrategy.search());
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                executor.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        executor.shutdown();
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

    private void redLight() {
        mainFrame.getGraph().whiteVertices();
        color = Color.RED;
        mainFrame.getGraph().repaint();
    }

    private void removeVertex() {
        mainFrame.getGraph().removeVertex(this);
    }

    void connectVertex(Vertex vertex, int weight) {
        connectedVertices.put(weight, vertex);
    }

    void disconnectVertex(Vertex vertex) {
        for (var entry : new HashSet<>(connectedVertices.entrySet())) {
            if (connectedVertices.get(entry.getKey()) == vertex) {
                connectedVertices.remove(entry.getKey());
            }
        }
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

    Map<Integer, Vertex> getConnectedVertices() {
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
