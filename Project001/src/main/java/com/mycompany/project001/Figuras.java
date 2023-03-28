package com.mycompany.project001;

/**
 *
 * @author Sebastian Lemus Rodrguez y Valeria Tellez Anaya
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;




public class Figuras extends JFrame implements ActionListener {

    private JRadioButtonMenuItem rectangleItem, circleItem, triangleItem;
    private JPanel drawingPanel;
    private Color lineColor = Color.BLACK;

    public Figuras() {
        
        
        super("Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JMenu menu = new JMenu("Menu");
        JMenu archivo = new JMenu("Archivo");
        JMenu editar = new JMenu("Editar");
        JMenu ayuda = new JMenu("?");
       

        //Creacion de la barra de menú
        JMenu figuraMenu = new JMenu("Figura");
        rectangleItem = new JRadioButtonMenuItem("Rectangulo");
        circleItem = new JRadioButtonMenuItem("Circulo");
        triangleItem = new JRadioButtonMenuItem("Triangulo");
        ButtonGroup group = new ButtonGroup();
        group.add(rectangleItem);
        group.add(circleItem);
        group.add(triangleItem);
        rectangleItem.addActionListener(this);
        circleItem.addActionListener(this);
        triangleItem.addActionListener(this);
        figuraMenu.add(rectangleItem);
        figuraMenu.add(circleItem);
        figuraMenu.add(triangleItem);

        //Apartado de herramientas
        JMenu herramientaMenu = new JMenu("Herramienta");
        JMenuItem colorItem = new JMenuItem("Color");
        colorItem.addActionListener(this);
        herramientaMenu.add(colorItem);
        herramientaMenu.addSeparator();
        herramientaMenu.add(figuraMenu);
        
        JMenu ayudaMenu = new JMenu("?");
        JMenuItem versionItem= new JMenu("Version 1.0.0.");
        versionItem.addActionListener(this);
        ayudaMenu.add(versionItem);

        //Apartado del menu principal 
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(archivo);
        menuBar.add(editar);
        menuBar.add(herramientaMenu);
        menuBar.add(ayudaMenu);
        setJMenuBar(menuBar);
       


        //Aquí los paneles de dibujos 
        drawingPanel = new JPanel();
        drawingPanel.setPreferredSize(new Dimension(300, 300));
        drawingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setContentPane(drawingPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Rectangulo")) {
            //Rectángulo
            drawingPanel.removeAll();
            drawingPanel.add(new RectanglePanel());
            drawingPanel.revalidate();
            drawingPanel.repaint();
        } else if (e.getActionCommand().equals("Circulo")) {
            //Círculo
            drawingPanel.removeAll();
            drawingPanel.add(new CirclePanel());
            drawingPanel.revalidate();
            drawingPanel.repaint();
        } else if (e.getActionCommand().equals("Triangulo")) {
            //Triángulo
            drawingPanel.removeAll();
            drawingPanel.add(new TrianglePanel());
            drawingPanel.revalidate();
            drawingPanel.repaint();
        } else if (e.getActionCommand().equals("Color")) {
            //Apartado para poder cambiar el color de la linea
            Color newColor = JColorChooser.showDialog(this, "Seleccione un color", lineColor);
            if (newColor != null) {
                lineColor = newColor;
                drawingPanel.repaint();
            }
        }
    }

    
    private class RectanglePanel extends JPanel {
        public RectanglePanel() {
            setPreferredSize(new Dimension(100, 100));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(lineColor); 
            g.drawRect(10, 10, 80, 80);
        }
    }

    private class CirclePanel extends JPanel {
        public CirclePanel() {
            setPreferredSize(new Dimension(100, 100));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(lineColor);
            g.drawOval(10, 10, 80, 80);
        }
    }

   
    private class TrianglePanel extends JPanel {
        public TrianglePanel() {
            setPreferredSize(new Dimension(100, 100));
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(lineColor); 
            int[] xPoints = {50, 10, 90};
            int[] yPoints = {10, 90, 90};
            g.drawPolygon(xPoints, yPoints, 3);
        }
    }
     
    public static void main(String[] args) {
        new Figuras();
    }
}


        

        