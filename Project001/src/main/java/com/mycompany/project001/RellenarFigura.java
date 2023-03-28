package com.mycompany.project001;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class RellenarFigura extends JPanel implements MouseListener {

    private Point[] vertices = new Point[4];
    private BufferedImage imagen;
    private Color colorFigura = Color.BLUE;

    public RellenarFigura() {
        super();
        addMouseListener(this);
        imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    private void rellenarFigura() {
        // Encontrar la coordenada y mínima y máxima de los vértices
        int yMin = vertices[0].y;
        int yMax = vertices[0].y;
        for (int i = 1; i < vertices.length; i++) {
            if (vertices[i].y < yMin) {
                yMin = vertices[i].y;
            }
            if (vertices[i].y > yMax) {
                yMax = vertices[i].y;
            }
        }

        // Escanear cada fila de la figura y encontrar las intersecciones
        // entre la fila y los lados de la figura
        for (int yCoord = yMin; yCoord <= yMax; yCoord++) {
            int interseccionIzquierda = -1;
            int interseccionDerecha = -1;

            for (int i = 0; i < vertices.length; i++) {
                int j = (i + 1) % vertices.length;

                if (vertices[i].y < yCoord && vertices[j].y >= yCoord
                        || vertices[j].y < yCoord && vertices[i].y >= yCoord) {
                    double xCoord = vertices[i].x + (double) (yCoord - vertices[i].y)
                            * (vertices[j].x - vertices[i].x) / (vertices[j].y - vertices[i].y);

                    if (interseccionIzquierda == -1 || xCoord < interseccionIzquierda) {
                        interseccionIzquierda = (int) xCoord;
                    }
                    if (interseccionDerecha == -1 || xCoord > interseccionDerecha) {
                        interseccionDerecha = (int) xCoord;
                    }
                }
            }

            // Llenar los píxeles entre las intersecciones
            for (int xCoord = interseccionIzquierda; xCoord <= interseccionDerecha; xCoord++) {
                imagen.setRGB(xCoord, yCoord, colorFigura.getRGB());
            }
        }

        // Repintar el panel
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (vertices[0] == null) {
                vertices[0] = e.getPoint();
            } else if (vertices[1] == null) {
                vertices[1] = e.getPoint();
            } else if (vertices[2] == null) {
                vertices[2] = e.getPoint();
            } else if (vertices[3] == null) {
                vertices[3] = e.getPoint();
                rellenarFigura();
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            // Cambiar el color de la figura al hacer clic con el botón derecho del ratón
            colorFigura = JColorChooser.showDialog(this, "Elige un color", colorFigura);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // No se usa
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // No se usa
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // No se usa
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // No se usa
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Rellenar figura");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(500, 500);
        RellenarFigura panel = new RellenarFigura();
        ventana.add(panel);
        ventana.setVisible(true);
    }

}

