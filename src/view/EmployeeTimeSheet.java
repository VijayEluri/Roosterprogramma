/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * medewerkerInfo.java
 *
 * Created on 8-jan-2011, 14:25:19
 */
package view;

import java.util.Calendar;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import model.WorkHours;
import roosterprogramma.RoosterProgramma;
import roosterprogramma.Translater;

/**
 *
 * @author Dark
 */
public class EmployeeTimeSheet extends javax.swing.JPanel {

    private Employee employee;
    private DefaultTableModel model;
    private Calendar calendar = Calendar.getInstance();
    private int year, month;

    /** Creates new form medewerkerInfo */
    public EmployeeTimeSheet(Employee employee, int year, int month) {
        this.employee = employee;
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        this.year = year;
        this.month = month;
        initComponents();
        fillInfoTable();
        fillVerantwoordingTable();
        fillBoxes();
    }

    private void fillBoxes() {
        for (int i = -20; i <= 20; i++) {
            cmbYear.addItem(calendar.get(Calendar.YEAR) + i);
        }
        cmbYear.setSelectedItem(year);
        for (int j = 1; j <= 12; j++) {
            cmbMonth.addItem(j);
        }
        cmbMonth.setSelectedItem(month);
    }

    private void fillInfoTable() {
        ((DefaultTableModel) tblEmployeeInformation.getModel()).addRow(new Object[]{
                    employee.getFirstName(),
                    employee.getFamilyName(),
                    employee.getEmployeeNumber(),
                    employee.isFullTime(),
                    employee.isPartTime(),
                    employee.isCallWorker()
                });
    }

    private void fillVerantwoordingTable() {
        Translater translater = new Translater();
        model = (DefaultTableModel) tblTimeSheet.getModel();
        model.addColumn("Dag vd Maand");
        if (employee.isClerk()) {
            model.addColumn("Ingeroosterde Uren");
        }
        model.addColumn("Gewerkt");
        model.addColumn("Compensatie 150");
        model.addColumn("Compensatie 200");
        model.addColumn("Vakantie");
        model.addColumn("ADV");
        model.addColumn("Ziek");
        model.addColumn("Speciaal Verlof");
        model.addColumn("Project");
        model.addColumn("Totaal");
        int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysOfMonth; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            WorkHours hour = employee.getWorkHours(getYear() + "-" + getMonth() + "-" + getDay());
            Object[] fields;
            if (employee.isClerk()) {
                fields = new Object[11];
            } else {
                fields = new Object[10];
            }
            fields[0] = calendar.get(Calendar.DAY_OF_MONTH) + " - " + translater.Translate(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
            fields[1] = hour.getShouldWork();
            if (employee.isClerk()) {
                fields[2] = hour.getWorked();
            }
            fields[employee.isClerk() ? 3 : 2] = hour.getCompensation150();
            fields[employee.isClerk() ? 4 : 3] = hour.getCompensation200();
            fields[employee.isClerk() ? 5 : 4] = hour.getVacation();
            fields[employee.isClerk() ? 6 : 5] = hour.getADV();
            fields[employee.isClerk() ? 7 : 6] = hour.getIllness();
            fields[employee.isClerk() ? 8 : 7] = hour.getLeave();
            fields[employee.isClerk() ? 9 : 8] = hour.getProject();
            fields[employee.isClerk() ? 10 : 9] = 0;
            model.addRow(fields);
        }
        model.addRow(new Object[]{
                    "Totaal",
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
                });
        calculateTotal();
    }

    private void calculateTotal() {
        for (int i = 0; i < model.getRowCount(); i++) {
            double totalHours = 0;
            for (int j = 1; j < model.getColumnCount() - 1; j++) {
                totalHours += model.getValueAt(i, j).toString().isEmpty() ? 0 : Double.parseDouble(model.getValueAt(i, j).toString());
            }
            model.setValueAt(totalHours, i, model.getColumnCount() - 1);
        }
        for (int k = 1; k < model.getColumnCount(); k++) {
            double totalHours = 0;
            for (int l = 0; l < model.getRowCount() - 1; l++) {
                totalHours += model.getValueAt(l, k).toString().isEmpty() ? 0 : Double.parseDouble(model.getValueAt(l, k).toString());
            }
            model.setValueAt(totalHours, model.getRowCount() - 1, k);
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

        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmployeeInformation = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTimeSheet = new javax.swing.JTable();
        lblMonth = new javax.swing.JLabel();
        btnPreviousMonth = new javax.swing.JButton();
        btnNextMonth = new javax.swing.JButton();
        pnlDateSelect = new javax.swing.JPanel();
        cmbYear = new javax.swing.JComboBox();
        cmbMonth = new javax.swing.JComboBox();
        btnGo = new javax.swing.JButton();

        btnSave.setText("Wijzigingen opslaan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setText("Vorige");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblEmployeeInformation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Voornaam", "Achternaam", "Personeelsnummer", "Fulltime", "Parttime", "Oproepkracht", "Noodhulp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEmployeeInformation);

        tblTimeSheet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTimeSheet.setRowSelectionAllowed(false);
        tblTimeSheet.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tblTimeSheetFocusGained(evt);
            }
        });
        jScrollPane3.setViewportView(tblTimeSheet);

        btnPreviousMonth.setText("Vorige maand");
        btnPreviousMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousMonthActionPerformed(evt);
            }
        });

        btnNextMonth.setText("Volgende maand");
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });

        pnlDateSelect.add(cmbYear);

        pnlDateSelect.add(cmbMonth);

        btnGo.setText("Ga");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        pnlDateSelect.add(btnGo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(pnlDateSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 1303, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1117, Short.MAX_VALUE)
                                .addComponent(btnSave))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1307, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMonth)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPreviousMonth)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1095, Short.MAX_VALUE)
                                .addComponent(btnNextMonth))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1307, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 381, Short.MAX_VALUE)
                        .addComponent(lblMonth)
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(pnlDateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnPreviousMonth)
                            .addComponent(btnNextMonth))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnBack))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        RoosterProgramma.getInstance().showPanel(new EmployeeSelect());
    }//GEN-LAST:event_btnBackActionPerformed

    private void tblTimeSheetFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblTimeSheetFocusGained
        calculateTotal();
    }//GEN-LAST:event_tblTimeSheetFocusGained

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        for (int i = 0; i < model.getRowCount() - 1; i++) {
            WorkHours hours = employee.getWorkHours(getYear() + "-" + getMonth() + "-" + model.getValueAt(i, 0).toString().split(" - ")[0]);
            for (int j = 0; j < model.getColumnCount(); j++) {
                if (model.getColumnName(j).equals("Gewerkt")) {
                    hours.setWorked(Double.parseDouble(model.getValueAt(i, j).toString()));
                } else if (model.getColumnName(j).equals("Compensatie 150")) {
                    hours.setCompensation150(Double.parseDouble(model.getValueAt(i, j).toString()));
                } else if (model.getColumnName(j).equals("Compensatie 200")) {
                    hours.setCompensation200(Double.parseDouble(model.getValueAt(i, j).toString()));
                } else if (model.getColumnName(j).equals("Vakantie")) {
                    hours.setVacation(Double.parseDouble(model.getValueAt(i, j).toString()));
                } else if (model.getColumnName(j).equals("ADV")) {
                    hours.setADV(Double.parseDouble(model.getValueAt(i, j).toString()));
                } else if (model.getColumnName(j).equals("Ziek")) {
                    hours.setIllness(Double.parseDouble(model.getValueAt(i, j).toString()));
                } else if (model.getColumnName(j).equals("Speciaal Verlof")) {
                    hours.setLeave(Double.parseDouble(model.getValueAt(i, j).toString()));
                } else if (model.getColumnName(j).equals("Project")) {
                    hours.setProject(Double.parseDouble(model.getValueAt(i, j).toString()));
                }
            }
        }
        employee.updateWorkHours();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPreviousMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousMonthActionPerformed
        handleTime(year, month - 1);
    }//GEN-LAST:event_btnPreviousMonthActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed
        handleTime(year, month + 1);
    }//GEN-LAST:event_btnNextMonthActionPerformed

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        int selectedYear = Integer.parseInt(cmbYear.getSelectedItem().toString());
        int selectedMonth = Integer.parseInt(cmbMonth.getSelectedItem().toString());
        handleTime(selectedYear, selectedMonth);
    }//GEN-LAST:event_btnGoActionPerformed

    private void handleTime(int year, int month) {
        if (month == 0) {
            month = 12;
            year -= 1;
        } else if (month == 13) {
            month = 1;
            year += 1;
        }
        RoosterProgramma.getInstance().showPanel(new EmployeeTimeSheet(employee, year, month));
    }

    private String getYear() {
        return year < 10 ? "0" + Integer.toString(year) : Integer.toString(year);
    }

    private String getMonth() {
        return month < 10 ? "0" + Integer.toString(month) : Integer.toString(month);
    }

    private String getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) : Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnPreviousMonth;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbMonth;
    private javax.swing.JComboBox cmbYear;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JPanel pnlDateSelect;
    private javax.swing.JTable tblEmployeeInformation;
    private javax.swing.JTable tblTimeSheet;
    // End of variables declaration//GEN-END:variables
}
