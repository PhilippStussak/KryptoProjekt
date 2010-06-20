/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;

/**
 *
 * @author Stefan
 */
public class Connection {

    private Kit parent, child;

    public Connection(Kit parent, Kit child) {
        this.parent = parent;
        this.child = child;
    }

    public Kit getParent() {
        return parent;
    }

    public Kit getChild() {
        return child;
    }

    public Shape getArrow() {
        GeneralPath path = new GeneralPath();
        path.moveTo(parent.getX(), parent.getY());
        path.lineTo(child.getX(), child.getY());
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Connection) {
            Connection con = (Connection) o;
            if (this.parent == con.parent && this.child == con.child
                    || this.child == con.parent && this.child == con.parent) {
                return true;
            }
        }
        return false;
    }
}
