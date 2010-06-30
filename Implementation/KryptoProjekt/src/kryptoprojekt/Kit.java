/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Stefan
 */
public class Kit extends JInternalFrame {

    protected ConnectionHandler handler;
    private static int id = 1;
    protected HashMap<String, Object> results;
    protected LinkedList<Kit> parents, children;

    public Kit(ConnectionHandler handler) {
        super("#" + id++, false, true, false, false);
        this.handler = handler;
        results = new HashMap<String, Object>();
        parents = new LinkedList<Kit>();
        children = new LinkedList<Kit>();
        handler.add(this);
    }

    public Object getResult(String id) {
        return this.results.get(id);
    }

    public LinkedList<Kit> getParents() {
        return parents;
    }

    public HashMap<String, Object> getResults() {
        return results;
    }

    public LinkedList<Kit> getChildren() {
        return children;
    }

    public DragList getDragList(Object[] list) {
        return new DragList(list, this);
    }

    public DropTextField getDropTextField() {
        return new DropTextField(this);
    }

    public String execute() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dispose() {
        handler.remove(this);
        super.dispose();
    }

    public class DragList extends JList implements DragGestureListener {

        private Kit origin;

        public DragList(Object[] field, Kit origin) {
            super(field);
            this.setDragEnabled(true);
            this.origin = origin;
            new DragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY, this);
        }

        public void dragGestureRecognized(DragGestureEvent dge) {
            parent = origin;
        }
    }
    private static Kit parent;

    public class DropTextField extends JTextField implements DropTargetListener {

        private Kit origin, p;
        private String key;

        public DropTextField(Kit origin) {
            this.origin = origin;
            new DropTarget(this, this);
            this.addKeyListener(new KeyListener() {

                public void keyTyped(KeyEvent e) {
                    keyTypedAction();
                    setColor(Color.black);
                }

                public void keyPressed(KeyEvent e) {
                }

                public void keyReleased(KeyEvent e) {
                }
            });
        }

        public void setColor(Color c) {
            this.setForeground(c);
        }

        public void keyTypedAction() {
            p = null;
            key = null;
            handler.removeSameTarget(new Connection(null, null, (DropTextField) this));
        }

        public Object getResult() {
            if(p == null)
                return null;
            return p.getResult(key);
        }

        public void removeConnection() {
            key = null;
            p = null;
        }

        public void dragEnter(DropTargetDragEvent dtde) {
        }

        public void dragOver(DropTargetDragEvent dtde) {
        }

        public void dropActionChanged(DropTargetDragEvent dtde) {
        }

        public void dragExit(DropTargetEvent dte) {
        }

        public void drop(DropTargetDropEvent dtde) {
            try {
                Transferable ta = dtde.getTransferable();
                if (ta.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    if (parent == origin) {
                        JOptionPane.showMessageDialog(null, "Drop on same frame not possible!");
                    } else {
                        Connection con = new Connection(parent, origin, this);
                        Connection old = handler.removeSameTarget(con);
                        if (old != null) {
                            origin.parents.remove(old.getParent());
                        }
                        if(cyrcle(parent, origin))
                            JOptionPane.showMessageDialog(null, "Recursion not possible!");
                        else if (!handler.add(con)) {
                            JOptionPane.showMessageDialog(null, "Connection already exists!");
                        } else {
                            dtde.acceptDrop(DnDConstants.ACTION_COPY);
                            String text = (String) ta.getTransferData(DataFlavor.stringFlavor);
                            this.setText(text);
                            dtde.getDropTargetContext().dropComplete(true);
                            origin.parents.add(parent);
                            parent.children.add(origin);
                            p = parent;
                            key = text;
                            setColor(Color.green);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong data!");
                    dtde.rejectDrop();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                e.printStackTrace();
            }
        }

        private boolean cyrcle(Kit par, Kit ch) {
            if(par == ch)
                return true;
            for(Kit k : par.getParents())
                if(cyrcle(k, ch))
                    return true;
            return false;
        }
    }
}
