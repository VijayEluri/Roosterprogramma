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

import model.Employee;
import roosterprogramma.RoosterProgramma;
import roosterprogramma.Utils;

/**
 *
 * @author Dark
 */
public class Login extends javax.swing.JPanel {

    private Employee employee;

    /**
     * Creates new form Login2
     */
    public Login() {
        initComponents();
        disablePassword();
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
        if (Utils.isNumeric(tfEmployeeNumber.getText())) {
            int employeeNumber = Integer.parseInt(tfEmployeeNumber.getText());
            employee = RoosterProgramma.getQueryManager().getEmployee(employeeNumber);
            if (!employee.getFirstName().isEmpty()) {
                if ("TEST".equals(employee.getPassword())) {
                    String password = Utils.promptInput(
                            "Uw account heeft geen ingesteld wachtwoord, dit is wel vereist...\n"
                            + "Dit veld is niet gemaskeerd, zorg ervoor dat er niemand in de buurt is.\n"
                            + "Voer hieronder het gewenste wachtwoord in.\n");
                    if (password != null && !password.isEmpty()) {
                        password = Utils.SHA1(password);
                        employee.setPassword(password);
                        employee.update();
                        if (RoosterProgramma.getQueryManager().getEmployee(employeeNumber).getPassword().equals(password)) {
                            RoosterProgramma.getInstance().setEmployee(employee);
                            jpbLoading.setValue(10);
                            RoosterProgramma.getInstance().initializeSettings();
                            jpbLoading.setValue(30);
                            RoosterProgramma.getInstance().initializeEmployees();
                            jpbLoading.setValue(100);
                            RoosterProgramma.getInstance().showPanel(new MainMenu());
                        } else {
                            lblIncorrectField.setText("Er is een fout opgetreden bij het updaten van het wachtwoord in de database.");
                        }
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
        String Sha1Pass = Utils.SHA1(Utils.decodePassword(tfPassword.getPassword()));
        if (Sha1Pass.equals(employee.getPassword())) {
            RoosterProgramma.getInstance().setEmployee(employee);
            jpbLoading.setValue(10);
            RoosterProgramma.getInstance().initializeSettings();
            jpbLoading.setValue(30);
            RoosterProgramma.getInstance().initializeEmployees();
            jpbLoading.setValue(100);
            RoosterProgramma.getInstance().showPanel(new MainMenu());
        } else {
            lblIncorrectField.setText("Wachtwoord onjuist");
            tfPassword.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        lblLogo = new javax.swing.JLabel();
        updatePanel = new javax.swing.JPanel();
        jpbLoading = new javax.swing.JProgressBar();
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

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N

        javax.swing.GroupLayout familyNamePanelLayout = new javax.swing.GroupLayout(familyNamePanel);
        familyNamePanel.setLayout(familyNamePanelLayout);
        familyNamePanelLayout.setHorizontalGroup(
            familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(familyNamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(familyNamePanelLayout.createSequentialGroup()
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
                                .addComponent(btnPasswordOK))))
                    .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        familyNamePanelLayout.setVerticalGroup(
            familyNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, familyNamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap())
        );

        javax.swing.GroupLayout updatePanelLayout = new javax.swing.GroupLayout(updatePanel);
        updatePanel.setLayout(updatePanelLayout);
        updatePanelLayout.setHorizontalGroup(
            updatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpbLoading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        updatePanelLayout.setVerticalGroup(
            updatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatePanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jpbLoading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        textPanel.add(lblIncorrectField);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(updatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(familyNamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
    private javax.swing.JProgressBar jpbLoading;
    private javax.swing.JLabel lblEmployeeNumber;
    private javax.swing.JLabel lblIncorrectField;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel textPanel;
    private javax.swing.JTextField tfEmployeeNumber;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JPanel updatePanel;
    // End of variables declaration//GEN-END:variables
}