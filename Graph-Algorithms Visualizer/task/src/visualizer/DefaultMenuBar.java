package visualizer;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class DefaultMenuBar extends JMenuBar {
    private final Graph graph;
    private final JMenu modeMenu = new JMenu("Mode");
    private final JMenuItem addVertex = new JMenuItem(GraphMode.ADD_VERTEX.getMode());
    private final JMenuItem addEdge = new JMenuItem(GraphMode.ADD_EDGE.getMode());
    private final JMenuItem none = new JMenuItem(GraphMode.NONE.getMode());
    private final JMenuItem exit = new JMenuItem("Exit");

    DefaultMenuBar(MainFrame mainFrame, Graph graph) {
        mainFrame.setJMenuBar(this);
        this.graph = graph;
        initComponents();
    }

    void initComponents() {
        modeMenu.setMnemonic(KeyEvent.VK_Q);
        add(modeMenu);

        addVertex.addActionListener(e -> graph.setGraphMode(GraphMode.ADD_VERTEX));
        addEdge.addActionListener(e -> graph.setGraphMode(GraphMode.ADD_EDGE));
        none.addActionListener(e -> graph.setGraphMode(GraphMode.NONE));
        exit.addActionListener(e -> System.exit(0));

        modeMenu.add(addVertex);
        modeMenu.add(addEdge);
        modeMenu.add(none);
        modeMenu.addSeparator();
        modeMenu.add(exit);
    }
}
