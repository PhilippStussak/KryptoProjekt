/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author Stefan
 */
public class Executor extends Thread {

    private LinkedList<Kit> orderOfExecution = new LinkedList<Kit>();
    private ResultFrame rf;
    private JProgressBar progressBar;
    private double progress = 0;
    private double timeslot;

    public Executor(ConnectionHandler handler, ResultFrame rf, JProgressBar progressBar) {
        progressBar.setStringPainted(true);
        LinkedList<Kit> frames = new LinkedList<Kit>();
        for (Kit kit : handler.getFrames()) {
            frames.add(kit);
        }
        this.rf = rf;
        this.progressBar = progressBar;
        for (Kit kit : frames) {
            if (kit.getChildren().size() == 0) {
                insert(kit);
            }
        }
        timeslot = 100. / orderOfExecution.size();
        this.setDaemon(true);
    }

    private void insert(Kit k) {
        if (k.getParents().size() == 0) {
            if(!orderOfExecution.contains(k))
                orderOfExecution.add(k);
            return;
        } else {
            for (Kit kit : k.getParents()) {
                insert(kit);
            }
            if(!orderOfExecution.contains(k))
                orderOfExecution.add(k);
        }
    }

    @Override
    public void run() {
        final long time = System.currentTimeMillis();
        Kit recentKit = null;
        for (Kit kit : orderOfExecution) {
            try {
                recentKit = kit;
                progressBar((int)(progress += timeslot), kit.getTitle(), true);
                progressMessage(kit.getTitle(), kit.execute());
            } catch (NullPointerException npe) {
                exception(Kit.xmlReader.getTagElement("Executor", "MissingParamException") + " " + kit.getTitle());
            } catch (ClassCastException cce) {
                exception(Kit.xmlReader.getTagElement("Executor", "WrongParam") + " " + kit.getTitle() + "!");
            } catch (Exception e) {
                exception(Kit.xmlReader.getTagElement("Executor", "UndefinedException") + kit.getTitle() + "!");
            }
        }
        progressBar(100, "", true);
        progressMessage(recentKit.getTitle(), Kit.xmlReader.getTagElement("Executor", "ComputeTime") + ": " + (System.currentTimeMillis() - time));
        progressBar(100, "", false);
    }

    private void progressMessage(final String kitTitle, final String progressMessage) {
        try {
            EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    rf.addText(Kit.xmlReader.getTagElement("Executor", "InWindow") + " " + kitTitle + ": \n" + progressMessage);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void progressBar(final int progress, final String text, final boolean visible) {
        try {
            EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    progressBar.setVisible(visible);
                    progressBar.setValue(progress);
                    progressBar.setString(text);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exception(final String message) {
        try {
            EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    JOptionPane.showMessageDialog(rf, message);
                }
            });
        } catch (InterruptedException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Executor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
