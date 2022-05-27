package visualizer;

import javax.swing.*;
import java.awt.*;

public class Vertex extends JPanel {
    private static final int SIZE = 50;
    private final JLabel label = new JLabel();


    public Vertex(String vertexName, String labelName, String labelText, int i) {
        super.setName(vertexName);
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        int diffX = 734;
        int diffY = 511;
        setBounds((i % 2) * diffX, (i / 2) * diffY, SIZE, SIZE);

        label.setName(labelName);
        label.setText(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 40));
        label.setForeground(Color.BLACK);

        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(0, 0, SIZE, SIZE);
    }

    void setLabelText(String labelText) {
        label.setText(labelText);
    }
}
