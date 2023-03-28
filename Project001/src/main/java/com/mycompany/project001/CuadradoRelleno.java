package com.mycompany.project001;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CuadradoRelleno extends JFrame implements MouseMotionListener {
    private int x1, y1, x2, y2;
    private boolean dibujando = false;
    private JPanel lienzo;
    private Color color = Color.BLACK;

    public CuadradoRelleno() {
        super("Cuadrado Relleno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        lienzo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (dibujando) {
                    g.setColor(color);
                    int x = Math.min(x1, x2);
                    int y = Math.min(y1, y2);
                    int width = Math.abs(x1 - x2);
                    int height = Math.abs(y1 - y2);
                    g.fillRect(x, y, width, height);
                }
            }
        };
        lienzo.setBackground(Color.WHITE);
        lienzo.addMouseMotionListener(this);
        add(lienzo);
        setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        dibujando = true;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        new CuadradoRelleno();
    }
}




