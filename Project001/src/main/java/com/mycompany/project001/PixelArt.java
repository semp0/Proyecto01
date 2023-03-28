package com.mycompany.project001;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PixelArt extends JPanel implements MouseListener {
    private int x1, y1, x2, y2;
    private boolean primerClick = true;

    public PixelArt() {
        setPreferredSize(new Dimension(400, 400));
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {
        if (primerClick) {
            x1 = e.getX();
            y1 = e.getY();
            primerClick = false;
        } else {
            x2 = e.getX();
            y2 = e.getY();
            primerClick = true;
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e) {}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        if (!primerClick) {
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pixel Art");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PixelArt());
        frame.pack();
        frame.setVisible(true);
    }
}
