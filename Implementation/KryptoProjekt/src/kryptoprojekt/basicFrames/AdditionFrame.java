/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddFrame.java
 *
 * Created on 20.06.2010, 17:08:56
 */

package kryptoprojekt.basicFrames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import kryptoprojekt.controller.BasicController;
import kryptoprojekt.model.KryptoType;

/**
 *
 * @author Stefan
 */
public class AdditionFrame extends Kit {

    private DropTextField textField1 = getDropTextField();
    private DropTextField textField2 = getDropTextField();

    /** Creates new form AddFrame */
    public AdditionFrame(ConnectionHandler handler) {
        super(handler);
        initComponents();
        initLogicComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("addition");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initLogicComponents() {
        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0.495;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        jPanel1.add(textField1, c);

        c.weightx = 0.01;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        jPanel1.add(new JLabel("+"), c);

        c.weightx = 0.495;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 0;
        jPanel1.add(textField2, c);

        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        jPanel1.add(getDragList(new Object[] {getTitle() + "_sum"}), c);

        this.setSize(160, 120);
    }

    @Override
    public String execute() {
        KryptoType result = BasicController.addition((KryptoType)textField1.getResult(), (KryptoType)textField2.getResult());
        results.put(getTitle() + "_sum", result);
        return "In Window " + getTitle() + ": " + (KryptoType)textField1.getResult() + " + " + (KryptoType)textField2.getResult() + " = " + result.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
