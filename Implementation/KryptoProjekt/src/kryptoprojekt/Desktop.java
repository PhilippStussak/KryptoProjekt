/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JDesktopPane;

/**
 *
 * @author Stefan, Phil
 */
public class Desktop extends JDesktopPane {

    private ConnectionHandler handler;
    private Image image;

    public Desktop(ConnectionHandler handler) {
        this.handler = handler;
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
        this.setDoubleBuffered(true);
        try {
            URL url = new URL(getClass().getResource("resources/panelBackground.png").toString());
            InputStream stream = url.openStream();
            image = javax.imageio.ImageIO.read(stream);
        } catch (Exception e) {
        }
        this.setBackground(Color.white);
        handler.setDesktop(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        if (image != null) {
            g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        }
        handler.drawArrows(g2d);
    }

    @Override
    public Component add(Component c) {
        super.add(c);
        moveToFront(c);
        return c;
    }
}
