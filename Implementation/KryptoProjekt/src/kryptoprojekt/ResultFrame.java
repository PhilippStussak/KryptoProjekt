/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Stefan
 */
class ResultFrame extends JInternalFrame {

    private static int id = 1;
    private JTextArea textArea = new JTextArea("");
    private JScrollPane scrollPane = new JScrollPane(textArea);
    private JPanel panel = new JPanel(new BorderLayout());

    public ResultFrame() {
        super("result #" + id++, true, true, true, true);
        panel.add(scrollPane);
        this.getContentPane().add(panel);
    }

    public void addText(String text) {
        String oldText = textArea.getText();
        textArea.setText(oldText.equals("") ? text : (oldText + "\n" + text));
    }

    public void clearText() {
        textArea.setText("");
    }
}
