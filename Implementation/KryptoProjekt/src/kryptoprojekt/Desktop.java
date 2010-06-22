/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import javax.swing.JDesktopPane;

/**
 *
 * @author Stefan
 */
public class Desktop extends JDesktopPane {

    ConnectionHandler handler;

    public Desktop(ConnectionHandler handler) {
        this.handler = handler;
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
        for (Shape arrow : handler.getConnectionArrows())
            g2.draw(arrow);
        repaint();
    }
    
}
