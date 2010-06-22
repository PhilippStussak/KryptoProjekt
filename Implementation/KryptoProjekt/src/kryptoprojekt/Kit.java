/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
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
    protected Result[] result;

    public Kit(ConnectionHandler handler) {
        super("#" + id++);
        this.handler = handler;
        handler.add(this);
    }

    public Result getResult(int id) {
        return this.result[id];
    }

    public JList getDragList(Object[] list) {
        return new DragList(list, this);
    }

    public JTextField getDropTextField() {
        return new DropTextField(this);
    }

    public void execute() {
        throw new UnsupportedOperationException();
    }

    class DragList extends JList implements DragGestureListener {

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

    class DropTextField extends JTextField implements DropTargetListener {

        private Kit origin;

        public DropTextField(Kit origin) {
            this.origin = origin;
            new DropTarget(this, this);
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
                        handler.removeSameTarget(con);
                        if (!handler.add(con)) {
                            JOptionPane.showMessageDialog(null, "Connection already exists!");
                        } else {
                            dtde.acceptDrop(DnDConstants.ACTION_COPY);
                            this.setText((String) ta.getTransferData(DataFlavor.stringFlavor));
                            dtde.getDropTargetContext().dropComplete(true);
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
    }

    public String toString() {
        return getTitle();
    }
}
