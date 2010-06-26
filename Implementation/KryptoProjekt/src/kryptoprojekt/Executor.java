/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JTextArea;

/**
 *
 * @author Stefan
 */
public class Executor extends Thread {

    LinkedList<Kit> orderOfExecution = new LinkedList<Kit>();
    JTextArea area;

    public Executor(ConnectionHandler handler, JTextArea area) {
        LinkedList<Kit> frames = new LinkedList<Kit>();
        for(Kit kit : handler.getFrames())
            frames.add(kit);
        this.area = area;
        for(Kit kit : frames)
            if(kit.getChildren().size() == 0)
                insert(kit);
    }

    private void insert(Kit k) {
        if(k.getParents().size() == 0) {
            orderOfExecution.add(k);
            return ;
        }
        else {
            for(Kit kit : k.getParents())
                insert(kit);
            orderOfExecution.add(k);
        }
    }

    public void run() {
        for(Kit kit : orderOfExecution)
            area.setText(area.getText() + "\n" + kit.execute());
    }

}
