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
import java.util.LinkedList;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import kryptoprojekt.ConnectionHandler;
import kryptoprojekt.Kit;
import kryptoprojekt.model.Tuple;
import kryptoprojekt.model.Triple;
import java.util.ArrayList;
import kryptoprojekt.model.KryptoType;
import kryptoprojekt.model.Z;
import kryptoprojekt.model.PrimeTest;
import kryptoprojekt.model.FermatZ;
import kryptoprojekt.model.Basic;
import kryptoprojekt.controller.PrimeTestController;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import kryptoprojekt.controller.LogicValidator;

/**
 *
 * @author Michael
 */
public class LucasFrame extends Kit{

    private DropTextField basesTextField = getDropTextField();
    private DropTextField moduloTextField = getDropTextField();
    private DropTextField summandTextField = getDropTextField();
    private String extension = "";
    private String outputWindow = "";
    private boolean calcProb; //ob die Wahrscheinlichkeit beim Lucas-Test berechnet werden soll
    private boolean correctArguments; //zeigt an, ob für Basen und Moduls korrekte Werte übergeben wurden

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
        jPanel1 = new javax.swing.JPanel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setDoubleBuffered(true);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(LucasFrame.class);
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
            .addGap(0, 301, Short.MAX_VALUE)
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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initLogicComponents() {

        basesTextField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (LogicValidator.isTermMultiplication(basesTextField.getText())) {
                    basesTextField.setForeground(Color.black);
                } else {
                    basesTextField.setForeground(Color.red);
                }
            }
        });

        moduloTextField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (LogicValidator.isPosInteger(moduloTextField.getText())) {
                    moduloTextField.setForeground(Color.black);
                } else {
                    moduloTextField.setForeground(Color.red);
                }
            }
        });

        summandTextField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                if (LogicValidator.isInteger(summandTextField.getText())) {
                    summandTextField.setForeground(Color.black);
                } else {
                    summandTextField.setForeground(Color.red);
                }
            }
        });

        jPanel1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        JLabel l = new JLabel();
        l.setText("prime factors");
        jPanel1.add(l, c);

        c = new GridBagConstraints();
        c.weightx = 0.6;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        jPanel1.add(basesTextField, c);

        c = new GridBagConstraints();
        //c.weightx = 0.4;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        JLabel l2 = new JLabel();
        l2.setText("bases:");
        jPanel1.add(l2, c);

        c = new GridBagConstraints();
        c.weightx = 0.2;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        jPanel1.add(moduloTextField, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(1,2,1,2);
        jPanel1.add(new JLabel("+"), c);

        c = new GridBagConstraints();
        c.weightx = 0.4;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 3;
        c.gridy = 1;
        jPanel1.add(summandTextField, c);
        summandTextField.setText("1");
        summandTextField.setEnabled(false);

        this.setSize(300, 150);
    }

    @Override
    public String execute() {
        ArrayList<KryptoType> bases = new ArrayList<KryptoType>();
        //ArrayList<Tuple<KryptoType, KryptoType>> primeFactors = new ArrayList<Tuple<KryptoType, KryptoType>>();
        ArrayList<KryptoType> primeFactors = new ArrayList<KryptoType>();
        ArrayList<KryptoType> factorPowers = new ArrayList<KryptoType>();
        //ArrayList<Tuple<KryptoType, KryptoType>> summands = new ArrayList<Tuple<KryptoType, KryptoType>>();
        ArrayList<KryptoType> summands = new ArrayList<KryptoType>();
        ArrayList<KryptoType> summandPowers = new ArrayList<KryptoType>();
        ArrayList<Triple<Boolean, Double, LinkedList<String>>> result; //beinhaltet für jede Primzahl einzeln ob es prime ist, Wahrscheinlichkeit, Zwischenschritte


        ArrayList<Z> basen = new ArrayList<Z>();
        ArrayList<Z> factors = new ArrayList<Z>();
        ArrayList<Z> summand = new ArrayList<Z>();
        ArrayList<Z> powers = new ArrayList<Z>();
       /* ArrayList<Triple<Boolean, Double, LinkedList<String>>> result; //beinhaltet für jede Primzahl einzeln ob es prime ist, Wahrscheinlichkeit, Zwischenschritte

        //ACHTUNG, ES MÜSSEN NOCH POWER EINGABEFELDER EINGERICHTET WERDEN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //lucasPrimeFactors Triple erzeugen
        Triple<ArrayList<Z>, ArrayList<Z>, ArrayList<Z>> primeFactors = new Triple<ArrayList<Z>, ArrayList<Z>, ArrayList<Z>>(basen, factors, powers);
        ArrayList<Triple<ArrayList<Z>, ArrayList<Z>, ArrayList<Z>>> lucasPrimeFactors = new ArrayList<Triple<ArrayList<Z>, ArrayList<Z>, ArrayList<Z>>>();
        lucasPrimeFactors.add(primeFactors);

        //Summanden Liste erzeugen
        Tuple<Z, Z> sum = new Tuple<Z, Z>(new Z(1), new Z(1)); //lege ich bereits so fest, bitte das Eingabefeld bei dem Summanden gleich auf 1 setzen
        ArrayList<Tuple<Z, Z>> summands = new ArrayList<Tuple<Z, Z>>();
        summands.add(sum);*
/*
    public LucasZ(Collection<Z> bases, Collection<Tuple<Z, Z>> primeFactors, Collection<Tuple<Z, Z>> summands, boolean calcProb){
        super(bases, primeFactors, summands, calcProb);
    }
*/
        if(basesTextField.getResult() != null)
            factors.add((Z)basesTextField.getResult());
        else
            factors.add(new Z (basesTextField.getText()));

        //ACHTUNG, MODUL WIRD BEI DEM LUCAS-TEST NICHT GEBRAUCHT
        if(moduloTextField.getResult() != null)
            bases.add((Z)moduloTextField.getResult());
        else
            bases.add(new Z(moduloTextField.getText()));
        //ACHTUNG, MODUL WIRD BEI DEM LUCAS-TEST NICHT GEBRAUCHT


        if(summandTextField.getResult() != null)
            summand.add((Z)summandTextField.getResult());
        else
            summand.add(new Z(summandTextField.getText()));

        try{
            result = PrimeTestController.primeTestLucas(bases, primeFactors, factorPowers, summands, summandPowers, calcProb);
            results.put(getTitle() + "_prime", result);
            //return "In Window " + getTitle() + ": " + basen.get(0) + " ^ "+moduls.get(0).subtract(new Z(1))+ " mod "+moduls.get(0)+ " = " + result.get(0).first();
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

        Z primeValue = new Z("1");
        int j = 0;
        for (Z factor : factors){
            primeValue = primeValue.multiply(Basic.squareAndMultiply(factor, powers.get(j)).first());
            ++j;
        }

        extension = "";
        outputWindow = "";
        int i = 0;
        String probability = "";
        for(Triple<Boolean, Double, LinkedList<String>> output: result){
            if (output.second() == -2.0){ //dies korrigieren, gibt es bei MillerRabin nicht. Wenn nur -1
                probability = "n.d.";
            }else{
                double probDouble = output.second()*100;
                probability = String.valueOf(probDouble)+"%";
            }
   
   //DIESE AUSKOMMENTIERTEN FELDER WIEDER AKTIVIEREN. ICH WEIß NOCH NICHT WIE MAN BASEN, HIER AM BESTEN AUSGIBT. ES IST DAS PRODUKT primeVALUE was ich oben stehen habe         
   //         extension += basen.get(i) + " ^ "+moduls.get(i).subtract(new Z(1))+ " mod "+moduls.get(i)+ " = " + output.first()+ "   probability = " +probability;
   //         outputWindow += moduls.get(i) + ": "  + result.get(i).first()+ "\n";
            i++;
        }
        //return "In Window " + getTitle() + ": " + "\n\nPrimzahlen:\n" +moduls.get(0)+ ": "  + result.get(0).first();
   //     return "In Window " + getTitle() + ": " + basen + " + " + moduls + " = ";// + result.toString();
        return "";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables



}
