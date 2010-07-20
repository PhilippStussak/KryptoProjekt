/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FermatFrame.java
 *
 * Created on 29.06.2010, 17:14:20
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
import kryptoprojekt.model.FermatZ;
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

/**
 *
 * @author Michael
 */
public class FermatFrame extends Kit {

    private JCheckBox probabilityCB;
    private DropTextField basesTextField = getDropTextField();
    private DropTextField moduloTextField = getDropTextField();
    private JLabel randomSPLabel;
    private JSpinner randomNumberSP;
    private LinkedList<String> extendList;
    private LinkedList<LinkedList<String>> extension;
    private LinkedList<Integer> probBases;
    private StringBuilder outputWindow;
    private StyledDocument doc;
    private Font fontSettings;
    private boolean calcProb; //ob die Wahrscheinlichkeit beim FermatTest berechnet werden soll
    private boolean correctArguments; //zeigt an, ob für Basen und Moduls korrekte Werte übergeben wurden



    /** Creates new form FermatFrame */
    public FermatFrame(ConnectionHandler handler) {
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

        fermatTestLabel = new javax.swing.JLabel();
        jPanelPrime = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanelSettings = new javax.swing.JPanel();
        jPanelDropList = new javax.swing.JPanel();

        setClosable(true);
        setDoubleBuffered(true);
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(280, 190));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(FermatFrame.class);
        fermatTestLabel.setText(resourceMap.getString("fermatTestLabel.text")); // NOI18N
        fermatTestLabel.setName("fermatTestLabel"); // NOI18N

        jPanelPrime.setName("jPanelPrime"); // NOI18N

        javax.swing.GroupLayout jPanelPrimeLayout = new javax.swing.GroupLayout(jPanelPrime);
        jPanelPrime.setLayout(jPanelPrimeLayout);
        jPanelPrimeLayout.setHorizontalGroup(
            jPanelPrimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 79, Short.MAX_VALUE)
        );
        jPanelPrimeLayout.setVerticalGroup(
            jPanelPrimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 97, Short.MAX_VALUE)
        );

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setAlignmentX(0.5F);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanelSettings.setMinimumSize(new java.awt.Dimension(120, 97));
        jPanelSettings.setName("jPanelSettings"); // NOI18N
        jPanelSettings.setPreferredSize(new java.awt.Dimension(135, 183));

        javax.swing.GroupLayout jPanelSettingsLayout = new javax.swing.GroupLayout(jPanelSettings);
        jPanelSettings.setLayout(jPanelSettingsLayout);
        jPanelSettingsLayout.setHorizontalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        jPanelSettingsLayout.setVerticalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 97, Short.MAX_VALUE)
        );

        jPanelDropList.setName("jPanelDropList"); // NOI18N

        javax.swing.GroupLayout jPanelDropListLayout = new javax.swing.GroupLayout(jPanelDropList);
        jPanelDropList.setLayout(jPanelDropListLayout);
        jPanelDropListLayout.setHorizontalGroup(
            jPanelDropListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanelDropListLayout.setVerticalGroup(
            jPanelDropListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelDropList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fermatTestLabel)
                            .addComponent(jPanelPrime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jPanelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1))
                    .addComponent(fermatTestLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPrime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDropList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JInternalFrame frame = new JInternalFrame(getTitle() + "_extension", true, true, true, true);
        frame.setLocation(getX(), getY());
        frame.setSize(400, 340);

        JTextPane fermatPane = new JTextPane(){
            @Override
            public boolean getScrollableTracksViewportWidth(){
                return false;
            }
        };
        fermatPane.setEditable(false);
        doc = fermatPane.getStyledDocument();
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
        fermatPane.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(fermatPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(Color.white);
        frame.add(scrollPane);
        frame.setVisible(true);
        getParent().add(frame);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void append(String text, String styleName){
        try{
            doc.insertString(doc.getLength(), text, doc.getStyle(styleName));
        } catch (BadLocationException e) {
            //System.err.println("get Message Ausgabe: Fehler in der Start.java: " +e.getMessage());
            e.printStackTrace();
        }
    }

    private void initLogicComponents() {
        jPanelPrime.setLayout(new GridBagLayout());
        jPanelSettings.setLayout(new GridBagLayout());
        jPanelDropList.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setSize(290, 210);

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
        jPanelPrime.add(primeLabel, c);

        
        c = new GridBagConstraints();
        c.ipadx = 80;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
        jPanelPrime.add(moduloTextField, c);

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
        jPanelPrime.add(baseLabel, c);

        c = new GridBagConstraints();
        c.ipadx = 80;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        jPanelPrime.add(basesTextField, c);

   //JPanelDropList
      //drop List
        c = new GridBagConstraints();
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        jPanelDropList.add(getDragList(new Object[]{getTitle() + "_primeFermat"}), c);

   //JPanelSettings
        Border settingsBorder = BorderFactory.createTitledBorder("settings");
        jPanelSettings.setBorder(settingsBorder);

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
        jPanelSettings.add(probabilityCB, c);

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
        jPanelSettings.add(randomSPLabel, c);

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
        jPanelSettings.add(randomNumberSP, c);

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
        jPanelSettings.add(randomBasesCB, c);
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
            throw new IllegalArgumentException("Wrong parameters found for bases, modules in window Fermat-Test " +getTitle());
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
        LinkedList<KryptoType> posResults = new LinkedList<KryptoType>(); //beinhaltet die erkannten Primzahlen für die Weiterleitung an ein anderes Fenster

        if(basesTextField.getResult() != null)
            basen.add((KryptoType)basesTextField.getResult());
        else
            basen = splitInputToZ(basesTextField.getText());
        if(moduloTextField.getResult() != null)
            moduls.add((KryptoType)moduloTextField.getResult());
        else
            moduls = splitInputToZ(moduloTextField.getText());        

        try{
            result = PrimeTestController.primeTestFermat(basen, moduls, calcProb);
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
            if (output.second() == -2.0){
                probability = "    probability = undefined";
            }else if(output.second() == -1.0){
                probability = "";
            }
            else{
                double probDouble = output.second()*100;
                probability = "    probability = " +String.valueOf(probDouble)+"%";
            }
            if(output.first() == true){
                posResults.add(moduls.get(i));
            }
            extendList = output.third(); //erhält von der jeweiligen Primzahl die Zwischenschritte
            extendList.addFirst(moduls.get(i)+ ":");
            extendList.addLast("result");
            extendList.addLast(moduls.get(i)+ " is prime number: " +output.first() +probability);
            extension.add(extendList);
            
            outputWindow.append(moduls.get(i) + ": "  + result.get(i).first()+ "\n");     
            i++;
        }
        results.put(getTitle() + "_primeFermat", posResults);
        return "In Window " + getTitle() + ": " + "\n\nprime numbers:\n" +outputWindow.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fermatTestLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanelDropList;
    private javax.swing.JPanel jPanelPrime;
    private javax.swing.JPanel jPanelSettings;
    // End of variables declaration//GEN-END:variables



}
