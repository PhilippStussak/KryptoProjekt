/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MatrixPrimeFieldFrame.java
 *
 * Created on 18.07.2010, 15:18:05
 */
package kryptoprojekt.matrixFrames;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JList;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import kryptoprojekt.ResultFrame;
import kryptoprojekt.model.Matrix;
import kryptoprojekt.model.PrimeFieldElement;

/**
 *
 * @author Stefan
 */
public class MatrixPrimeFieldFrame extends Kit {

    /** Creates new form MatrixPrimeFieldFrame */
    public MatrixPrimeFieldFrame(ConnectionHandler handler) {
        super(handler);
        initComponents();
        initLogicComponents();
        headerLbl.setText(Kit.xmlReader.getTagElement("MatrixPrimeFieldFrame", "HeaderLabel"));
        jButton1.setText(Kit.xmlReader.getTagElement("MatrixPrimeFieldFrame", "ExtendBtn"));
        jLabel2.setText(Kit.xmlReader.getTagElement("MatrixPrimeFieldFrame", "PrimefieldLabel"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerLbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(MatrixPrimeFieldFrame.class);
        headerLbl.setText(resourceMap.getString("headerLbl.text")); // NOI18N
        headerLbl.setName("headerLbl"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(headerLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(headerLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (results.get(getTitle() + "_matrix") != null) {
            ResultFrame frame = new ResultFrame(getTitle() + "_extension");
            frame.setLocation(getX(), getY());
            frame.setSize(320, 240);
            frame.addText(((Matrix<PrimeFieldElement>) results.get(getTitle() + "_matrix")).toStringWithIndex());
            frame.setVisible(true);
            getParent().add(frame);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        setMatrix();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        setMatrix();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void setMatrix() {
        try {
            results.put(getTitle() + "_matrix", Matrix.valueOf(jTextField1.getText(), jTextField2.getText()));
            jTextField1.setForeground(Color.black);
            jTextField2.setForeground(Color.black);
        } catch (Exception e) {
            results.put(getTitle() + "_matrix", null);
            jTextField1.setForeground(Color.red);
            jTextField2.setForeground(Color.red);
        }
    }

    private void initLogicComponents() {
        jTextField1.setTransferHandler(null);
        JList list = getDragList(new Object[]{getTitle() + "_matrix"});
        results.put(getTitle() + "_matrix", null);
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(list);
        this.setSize(300, 160);
    }

    @Override
    public String execute() {
        return results.get(getTitle() + "_matrix").toString();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel headerLbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}