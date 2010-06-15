/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import javax.swing.JDesktopPane;

/**
 *
 * @author Stefan
 */
public class Desktop extends JDesktopPane {

    LinkedList<Connection> connections;

    public Desktop() {
        connections = new LinkedList<Connection>();
        this.setAlignmentX(CENTER_ALIGNMENT);
        this.setAlignmentY(CENTER_ALIGNMENT);
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        for (Connection connection : connections) {
            ((Graphics2D) g).draw(connection.getArrow());
        }
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
    }

    public void removeConnection(Connection connection) {
        connections.remove(connection);
    }
}
