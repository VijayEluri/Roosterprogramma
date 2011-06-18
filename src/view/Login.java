/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Login2.java
 *
 * Created on 20-jan-2011, 11:12:42
 */
package view;

import connectivity.ShaEncrypt;
import java.awt.Color;
import model.Employee;
import roosterprogramma.RoosterProgramma;

/**
 *
 * @author Dark
 */
public class Login extends javax.swing.JPanel {

    private Employee employee;

    /** Creates new form Login2 */
    public Login(int state) {
        initComponents();
        setUpdateLabel(state);
        disablePassword();
    }

    private void setUpdateLabel(int state) {
        switch (state) {
            case 0: {
                lblUpdate.setText("U gebruikt de laatste versie van deze applicatie.");
                lblUpdate.setForeground(Color.green);
                break;
            }
            case 1: {
                lblUpdate.setText("U gebruikt niet de laatste versie van deze applicatie");
                lblUpdate.setForeground(Color.red);
                break;
            }
            case 2: {
                lblUpdate.setText("U heeft geen internetverbinding, versiecontrole is uit.");
                lblUpdate.setForeground(Color.orange);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void enablePassword() {
        tfPassword.setVisible(true);
        lblPassword.setVisible(true);
        btnPasswordOK.setVisible(true);
    }

    private void disablePassword() {
        tfPassword.setVisible(false);
        lblPassword.setVisible(false);
        btnPasswordOK.setVisible(false);
    }

    private void handleNumber() {
        if (RoosterProgramma.getInstance().isNumeric(tfEmployeeNumber.getText())) {
            int employeeNumber = Integer.parseInt(tfEmployeeNumber.getText());
            employee = RoosterProgramma.getQueryManager().getEmployee(employeeNumber);
            if (!employee.getFirstName().isEmpty()) {
                if (employee.getPassword() == null) {
                    String password = RoosterProgramma.getInstance().promptInput(
                            "Uw account heeft geen ingesteld wachtwoord, dit is wel vereist...\n"
                            + "Dit veld is niet gemaskeerd, zorg ervoor dat er niemand in de buurt is.\n"
                            + "Voer hieronder het gewenste wachtwoord in.\n");
                    if (!RoosterProgramma.getInstance().isEmpty(password)) {
                        password = ShaEncrypt.SHA1(password);
                        employee.setPassword(password);
                        employee.update();
                        RoosterProgramma.getInstance().showPanel(new MainMenu());
                    }
                } else {
                    enablePassword();
                    lblIncorrectField.setText("");
                    tfPassword.requestFocusInWindow();
                }
            } else {
                disablePassword();
                lblIncorrectField.setText("Er bestaat geen medewerker met dat personeelsnummer.");
                tfPassword.setText("");
            }
        } else {
            disablePassword();
            lblIncorrectField.setText("Dat is dus geen nummer...");
            tfPassword.setText("");
        }
    }

    private void handlePassword() {
        String Sha1Pass = ShaEncrypt.SHA1(RoosterProgramma.getInstance().decodePassword(tfPassword.getPassword()));
        if (Sha1Pass.equals(employee.getPassword())) {
            RoosterProgramma.getInstance().setEmployee(employee);
            RoosterProgramma.getInstance().showPanel(new MainMenu());
        } else {
            lblIncorrectField.setText("Wachtwoord onjuist");
            tfPassword.setText("");
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

        mainPanel = new javax.swing.JPanel();
        familyNamePanel = new javax.swing.JPanel();
        btnNumberOK = new javax.swing.JButton();
        tfEmployeeNumber = new javax.swing.JTextField();
        lblEmployeeNumber = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        lblPassword = new javax.swing.JLabel();
        btnPasswordOK = new javax.swing.JButton();
        updatePanel = new javax.swing.JPanel();
        lblUpdate = new javax.swing.JLabel();
        textPanel = new javax.swing.JPanel();
        lblIncorrectField = new javax.swing.JLabel();

        btnNumberOK.setText("OK");
        btnNumberOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNumberOKActionPerformed(evt);
            }
        });

        tfEmployeeNumber.setPreferredSize(new java.awt.Dimension(150, 20));
        tfEmployeeNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfEmployeeNumberKeyTyped(evt);
            }
        });

        lblEmployeeNumber.setText("Personeelsnummer");

        tfPassword.setPreferredSize(new java.awt.Dimension(150, 20));
        tfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPasswordKeyTyped(evt);
            }
        });

        lblPassword.setText("Wachtwoord");

        btnPasswordOK.setText("OK");
        btnPasswordOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasswordOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout familyNamePanelLayout = new javax.swing.GroupLayout(familyNamePanel);
        familyNamePanel.setLayout(familyNamePanelLayout);
        familyNamePanelLayout.setHorizontalGroup(
            familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familyNamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmployeeNumber)
                    .addComponent(lblPassword))
                .addGap(41, 41, 41)
                .addGroup(familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(familyNamePanelLayout.createSequentialGroup()
                        .addComponent(tfEmployeeNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNumberOK))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, familyNamePanelLayout.createSequentialGroup()
                        .addComponent(tfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPasswordOK)))
                .addContainerGap())
        );
        familyNamePanelLayout.setVerticalGroup(
            familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familyNamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmployeeNumber)
                    .addGroup(familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNumberOK)))
                .addGap(18, 18, 18)
                .addGroup(familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPasswordOK))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        updatePanel.add(lblUpdate);

        textPanel.add(lblIncorrectField);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(updatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(familyNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(familyNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addComponent(textPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void tfEmployeeNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmployeeNumberKeyTyped
        if (evt.getKeyChar() == '\n') {
            handleNumber();
        }
    }//GEN-LAST:event_tfEmployeeNumberKeyTyped

    private void tfPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPasswordKeyTyped
        if (evt.getKeyChar() == '\n') {
            handlePassword();
        }
    }//GEN-LAST:event_tfPasswordKeyTyped

    private void btnNumberOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNumberOKActionPerformed
        handleNumber();
    }//GEN-LAST:event_btnNumberOKActionPerformed

    private void btnPasswordOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasswordOKActionPerformed
        handlePassword();
    }//GEN-LAST:event_btnPasswordOKActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNumberOK;
    private javax.swing.JButton btnPasswordOK;
    private javax.swing.JPanel familyNamePanel;
    private javax.swing.JLabel lblEmployeeNumber;
    private javax.swing.JLabel lblIncorrectField;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUpdate;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel textPanel;
    private javax.swing.JTextField tfEmployeeNumber;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables
}