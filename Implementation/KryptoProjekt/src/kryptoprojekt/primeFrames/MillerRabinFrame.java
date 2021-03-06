/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MillerRabinFrame.java
 *
 * Created on 01.07.2010, 00:44:00
 */
package kryptoprojekt.primeFrames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.TreeSet;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import java.util.ArrayList;
import kryptoprojekt.model.KryptoType;
import kryptoprojekt.model.Z;
import kryptoprojekt.model.PrimeUtility;
import kryptoprojekt.model.Triple;
import kryptoprojekt.controller.PrimeTestController;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import kryptoprojekt.controller.LogicValidator;
import kryptoprojekt.controller.XMLReader;

/**
 *
 * @author Michael
 */
public class MillerRabinFrame extends Kit {

    private DropTextField basesTextField = getDropTextField();
    private DropTextField moduloTextField = getDropTextField();
    private Integer previousValue; //includes the value of the spinnbox
    private JCheckBox probabilityCB;
    private JCheckBox randomBasesCB;
    private JLabel randomSPLabel;
    private JSpinner randomNumberSP;
    private LinkedList<String> extendList;
    private LinkedList<LinkedList<String>> extension;
    private StringBuilder outputWindow;
    private StyledDocument doc;
    private boolean calcProb;
    private boolean correctModulsArguments;
    private boolean correctBasesArguments;

    /** Creates new form MillerRabinFrame */
    public MillerRabinFrame(ConnectionHandler handler) {
        super(handler);
        initComponents();
        initLogicComponents();
        initializeControlsLanguage(Kit.xmlReader);
        ExtendBtMiller.setText(Kit.xmlReader.getTagElement("MillerRabinFrame", "ExtendBtn"));
    }

    private void initializeControlsLanguage(XMLReader xml) {
        //startMenuItem.setText(xml.getTagElement("KryptoView", "startMenuItem"));
        //basicMenu.setText(xml.getTagElement("KryptoView", "basicMenu"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        millerRabinTestLabel = new javax.swing.JLabel();
        jPanelPrimeMiller = new javax.swing.JPanel();
        ExtendBtMiller = new javax.swing.JButton();
        jPanelSettingsMiller = new javax.swing.JPanel();
        jPanelDropListMiller = new javax.swing.JPanel();

        setClosable(true);
        setResizable(true);
        setDoubleBuffered(true);
        setMinimumSize(new java.awt.Dimension(295, 210));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(295, 210));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                extendButtonClick(evt);
            }
        });

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(MillerRabinFrame.class);
        millerRabinTestLabel.setText(resourceMap.getString("millerRabinTestLabel.text")); // NOI18N
        millerRabinTestLabel.setName("millerRabinTestLabel"); // NOI18N

        jPanelPrimeMiller.setName("jPanelPrimeMiller"); // NOI18N

        javax.swing.GroupLayout jPanelPrimeMillerLayout = new javax.swing.GroupLayout(jPanelPrimeMiller);
        jPanelPrimeMiller.setLayout(jPanelPrimeMillerLayout);
        jPanelPrimeMillerLayout.setHorizontalGroup(
            jPanelPrimeMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 124, Short.MAX_VALUE)
        );
        jPanelPrimeMillerLayout.setVerticalGroup(
            jPanelPrimeMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        ExtendBtMiller.setText(resourceMap.getString("ExtendBtMiller.text")); // NOI18N
        ExtendBtMiller.setAlignmentX(0.5F);
        ExtendBtMiller.setName("ExtendBtMiller"); // NOI18N
        ExtendBtMiller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExtendBtMillerActionPerformed(evt);
            }
        });

        jPanelSettingsMiller.setMinimumSize(new java.awt.Dimension(125, 97));
        jPanelSettingsMiller.setName("jPanelSettingsMiller"); // NOI18N
        jPanelSettingsMiller.setPreferredSize(new java.awt.Dimension(125, 183));

        javax.swing.GroupLayout jPanelSettingsMillerLayout = new javax.swing.GroupLayout(jPanelSettingsMiller);
        jPanelSettingsMiller.setLayout(jPanelSettingsMillerLayout);
        jPanelSettingsMillerLayout.setHorizontalGroup(
            jPanelSettingsMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );
        jPanelSettingsMillerLayout.setVerticalGroup(
            jPanelSettingsMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        jPanelDropListMiller.setName("jPanelDropListMiller"); // NOI18N

        javax.swing.GroupLayout jPanelDropListMillerLayout = new javax.swing.GroupLayout(jPanelDropListMiller);
        jPanelDropListMiller.setLayout(jPanelDropListMillerLayout);
        jPanelDropListMillerLayout.setHorizontalGroup(
            jPanelDropListMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 259, Short.MAX_VALUE)
        );
        jPanelDropListMillerLayout.setVerticalGroup(
            jPanelDropListMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDropListMiller, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelPrimeMiller, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelSettingsMiller, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(millerRabinTestLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExtendBtMiller)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExtendBtMiller)
                    .addComponent(millerRabinTestLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSettingsMiller, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jPanelPrimeMiller, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDropListMiller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void extendButtonClick(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_extendButtonClick
    }//GEN-LAST:event_extendButtonClick

    private void ExtendBtMillerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExtendBtMillerActionPerformed
        JInternalFrame frame = new JInternalFrame(getTitle() + "_extension", true, true, true, true);
        frame.setLocation(getX(), getY());
        frame.setSize(400, 340);

        JTextPane millerRabinPane = new JTextPane() {

            @Override
            public boolean getScrollableTracksViewportWidth() {
                return false;
            }
        };
        millerRabinPane.setEditable(false);
        doc = millerRabinPane.getStyledDocument();
        Style defaultStyle = doc.getStyle("default");
        Style intermediateHeadStyle = doc.addStyle("outputHead", defaultStyle);
        StyleConstants.setFontSize(intermediateHeadStyle, StyleConstants.getFontSize(intermediateHeadStyle) + 1);
        StyleConstants.setBold(intermediateHeadStyle, true);
        if (extension != null) {
            for (LinkedList<String> linkedStringList : extension) {
                append(linkedStringList.pollFirst() + "\n", intermediateHeadStyle.getName());
                for (String intermediateValues : linkedStringList) {
                    append(intermediateValues + "\n", defaultStyle.getName());
                }
                append("\n", defaultStyle.getName());
            }
        }
        millerRabinPane.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(millerRabinPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(Color.white);
        frame.add(scrollPane);
        frame.setVisible(true);
        getParent().add(frame);
}//GEN-LAST:event_ExtendBtMillerActionPerformed

    private void append(String text, String styleName) {
        try {
            doc.insertString(doc.getLength(), text, doc.getStyle(styleName));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void initLogicComponents() {
        jPanelPrimeMiller.setLayout(new GridBagLayout());
        jPanelSettingsMiller.setLayout(new GridBagLayout());
        jPanelDropListMiller.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setSize(295, 210);

        moduloTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if(validateModuloTextField(getModulTextFieldValue())){
                    moduloTextField.setForeground(Color.black);
                    correctModulsArguments = true;
                    if(randomBasesCB.isSelected()){
                        setRandomBasesField();
                        setMaxSpinnBox();
                    }
                }else{
                    moduloTextField.setForeground(Color.red);
                    correctModulsArguments = false;
                    basesTextField.setText("");
                }
            }
        });

        basesTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if(validateBaseTextField(getBaseTextFieldValue())){
                    basesTextField.setForeground(Color.black);
                    correctBasesArguments = true;
                }else{
                    basesTextField.setForeground(Color.red);
                    correctBasesArguments = false;
                }
            }
        });

        //JPanelPrime
        //prime input
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        JLabel primeLabel = new JLabel();
        primeLabel.setText(Kit.xmlReader.getTagElement("PrimeFrames", "CheckWetherPrimes") + ":");
        jPanelPrimeMiller.add(primeLabel, c);

        c = new GridBagConstraints();
        c.ipadx = 80;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        jPanelPrimeMiller.add(moduloTextField, c);

        //base input
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(5, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 0;
        JLabel baseLabel = new JLabel();
        baseLabel.setText(Kit.xmlReader.getTagElement("PrimeFrames", "Bases") + ":");
        jPanelPrimeMiller.add(baseLabel, c);

        c = new GridBagConstraints();
        c.ipadx = 80;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        jPanelPrimeMiller.add(basesTextField, c);

        //JPanelDropList
        //drop List
        c = new GridBagConstraints();
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        jPanelDropListMiller.add(getDragList(new Object[]{getTitle() + "_primeMillerRabin"}), c);

        //JPanelSettings
        Border settingsBorder = BorderFactory.createTitledBorder(Kit.xmlReader.getTagElement("PrimeFrames", "Settings"));
        jPanelSettingsMiller.setBorder(settingsBorder);

        //checkbox Probability
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0;
        probabilityCB = new JCheckBox(Kit.xmlReader.getTagElement("PrimeFrames", "CalcProbability"), true);
        calcProb = true;
        probabilityCB.setFont(probabilityCB.getFont().deriveFont(probabilityCB.getFont().getSize2D() - 0.6f));
        probabilityCB.setHorizontalAlignment(JLabel.LEFT);
        probabilityCB.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    calcProb = false;
                } else {
                    calcProb = true;
                }
            }
        });
        jPanelSettingsMiller.add(probabilityCB, c);

        //random spinner label number
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(3, 20, 0, 0);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 0.5;
        randomSPLabel = new JLabel();
        randomSPLabel.setFont(randomSPLabel.getFont().deriveFont(randomSPLabel.getFont().getSize2D() - 0.6f));
        randomSPLabel.setText(Kit.xmlReader.getTagElement("PrimeFrames", "RandNumber"));
        randomSPLabel.setEnabled(false);
        jPanelSettingsMiller.add(randomSPLabel, c);

        //spinner number of random bases
        c = new GridBagConstraints();
        c.ipadx = 20;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0, 3, 0, 0);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0;
        randomNumberSP = new JSpinner(new SpinnerNumberModel(3, 0, 9999, 1));
        randomNumberSP.setFont(randomNumberSP.getFont().deriveFont(randomNumberSP.getFont().getSize2D() - 0.6f));
        randomNumberSP.setEnabled(false);
        randomNumberSP.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(correctModulsArguments){
                    assert randomNumberSP.getModel() instanceof SpinnerNumberModel: "Error, Spinnbox isn't instance of SpinnerNumberModel. randomNumberSP.";
                    final JTextField spinnTextField = ((JSpinner.DefaultEditor) randomNumberSP.getEditor()).getTextField();
                    final SpinnerNumberModel maxBaseSpinner = (SpinnerNumberModel)randomNumberSP.getModel();
                    previousValue = (Integer)randomNumberSP.getValue();
                    setMaxSpinnBox();
                    if (new Integer(deleteChar(spinnTextField.getText(), ".")) > ((Integer)maxBaseSpinner.getMaximum())){
                        //spinnTextField.setText((String)maxBaseSpinner.getMaximum().toString());
                        randomNumberSP.setValue(new Integer(maxBaseSpinner.getMaximum().toString()));
                    }
                    setRandomBasesField();
                }
            }
        });
        assert randomNumberSP.getModel() instanceof SpinnerNumberModel: "Error, Spinnbox isn't instance of SpinnerNumberModel. randomNumberSP.";
        final JTextField spinnTextField = ((JSpinner.DefaultEditor) randomNumberSP.getEditor()).getTextField();
        final SpinnerNumberModel maxBaseSpinner = (SpinnerNumberModel)randomNumberSP.getModel();
        previousValue = (Integer)randomNumberSP.getValue();
        spinnTextField.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if (new Integer(spinnTextField.getText()) <= ((Integer)maxBaseSpinner.getMaximum())){
                        if(correctModulsArguments){
                            Integer newValue = new Integer(deleteChar(spinnTextField.getText(), "."));
                            if((newValue >= previousValue) && (newValue >= new Integer(getModulTextFieldSet().first().toString())) || newValue<0){
                                spinnTextField.setText(Integer.toString(previousValue));
                                //randomNumberSP.setValue(previousValue);
                            } else{
                                previousValue = newValue;
                            }
                        }
                    } else{
                        spinnTextField.setText(maxBaseSpinner.getMaximum().toString());
                        //randomNumberSP.setValue((Integer)maxBaseSpinner.getMaximum());
                    }
                }
            }
            public void keyReleased(KeyEvent e){
            }
            public void keyTyped(KeyEvent e){
            }
        });
        jPanelSettingsMiller.add(randomNumberSP, c);

        //checkbox random bases
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0;
        randomBasesCB = new JCheckBox(Kit.xmlReader.getTagElement("PrimeFrames", "RandBases"), false);
        randomBasesCB.setFont(randomBasesCB.getFont().deriveFont(randomBasesCB.getFont().getSize2D() - 0.6f));
        randomBasesCB.setHorizontalAlignment(JLabel.LEFT);
        randomBasesCB.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    basesTextField.setEnabled(false);
                    basesTextField.setText("");
                    randomSPLabel.setEnabled(true);
                    randomNumberSP.setEnabled(true);
                    if(correctModulsArguments){
                        setMaxSpinnBox();
                        setRandomBasesField();
                    }
                    correctBasesArguments = true;
                } else {
                    basesTextField.setEnabled(true);
                    basesTextField.setText("");
                    randomSPLabel.setEnabled(false);
                    randomNumberSP.setEnabled(false);
                    correctBasesArguments = false;
                }
            }
        });
        jPanelSettingsMiller.add(randomBasesCB, c);
    }

        private boolean validateModuloTextField(String moduloTextFieldString){
        StringBuilder numbSequence = new StringBuilder(moduloTextFieldString);
        int dashPos = 0;
        boolean checkOther = true;

        int assertCounter = 0;
        if (dashPos != -1) {
            for (int k = 0; k < numbSequence.length() && k >= 0;) {
                assert assertCounter < moduloTextFieldString.length() : "Too many iterations.";
                dashPos = numbSequence.indexOf("-", k);
                k = -1;
                if (dashPos > 0 && dashPos < numbSequence.length() - 1) {
                    if (LogicValidator.isPosInteger(String.valueOf(numbSequence.charAt(dashPos - 1))) && LogicValidator.isPosInteger(String.valueOf(numbSequence.charAt(dashPos + 1)))) {
                        numbSequence = numbSequence.deleteCharAt(dashPos);
                        k = dashPos;
                    } else {
                        dashPos = -1; //wrong parameter
                        checkOther = false;
                    }
                } else if (dashPos == 0) {
                    dashPos = -1; //wrong parameter
                    checkOther = false;
                }
                assertCounter++;
            }
            assertCounter = 0;
        }
        if (checkOther) {
            numbSequence = deleteChar(numbSequence, ","); //delete
            numbSequence = deleteChar(numbSequence, "."); //delete
            numbSequence = deleteChar(numbSequence, " "); //delete
        }
        if (LogicValidator.isPosInteger(numbSequence.toString())) {
            return true;

        } else {
            return false;
        }
    }

    private boolean validateBaseTextField(String baseTextFieldString){
        StringBuilder numbSequence = new StringBuilder(baseTextFieldString);
        int dashPos = 0;
        boolean checkOther = true;

        int assertCounter = 0;
        if (dashPos != -1) {
            for (int k = 0; k < numbSequence.length() && k >= 0;) {
                assert assertCounter < baseTextFieldString.length() : "Too many iterations.";
                dashPos = numbSequence.indexOf("-", k);
                k = -1;
                if (dashPos > 0 && dashPos < numbSequence.length() - 1) {
                    if (LogicValidator.isPosInteger(String.valueOf(numbSequence.charAt(dashPos - 1))) && LogicValidator.isPosInteger(String.valueOf(numbSequence.charAt(dashPos + 1)))) {
                        numbSequence = numbSequence.deleteCharAt(dashPos);
                        k = dashPos;
                    } else {
                        dashPos = -1; //wrong parameter
                        checkOther = false;
                    }
                } else if (dashPos == 0) {
                    dashPos = -1; //wrong parameter
                    checkOther = false;
                }
                assertCounter++;
            }
            assertCounter = 0;
        }
        if (checkOther) {
            numbSequence = deleteChar(numbSequence, ","); //delete
            numbSequence = deleteChar(numbSequence, "."); //delete
            numbSequence = deleteChar(numbSequence, " "); //delete
        }
        if (LogicValidator.isPosInteger(numbSequence.toString())) {
            return true;
        } else {
            return false;
        }
    }

    //set the max value you can enter in the spinn box depending on the moduls
    private void setMaxSpinnBox(){
        TreeSet<KryptoType> moduls = getModulTextFieldSet();
        int newModul = new Integer(moduls.first().toString());
        if(newModul == 2){
            Iterator<KryptoType> itModuls = moduls.iterator();
            itModuls.next();
            if(itModuls.hasNext()){
                newModul = new Integer(itModuls.next().toString());
            }
        }
        setMaxSpinnBox(newModul-2);
    }

    private void setMaxSpinnBox(int newMaxSpineBase){
        //Precondition
        assert randomNumberSP.getModel() instanceof SpinnerNumberModel: "Error, Spinnbox isn't instance of SpinnerNumberModel.";
        SpinnerNumberModel newMaxBaseSpinner = (SpinnerNumberModel)randomNumberSP.getModel();
        if(newMaxSpineBase >=0 && newMaxSpineBase <= 9999){
            newMaxBaseSpinner.setMaximum(new Integer(newMaxSpineBase));
            randomNumberSP.setModel(newMaxBaseSpinner);
        } else {
            if (newMaxSpineBase >=0) {
                newMaxBaseSpinner.setMaximum(new Integer(9999));
                randomNumberSP.setModel(newMaxBaseSpinner);
            }
        }
    }

    //sets the base values when using the random checkbox
    private boolean setRandomBasesField(){
        //Precondition
        assert correctModulsArguments :"Error, Moduls arguments have a false state. moduls = " +correctModulsArguments;
        TreeSet<KryptoType> moduls = getModulTextFieldSet();
        if (!moduls.isEmpty() && correctModulsArguments) {
            Integer numberRandomBases = (Integer) randomNumberSP.getValue();
            basesTextField.setText("");
            if (numberRandomBases > 0 && numberRandomBases <= 9999) {
                //max. number for a randomly generated base
                int endRandBaseRange;
                if (Integer.parseInt(moduls.first().toString()) == 2) {
                    Iterator<KryptoType> itModuls = moduls.iterator();
                    itModuls.next();
                    if (itModuls.hasNext()) {
                        endRandBaseRange = Integer.parseInt(itModuls.next().toString());
                        int maxProbNumbers = endRandBaseRange - 2;
                        numberRandomBases = numberRandomBases < maxProbNumbers ? numberRandomBases : maxProbNumbers;
                    } else {
                        endRandBaseRange = 2;
                        numberRandomBases = 1;
                    }
                } else {
                    endRandBaseRange = Integer.parseInt(moduls.first().toString());
                    int maxProbNumbers = endRandBaseRange - 2;
                    numberRandomBases = numberRandomBases < maxProbNumbers ? numberRandomBases : maxProbNumbers;
                }
                LinkedList<Integer> randomBases = new LinkedList<Integer>(getRandomBases(2, endRandBaseRange, numberRandomBases));
                StringBuilder randBasesString = new StringBuilder("");
                int randomBasesSize = randomBases.size();
                for (int i = 0; i < randomBasesSize; i++) {
                    randBasesString.append(randomBases.get(i));
                    if(i < randomBasesSize-1){
                        randBasesString.append(", ");
                    }
                }
                basesTextField.setText(randBasesString.toString());
                return true;
            }
        }
        return false;
    }

    //returns the value of the moduls as String
    private String getModulTextFieldValue(){
        return moduloTextField.getText();
    }

    //returns the value of the moduls as sorted List
    private TreeSet<KryptoType> getModulTextFieldSet(){
        return new TreeSet<KryptoType>(splitInputToZ(getModulTextFieldValue()));
    }

    //returns the value of the bases as String
    private String getBaseTextFieldValue(){
        return basesTextField.getText();
    }

    //deleted the passed char out of the given string
    private StringBuilder deleteChar(StringBuilder originalString, String delChar, int fromIndex) {
        StringBuilder withoutCharString = new StringBuilder(originalString);
        int charPosition = withoutCharString.indexOf(delChar, fromIndex);
        while (charPosition >= 0) {
            withoutCharString = withoutCharString.deleteCharAt(charPosition);
            charPosition = withoutCharString.indexOf(delChar, charPosition);
        }
        return withoutCharString;
    }

    private StringBuilder deleteChar(StringBuilder originalString, String delChar) {
        return deleteChar(originalString, delChar, 0);
    }

    private String deleteChar(String originalString, String delChar) {
        return deleteChar(new StringBuilder(originalString), delChar, 0).toString();
    }

    //splits the series of numbers in an ArrayList
    private ArrayList<KryptoType> splitInputToZ(String splitMe) {
        //Precondition
        assert splitMe.length()!=0: "Error, String is empty. splitMe = " +splitMe;
        assert correctModulsArguments || correctBasesArguments !=false: "Error, moduls or bases arguments have a false state. moduls = " +correctModulsArguments+ " bases =" +correctBasesArguments;
        Pattern baseModulSeparator = Pattern.compile("(([,]+[\\s]*)+|([\\s]+[,]*)+)"); //split an input list of bases and moduls(primes)
        Pattern dashSeparator = Pattern.compile("[\\-]");
        StringBuilder numbSequence = new StringBuilder(splitMe);
        int delPointPos = 0;

        //removes all points from the series of number
        for (int i = 0; i < numbSequence.length() && i >= 0;) {
            delPointPos = numbSequence.indexOf(".", i);
            if (delPointPos != -1) {
                numbSequence = numbSequence.deleteCharAt(delPointPos);
            }
            i = delPointPos;
        }
        String[] result = baseModulSeparator.split(numbSequence);
        ArrayList<KryptoType> resultZ = new ArrayList<KryptoType>();
        for (String s : result) {
            if(s.equals("")){
                continue;
            }
            if (s.contains("-")) {
                String[] range = dashSeparator.split(s);
                resultZ.addAll(fillKryptoTypeZList(range));
                continue;
            }
            resultZ.add(new Z(s));
        }
        return resultZ;
    }

    //fills a list of Z from start (first argument in array) to end (second argument in array)
    private ArrayList<Z> fillKryptoTypeZList(String[] range) {
        //Precondition
        assert range.length == 2 : "Error, the array has more than 2 elements: " + Arrays.toString(range);
        ArrayList<Z> listKrypto = new ArrayList<Z>();
        int first = Integer.valueOf(range[0]);
        int second = Integer.valueOf(range[1]);
        int start, end;

        if (first <= second) {
            start = first;
            end = second;
        } else {
            start = second;
            end = first;
        }
        while (start <= end) {
            listKrypto.add(new Z(start));
            start++;
        }
        return listKrypto;
    }

    //returns a list of random numbers (between start and end)
    private LinkedList<Integer> getRandomBases(int startBaseRange, int endRandBaseRange, int numberRandomBases){
        return new LinkedList<Integer>(PrimeUtility.getRandomNumber(startBaseRange, endRandBaseRange, numberRandomBases));
    }

    @Override
    public String execute() {
        ArrayList<KryptoType> bases = new ArrayList<KryptoType>();
        ArrayList<KryptoType> moduls = new ArrayList<KryptoType>();
        ArrayList<Triple<Boolean, Double, LinkedList<String>>> result; //contains for each number whether it is a prime, probability, intermediate
        LinkedList<KryptoType> posResults = new LinkedList<KryptoType>();

        //verify the parameters from the TextFields (moduloTextField & basesTextField)
        if (moduloTextField.getResult() != null) {
            if(validateModuloTextField(getModulTextFieldValue()) && validateBaseTextField(getBaseTextFieldValue())){
                moduls = splitInputToZ(moduloTextField.getResult().toString());
                //moduls.add((KryptoType) moduloTextField.getResult());
            } else{
                return correctModulsArguments ? Kit.xmlReader.getTagElement("PrimeFrames", "InvalidBase") :
                        Kit.xmlReader.getTagElement("PrimeFrames", "InvalidNumber");
            }
        } else if(correctModulsArguments && getModulTextFieldValue().length() != 0){
            moduls = splitInputToZ(getModulTextFieldValue());
        } else{
            return Kit.xmlReader.getTagElement("PrimeFrames", "NumberGreater1Required");
        }

        if (basesTextField.getResult() != null) {
            if(validateBaseTextField(getBaseTextFieldValue()) && validateModuloTextField(getModulTextFieldValue())){
                bases = splitInputToZ(basesTextField.getResult().toString());
                //bases.add((KryptoType) basesTextField.getResult());
            } else{
                return correctBasesArguments ? Kit.xmlReader.getTagElement("PrimeFrames", "InvalidNumber") :
                        Kit.xmlReader.getTagElement("PrimeFrames", "InvalidBase");
            }
        } else if(correctBasesArguments && getBaseTextFieldValue().length() != 0){
            bases = splitInputToZ(getBaseTextFieldValue());
        } else if(getModulTextFieldValue().equals("2")){
            ArrayList<KryptoType> two = new ArrayList<KryptoType>();
            two.add(new Z("2"));
            bases = two;
        } else{
            return Kit.xmlReader.getTagElement("PrimeFrames", "BaseGreater1Required");
        }
        //end of the verification

        try {
            //starts the Miller-Rabin-Test
            result = PrimeTestController.primeTestRabin(bases, moduls, calcProb);
        } catch (RuntimeException e) {
            return e.getMessage();
        } catch (NoSuchMethodException e) {
            return e.getMessage();
        } catch (InstantiationException e) {
            return e.getMessage();
        } catch (IllegalAccessException e) {
            return e.getMessage();
        } catch (InvocationTargetException e) {
            return e.getMessage();
        }

        extendList = new LinkedList<String>();
        extension = new LinkedList<LinkedList<String>>();
        outputWindow = new StringBuilder();
        int i = 0;
        String probability = "";
        for (Triple<Boolean, Double, LinkedList<String>> output : result) {
            if (output.second() == -1.0) {
                probability = "";
            } else {
                double probDouble = output.second() * 100;
                if(output.first() == true && probDouble == 100){
                     probability = "    " + Kit.xmlReader.getTagElement("PrimeFrames", "Probability") + " = <" + String.valueOf(probDouble) + "%";
                }else{
                     probability = "    " + Kit.xmlReader.getTagElement("PrimeFrames", "Probability") + " = " + String.valueOf(probDouble) + "%";
                }
            }
            if (output.first() == true) {
                posResults.add(moduls.get(i));
            }
            extendList = output.third();
            extendList.addFirst(moduls.get(i) + ":");
            extendList.addLast(Kit.xmlReader.getTagElement("PrimeFrames", "Result"));
            extendList.addLast(moduls.get(i) + " " + Kit.xmlReader.getTagElement("PrimeFrames", "IsPrimeNumber") + ": " + output.first() + probability);
            extension.add(extendList);

            outputWindow.append(moduls.get(i) + ": " + result.get(i).first() + "\n");
            i++;
        }
        results.put(getTitle() + "_primeMillerRabin", posResults);
        return Kit.xmlReader.getTagElement("PrimeFrames", "PrimeNumbers") + ":\n" + outputWindow.toString();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExtendBtMiller;
    private javax.swing.JPanel jPanelDropListMiller;
    private javax.swing.JPanel jPanelPrimeMiller;
    private javax.swing.JPanel jPanelSettingsMiller;
    private javax.swing.JLabel millerRabinTestLabel;
    // End of variables declaration//GEN-END:variables
}