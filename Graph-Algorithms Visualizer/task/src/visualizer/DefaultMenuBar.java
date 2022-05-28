package visualizer;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class DefaultMenuBar extends JMenuBar {
    private final MainFrame mainFrame;
    private final JMenu modeMenu = new JMenu("Mode");
    private final JMenuItem addVertex = new JMenuItem(Mode.ADD_VERTEX.getName());
    private final JMenuItem addEdge = new JMenuItem(Mode.ADD_EDGE.getName());
    private final JMenuItem none = new JMenuItem(Mode.NONE_MODE.getName());
    private final JMenuItem exit = new JMenuItem("Exit");

    DefaultMenuBar(MainFrame mainFrame) {
        mainFrame.setJMenuBar(this);
        this.mainFrame = mainFrame;
        initComponents();
    }

    void initComponents() {
        modeMenu.setMnemonic(KeyEvent.VK_Q);
        add(modeMenu);

        addVertex.setName("Add a Vertex");
        addEdge.setName("Add an Edge");
        none.setName("None");

        addVertex.addActionListener(e -> mainFrame.setMode(Mode.ADD_VERTEX));
        addEdge.addActionListener(e -> mainFrame.setMode(Mode.ADD_EDGE));
        none.addActionListener(e -> mainFrame.setMode(Mode.NONE_MODE));
        exit.addActionListener(e -> System.exit(0));

        modeMenu.add(addVertex);
        modeMenu.add(addEdge);
        modeMenu.add(none);
        modeMenu.addSeparator();
        modeMenu.add(exit);
    }
}
