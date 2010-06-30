/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InitHammingJFrame.java
 *
 * Created on 27.06.2010, 11:19:09
 */

package kryptoprojekt.coderFrames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import kryptoprojekt.controller.CoderController;
import kryptoprojekt.model.HammingCode;
import kryptoprojekt.model.Matrix;
import kryptoprojekt.model.PrimeFieldElement;

/**
 *
 * @author mario
 */
public class InitHammingJFrame extends Kit {


    private DropTextField textGeneratorMatrix = getDropTextField();
    private DropTextField textSourceCodeword = getDropTextField();
    private JCheckBox enableMatrix = new JCheckBox("add own generator matrix");



    /** Creates new form InitHammingJFrame */
    public InitHammingJFrame(ConnectionHandler handler) {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(InitHammingJFrame.class);
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
            .addGap(0, 258, Short.MAX_VALUE)
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

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        jPanel1.add(new JLabel("source codeword"), c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        jPanel1.add(textSourceCodeword, c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        jPanel1.add(getDragList(new Object[] {getTitle() + "source codeword"}), c);
        
        textGeneratorMatrix.setEnabled(false);

        enableMatrix.addItemListener(
                new ItemListener() {

                    public void itemStateChanged(ItemEvent e) {
                        // Set "ignore" whenever box is checked or unchecked.
                       if(e.getStateChange() == ItemEvent.SELECTED)
                           textGeneratorMatrix.setEnabled(true);
                       else
                           textGeneratorMatrix.setEnabled(false);
                    }
                });


        c.weighty = 3;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        jPanel1.add(enableMatrix, c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 4;
        jPanel1.add(new JLabel("generator matrix"), c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 5;
        jPanel1.add(textGeneratorMatrix, c);


        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 6;
        jPanel1.add(getDragList(new Object[] {getTitle() + "generatorMatrix"}), c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 7;
        jPanel1.add(getDragList(new Object[] {getTitle() + "hammingObject"}), c);
        

        this.setSize(180, 200);
    }

    @Override
    public String execute() {
        Matrix<PrimeFieldElement> generatorM = null;
        HammingCode hc = null;
        if (enableMatrix.isSelected()) {
            hc = CoderController.initHammingCode(true, generatorM, (String)textSourceCodeword.getText());
        } else {
            hc = CoderController.initHammingCode(false, null, (String)textSourceCodeword.getText());
        }
        results.put(getTitle() + "hammingObject", hc);
        results.put(getTitle() + "generatorMatrix", textGeneratorMatrix);
        results.put(getTitle() + "source codeword", textSourceCodeword);

        return hc.toString();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
