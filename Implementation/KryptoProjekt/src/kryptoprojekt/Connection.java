/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 *
 * @author Stefan
 */
public class Connection {

    private Kit parent, child;
    private Kit.DropTextField drop;

    public Connection(Kit parent, Kit child, Kit.DropTextField drop) {
        this.parent = parent;
        this.child = child;
        this.drop = drop;
    }

    public Kit getParent() {
        return parent;
    }

    public Kit getChild() {
        return child;
    }

    public Kit.DropTextField getDrop() {
        return drop;
    }

    public void drawArrow(Graphics2D g2d) {
        int px, py, cx, cy;
        int parentMidX = parent.getX() + parent.getWidth() / 2;
        int parentMidY = parent.getY() + parent.getHeight() / 2;
        int childMidX = child.getX() + child.getWidth() / 2;
        int childMidY = child.getY() + child.getHeight() / 2;
        int dX = Math.abs(parentMidX - childMidX);
        int dY = Math.abs(parentMidY - childMidY);
        if (parentMidX < childMidX) {
            if (parentMidY < childMidY) {
                if (dX < dY) {
                    drawArrow(g2d, parentMidX, parent.getY() + parent.getHeight(), childMidX, child.getY(), (float) (Math.PI / 4));
                } else {
                    drawArrow(g2d, parent.getX() + parent.getWidth(), parentMidY, child.getX(), childMidY, (float) (Math.PI / 4));
                }
            } else {
                if (dX < dY) {
                    drawArrow(g2d, parentMidX, parent.getY(), childMidX, child.getY() + child.getHeight(), (float) (Math.PI / 4));
                } else {
                    drawArrow(g2d, parent.getX() + parent.getWidth(), parentMidY, child.getX(), childMidY, (float) (Math.PI / 4));
                }
            }
        } else {
            if (parentMidY < childMidY) {
                if (dX < dY) {
                    drawArrow(g2d, parentMidX, parent.getY() + parent.getHeight(), childMidX, child.getY(), (float) (Math.PI / 4));
                } else {
                    drawArrow(g2d, parent.getX(), parentMidY, child.getX() + child.getWidth(), childMidY, (float) (Math.PI / 4));
                }
            } else {
                if (dX < dY) {
                    drawArrow(g2d, parentMidX, parent.getY(), childMidX, child.getY() + child.getHeight(), (float) (Math.PI / 4));
                } else {
                    drawArrow(g2d, parent.getX(), parentMidY, child.getX() + child.getWidth(), childMidY, (float) (Math.PI / 4));
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Connection) {
            Connection con = (Connection) o;
            if (this.drop == con.drop) {
                if (this.parent == con.parent && this.child == con.child
                        || this.child == con.parent && this.child == con.parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean sameTarget(Connection other) {
        return this.drop == other.drop;
    }

    private void drawArrow(Graphics2D g2d, int xCenter, int yCenter, int x, int y, float stroke) {
        double aDir = Math.atan2(xCenter - x, yCenter - y);
        g2d.drawLine(x, y, xCenter, yCenter);
        g2d.setStroke(new BasicStroke(1f));
        Polygon tmpPoly = new Polygon();
        int i1 = 12 + (int) (stroke * 2);
        int i2 = 6 + (int) stroke;
        tmpPoly.addPoint(x, y);
        tmpPoly.addPoint(x + xCor(i1, aDir + .5), y + yCor(i1, aDir + .5));
        tmpPoly.addPoint(x + xCor(i2, aDir), y + yCor(i2, aDir));
        tmpPoly.addPoint(x + xCor(i1, aDir - .5), y + yCor(i1, aDir - .5));
        tmpPoly.addPoint(x, y);
        g2d.drawPolygon(tmpPoly);
        g2d.fillPolygon(tmpPoly);
    }

    private static int yCor(int len, double dir) {
        return (int) (len * Math.cos(dir));
    }

    private static int xCor(int len, double dir) {
        return (int) (len * Math.sin(dir));
    }
}
