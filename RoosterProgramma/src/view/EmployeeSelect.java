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
import java.io.IOException;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import roosterprogramma.ExcelExporter;
import roosterprogramma.RoosterProgramma;

/**
 *
 * @author Dark
 */
public class EmployeeSelect extends javax.swing.JPanel {

    private Employee selectedEmployee;

    /** Creates new form medewerkerOverzicht */
    public EmployeeSelect() {
        initComponents();
        fillTable();
    }

    // ToDo : FillTable functie kan gegeneraliseerd worden waardoor SearchTable hem kan aanroepen...heb je niet meer 3x dezelfde code
    private void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
        for (Employee employee : RoosterProgramma.getQueryManager().getEmployees())
        {
            Object[] fields = new Object[4];
            fields[0] = employee.getEmployeeNumber();
            fields[1] = employee.getFirstName();
            fields[2] = RoosterProgramma.getInstance().isEmpty(employee.getInsertion()) ? "" : employee.getInsertion();
            fields[3] = employee.getFamilyName();
            model.addRow(fields);
        }
    }

    private void searchTable() {
        DefaultTableModel model = (DefaultTableModel) tblEmployee.getModel();
        while(model.getRowCount() != 0)
        {
            model.removeRow(0);
        }
        if (!tfEmployeeNr.getText().isEmpty())
        {
            Employee employee = RoosterProgramma.getQueryManager().getEmployee(Integer.parseInt(tfEmployeeNr.getText()));
            if(!employee.getFirstName().isEmpty())
            {
                Object[] fields = new Object[4];
                fields[0] = employee.getEmployeeNumber();
                fields[1] = employee.getFirstName();
                fields[2] = RoosterProgramma.getInstance().isEmpty(employee.getInsertion()) ? "" : employee.getInsertion();
                fields[3] = employee.getFamilyName();
                model.addRow(fields);
            }
        }
        else
        {
            if(!tfFirstName.getText().isEmpty() || !tfFamilyName.getText().isEmpty())
            {
                for(Employee employee : RoosterProgramma.getQueryManager().searchEmployee(tfFirstName.getText(), tfFamilyName.getText()))
                {
                    Object[] fields = new Object[4];
                    fields[0] = employee.getEmployeeNumber();
                    fields[1] = employee.getFirstName();
                    fields[2] = RoosterProgramma.getInstance().isEmpty(employee.getInsertion()) ? "" : employee.getInsertion();
                    fields[3] = employee.getFamilyName();
                    model.addRow(fields);
                }
            }
            else
            {
                fillTable();
            }
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
        tblEmployee = new javax.swing.JTable();
        OK = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfEmployeeNr = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfFamilyName = new javax.swing.JTextField();
        btnExportExcel = new javax.swing.JButton();

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PersoneelsNummer", "Voornaam", "Tussenvoegsel", "Achternaam"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        jLabel1.setText("Personeels Nr:");

        tfEmployeeNr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEmployeeNrKeyReleased(evt);
            }
        });

        jLabel2.setText("Voornaam");

        tfFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFirstNameKeyReleased(evt);
            }
        });

        jLabel3.setText("Achternaam");

        tfFamilyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFamilyNameKeyReleased(evt);
            }
        });

        btnExportExcel.setText("Exporteer naar Excel");
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 573, Short.MAX_VALUE)
                        .addComponent(btnExportExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OK))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(OK)
                    .addComponent(btnExportExcel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        RoosterProgramma.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_btnBackActionPerformed

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        showInfo();
    }//GEN-LAST:event_OKActionPerformed

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        if (evt.getClickCount() < 2)
        {
            OK.setEnabled(true);
            selectedEmployee = RoosterProgramma.getQueryManager().getEmployee(Integer.parseInt(tblEmployee.getModel().getValueAt(tblEmployee.getSelectedRow(), 0).toString()));
        }
        else
        {
            showInfo();
        }
    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void tfFirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFirstNameKeyReleased
        searchTable();
    }//GEN-LAST:event_tfFirstNameKeyReleased

    private void tfFamilyNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFamilyNameKeyReleased
        searchTable();
    }//GEN-LAST:event_tfFamilyNameKeyReleased

    private void tfEmployeeNrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmployeeNrKeyReleased
        searchTable();
    }//GEN-LAST:event_tfEmployeeNrKeyReleased

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
         try {
            ExcelExporter tabel = new ExcelExporter();
            String input = RoosterProgramma.getInstance().showFileChooser("Opslaan");
            tabel.exportTable(tblEmployee, new File(input.contains(".xls") ? input : input + ".xls"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnExportExcelActionPerformed

    private void showInfo() {
        Calendar calendar = Calendar.getInstance();
        RoosterProgramma.getInstance().showPanel(new EmployeeTimeSheet(selectedEmployee, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExportExcel;
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