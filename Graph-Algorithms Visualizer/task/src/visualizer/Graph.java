package visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Graph extends JPanel implements MouseListener {
    private final JFrame parent;

    public Graph(JFrame parent, LayoutManager layout, String name) {
        super(layout);
        this.parent = parent;
        setName(name);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int diffX = -25;
        int diffY = -25;
        Vertex vertex = VertexCreator.createVertex(this, e.getX() + diffX, e.getY() + diffY);
        add(vertex);
        paintComponents(getGraphics());
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
