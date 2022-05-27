package visualizer;

import javax.swing.*;
import java.awt.*;

public class Vertex extends JPanel {
    private static final int SIZE = 50;
    private final JLabel label = new JLabel();
    // private final int diffX = 734;  // for corner vertices apply x = (i % 2) * diffX
    // private final int diffY = 511;   // for corner vertices apply y = (i / 2) * diffY

    public Vertex(int x, int y, char id) {
        super.setName("Vertex " + id);
        setLayout(new GridBagLayout());
        setBounds(x, y, SIZE, SIZE);


        label.setName("VertexLabel " + id);
        label.setText(String.valueOf(id));
        label.setFont(new Font("SansSerif", Font.BOLD, 40));
        label.setForeground(Color.BLACK);
        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, SIZE, SIZE);
    }

    void setLabelText(String labelText) {
        label.setText(labelText);
    }
}
