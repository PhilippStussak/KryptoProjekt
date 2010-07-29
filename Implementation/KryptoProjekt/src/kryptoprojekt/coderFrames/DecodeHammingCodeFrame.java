/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DecodeHammingCodeFrame.java
 *
 * Created on 27.06.2010, 11:35:24
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

/**
 *
 * @author LiTTle, Mario
 */
public class DecodeHammingCodeFrame extends Kit {

    private DropTextField hcField = getDropTextField();
    private JCheckBox correctWordCeckbox = new JCheckBox(Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "correctWordCeckbox"));

    /** Creates new form DecodeHammingCodeFrame */
    public DecodeHammingCodeFrame(ConnectionHandler handler) {
        super(handler);
        initComponents();
        initLogicComponents();
        jLabel1.setText(Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "HeaderLabel"));
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
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(DecodeHammingCodeFrame.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jPanel1.add(new JLabel("HammingCode Element"), c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        jPanel1.add(hcField, c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        jPanel1.add(getDragList(new Object[]{getTitle() + "_hcElem"}), c);

        final DragList corrDecodedWord = getDragList(new Object[]{getTitle() + "_corrected decoded word"});
        correctWordCeckbox.addItemListener(
                new ItemListener() {

                    public void itemStateChanged(ItemEvent e) {
                        // Set "ignore" whenever box is checked or unchecked.
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            corrDecodedWord.setEnabled(true);
                        } else {
                            corrDecodedWord.setEnabled(false);
                        }
                    }
                });

        c.weighty = 3;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        jPanel1.add(correctWordCeckbox, c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 6;
        jPanel1.add(getDragList(new Object[]{getTitle() + "_decoded word"}), c);

        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 8;
        jPanel1.add(corrDecodedWord, c);
        corrDecodedWord.setEnabled(false);

        this.setSize(200, 200);
    }

    @Override
    public String execute() {
        try {
            HammingCode result = CoderController.decodeHammingCode((HammingCode) hcField.getResult());

            results.put(getTitle() + "_hcElem", result);
            results.put(getTitle() + "_decoded word", result.getDecodedWord());
            if (correctWordCeckbox.isSelected()) {
                if (result.isErrorsFound()) {
                    results.put(getTitle() + "_corrected decoded word", result.getCorrectedDecodedWord());
                    return Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "DecodedWord") + result.getDecodedWord() + "\n"
                            + Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "ErrorInEncodedWord")
                            + result.getEncodedWord() + Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "AtPosition") + result.getErrorPos() + "\n"
                            + Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "CorrectedDecodedWord") + result.getCorrectedDecodedWord();
                } else {
                    results.put(getTitle() + "_corrected decoded word", result.getDecodedWord());
                    return (Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "NoErrorsCreated") + "\n"
                            + Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "DecodedWord") + result.getDecodedWord());
                }
            }
            return Kit.xmlReader.getTagElement("DecodeHammingCodeFrame", "DecodedWord") + result.getDecodedWord();
        } catch (RuntimeException r) {
            return Kit.xmlReader.getTagElement("HammingCode", r.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
