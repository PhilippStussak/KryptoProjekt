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
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import java.util.ArrayList;
import kryptoprojekt.model.KryptoType;
import kryptoprojekt.model.Z;
import kryptoprojekt.model.PrimeTest;
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
    private JCheckBox probabilityCB;
    private JLabel randomSPLabel;
    private JSpinner randomNumberSP;
    private LinkedList<String> extendList;
    private LinkedList<LinkedList<String>> extension;
    private LinkedList<Integer> probBases;
    private StringBuilder outputWindow;
    private StyledDocument doc;
    private Font fontSettings;
    private boolean calcProb; //ob die Wahrscheinlichkeit beim Miller-Rabin-Test berechnet werden soll
    private boolean correctArguments; //zeigt an, ob für Basen und Moduls korrekte Werte übergeben wurden



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
        setDoubleBuffered(true);
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(280, 190));
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
            .addGap(0, 80, Short.MAX_VALUE)
        );
        jPanelPrimeMillerLayout.setVerticalGroup(
            jPanelPrimeMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 97, Short.MAX_VALUE)
        );

        ExtendBtMiller.setText(resourceMap.getString("ExtendBtMiller.text")); // NOI18N
        ExtendBtMiller.setAlignmentX(0.5F);
        ExtendBtMiller.setName("ExtendBtMiller"); // NOI18N
        ExtendBtMiller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExtendBtMillerActionPerformed(evt);
            }
        });

        jPanelSettingsMiller.setMinimumSize(new java.awt.Dimension(120, 97));
        jPanelSettingsMiller.setName("jPanelSettingsMiller"); // NOI18N
        jPanelSettingsMiller.setPreferredSize(new java.awt.Dimension(135, 183));

        javax.swing.GroupLayout jPanelSettingsMillerLayout = new javax.swing.GroupLayout(jPanelSettingsMiller);
        jPanelSettingsMiller.setLayout(jPanelSettingsMillerLayout);
        jPanelSettingsMillerLayout.setHorizontalGroup(
            jPanelSettingsMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanelSettingsMillerLayout.setVerticalGroup(
            jPanelSettingsMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 97, Short.MAX_VALUE)
        );

        jPanelDropListMiller.setName("jPanelDropListMiller"); // NOI18N

        javax.swing.GroupLayout jPanelDropListMillerLayout = new javax.swing.GroupLayout(jPanelDropListMiller);
        jPanelDropListMiller.setLayout(jPanelDropListMillerLayout);
        jPanelDropListMillerLayout.setHorizontalGroup(
            jPanelDropListMillerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelPrimeMiller, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(millerRabinTestLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ExtendBtMiller)
                            .addComponent(jPanelSettingsMiller, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(millerRabinTestLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ExtendBtMiller)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelSettingsMiller, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jPanelPrimeMiller, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDropListMiller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void append(String text, String styleName){
        try{
            doc.insertString(doc.getLength(), text, doc.getStyle(styleName));
        } catch (BadLocationException e) {
            //System.err.println("get Message Ausgabe: Fehler in der Start.java: " +e.getMessage());
            e.printStackTrace();
        }
    }

    private void extendButtonClick(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_extendButtonClick

    }//GEN-LAST:event_extendButtonClick

    private void ExtendBtMillerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExtendBtMillerActionPerformed
        JInternalFrame frame = new JInternalFrame(getTitle() + "_extension", true, true, true, true);
        frame.setLocation(getX(), getY());
        frame.setSize(400, 340);

        JTextPane millerRabinPane = new JTextPane(){
            @Override
            public boolean getScrollableTracksViewportWidth(){
                return false;
            }
        };
        millerRabinPane.setEditable(false);
        doc = millerRabinPane.getStyledDocument();
        Style defaultStyle = doc.getStyle("default");
        Style intermediateHeadStyle = doc.addStyle("outputHead", defaultStyle);
        StyleConstants.setFontSize(intermediateHeadStyle, StyleConstants.getFontSize(intermediateHeadStyle)+1);
        StyleConstants.setBold(intermediateHeadStyle, true);
        if(extension != null){
            for (LinkedList<String> linkedStringList : extension){
                append(linkedStringList.pollFirst()+ "\n", intermediateHeadStyle.getName());
                for (String intermediateValues : linkedStringList){
                    append(intermediateValues+ "\n", defaultStyle.getName());
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

        private void initLogicComponents() {
        jPanelPrimeMiller.setLayout(new GridBagLayout());
        jPanelSettingsMiller.setLayout(new GridBagLayout());
        jPanelDropListMiller.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setSize(260, 210);

        basesTextField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            //checkt ob gültige Eingabe in TextFeld gemacht wurde (Zahlen, Komma, Punkte, Leerzeichen, Bindestriche)
            public void keyReleased(KeyEvent e) {
                StringBuilder numbSequence = new StringBuilder(basesTextField.getText());
                int dashPos = 0;
                boolean checkOther = true;

                //checkt ob vor und hinter dem '-' eine Zahl steht. Wenn nein, markiere bases textfield rot.
                int assertCounter = 0;
                if(dashPos != -1){
                    for(int k = 0; k < numbSequence.length() && k>=0;){
                        assert assertCounter < basesTextField.getText().length(): "Too many iterations.";
                        dashPos = numbSequence.indexOf("-", k);
                        k = -1;
                        if(dashPos >0 && dashPos < numbSequence.length()-1){
                            if(LogicValidator.isPosInteger(String.valueOf(numbSequence.charAt(dashPos-1))) && LogicValidator.isPosInteger(String.valueOf(numbSequence.charAt(dashPos+1)))){
                            numbSequence = numbSequence.deleteCharAt(dashPos); //löscht "-" Zeichen und daher wird positive Int Zahl erkannt
                                 k = dashPos;
                            }else{
                                dashPos = -1; //wrong parameter
                                checkOther = false;
                            }
                        }else if(dashPos == 0){
                            dashPos = -1; //wrong parameter
                            checkOther = false;
                        }
                        assertCounter++;
                    }
                    assertCounter = 0;
                }

                if (checkOther){
                    numbSequence = deleteChar(numbSequence, ","); //alle Kommata löschen
                    numbSequence = deleteChar(numbSequence, "."); //alle Punkte löschen
                    numbSequence = deleteChar(numbSequence, " "); //alle Leerzeichen löschen
                }
                if (LogicValidator.isPosInteger(numbSequence.toString())) {
                    basesTextField.setForeground(Color.black);
                    correctArguments = true;
                } else {
                    basesTextField.setForeground(Color.red);
                    correctArguments = false;
                }
            }
        });

        moduloTextField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            //checkt ob gültige Eingabe in TextFeld gemacht wurde (Zahlen, Komma, Punkte, Leerzeichen, Bindestriche)
            public void keyReleased(KeyEvent e) {
                StringBuilder numbSequence = new StringBuilder(moduloTextField.getText());
                int dashPos = 0;
                boolean checkOther = true;

                //checkt ob vor und hinter dem '-' eine Zahl steht. Wenn nein, markiere modulo textfield rot.
                int assertCounter = 0;
                if(dashPos != -1){
                    for(int k = 0; k < numbSequence.length() && k>=0;){
                        assert assertCounter < moduloTextField.getText().length(): "Too many iterations.";
                        dashPos = numbSequence.indexOf("-", k);
                        k = -1;
                        if(dashPos >0 && dashPos < numbSequence.length()-1){
                            if(LogicValidator.isPosInteger(String.valueOf(numbSequence.charAt(dashPos-1))) && LogicValidator.isPosInteger(String.valueOf(numbSequence.charAt(dashPos+1)))){
                            numbSequence = numbSequence.deleteCharAt(dashPos);
                                 k = dashPos;
                            }else{
                                dashPos = -1; //wrong parameter
                                checkOther = false;
                            }
                        }else if(dashPos == 0){
                            dashPos = -1; //wrong parameter
                            checkOther = false;
                        }
                        assertCounter++;
                    }
                    assertCounter = 0;
                }

                if (checkOther){
                    numbSequence = deleteChar(numbSequence, ","); //alle Kommata löschen
                    numbSequence = deleteChar(numbSequence, "."); //alle Punkte löschen
                    numbSequence = deleteChar(numbSequence, " "); //alle Leerzeichen löschen
                }
                if (LogicValidator.isPosInteger(numbSequence.toString())) {
                    moduloTextField.setForeground(Color.black);
                    correctArguments = true;
                } else {
                    moduloTextField.setForeground(Color.red);
                    correctArguments = false;
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
        primeLabel.setText("check whether prime:");
        //primeLabel.setText("mod:");
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
        c.insets = new Insets(5,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 0;
        JLabel baseLabel = new JLabel();
        baseLabel.setText("bases:");
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
        Border settingsBorder = BorderFactory.createTitledBorder("settings");
        jPanelSettingsMiller.setBorder(settingsBorder);

        /*
        //probability Label
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.NORTHWEST;
       // c.gridwidth = 1;
        //c.gridheight = 0;
        c.gridx = 0;
        c.gridy = 0;
        JLabel probabilityLabel = new JLabel();
        probabilityLabel.setFont(probabilityLabel.getFont().deriveFont(probabilityLabel.getFont().getSize2D()-0.6f));
        probabilityLabel.setText("calc. probability");
        probabilityLabel.setHorizontalAlignment(JLabel.LEFT);
        jPanelSettings.add(probabilityLabel, c);
         * */


        //checkbox Probability
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0;
        probabilityCB = new JCheckBox("calc. probability", true);
        calcProb = true;
        probabilityCB.setFont(probabilityCB.getFont().deriveFont(probabilityCB.getFont().getSize2D()-0.6f));
        probabilityCB.setHorizontalAlignment(JLabel.LEFT);
        probabilityCB.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.DESELECTED){
                    calcProb = false;
                }else{
                    calcProb = true;
                }
            }
        });
        jPanelSettingsMiller.add(probabilityCB, c);

        //random spinner label number
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(3,20,0,0);
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.weighty = 0.5;
        randomSPLabel = new JLabel();
        randomSPLabel.setFont(randomSPLabel.getFont().deriveFont(randomSPLabel.getFont().getSize2D()-0.6f));
        randomSPLabel.setText("number");
        randomSPLabel.setEnabled(false);
        jPanelSettingsMiller.add(randomSPLabel, c);

        //spinner number of random bases
        c = new GridBagConstraints();
        c.ipadx = 20;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = new Insets(0,3,0,0);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.5;
        c.weighty = 0;
        randomNumberSP = new JSpinner(new SpinnerNumberModel(3, 1, 9999, 1));
        randomNumberSP.setFont(randomNumberSP.getFont().deriveFont(randomNumberSP.getFont().getSize2D()-0.6f));
        randomNumberSP.setEnabled(false);
        randomNumberSP.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent e){
                TreeSet<KryptoType> moduls = new TreeSet<KryptoType>(splitInputToZ(moduloTextField.getText()));
                if(!moduls.isEmpty()){
                    Integer numberRandomBases = (Integer)randomNumberSP.getValue();
                    basesTextField.setText("");
                    if(numberRandomBases >0 && numberRandomBases<=9999){
                        int endProbBaseRange; //max. Zahl für eine zufallsgenerierte Basis
                        if(Integer.parseInt(moduls.first().toString()) == 2){ //Achtung, möglicherweise funktioniert das nur für KryptoType 'Z'
                            Iterator<KryptoType> itModuls = moduls.iterator();
                            itModuls.next();
                            if(itModuls.hasNext()){
                                endProbBaseRange = Integer.parseInt(itModuls.next().toString()); //Achtung, möglicherweise funktioniert das nur für KryptoType 'Z'
                                int maxProbNumbers = endProbBaseRange-2; //Achtung, möglicherweise funktioniert das nur für KryptoType 'Z'
                                numberRandomBases = numberRandomBases<maxProbNumbers ? numberRandomBases : maxProbNumbers;
                            }else{
                                endProbBaseRange = 2;
                                numberRandomBases = 1;
                            }
                        } else{
                            endProbBaseRange = Integer.parseInt(moduls.first().toString());
                            int maxProbNumbers = endProbBaseRange-2;
                            numberRandomBases = numberRandomBases<maxProbNumbers ? numberRandomBases : maxProbNumbers;
                        }
                        probBases = new LinkedList<Integer>(PrimeUtility.getRandomNumber(2, endProbBaseRange, numberRandomBases));
                        StringBuilder probBasesString = new StringBuilder("");
                        for (int i = 0; i<probBases.size(); i++){
                            probBasesString.append(probBases.get(i)+", ");
                        }
                        basesTextField.setText(probBasesString.toString());
                    }
                }
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
        JCheckBox randomBasesCB = new JCheckBox("random bases", false);
        randomBasesCB.setFont(randomBasesCB.getFont().deriveFont(randomBasesCB.getFont().getSize2D()-0.6f));
        randomBasesCB.setHorizontalAlignment(JLabel.LEFT);
        randomBasesCB.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    basesTextField.setEnabled(false);
                    basesTextField.setText("");
                    randomSPLabel.setEnabled(true);
                    randomNumberSP.setEnabled(true);
                }else{
                    basesTextField.setEnabled(true);
                    basesTextField.setText("");
                    randomSPLabel.setEnabled(false);
                    randomNumberSP.setEnabled(false);
                }
            }
        });
        jPanelSettingsMiller.add(randomBasesCB, c);
    }


    //löscht den übergebenen char aus dem übergebenen String raus
    private StringBuilder deleteChar(StringBuilder originalString, String delChar, int fromIndex){
        StringBuilder withoutCharString = new StringBuilder(originalString);
        int charPosition = withoutCharString.indexOf(delChar, fromIndex);
        while(charPosition >=0){
            withoutCharString = withoutCharString.deleteCharAt(charPosition);
            charPosition = withoutCharString.indexOf(delChar, charPosition);
        }
        return withoutCharString;
    }

    private StringBuilder deleteChar(StringBuilder originalString, String delChar){
        return deleteChar(originalString, delChar, 0);
    }

    //splittet die Zahlenreihe in eine ArrayList auf
    //1.250, 15.0.0, 17, 18,,,19,,  21, , 23  24,25, 26,   28,,,  ,,29 , 30,54,  ,, ,,, , ,, ,  ,,  31, ..., .31, 21..., 60-65... //diese Zahlen in der Anordnung in die Testklasse aufnehmen - müssen dem regulären Ausdruck standhalten
    private ArrayList<KryptoType> splitInputToZ(String splitMe){
        Pattern baseModulSeparator = Pattern.compile("(([,]+[\\s]*)+|([\\s]+[,]*)+)"); //split an input list of bases and moduls(primes)
        Pattern dashSeparator = Pattern.compile("[\\-]");
        StringBuilder numbSequence = new StringBuilder(splitMe);
        int delPointPos = 0;

        if(correctArguments == false){
            throw new IllegalArgumentException("Wrong parameters found for bases, modules in window Miller-Rabin-Test " +getTitle());
        }
        //entfernt alle Punkte aus der Zahlenreihe
        for (int i = 0; i < numbSequence.length() && i>=0;){
            delPointPos = numbSequence.indexOf(".", i);
            if(delPointPos != -1){
                numbSequence = numbSequence.deleteCharAt(delPointPos);
            }
            i = delPointPos;
        }
        String[] result = baseModulSeparator.split(numbSequence);
        ArrayList<KryptoType> resultZ = new ArrayList<KryptoType>();
        for(String s : result){
            if(s.contains("-")){ //prüft ob eine range übergeben wurde und füllt diese auf
                String[] range = dashSeparator.split(s);
                resultZ.addAll(fillKryptoTypeZList(range));
                continue;
            }
            resultZ.add(new Z(s));
        }
        return resultZ;
    }

    //Füllt eine Liste mit Z von start bis end auf
    private ArrayList<Z> fillKryptoTypeZList(String[] range){
        //Precondition
        assert range.length == 2: "Error, the array has more than 2 elements: " +Arrays.toString(range);
        ArrayList<Z> listKrypto = new ArrayList<Z>();
        int first = Integer.valueOf(range[0]);
        int second = Integer.valueOf(range[1]);
        int start, end;

        if(first <= second){
            start = first;
            end = second;
        }else{
            start =second;
            end = first;
        }
        while(start <= end){
            listKrypto.add(new Z(start));
            start++;
        }
        return listKrypto;
    }

    @Override
    public String execute(){
        ArrayList<KryptoType> basen = new ArrayList<KryptoType>();
        ArrayList<KryptoType> moduls = new ArrayList<KryptoType>();
        ArrayList<Triple<Boolean, Double, LinkedList<String>>> result; //beinhaltet für jede Primzahl einzeln ob es prime ist, Wahrscheinlichkeit, Zwischenschritte

        if(basesTextField.getResult() != null)
            basen.add((KryptoType)basesTextField.getResult());
        else
            basen = splitInputToZ(basesTextField.getText());
        if(moduloTextField.getResult() != null)
            moduls.add((KryptoType)moduloTextField.getResult());
        else
            moduls = splitInputToZ(moduloTextField.getText()); 

        try{
            result = PrimeTestController.primeTestRabin(basen, moduls, calcProb);
        }catch(RuntimeException e){
            return e.getMessage();
        }catch(NoSuchMethodException e){
            return e.getMessage();
        }catch(InstantiationException e){
            return e.getMessage();
        }catch(IllegalAccessException e){
            return e.getMessage();
        }catch(InvocationTargetException e){
            return e.getMessage();
        }

        extendList = new LinkedList<String>(); //Zwischenschritte von der aktuell getesteten Primzahl
        extension = new LinkedList<LinkedList<String>>(); //ist die Gesamtliste an Zwischenschritten von allen Primzahlen wenn auf den Button extend geklickt wird
        outputWindow = new StringBuilder(); //für das untere Ausgabefenster
        int i = 0;
        String probability = "";
        for(Triple<Boolean, Double, LinkedList<String>> output: result){
            if (output.second() == -1.0){
                probability = "";
            }else{
                double probDouble = output.second()*100;
                probability = "    probability = " +String.valueOf(probDouble)+"%";
            }
            extendList = output.third(); //erhält von der jeweiligen Primzahl die Zwischenschritte
            extendList.addFirst(moduls.get(i)+ ":");
            extendList.addLast("result");
            extendList.addLast(moduls.get(i)+ " is prime number: " +output.first() +probability);
            extension.add(extendList);

            outputWindow.append(moduls.get(i) + ": "  + result.get(i).first()+ "\n");
            results.put(getTitle() + "_primeMillerRabin", output.first());
            i++;
        }
        return "In Window " + getTitle() + ": " + "\n\nprime numbers:\n" +outputWindow.toString();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExtendBtMiller;
    private javax.swing.JPanel jPanelDropListMiller;
    private javax.swing.JPanel jPanelPrimeMiller;
    private javax.swing.JPanel jPanelSettingsMiller;
    private javax.swing.JLabel millerRabinTestLabel;
    // End of variables declaration//GEN-END:variables

}
