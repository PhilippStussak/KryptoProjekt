/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Stefan
 */
public class ConnectionHandler {

    private LinkedList<Kit> frames = new LinkedList<Kit>();
    private LinkedList<Connection> connections = new LinkedList<Connection>();
    private Desktop desktop;

    public Desktop getDesktop() {
        return desktop;
    }

    public void setDesktop(Desktop desktop) {
        this.desktop = desktop;
    }

    public LinkedList<Kit> getFrames() {
        return frames;
    }

    public void add(Kit kit) {
        frames.add(kit);
    }

    public boolean remove(Kit kit) {
        for (Kit k : kit.getParents()) {
            k.getChildren().remove(kit);
        }
        for (Kit k : kit.getChildren()) {
            k.getParents().remove(kit);
        }
        Iterator<Connection> iterator = connections.iterator();
        while (iterator.hasNext()) {
            Connection con = iterator.next();
            if (con.getChild() == kit || con.getParent() == kit) {
                con.getDrop().removeConnection();
                iterator.remove();
            }
        }

        return frames.remove(kit);
    }

    public boolean add(Connection connection) {
        if (connections.contains(connection)) {
            return false;
        }
        connections.add(connection);
        LinkedList<Kit> newOrder = new LinkedList<Kit>();
        if (frames.indexOf(connection.getParent()) < frames.indexOf(connection.getChild())) {
            return true;
        }
        for (Kit kit : frames) {
            if (kit == connection.getChild()) {
                newOrder.add(connection.getParent());
                newOrder.add(kit);
            } else if (kit != connection.getParent()) {
                newOrder.add(kit);
            }
        }
        frames = newOrder;
        return true;
    }

    public boolean remove(Connection connection) {
        return connections.remove(connection);
    }

    public void drawArrows(Graphics2D g2d) {
        for (Connection c : connections) {
            c.drawArrow(g2d);
        }
    }

    public Connection removeSameTarget(Connection con) {
        Iterator<Connection> it = connections.iterator();
        while (it.hasNext()) {
            Connection connection = it.next();
            if (connection.sameTarget(con)) {
                it.remove();
                return connection;
            }
        }
        return null;
    }
}
