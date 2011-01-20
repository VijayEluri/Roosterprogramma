/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * medewerkerOverzicht.java
 *
 * Created on 8-jan-2011, 14:25:01
 */

package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import roosterprogramma.RoosterProgramma;

/**
 *
 * @author Dark
 */
public class MedewerkerOverzicht extends javax.swing.JPanel {

    private int medewerkerID;

    /** Creates new form medewerkerOverzicht */
    public MedewerkerOverzicht() {
        initComponents();
        fillTable();
        startChangeListener();
    }

    private void startChangeListener() {
        ChangeListener listen = new ChangeListener();
        listen.start();
    }

    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) medewerkerTabel.getModel();
        for (Employee employee : RoosterProgramma.getQueryManager().getEmployees())
        {
            model.addRow(new Object[] {
                employee.getPersoneelsNummer(),
                employee.getVoornaam(),
                employee.getAchternaam()
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

        jScrollPane1 = new javax.swing.JScrollPane();
        medewerkerTabel = new javax.swing.JTable();
        OK = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        toevoegen = new javax.swing.JButton();
        wijzigen = new javax.swing.JButton();
        verwijderen = new javax.swing.JButton();

        medewerkerTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PersoneelsNummer", "Voornaam", "Achternaam"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(medewerkerTabel);

        OK.setText("OK");
        OK.setEnabled(false);
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        btnBack.setText("Terug");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        toevoegen.setText("Medewerker toevoegen");
        toevoegen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toevoegenActionPerformed(evt);
            }
        });

        wijzigen.setText("Medewerker wijzigen");

        verwijderen.setText("Medewerker verwijderen");
        verwijderen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verwijderenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                        .addComponent(verwijderen)
                        .addGap(18, 18, 18)
                        .addComponent(wijzigen)
                        .addGap(18, 18, 18)
                        .addComponent(toevoegen)
                        .addGap(18, 18, 18)
                        .addComponent(OK)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(OK)
                    .addComponent(toevoegen)
                    .addComponent(wijzigen)
                    .addComponent(verwijderen))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        RoosterProgramma.getInstance().showPanel(new Login(4));
    }//GEN-LAST:event_btnBackActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        RoosterProgramma.getInstance().showPanel(new MedewerkerInfo(medewerkerID));
    }//GEN-LAST:event_OKActionPerformed

    private void toevoegenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toevoegenActionPerformed
        RoosterProgramma.getInstance().showPanel(new AddMedewerker());
    }//GEN-LAST:event_toevoegenActionPerformed

    private void verwijderenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verwijderenActionPerformed
        Employee employee = RoosterProgramma.getQueryManager().getEmployee(Integer.parseInt(medewerkerTabel.getModel().getValueAt(medewerkerTabel.getSelectedRow(), 0).toString()));

        int choice = JOptionPane.showConfirmDialog(
            null,
            "Weet je zeker dat je " + employee.getVoornaam() + " " + employee.getAchternaam() + " wilt verwijderen?",
            "Waarschuwing!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION)
        {
            RoosterProgramma.getQueryManager().deleteEmployee(Integer.parseInt(medewerkerTabel.getModel().getValueAt(medewerkerTabel.getSelectedRow(), 0).toString()));
            RoosterProgramma.getInstance().showPanel(new MedewerkerOverzicht());
        }
    }//GEN-LAST:event_verwijderenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JButton btnBack;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable medewerkerTabel;
    private javax.swing.JButton toevoegen;
    private javax.swing.JButton verwijderen;
    private javax.swing.JButton wijzigen;
    // End of variables declaration//GEN-END:variables

    private class ChangeListener extends Thread {
        @Override
        public void run() {
            while (true)
            {
                try {
                    if (medewerkerTabel.getSelectedColumn() != -1)
                    {
                        medewerkerID = Integer.parseInt(medewerkerTabel.getModel().getValueAt(medewerkerTabel.getSelectedRow(), 0).toString());
                        OK.setEnabled(true);
                    }
                    else
                    {
                        OK.setEnabled(false);
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MedewerkerOverzicht.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
