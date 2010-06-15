/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.Shape;
import java.awt.geom.GeneralPath;

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

    public Shape getArrow() {
        GeneralPath arrow = new GeneralPath();
        arrow.moveTo(parent.getX(), parent.getY());
        arrow.lineTo(child.getX(), child.getY());
        return arrow;
    }
}
