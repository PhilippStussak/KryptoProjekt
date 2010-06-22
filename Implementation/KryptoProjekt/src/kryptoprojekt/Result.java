/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

/**
 *
 * @author Stefan
 */
public class Result {

    private Object result = null;
    private boolean isComputed = false;

    public Result() {
    }

    public synchronized void setResult(Object result) {
        this.result = result;
        isComputed = true;
        notify();
    }

    public synchronized Object getResult() {
        while (!isComputed) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        notify();
        return result;
    }
}
