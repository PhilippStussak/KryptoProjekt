/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.Shape;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Stefan
 */
public class ConnectionHandler {

    private LinkedList<Kit> frames = new LinkedList<Kit>();
    private LinkedList<Connection> connections = new LinkedList<Connection>();

    public void add(Kit kit) {
        frames.add(kit);
    }

    public boolean remove(Kit kit) {
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
        System.out.println(frames);
        return true;
    }

    public boolean remove(Connection connection) {
        return connections.remove(connection);
    }

    public LinkedList<Shape> getConnectionArrows() {
        LinkedList<Shape> result = new LinkedList<Shape>();
        for (Connection c : connections) {
            result.add(c.getArrow());
        }
        return result;
    }

    public boolean removeSameTarget(Connection con) {
        Iterator<Connection> it = connections.iterator();
        while (it.hasNext()) {
            Connection connection = it.next();
            if (connection.sameTarget(con)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}
