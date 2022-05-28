package visualizer;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    Mode mode = Mode.ADD_VERTEX;
    private static final String CURRENT_MODE = "Current Mode -> ";
    private final UndirectedGraph graph = new UndirectedGraph(this, null, "Graph");
    private final JLabel modeLabel = new JLabel(CURRENT_MODE + mode.getName());
    private static final float TEXT_SIZE_PERCENT = 7.6f;
    public MainFrame() {
        setTitle("Graph-Algorithms Visualizer");
        setName("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        initComponents();

        setVisible(true);
    }
    void initComponents() {
        addModeLabel();
        add(graph);
        addMenuBar();
    }
    void addMenuBar() {
        new DefaultMenuBar(this);
    }
    private void addModeLabel() {
        modeLabel.setName("Mode");
        add(modeLabel);
        modeLabel.setBackground(Color.BLACK);
        modeLabel.setForeground(Color.RED);
        modeLabel.setBounds((int) (WIDTH - modeLabel.getText().length() * TEXT_SIZE_PERCENT),
                0, (int) (modeLabel.getText().length() * TEXT_SIZE_PERCENT), 50);
        add(modeLabel);
    }
    void setMode(Mode graphMode) {
        UndirectedEdgeCreator.reset();
        graph.whiteVertices();
        this.mode = graphMode;
        modeLabel.setText(CURRENT_MODE + graphMode.getName());
        modeLabel.setBounds((int) (WIDTH - modeLabel.getText().length() * TEXT_SIZE_PERCENT),
                0, (int) (modeLabel.getText().length() * TEXT_SIZE_PERCENT), 50);
        repaint();
    }
    Mode getMode() {
        return mode;
    }

    public UndirectedGraph getGraph() {
        return graph;
    }
}