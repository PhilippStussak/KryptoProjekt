/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LucasFrame.java
 *
 * Created on 29.06.2010, 17:14:20
 */
package kryptoprojekt.primeFrames;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import java.util.ArrayList;
import kryptoprojekt.model.KryptoType;
import kryptoprojekt.model.Z;
import kryptoprojekt.model.Triple;
import kryptoprojekt.model.Tuple;
import kryptoprojekt.controller.PrimeTestController;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import kryptoprojekt.controller.LogicValidator;

/**
 *
 * @author Michael
 */
public class LucasFrame extends Kit {

    private DropTextField primeFactorsTextField = getDropTextField();
    private DropTextField basesTextField = getDropTextField();
    private DropTextField summandTextField = getDropTextField();
    private boolean calcProb = true; //the user should set this value (in an later software release)
    private LinkedList<String> extendList;
    private LinkedList<LinkedList<String>> extension;
    private StyledDocument doc;
    private StringBuilder outputWindow;
    private boolean correctPrimeFactorsArguments;
    private boolean correctSummandArguments;
    private boolean correctBasesArguments;
    
    /** Creates new form LucasFrame */
    public LucasFrame(ConnectionHandler handler) {
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
        jPanelDropListLucas = new javax.swing.JPanel();
        extendBtLucas = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setDoubleBuffered(true);
        setMinimumSize(new java.awt.Dimension(200, 200));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(250, 200));

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(LucasFrame.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 14));

        jPanelDropListLucas.setName("jPanelDropListLucas"); // NOI18N

        javax.swing.GroupLayout jPanelDropListLucasLayout = new javax.swing.GroupLayout(jPanelDropListLucas);
        jPanelDropListLucas.setLayout(jPanelDropListLucasLayout);
        jPanelDropListLucasLayout.setHorizontalGroup(
            jPanelDropListLucasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        jPanelDropListLucasLayout.setVerticalGroup(
            jPanelDropListLucasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        extendBtLucas.setText(resourceMap.getString("ExtendBtLucas.text")); // NOI18N
        extendBtLucas.setAlignmentX(0.5F);
        extendBtLucas.setAlignmentY(0.0F);
        extendBtLucas.setDoubleBuffered(true);
        extendBtLucas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        extendBtLucas.setIconTextGap(0);
        extendBtLucas.setName("ExtendBtLucas"); // NOI18N
        extendBtLucas.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        extendBtLucas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extendBtLucasActionPerformed(evt);
            }
        });

        jPanel1.setMaximumSize(new java.awt.Dimension(320, 320));
        jPanel1.setMinimumSize(new java.awt.Dimension(120, 92));
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(120, 92));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(extendBtLucas))
                    .addComponent(jPanelDropListLucas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(extendBtLucas)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDropListLucas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void append(String text, String styleName) {
        try {
            doc.insertString(doc.getLength(), text, doc.getStyle(styleName));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void extendBtLucasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extendBtLucasActionPerformed
        JInternalFrame frame = new JInternalFrame(getTitle() + "_extension", true, true, true, true);
        frame.setLocation(getX(), getY());
        frame.setSize(400, 340);

        JTextPane fermatPane = new JTextPane() {

            @Override
            public boolean getScrollableTracksViewportWidth() {
                return false;
            }
        };
        fermatPane.setEditable(false);
        doc = fermatPane.getStyledDocument();
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
        fermatPane.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(fermatPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setBackground(Color.white);
        frame.add(scrollPane);
        frame.setVisible(true);
        getParent().add(frame);
}//GEN-LAST:event_extendBtLucasActionPerformed

    private void initLogicComponents() {
        jPanel1.setLayout(new GridBagLayout());
        jPanelDropListLucas.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setSize(250, 200);

        primeFactorsTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (validatePrimeFactorsField(getPrimeFactorsTextFieldValue())) {
                    primeFactorsTextField.setForeground(Color.black);
                    correctPrimeFactorsArguments = true;
                } else {
                    primeFactorsTextField.setForeground(Color.red);
                    correctPrimeFactorsArguments = false;
                }
            }
        });

        summandTextField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (validateSummandTextField(getSummandTextFieldValue())) {
                    summandTextField.setForeground(Color.black);
                    correctSummandArguments = true;
                } else {
                    summandTextField.setForeground(Color.red);
                    correctSummandArguments = false;
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

        //JPanel1
        //prime input
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        //c.insets = new Insets(0, 2, 1, 2);
        JLabel l = new JLabel();
        l.setText("prime factors");
        jPanel1.add(l, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        jPanel1.add(primeFactorsTextField, c);

        //summand
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTH; //wegmachen
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(1, 2, 1, 2);
        jPanel1.add(new JLabel("+"), c);
        
        c = new GridBagConstraints();
        c.weightx = 0.4;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.NORTH; //wegmachen
        c.gridx = 3;
        c.gridy = 1;
        jPanel1.add(summandTextField, c);
        summandTextField.setText("1");
        summandTextField.setEnabled(false);

        //base input
        c = new GridBagConstraints();
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(5, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 2;
        JLabel l2 = new JLabel();
        l2.setText("bases:");
        jPanel1.add(l2, c);

        c = new GridBagConstraints();
        c.weightx = 0.2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        jPanel1.add(basesTextField, c);

        //JPanelDropListLucas
        //drop List
        c = new GridBagConstraints();
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 4;
        jPanelDropListLucas.add(getDragList(new Object[]{getTitle() + "_primeLucas"}), c);
    }

    private boolean validatePrimeFactorsField(String primeFactorsTextFieldString){
        if (LogicValidator.isTermMultiplication(primeFactorsTextFieldString)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateSummandTextField(String summandTextFieldString){
        if (LogicValidator.isInteger(summandTextFieldString)) {
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

    //returns the value of the prime factors as String
    private String getPrimeFactorsTextFieldValue(){
        return primeFactorsTextField.getText();
    }

    //return the value of the summand as String
    private String getSummandTextFieldValue(){
        return summandTextField.getText();
    }

    //returns the value of the bases as String
    private String getBaseTextFieldValue(){
        return basesTextField.getText();
    }

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

    private ArrayList<KryptoType> splitInputToZ(String splitMe) {
        //Precondition
        assert splitMe.length()!=0: "Error, String is empty. splitMe = " +splitMe;
        assert correctPrimeFactorsArguments || correctBasesArguments !=false: "Error, prime factors or bases arguments have a false state. moduls = " +correctPrimeFactorsArguments+ " bases =" +correctBasesArguments;
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

    private LinkedList<Tuple<Z, Z>> getFactors(String s) {
        LinkedList<Tuple<Z, Z>> result = new LinkedList<Tuple<Z, Z>>();
        for (String factor : s.replaceAll("[ ]+", "").split("\\*")) {
            String[] baseExponent = factor.split("\\^");
            result.add(new Tuple<Z, Z>(new Z(baseExponent[0]), new Z((baseExponent.length == 2) ? baseExponent[1] : "1")));
        }
        return result;
    }

    @Override
    public String execute() {
        ArrayList<KryptoType> bases = new ArrayList<KryptoType>();
        ArrayList<KryptoType> primeFactors = new ArrayList<KryptoType>();
        ArrayList<KryptoType> factorPowers = new ArrayList<KryptoType>();
        ArrayList<KryptoType> summands = new ArrayList<KryptoType>();
        ArrayList<KryptoType> summandPowers = new ArrayList<KryptoType>();
        ArrayList<Triple<Boolean, Double, LinkedList<String>>> result; //contains for each number whether it is a prime, probability, intermediate
        LinkedList<KryptoType> posResults = new LinkedList<KryptoType>();

        ArrayList<Z> factors = new ArrayList<Z>();
        ArrayList<Z> powers = new ArrayList<Z>();
        LinkedList<Tuple<Z, Z>> factorsLinkedList = new LinkedList<Tuple<Z, Z>>();

        //verify the parameters from the TextFields (primeFactorsTextField & summandTextField & basesTextField)
        if (primeFactorsTextField.getResult() != null) {
            if(validatePrimeFactorsField(getPrimeFactorsTextFieldValue()) && validateSummandTextField(getSummandTextFieldValue()) && validateBaseTextField(getBaseTextFieldValue())){
                factorsLinkedList = getFactors(primeFactorsTextField.getResult().toString());
                for (Tuple<Z, Z> factorsL : factorsLinkedList) {
                    primeFactors.add(factorsL.first());
                    factorPowers.add(factorsL.second());
                }
            } else{
                if(!correctPrimeFactorsArguments){
                    return "You passed an invalid Lucas term in order to check it's a prime.";
                } else if(!correctSummandArguments){
                    return "You passed an invalid summand.";
                } else if(!correctBasesArguments){
                    return "You passed an invalid base.";
                }
            }
        } else if(correctPrimeFactorsArguments && primeFactorsTextField.getText().length() != 0){
            factorsLinkedList = getFactors(primeFactorsTextField.getText());
            for (Tuple<Z, Z> factorsL : factorsLinkedList) {
                primeFactors.add(factorsL.first());
                factorPowers.add(factorsL.second());
            }
        } else{
            return "You have to enter a valid Lucas term in order to check it's a prime (e.g. 2^4*5*4^5).";
        }

        if (summandTextField.getResult() != null) {
            if(validateSummandTextField(getSummandTextFieldValue()) && validatePrimeFactorsField(getPrimeFactorsTextFieldValue()) && validateBaseTextField(getBaseTextFieldValue())){
                summands.add((Z) summandTextField.getResult());
                summandPowers.add(new Z("1"));
            } else{
                if(!correctSummandArguments){
                    return "You passed an invalid base.";
                } else if(!correctPrimeFactorsArguments){
                    return "You passed an invalid Lucas term in order to check it's a prime.";
                } else if(!correctSummandArguments){
                    return "You passed an invalid summand.";
                }
            }
        } else if(validateSummandTextField(getSummandTextFieldValue()) && getSummandTextFieldValue().length() != 0){
            summands.add(new Z(getSummandTextFieldValue()));
            summandPowers.add(new Z("1"));
        } else{
                return "You have to enter a summand >=1 (recommended summand = 1).";
        }

        if (basesTextField.getResult() != null) {
            if(validateBaseTextField(getBaseTextFieldValue()) && validatePrimeFactorsField(getPrimeFactorsTextFieldValue()) && validateSummandTextField(getSummandTextFieldValue())){
                bases = splitInputToZ(basesTextField.getResult().toString());
                //bases.add((Z) basesTextField.getResult());
            } else{
                if(!correctBasesArguments){
                    return "You passed an invalid base.";
                } else if(!correctPrimeFactorsArguments){
                    return "You passed an invalid Lucas term in order to check it's a prime.";
                } else if(!correctSummandArguments){
                    return "You passed an invalid summand.";
                }
            }
        } else if(correctBasesArguments && getBaseTextFieldValue().length() != 0){
            bases = splitInputToZ(getBaseTextFieldValue());
        } else {
            return "You have to enter a valid base >=2.";
        }
        //end of the verification
        
        try {
            //starts the Lucas-Test
            result = PrimeTestController.primeTestLucas(bases, primeFactors, factorPowers, summands, summandPowers, calcProb);
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
        String verifiedNumb;
        String probability = "";
        for (Triple<Boolean, Double, LinkedList<String>> output : result) {
            if (output.second() == -2.0) {
                probability = "    probability = undefined";
            } else if (output.second() == -1.0) {
                probability = "";
            } else {
                double probDouble = output.second() * 100;
                probability = "    probability = " + String.valueOf(probDouble) + "%";
            }
            extendList = output.third();
            verifiedNumb = extendList.getFirst();
            if (output.first() == true) {
                posResults.add(new Z(verifiedNumb));
            }
            extendList.addFirst(extendList.pollFirst() + ":");
            extendList.addLast("result");
            extendList.addLast(verifiedNumb + " is prime number: " + output.first() + probability);

            extension.add(extendList);

            outputWindow.append(verifiedNumb + ": " + result.get(i).first() + "\n");
            KryptoType er = new Z("2");
            i++;
        }
        results.put(getTitle() + "_primeLucas", posResults);
        return "prime numbers:\n" + outputWindow.toString();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton extendBtLucas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelDropListLucas;
    // End of variables declaration//GEN-END:variables
}
