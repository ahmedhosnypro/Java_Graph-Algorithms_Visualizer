package visualizer;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class DefaultMenuBar extends JMenuBar {
    private final MainFrame mainFrame;
    private final JMenu fileMenu = new JMenu("File");
    private final JMenu modeMenu = new JMenu("Mode");
    private final JMenu algorithmsMenu = new JMenu("Algorithms");
    private final JMenuItem addVertex = new JMenuItem(Mode.ADD_VERTEX.getName());
    private final JMenuItem addEdge = new JMenuItem(Mode.ADD_EDGE.getName());
    private final JMenuItem removeVertex = new JMenuItem(Mode.REMOVE_VERTEX.getName());
    private final JMenuItem removeEdge = new JMenuItem(Mode.REMOVE_EDGE.getName());
    private final JMenuItem newMenuItem = new JMenuItem("New");
    private final JMenuItem noneModeMenuItem = new JMenuItem(Mode.NONE_MODE.getName());
    private final JMenuItem DFS_menuItem = new JMenuItem("Depth-First Search");
    private final JMenuItem BFS_menuItem = new JMenuItem("Breadth-First Search");
    private final JMenuItem dijkstraAlgorithmMenuItem = new JMenuItem("Dijkstra's Algorithm");
    private final JMenuItem exit = new JMenuItem("Exit");

    DefaultMenuBar(MainFrame mainFrame) {
        mainFrame.setJMenuBar(this);
        this.mainFrame = mainFrame;
        initComponents();
    }

    void initComponents() {
        fileMenu.setName("File");
        add(fileMenu);

        newMenuItem.setName("New");
        newMenuItem.addActionListener(e -> mainFrame.getGraph().reset());

        exit.setName("Exit");
        exit.addActionListener(e -> System.exit(0));

        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        modeMenu.setName("Mode");
        modeMenu.setMnemonic(KeyEvent.VK_Q);
        add(modeMenu);


        addVertex.setName("Add a Vertex");
        addEdge.setName("Add an Edge");
        removeVertex.setName("Remove a Vertex");
        removeEdge.setName("Remove an Edge");
        noneModeMenuItem.setName("None");

        addVertex.addActionListener(e -> mainFrame.setMode(Mode.ADD_VERTEX));
        addEdge.addActionListener(e -> mainFrame.setMode(Mode.ADD_EDGE));
        removeVertex.addActionListener(e -> mainFrame.setMode(Mode.REMOVE_VERTEX));
        removeEdge.addActionListener(e -> mainFrame.setMode(Mode.REMOVE_EDGE));
        noneModeMenuItem.addActionListener(e -> mainFrame.setMode(Mode.NONE_MODE));

        modeMenu.add(addVertex);
        modeMenu.add(addEdge);
        modeMenu.add(removeVertex);
        modeMenu.add(removeEdge);
        modeMenu.add(noneModeMenuItem);

        algorithmsMenu.setName("Algorithms");
        DFS_menuItem.setName("Depth-First Search");
        BFS_menuItem.setName("Breadth-First Search");
        dijkstraAlgorithmMenuItem.setName("Dijkstra's Algorithm");

        DFS_menuItem.addActionListener(e -> mainFrame.setMode(Mode.DFS_ALGORITHM));
        BFS_menuItem.addActionListener(e -> mainFrame.setMode(Mode.BFS_ALGORITHM));
        dijkstraAlgorithmMenuItem.addActionListener(e -> mainFrame.setMode(Mode.DIJKSTRA_ALGORITHM));

        algorithmsMenu.add(DFS_menuItem);
        algorithmsMenu.add(BFS_menuItem);
        algorithmsMenu.add(dijkstraAlgorithmMenuItem);

        add(algorithmsMenu);
    }
}
