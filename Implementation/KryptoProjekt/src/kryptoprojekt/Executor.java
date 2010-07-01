/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Stefan
 */
public class Executor extends Thread {

    LinkedList<Kit> orderOfExecution = new LinkedList<Kit>();
    ResultFrame rf;

    public Executor(ConnectionHandler handler, ResultFrame rf) {
        LinkedList<Kit> frames = new LinkedList<Kit>();
        for (Kit kit : handler.getFrames()) {
            frames.add(kit);
        }
        this.rf = rf;
        for (Kit kit : frames) {
            if (kit.getChildren().size() == 0) {
                insert(kit);
            }
        }
    }

    private void insert(Kit k) {
        if (k.getParents().size() == 0) {
            orderOfExecution.add(k);
            return;
        } else {
            for (Kit kit : k.getParents()) {
                insert(kit);
            }
            orderOfExecution.add(k);
        }
    }

    public void run() {
        for (Kit kit : orderOfExecution) {
            try {
            rf.addText(kit.execute());
            } catch(NullPointerException npe) {
                JOptionPane.showMessageDialog(rf, "Wrong parameters in Window " + kit.getTitle());
            } catch(Exception e) {
                JOptionPane.showMessageDialog(rf, e.getMessage());
            }
        }
    }
}
