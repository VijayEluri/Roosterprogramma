/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainMenu.java
 *
 * Created on 20-jan-2011, 15:40:02
 */
package view;

import java.util.Calendar;
import roosterprogramma.RoosterProgramma;

/**
 *
 * @author Dark
 */
public class MainMenu extends javax.swing.JPanel {

    /** Creates new form MainMenu */
    public MainMenu() {
        initComponents();
        adminCheck();
    }

    private void adminCheck() {
        btnManage.setEnabled(RoosterProgramma.getEmployee().isAdmin());
        btnOverview.setEnabled(RoosterProgramma.getEmployee().isAdmin());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogout = new javax.swing.JButton();
        flowPanel = new javax.swing.JPanel();
        boxPanel = new javax.swing.JPanel();
        btnRoosters = new javax.swing.JButton();
        btnOverview = new javax.swing.JButton();
        btnManage = new javax.swing.JButton();
        btnSettings = new javax.swing.JButton();

        btnLogout.setText("Uitloggen");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        boxPanel.setLayout(new javax.swing.BoxLayout(boxPanel, javax.swing.BoxLayout.Y_AXIS));

        btnRoosters.setText("Roosters bekijken en veranderen");
        btnRoosters.setMaximumSize(new java.awt.Dimension(200, 100));
        btnRoosters.setPreferredSize(new java.awt.Dimension(200, 100));
        btnRoosters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoostersActionPerformed(evt);
            }
        });
        boxPanel.add(btnRoosters);

        btnOverview.setText("Medewerker Overzicht");
        btnOverview.setMaximumSize(new java.awt.Dimension(200, 100));
        btnOverview.setPreferredSize(new java.awt.Dimension(200, 100));
        btnOverview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOverviewActionPerformed(evt);
            }
        });
        boxPanel.add(btnOverview);

        btnManage.setText("Medewerkers beheren");
        btnManage.setMaximumSize(new java.awt.Dimension(200, 100));
        btnManage.setPreferredSize(new java.awt.Dimension(200, 100));
        btnManage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageActionPerformed(evt);
            }
        });
        boxPanel.add(btnManage);

        btnSettings.setText("Instellingen");
        btnSettings.setMaximumSize(new java.awt.Dimension(200, 100));
        btnSettings.setPreferredSize(new java.awt.Dimension(200, 100));
        btnSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingsActionPerformed(evt);
            }
        });
        boxPanel.add(btnSettings);

        flowPanel.add(boxPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLogout)
                    .addComponent(flowPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1468, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(flowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOverviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOverviewActionPerformed
        RoosterProgramma.getInstance().showPanel(new EmployeeSelect());
    }//GEN-LAST:event_btnOverviewActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        RoosterProgramma.getInstance().showPanel(new Login(4));
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRoostersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoostersActionPerformed
        Calendar calendar = Calendar.getInstance();
        RoosterProgramma.getInstance().showPanel(new Rooster(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));
    }//GEN-LAST:event_btnRoostersActionPerformed

    private void btnManageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageActionPerformed
        RoosterProgramma.getInstance().showPanel(new EmployeeOverview());
    }//GEN-LAST:event_btnManageActionPerformed

    private void btnSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingsActionPerformed
        boolean forward = RoosterProgramma.getInstance().promptQuestion("Weet u zeker dat u de instellingen wilt aanpassen?\nDit kan ongewenste gevolgen hebben als u niet weet wat u doet!", true, "Ja, ik weet het zeker", "Nee, ik wil terug naar het hoofdmenu");
        if (forward) {
            RoosterProgramma.getInstance().showPanel(new SettingsMenu());
        }
    }//GEN-LAST:event_btnSettingsActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boxPanel;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManage;
    private javax.swing.JButton btnOverview;
    private javax.swing.JButton btnRoosters;
    private javax.swing.JButton btnSettings;
    private javax.swing.JPanel flowPanel;
    // End of variables declaration//GEN-END:variables
}
