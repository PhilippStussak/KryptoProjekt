/*
 * KryptoProjektView.java
 */

package kryptoprojekt;

import kryptoprojekt.basicFrames.AdditionFrame;
import kryptoprojekt.basicFrames.SubtractFrame;
import kryptoprojekt.basicFrames.ZFrame;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import kryptoprojekt.basicFrames.DivisionFrame;
import kryptoprojekt.basicFrames.GCDFrame;
import kryptoprojekt.basicFrames.ModuloFrame;
import kryptoprojekt.basicFrames.MultiplicationFrame;
import kryptoprojekt.basicFrames.PhiFrame;
import kryptoprojekt.basicFrames.PrimeFieldElementFrame;
import kryptoprojekt.basicFrames.SaMFrame;
import kryptoprojekt.basicFrames.SaMModFrame;
import kryptoprojekt.coderFrames.DecodeHammingCodeFrame;
import kryptoprojekt.coderFrames.EncodeHammingCodeFrame;
import kryptoprojekt.coderFrames.HammingDistanceFrame;
import kryptoprojekt.coderFrames.HammingSyndromFrame;
import kryptoprojekt.coderFrames.HammingWeightFrame;
import kryptoprojekt.coderFrames.InitHammingJFrame;
import kryptoprojekt.primeFrames.FermatFrame;

/**
 * The application's main frame.
 */
public class KryptoProjektView extends FrameView {

    private Desktop desktop;
    private ConnectionHandler handler = new ConnectionHandler();

    public KryptoProjektView(SingleFrameApplication app) {
        super(app);

        initComponents();

        desktop = new Desktop(handler);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
        );

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = KryptoProjektApp.getApplication().getMainFrame();
            aboutBox = new KryptoProjektAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        KryptoProjektApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainMenuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        startMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        basicMenu = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        additionMenuItem = new javax.swing.JMenuItem();
        subtractionMenuItem = new javax.swing.JMenuItem();
        multiplicationMenuItem = new javax.swing.JMenuItem();
        divisionMenuItem = new javax.swing.JMenuItem();
        modMenuItem = new javax.swing.JMenuItem();
        samMenuItem = new javax.swing.JMenuItem();
        sammodMenuItem = new javax.swing.JMenuItem();
        gcdMenuItem = new javax.swing.JMenuItem();
        phiMenuItem = new javax.swing.JMenuItem();
        zMenuItem = new javax.swing.JMenuItem();
        primeFieldMenuItem = new javax.swing.JMenuItem();
        primeTestMenu = new javax.swing.JMenu();
        fermatMenuItem = new javax.swing.JMenuItem();
        coderMenu = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        initHammingCodeMenuItem = new javax.swing.JMenuItem();
        encodeMenuItem = new javax.swing.JMenuItem();
        syndromMenuItem = new javax.swing.JMenuItem();
        decodeMenuItem = new javax.swing.JMenuItem();
        hammingDistanceMenuItem = new javax.swing.JMenuItem();
        vectorWeightMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        mainMenuBar.setName("mainMenuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getResourceMap(KryptoProjektView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        startMenuItem.setText(resourceMap.getString("startMenuItem.text")); // NOI18N
        startMenuItem.setName("startMenuItem"); // NOI18N
        startMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(startMenuItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(kryptoprojekt.KryptoProjektApp.class).getContext().getActionMap(KryptoProjektView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        basicMenu.setText(resourceMap.getString("basicMenu.text")); // NOI18N
        basicMenu.setName("basicMenu"); // NOI18N

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        additionMenuItem.setText(resourceMap.getString("additionMenuItem.text")); // NOI18N
        additionMenuItem.setName("additionMenuItem"); // NOI18N
        additionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                additionMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(additionMenuItem);

        subtractionMenuItem.setText(resourceMap.getString("subtractionMenuItem.text")); // NOI18N
        subtractionMenuItem.setName("subtractionMenuItem"); // NOI18N
        subtractionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subtractionMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(subtractionMenuItem);

        multiplicationMenuItem.setText(resourceMap.getString("multiplicationMenuItem.text")); // NOI18N
        multiplicationMenuItem.setName("multiplicationMenuItem"); // NOI18N
        multiplicationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multiplicationMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(multiplicationMenuItem);

        divisionMenuItem.setText(resourceMap.getString("divisionMenuItem.text")); // NOI18N
        divisionMenuItem.setName("divisionMenuItem"); // NOI18N
        divisionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                divisionMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(divisionMenuItem);

        modMenuItem.setText(resourceMap.getString("modMenuItem.text")); // NOI18N
        modMenuItem.setName("modMenuItem"); // NOI18N
        modMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(modMenuItem);

        samMenuItem.setText(resourceMap.getString("samMenuItem.text")); // NOI18N
        samMenuItem.setName("samMenuItem"); // NOI18N
        samMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                samMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(samMenuItem);

        sammodMenuItem.setText(resourceMap.getString("sammodMenuItem.text")); // NOI18N
        sammodMenuItem.setName("sammodMenuItem"); // NOI18N
        sammodMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sammodMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(sammodMenuItem);

        gcdMenuItem.setText(resourceMap.getString("gcdMenuItem.text")); // NOI18N
        gcdMenuItem.setName("gcdMenuItem"); // NOI18N
        gcdMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcdMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(gcdMenuItem);

        phiMenuItem.setText(resourceMap.getString("phiMenuItem.text")); // NOI18N
        phiMenuItem.setName("phiMenuItem"); // NOI18N
        phiMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phiMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(phiMenuItem);

        basicMenu.add(jMenu2);

        zMenuItem.setText(resourceMap.getString("zMenuItem.text")); // NOI18N
        zMenuItem.setName("zMenuItem"); // NOI18N
        zMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zMenuItemActionPerformed(evt);
            }
        });
        basicMenu.add(zMenuItem);

        primeFieldMenuItem.setText(resourceMap.getString("primeFieldMenuItem.text")); // NOI18N
        primeFieldMenuItem.setName("primeFieldMenuItem"); // NOI18N
        primeFieldMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primeFieldMenuItemActionPerformed(evt);
            }
        });
        basicMenu.add(primeFieldMenuItem);

        mainMenuBar.add(basicMenu);

        primeTestMenu.setText(resourceMap.getString("primeTestMenu.text")); // NOI18N
        primeTestMenu.setName("primeTestMenu"); // NOI18N

        fermatMenuItem.setText(resourceMap.getString("fermatMenuItem.text")); // NOI18N
        fermatMenuItem.setName("fermatMenuItem"); // NOI18N
        fermatMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fermatMenuItemActionPerformed(evt);
            }
        });
        primeTestMenu.add(fermatMenuItem);

        mainMenuBar.add(primeTestMenu);

        coderMenu.setText(resourceMap.getString("coderMenu.text")); // NOI18N
        coderMenu.setName("coderMenu"); // NOI18N

        jMenu11.setText(resourceMap.getString("jMenu11.text")); // NOI18N
        jMenu11.setName("jMenu11"); // NOI18N

        initHammingCodeMenuItem.setText(resourceMap.getString("initHammingCodeMenuItem.text")); // NOI18N
        initHammingCodeMenuItem.setName("initHammingCodeMenuItem"); // NOI18N
        initHammingCodeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                initHammingCodeMenuItemActionPerformed(evt);
            }
        });
        jMenu11.add(initHammingCodeMenuItem);

        encodeMenuItem.setText(resourceMap.getString("encodeMenuItem.text")); // NOI18N
        encodeMenuItem.setName("encodeMenuItem"); // NOI18N
        encodeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encodeMenuItemActionPerformed(evt);
            }
        });
        jMenu11.add(encodeMenuItem);

        syndromMenuItem.setText(resourceMap.getString("syndromMenuItem.text")); // NOI18N
        syndromMenuItem.setName("syndromMenuItem"); // NOI18N
        syndromMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syndromMenuItemActionPerformed(evt);
            }
        });
        jMenu11.add(syndromMenuItem);

        decodeMenuItem.setText(resourceMap.getString("decodeMenuItem.text")); // NOI18N
        decodeMenuItem.setName("decodeMenuItem"); // NOI18N
        decodeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decodeMenuItemActionPerformed(evt);
            }
        });
        jMenu11.add(decodeMenuItem);

        hammingDistanceMenuItem.setText(resourceMap.getString("hammingDistanceMenuItem.text")); // NOI18N
        hammingDistanceMenuItem.setName("hammingDistanceMenuItem"); // NOI18N
        hammingDistanceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hammingDistanceMenuItemActionPerformed(evt);
            }
        });
        jMenu11.add(hammingDistanceMenuItem);

        vectorWeightMenuItem.setText(resourceMap.getString("vectorWeightMenuItem.text")); // NOI18N
        vectorWeightMenuItem.setName("vectorWeightMenuItem"); // NOI18N
        vectorWeightMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vectorWeightMenuItemActionPerformed(evt);
            }
        });
        jMenu11.add(vectorWeightMenuItem);

        coderMenu.add(jMenu11);

        mainMenuBar.add(coderMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        mainMenuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        jMenuBar1.setName("jMenuBar1"); // NOI18N

        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N
        jMenuBar1.add(jMenu5);

        jMenu6.setText(resourceMap.getString("jMenu6.text")); // NOI18N
        jMenu6.setName("jMenu6"); // NOI18N
        jMenuBar1.add(jMenu6);

        jMenuBar2.setName("jMenuBar2"); // NOI18N

        jMenu7.setText(resourceMap.getString("jMenu7.text")); // NOI18N
        jMenu7.setName("jMenu7"); // NOI18N
        jMenuBar2.add(jMenu7);

        jMenu8.setText(resourceMap.getString("jMenu8.text")); // NOI18N
        jMenu8.setName("jMenu8"); // NOI18N
        jMenuBar2.add(jMenu8);

        jMenuBar3.setName("jMenuBar3"); // NOI18N

        jMenu9.setText(resourceMap.getString("jMenu9.text")); // NOI18N
        jMenu9.setName("jMenu9"); // NOI18N
        jMenuBar3.add(jMenu9);

        jMenu10.setText(resourceMap.getString("jMenu10.text")); // NOI18N
        jMenu10.setName("jMenu10"); // NOI18N
        jMenuBar3.add(jMenu10);

        setComponent(mainPanel);
        setMenuBar(mainMenuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void zMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zMenuItemActionPerformed
        Kit kit = new ZFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_zMenuItemActionPerformed

    private void additionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_additionMenuItemActionPerformed
        Kit kit = new AdditionFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_additionMenuItemActionPerformed

    private void subtractionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subtractionMenuItemActionPerformed
        Kit kit = new SubtractFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_subtractionMenuItemActionPerformed

    private void multiplicationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multiplicationMenuItemActionPerformed
        Kit kit = new MultiplicationFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_multiplicationMenuItemActionPerformed

    private void divisionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_divisionMenuItemActionPerformed
        Kit kit = new DivisionFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_divisionMenuItemActionPerformed

    private void decodeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decodeMenuItemActionPerformed
        Kit kit = new DecodeHammingCodeFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_decodeMenuItemActionPerformed

    private void startMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startMenuItemActionPerformed
        JInternalFrame results = new JInternalFrame("result", true, true, true, true);
        results.setSize(desktop.getWidth(), desktop.getHeight());
        results.setVisible(true);
        results.moveToFront();
        JScrollPane scrollpane = new JScrollPane();
        JTextArea area = new JTextArea();
        area.setVisible(true);
        results.add(area);
        desktop.add(results);
        new Executor(handler, area).start();
    }//GEN-LAST:event_startMenuItemActionPerformed

    private void primeFieldMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primeFieldMenuItemActionPerformed
        Kit kit = new PrimeFieldElementFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_primeFieldMenuItemActionPerformed

    private void modMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modMenuItemActionPerformed
        Kit kit = new ModuloFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_modMenuItemActionPerformed

    private void samMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_samMenuItemActionPerformed
        Kit kit = new SaMFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_samMenuItemActionPerformed

    private void sammodMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sammodMenuItemActionPerformed
        Kit kit = new SaMModFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_sammodMenuItemActionPerformed

    private void gcdMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcdMenuItemActionPerformed
        Kit kit = new GCDFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_gcdMenuItemActionPerformed

    private void initHammingCodeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_initHammingCodeMenuItemActionPerformed
        Kit kit = new InitHammingJFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_initHammingCodeMenuItemActionPerformed

    private void hammingDistanceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hammingDistanceMenuItemActionPerformed
        Kit kit = new HammingDistanceFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_hammingDistanceMenuItemActionPerformed

    private void vectorWeightMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vectorWeightMenuItemActionPerformed
        Kit kit = new HammingWeightFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_vectorWeightMenuItemActionPerformed

    private void encodeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encodeMenuItemActionPerformed
        Kit kit = new EncodeHammingCodeFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_encodeMenuItemActionPerformed

    private void syndromMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syndromMenuItemActionPerformed
        Kit kit = new HammingSyndromFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_syndromMenuItemActionPerformed

    private void phiMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phiMenuItemActionPerformed
        Kit kit = new PhiFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_phiMenuItemActionPerformed

    private void fermatMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fermatMenuItemActionPerformed
        Kit kit = new FermatFrame(handler);
        kit.setVisible(true);
        desktop.add(kit);
    }//GEN-LAST:event_fermatMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem additionMenuItem;
    private javax.swing.JMenu basicMenu;
    private javax.swing.JMenu coderMenu;
    private javax.swing.JMenuItem decodeMenuItem;
    private javax.swing.JMenuItem divisionMenuItem;
    private javax.swing.JMenuItem encodeMenuItem;
    private javax.swing.JMenuItem fermatMenuItem;
    private javax.swing.JMenuItem gcdMenuItem;
    private javax.swing.JMenuItem hammingDistanceMenuItem;
    private javax.swing.JMenuItem initHammingCodeMenuItem;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuItem modMenuItem;
    private javax.swing.JMenuItem multiplicationMenuItem;
    private javax.swing.JMenuItem phiMenuItem;
    private javax.swing.JMenuItem primeFieldMenuItem;
    private javax.swing.JMenu primeTestMenu;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenuItem samMenuItem;
    private javax.swing.JMenuItem sammodMenuItem;
    private javax.swing.JMenuItem startMenuItem;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JMenuItem subtractionMenuItem;
    private javax.swing.JMenuItem syndromMenuItem;
    private javax.swing.JMenuItem vectorWeightMenuItem;
    private javax.swing.JMenuItem zMenuItem;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
