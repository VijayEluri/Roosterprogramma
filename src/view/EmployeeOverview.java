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

import java.io.File;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import roosterprogramma.ExcelExporter;
import roosterprogramma.RoosterProgramma;
import roosterprogramma.Utils;

/**
 *
 * @author Dark
 */
public class EmployeeOverview extends javax.swing.JPanel {

    private Employee selectedEmployee;
    private DefaultTableModel model;
    private boolean isEdit = false;

    /**
     * Creates new form medewerkerOverzicht
     *
     * @param isEdit
     */
    public EmployeeOverview(boolean isEdit) {
        this.isEdit = isEdit;
        initComponents();
        model = (DefaultTableModel) tblEmployee.getModel();
        searchTable();
        if (!isEdit) {
            btnAdd.setToolTipText("OK");
            btnAdd.setIcon(new ImageIcon(getClass().getResource("/images/btnNext.png")));
            btnChange.setToolTipText("Exporteer naar Excel");
            btnChange.setIcon(new ImageIcon(getClass().getResource("/images/btnExport.png")));
            btnDelete.setVisible(false);
        }
    }

    private void insertEmployeeIntoTable(Employee employee) {
        String contracttype = "Fulltime";
        if (employee.isCallWorker() || employee.isMuseumEducator() || employee.isClerk()) {
            contracttype = "Oproepkracht";
        }
        if (employee.isPartTime()) {
            contracttype = "Parttime";
        }
        Object[] fields = new Object[5];
        fields[0] = employee.getEmployeeNumber();
        fields[1] = employee.getFirstName();
        fields[2] = employee.getInsertion().isEmpty() ? "" : employee.getInsertion();
        fields[3] = employee.getFamilyName();
        fields[4] = contracttype;
        model.addRow(fields);
    }

    private void searchTable() {
        while (model.getRowCount() != 0) {
            model.removeRow(0);
        }
        if (!tfEmployeeNr.getText().isEmpty()) {
            Employee employee = RoosterProgramma.getInstance().getEmployee(Integer.parseInt(tfEmployeeNr.getText()));
            if (employee != null) {
                insertEmployeeIntoTable(employee);
            }
        } else {
            if (!tfFirstName.getText().isEmpty() || !tfFamilyName.getText().isEmpty()) {
                for (Employee employee : RoosterProgramma.getQueryManager().searchEmployee(tfFirstName.getText(), tfFamilyName.getText())) {
                    insertEmployeeIntoTable(employee);
                }
            } else {
                for (Employee employee : RoosterProgramma.getInstance().getEmployees()) {
                    insertEmployeeIntoTable(employee);
                }
            }
        }
    }

    private void showInfo() {
        Calendar calendar = Calendar.getInstance();
        RoosterProgramma.getInstance().showPanel(
                new EmployeeTimeSheet(selectedEmployee, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        tfEmployeeNr = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfFamilyName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Personeelsnummer", "Voornaam", "Tussenvoegsel", "Achternaam", "Contracttype"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployee);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnPrevious.png"))); // NOI18N
        btnBack.setToolTipText("Vorige");
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnAdd.png"))); // NOI18N
        btnAdd.setToolTipText("Toevoegen");
        btnAdd.setContentAreaFilled(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnEdit.png"))); // NOI18N
        btnChange.setToolTipText("Wijzijgen");
        btnChange.setContentAreaFilled(false);
        btnChange.setEnabled(false);
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnDelete.png"))); // NOI18N
        btnDelete.setToolTipText("Verwijderen");
        btnDelete.setContentAreaFilled(false);
        btnDelete.setEnabled(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tfEmployeeNr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEmployeeNrKeyReleased(evt);
            }
        });

        jLabel1.setText("Personeels Nr:");

        tfFamilyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFamilyNameKeyReleased(evt);
            }
        });

        jLabel2.setText("Voornaam");

        jLabel3.setText("Achternaam");

        tfFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFirstNameKeyReleased(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 400, Short.MAX_VALUE)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnChange)
                        .addGap(18, 18, 18)
                        .addComponent(btnAdd))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tfEmployeeNr, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(tfFamilyName, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmployeeNr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFamilyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnAdd)
                    .addComponent(btnChange)
                    .addComponent(btnDelete))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        RoosterProgramma.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        RoosterProgramma.getInstance().showPanel(new ChAddEmployee());
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (Utils.promptQuestion("Weet je zeker dat je " + selectedEmployee.getFirstName() + " " + selectedEmployee.getFamilyName() + " wilt verwijderen?", true, "Ja", "Nee")) {
            if (selectedEmployee.isAdmin()) {
                Utils.showMessage("U kunt geen administrator verwijderen.", "Fout!", true, "");
                return;
            }
            selectedEmployee.delete();
            searchTable();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        selectedEmployee = RoosterProgramma.getInstance().getEmployee(Integer.parseInt(tblEmployee.getModel().getValueAt(tblEmployee.getSelectedRow(), 0).toString()));
        if (evt.getClickCount() < 2) {
            btnDelete.setEnabled(true);
            btnChange.setEnabled(true);
        } else {
            if (isEdit) {
                RoosterProgramma.getInstance().showPanel(new ChAddEmployee(selectedEmployee));
            } else {
                showInfo();
            }
        }
    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        if (isEdit) {
            RoosterProgramma.getInstance().showPanel(new ChAddEmployee(selectedEmployee));
        } else {
            String input = Utils.showFileChooser("Opslaan");
            if (!input.isEmpty()) {
                ExcelExporter.Export(tblEmployee, new File(input.contains(".xls") ? input : input + ".xls"), false);
            }
        }
    }//GEN-LAST:event_btnChangeActionPerformed

    private void tfEmployeeNrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmployeeNrKeyReleased
        searchTable();
}//GEN-LAST:event_tfEmployeeNrKeyReleased

    private void tfFamilyNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFamilyNameKeyReleased
        searchTable();
}//GEN-LAST:event_tfFamilyNameKeyReleased

    private void tfFirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFirstNameKeyReleased
        searchTable();
}//GEN-LAST:event_tfFirstNameKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTextField tfEmployeeNr;
    private javax.swing.JTextField tfFamilyName;
    private javax.swing.JTextField tfFirstName;
    // End of variables declaration//GEN-END:variables
}