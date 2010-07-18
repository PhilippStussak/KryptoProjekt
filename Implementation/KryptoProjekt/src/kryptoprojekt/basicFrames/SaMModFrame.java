/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SaMlModFrame.java
 *
 * Created on 27.06.2010, 11:47:21
 */

package kryptoprojekt.basicFrames;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import kryptoprojekt.ResultFrame;
import kryptoprojekt.controller.LogicValidator;
import kryptoprojekt.model.Basic;
import kryptoprojekt.model.KryptoType;
import kryptoprojekt.model.Tuple;
import kryptoprojekt.model.Z;

/**
 *
 * @author phil
 */
public class SaMModFrame extends Kit {

    private DropTextField textField1 = getDropTextField();
    private DropTextField textField2 = getDropTextField();
    private DropTextField textField3 = getDropTextField();
    String extension = "";

    /** Creates new form SaMlModFrame */
    public SaMModFrame(ConnectionHandler handler) {
        super(handler);
        initComponents();
        initLogicComponents();
    }

    private void initLogicComponents() {

        textField1.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (LogicValidator.isInteger(textField1.getText())) {
                    textField1.setForeground(Color.black);
                } else {
                    textField1.setForeground(Color.red);
                }
            }
        });

        textField2.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (LogicValidator.isInteger(textField2.getText())) {
                    textField2.setForeground(Color.black);
                } else {
                    textField2.setForeground(Color.red);
                }
            }
        });

        textField3.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (LogicValidator.isInteger(textField3.getText())) {
                    textField3.setForeground(Color.black);
                } else {
                    textField3.setForeground(Color.red);
                }
            }
        });
        
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 0.49;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        jPanel1.add(textField1, c);

        c.weightx = 0.02;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        jPanel1.add(new JLabel("^"), c);

        c.weightx = 0.49;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        jPanel1.add(textField2, c);

        c.weightx = 0.30;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        jPanel1.add(new JLabel("Mod:"), c);
        
        c.weightx = 0.70;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        jPanel1.add(textField3, c);
        
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        jPanel1.add(getDragList(new Object[] {getTitle() + "_SaMMod"}), c);

        this.setSize(250, 150);
    }

    @Override
    public String execute() {
        KryptoType value1, value2, value3;
        if(textField1.getResult() != null)
            value1 = (KryptoType)textField1.getResult();
        else
            value1 = new Z(textField1.getText());
        if(textField2.getResult() != null)
            value2 = (KryptoType)textField2.getResult();
        else
            value2 = new Z(textField2.getText());
        if(textField3.getResult() != null)
            value3 = (KryptoType)textField3.getResult();
        else
            value3 = new Z(textField3.getText());
        Tuple result = Basic.squareAndMultiply(value1, value2, value3);
        results.put(getTitle() + "_SaMMod", result.first());
        extension = "";
        for(String s : (LinkedList<String>)result.second())
            extension += s + "\n";
        return value1.toString() + " ^ " + value2.toString() + " mod " + value3.toString() + " = " + result.first().toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(SaMModFrame.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ResultFrame frame = new ResultFrame(getTitle() + "_extension");
        frame.setLocation(getX(), getY());
        frame.setSize(320, 240);
        frame.addText(extension);
        frame.setVisible(true);
        getParent().add(frame);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
