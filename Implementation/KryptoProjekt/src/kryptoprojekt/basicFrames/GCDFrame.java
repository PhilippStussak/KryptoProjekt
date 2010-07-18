/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GCDFrame.java
 *
 * Created on 27.06.2010, 12:26:13
 */
package kryptoprojekt.basicFrames;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import kryptoprojekt.ResultFrame;
import kryptoprojekt.controller.BasicController;
import kryptoprojekt.controller.LogicValidator;
import kryptoprojekt.model.KryptoType;
import kryptoprojekt.model.Tuple;
import kryptoprojekt.model.Z;

/**
 *
 * @author phil
 */
public class GCDFrame extends Kit {

    private DropTextField textField1 = getDropTextField();
    private DropTextField textField2 = getDropTextField();
    private String extension = "";

    /** Creates new form GCDFrame */
    public GCDFrame(ConnectionHandler handler) {
        super(handler);
        initComponents();
        initLogicComponents();
        jLabel1.setText(Kit.xmlReader.getTagElement("GCDFrame", "HeaderLabel"));
        jButton1.setText(Kit.xmlReader.getTagElement("GCDFrame", "ExtendBtn"));
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

        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.04;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        jPanel1.add(new JLabel(Kit.xmlReader.getTagElement("GCDFrame", "GCD") + "("), c);

        c.weightx = 0.46;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        jPanel1.add(textField1, c);

        c.weightx = 0.02;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        jPanel1.add(new JLabel(" , "), c);

        c.weightx = 0.46;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 0;
        jPanel1.add(textField2, c);

        c.weightx = 0.04;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 4;
        c.gridy = 0;
        jPanel1.add(new JLabel(")"), c);

        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 1;
        jPanel1.add(getDragList(new Object[]{getTitle() + "_gcd"}), c);

        this.setSize(160, 120);
    }

    @Override
    public String execute() {
        KryptoType value1, value2;
        if(textField1.getResult() != null)
            value1 = (KryptoType)textField1.getResult();
        else
            value1 = new Z(textField1.getText());
        if(textField2.getResult() != null)
            value2 = (KryptoType)textField2.getResult();
        else
            value2 = new Z(textField2.getText());

        Tuple result = BasicController.calculateGCD(value1, value2);
        results.put(getTitle() + "_gcd", result.first());
        extension = "";
        for(Object[] o : (LinkedList<Object[]>)result.second())
            extension += o[0] + " = " + o[1] + " * " + o[2] + " + " + o[3] + "\n";
        return Kit.xmlReader.getTagElement("GCDFrame", "GCD") + "(" + value1.toString() + ", " + value2.toString() + ") = " +  result.first().toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(GCDFrame.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
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
