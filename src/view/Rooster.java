/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Rooster.java
 *
 * Created on 21-jan-2011, 11:32:49
 */
package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import model.WorkHours;
import roosterprogramma.ExcelExporter;
import roosterprogramma.RoosterProgramma;
import roosterprogramma.Translater;

/**
 *
 * @author Dark
 */
public class Rooster extends javax.swing.JPanel {

    private DefaultTableModel model;
    private Translater translater = new Translater();
    private Calendar calendar = Calendar.getInstance();
    private int year, month;
    private ItemListener changeListener = new ItemListener() {

        public void itemStateChanged(ItemEvent e) {
            int selectedYear = Integer.parseInt(cmbYear.getSelectedItem().toString());
            int selectedMonth = Integer.parseInt(cmbMonth.getSelectedItem().toString());
            handleTime(selectedYear, selectedMonth);
        }
    };

    /** Creates new form Rooster */
    public Rooster(int year, int month) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        this.year = year;
        this.month = month;
        initComponents();
        fillBoxes();
        process();
    }

    private void fillBoxes() {
        for (int i = -20; i <= 20; i++) {
            cmbYear.addItem(calendar.get(Calendar.YEAR) + i);
        }
        for (int j = 1; j <= 12; j++) {
            cmbMonth.addItem(j);
        }
        cmbYear.setSelectedItem(year);
        cmbMonth.setSelectedItem(month);
        cmbYear.addItemListener(changeListener);
        cmbMonth.addItemListener(changeListener);
    }

    private void process() {
        tblSchedule.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model = (DefaultTableModel) tblSchedule.getModel();
        model.addColumn("Naam");
        model.addColumn("Contracturen");
        int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysOfMonth; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            String day = translater.Translate(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
            model.addColumn(i + " - " + day.substring(0, 2));
        }
        for (Employee employee : RoosterProgramma.getQueryManager().getEmployees()) {
            if (employee.isCallWorker() || employee.isClerk() || employee.isMuseumEducator()) {
                Object[] fields = new Object[daysOfMonth + 2];
                fields[0] = employee.getEmployeeNumber() + " - " + employee.getFullName();
                fields[1] = employee.getContractHours();
                for (int i = 1; i <= daysOfMonth; i++) {
                    calendar.set(Calendar.DAY_OF_MONTH, i);
                    handleField(calendar, employee, fields);
                }
                model.addRow(fields);
            }
        }
        for (int i = 1; i <= daysOfMonth; i++) {
            tblSchedule.getColumnModel().getColumn(i + 1).setPreferredWidth(50);
        }
        tblSchedule.getColumnModel().getColumn(0).setPreferredWidth(150);
    }

    private void handleField(Calendar calendar, Employee employee, Object[] fields) {
        WorkHours hour = RoosterProgramma.getQueryManager().getWorkHours(employee.getEmployeeNumber(), getDate(calendar));
        fields[calendar.get(Calendar.DAY_OF_MONTH) + 1] = hour.getShouldWork();
    }

    private String getDate(Calendar calendar) {
        String strDay = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)).length() < 2 ? "0" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) : Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        return year + "-" + getMonth() + "-" + strDay;
    }

    private String getMonth() {
        return month < 10 ? "0" + Integer.toString(month) : Integer.toString(month);
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
        btnPreviousMonth = new javax.swing.JButton();
        pnlControls = new javax.swing.JPanel();
        cmbYear = new javax.swing.JComboBox();
        cmbMonth = new javax.swing.JComboBox();
        btnExcelExport = new javax.swing.JButton();
        btnNextMonth = new javax.swing.JButton();
        jspSchedule = new javax.swing.JScrollPane();
        tblSchedule = new javax.swing.JTable();

        btnSave.setText("Opslaan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setText("Terug");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnPreviousMonth.setText("Vorige Maand");
        btnPreviousMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousMonthActionPerformed(evt);
            }
        });

        pnlControls.add(cmbYear);

        pnlControls.add(cmbMonth);

        btnExcelExport.setText("Exporteer naar excel");
        btnExcelExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelExportActionPerformed(evt);
            }
        });

        btnNextMonth.setText("Volgende Maand");
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });

        tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSchedule.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblSchedule.setMaximumSize(new java.awt.Dimension(2500, 1700));
        jspSchedule.setViewportView(tblSchedule);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 1706, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1417, Short.MAX_VALUE)
                        .addComponent(btnExcelExport)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnPreviousMonth)
                        .addGap(18, 18, 18)
                        .addComponent(pnlControls, javax.swing.GroupLayout.DEFAULT_SIZE, 1458, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextMonth)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextMonth)
                    .addComponent(btnPreviousMonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnBack)
                    .addComponent(btnExcelExport))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        RoosterProgramma.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        for (int i = 0; i < model.getRowCount(); i++) {
            String[] pieces = model.getValueAt(i, 0).toString().split(" - ");
            Employee employee = RoosterProgramma.getQueryManager().getEmployee(Integer.parseInt(pieces[0]));
            for (int j = 2; j < model.getColumnCount(); j++) {
                String date = year + "-" + getMonth() + "-" + model.getColumnName(j).split(" - ")[0];
                String shouldWork = model.getValueAt(i, j).toString();
                if (isValidWorkHour(shouldWork)) {
                    WorkHours hour = RoosterProgramma.getQueryManager().getWorkHours(employee.getEmployeeNumber(), date);
                    if (!hour.getShouldWork().equals(shouldWork)) {
                        hour.setShouldWork(shouldWork);
                        RoosterProgramma.getQueryManager().updateWorkHours(hour);
                    }
                } else {
                    if (!shouldWork.equals("")) {
                        RoosterProgramma.getInstance().showMessage("De waarde ingevuld voor " + employee.getFullName() + " op " + date + " is incorrect.", "Incorrecte veldwaarde!", true);
                    }
                }
            }
        }
        RoosterProgramma.getInstance().showMessage("Succesvol opgeslagen.", "Opslaan gelukt!", false);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPreviousMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousMonthActionPerformed
        handleTime(year, month - 1);
    }//GEN-LAST:event_btnPreviousMonthActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed
        handleTime(year, month + 1);
    }//GEN-LAST:event_btnNextMonthActionPerformed

    private void btnExcelExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelExportActionPerformed
        String input = RoosterProgramma.getInstance().showFileChooser("Opslaan");
        if (!input.isEmpty()) {
            boolean inverted = RoosterProgramma.getInstance().promptQuestion("Wilt u de tabel omgedraaid of precies zoals hierboven?", false, "Zoals hierboven", "Omgedraaid");
            ExcelExporter.Export(tblSchedule, new File(input.contains(".xls") ? input : input + ".xls"), inverted);
        }
    }//GEN-LAST:event_btnExcelExportActionPerformed

    private boolean isValidWorkHour(String shouldWork) {
        return (shouldWork.equalsIgnoreCase("z")
                || shouldWork.equalsIgnoreCase("x1")
                || shouldWork.equalsIgnoreCase("x2")
                || shouldWork.equalsIgnoreCase("x3")
                || shouldWork.equalsIgnoreCase("v")
                || RoosterProgramma.getInstance().isNumeric(shouldWork));
    }

    private void handleTime(int year, int month) {
        if (month == 0) {
            month = 12;
            year -= 1;
        } else if (month == 13) {
            month = 1;
            year += 1;
        }
        RoosterProgramma.getInstance().showPanel(new Rooster(year, month));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExcelExport;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnPreviousMonth;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbMonth;
    private javax.swing.JComboBox cmbYear;
    private javax.swing.JScrollPane jspSchedule;
    private javax.swing.JPanel pnlControls;
    private javax.swing.JTable tblSchedule;
    // End of variables declaration//GEN-END:variables
}
