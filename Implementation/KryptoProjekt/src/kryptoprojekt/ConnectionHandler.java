/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt;

import java.awt.Shape;
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
        if(connections.contains(connection))
            return false;
        connections.add(connection);
        return true;
    }

    public boolean remove(Connection connection) {
        return connections.remove(connection);
    }

    public LinkedList<Shape> getConnectionArrows() {
        LinkedList<Shape> result = new LinkedList<Shape>();
        for(Connection c : connections) {
            result.add(c.getArrow());
        }
        return result;
    }

}
