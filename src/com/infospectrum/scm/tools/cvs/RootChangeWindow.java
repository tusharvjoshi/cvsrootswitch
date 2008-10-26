/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RootChangeWindow.java
 *
 * Created on Oct 25, 2008, 7:58:51 AM
 */
package com.infospectrum.scm.tools.cvs;

import com.l2fprod.common.swing.JDirectoryChooser;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author tusharj
 */
public class RootChangeWindow extends javax.swing.JFrame {

    /** Creates new form RootChangeWindow */
    public RootChangeWindow() {
        initComponents();
    }

    private void browsePath() {
        JDirectoryChooser chooser = new JDirectoryChooser();
        int choice = chooser.showOpenDialog(this);
        if (choice == JDirectoryChooser.CANCEL_OPTION) {
            System.out.println("User Canceled");
        } else {
            String path = chooser.getSelectedFile().getAbsolutePath();
            pathFolderField.setText(path);

            String oldRootText = RootChanger.getFirstRootText(path);
            oldRootTextField.setText(oldRootText);
            newRootTextField.setText(oldRootText);
        }
    }

    private void changeRoots() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                changeRootsThread();
            }
        });
        thread.start();
    }

    private void changeRootsThread() {

        changeRootsButton.setEnabled(false);

        RootChangeStatus rootChangeStatus = new RootChangeStatus();
        RootChanger rootChanger =
                new RootChanger(pathFolderField.getText(),
                oldRootTextField.getText(), newRootTextField.getText());
        rootChangeStatus.printStartMessage();
        rootChanger.changeRoots(rootChangeStatus);
        rootChangeStatus.printEndMessage();

        changeRootsButton.setEnabled(true);
    }

    private class RootChangeStatus extends RootChangeAdapter {


        public void printStartMessage() {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    outputTextArea.append("Starting root switch process...");
                    outputTextArea.append("\n");
                }
            });
        }

        public void printEndMessage() {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    outputTextArea.append("Root switch process complete.");
                    outputTextArea.append("\n");
                }
            });
        }

        @Override
        public void rootChangeFailed(final String rootFileName, final Exception e) {
            EventQueue.invokeLater(new Runnable() {

                public void run() {
                    outputTextArea.append("Root switch failed for:" + rootFileName);
                    outputTextArea.append("Error message is:" + e.getMessage());
                    outputTextArea.append("\n");
                }
            });
        }

        @Override
        public void rootChanged(final String rootFileName) {
            EventQueue.invokeLater(new Runnable() {

                public void run() {
                    outputTextArea.append("Root Switched for:" + rootFileName);
                    outputTextArea.append("\n");
                }
            });
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        borderPanel = new JPanel();
        cvsFolderPathLabel = new JLabel();
        pathFolderField = new JTextField();
        pathBrowserButton = new JButton();
        oldRootLabel = new JLabel();
        newRootLabel = new JLabel();
        oldRootTextField = new JTextField();
        newRootTextField = new JTextField();
        changeRootsButton = new JButton();
        outputPanel = new JPanel();
        outputScrollPane = new JScrollPane();
        outputTextArea = new JTextArea();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("CVS Root Switch");

        borderPanel.setBorder(BorderFactory.createTitledBorder("CVS Folder Details"));

        cvsFolderPathLabel.setText("CVS Folder Path:");

        pathBrowserButton.setText("...");
        pathBrowserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pathBrowserButtonActionPerformed(evt);
            }
        });

        oldRootLabel.setText("Old Root Text:");

        newRootLabel.setText("New Root Text:");

        changeRootsButton.setText("Change Roots");
        changeRootsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                changeRootsButtonActionPerformed(evt);
            }
        });

        GroupLayout borderPanelLayout = new GroupLayout(borderPanel);
        borderPanel.setLayout(borderPanelLayout);
        borderPanelLayout.setHorizontalGroup(
            borderPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, borderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(borderPanelLayout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(changeRootsButton)
                    .addGroup(borderPanelLayout.createSequentialGroup()
                        .addGroup(borderPanelLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(cvsFolderPathLabel)
                            .addComponent(oldRootLabel)
                            .addComponent(newRootLabel))
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addGroup(borderPanelLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(pathFolderField, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(oldRootTextField, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                            .addComponent(newRootTextField, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(pathBrowserButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        
        );
        borderPanelLayout.setVerticalGroup(
            borderPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(borderPanelLayout.createSequentialGroup()
                .addGroup(borderPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(cvsFolderPathLabel)
                    .addGroup(borderPanelLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(pathBrowserButton)
                        .addComponent(pathFolderField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(borderPanelLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(oldRootLabel)
                    .addComponent(oldRootTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borderPanelLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(newRootLabel)
                    .addComponent(newRootTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(changeRootsButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        
        );

        outputPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        outputScrollPane.setViewportView(outputTextArea);

        GroupLayout outputPanelLayout = new GroupLayout(outputPanel);
        outputPanel.setLayout(outputPanelLayout);
        outputPanelLayout.setHorizontalGroup(
            outputPanelLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(outputScrollPane, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        
        );
        outputPanelLayout.setVerticalGroup(
            outputPanelLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(outputScrollPane, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
        
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                    .addComponent(outputPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(borderPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(borderPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addComponent(outputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pathBrowserButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_pathBrowserButtonActionPerformed
        browsePath();
    }//GEN-LAST:event_pathBrowserButtonActionPerformed

    private void changeRootsButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_changeRootsButtonActionPerformed
        changeRoots();
    }//GEN-LAST:event_changeRootsButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RootChangeWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(RootChangeWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(RootChangeWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(RootChangeWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RootChangeWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel borderPanel;
    private JButton changeRootsButton;
    private JLabel cvsFolderPathLabel;
    private JLabel newRootLabel;
    private JTextField newRootTextField;
    private JLabel oldRootLabel;
    private JTextField oldRootTextField;
    private JPanel outputPanel;
    private JScrollPane outputScrollPane;
    private JTextArea outputTextArea;
    private JButton pathBrowserButton;
    private JTextField pathFolderField;
    // End of variables declaration//GEN-END:variables

}