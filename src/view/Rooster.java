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
import java.util.List;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import model.WorkHours;
import roosterprogramma.ExcelExporter;
import roosterprogramma.RoosterProgramma;
import roosterprogramma.Translater;
import roosterprogramma.Utils;

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

        @Override
        public void itemStateChanged(ItemEvent e) {
            int selectedYear = Integer.parseInt(cmbYear.getSelectedItem().toString());
            int selectedMonth = Integer.parseInt(cmbMonth.getSelectedItem().toString());
            handleTime(selectedYear, selectedMonth);
        }
    };

    /**
     * Creates new form Rooster
     *
     * @param year
     * @param month
     */
    public Rooster(int year, int month) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        this.year = year;
        this.month = month;
        initComponents();
        refreshTable();
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
        refreshTable();
        tblSchedule.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model = (DefaultTableModel) tblSchedule.getModel();
        model.addColumn("Naam");
        model.addColumn("Contracturen");
        int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int j = 1; j <= daysOfMonth; j++) {
            calendar.set(Calendar.DAY_OF_MONTH, j);
            model.addColumn(j + " - " + translater.Translate(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH)).substring(0, 2));
        }
        fill(daysOfMonth);
        tblSchedule.getColumnModel().getColumn(0).setPreferredWidth(150);
    }

    private void fill(int daysOfMonth) {
        removeRows();
        for (Employee employee : RoosterProgramma.getInstance().getEmployees()) {
            if ((chkClerk.isSelected() && employee.isClerk())
                    || (chkMuseumEducator.isSelected() && employee.isMuseumEducator())
                    || (chkCallWorker.isSelected() && employee.isCallWorker())) {
                insertEmployeeIntoTable(employee, daysOfMonth);
            }
        }
        for (int i = 1; i <= daysOfMonth; i++) {
            tblSchedule.getColumnModel().getColumn(i + 1).setPreferredWidth(50);
        }
    }

    private void handleField(Calendar calendar, Employee employee, Object[] fields) {
        WorkHours hour = RoosterProgramma.getQueryManager().getWorkHours(employee.getEmployeeNumber(), getDate(calendar));
        fields[calendar.get(Calendar.DAY_OF_MONTH) + 1] = hour.getShouldWork();
    }

    private String getDate(Calendar calendar) {
        return year
                + "-"
                + getMonth()
                + "-"
                + (Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)).length() < 2 ? "0" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) : Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
    }

    private String getMonth() {
        return month < 10 ? "0" + Integer.toString(month) : Integer.toString(month);
    }

    private void refreshTable() {
        tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}) {

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return colIndex != 0 && colIndex != 1;
            }

            @Override
            public void fireTableCellUpdated(int row, int column) {
                String value = model.getValueAt(row, column).toString();
                if (value.equalsIgnoreCase("x1")) {
                    model.setValueAt(RoosterProgramma.getInstance().getSettings().getX1(), row, column);
                } else if (value.equalsIgnoreCase("x2")) {
                    model.setValueAt(RoosterProgramma.getInstance().getSettings().getX2(), row, column);
                } else if (value.equalsIgnoreCase("x3")) {
                    model.setValueAt(RoosterProgramma.getInstance().getSettings().getX3(), row, column);
                }
                super.fireTableCellUpdated(row, column);
            }
        });
        jspSchedule.setViewportView(tblSchedule);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        pnlControls = new javax.swing.JPanel();
        btnPreviousMonth = new javax.swing.JButton();
        chkCallWorker = new javax.swing.JCheckBox();
        chkClerk = new javax.swing.JCheckBox();
        chkMuseumEducator = new javax.swing.JCheckBox();
        cmbYear = new javax.swing.JComboBox<Integer>();
        cmbMonth = new javax.swing.JComboBox<Integer>();
        btnNextMonth = new javax.swing.JButton();
        btnExcelExport = new javax.swing.JButton();
        jspSchedule = new javax.swing.JScrollPane();
        tblSchedule = new javax.swing.JTable();
        tfVoornaam = new javax.swing.JTextField();
        tfAchternaam = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfPersoneelsnummer = new javax.swing.JFormattedTextField();

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnSave.png"))); // NOI18N
        btnSave.setToolTipText("Opslaan");
        btnSave.setContentAreaFilled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnPrevious.png"))); // NOI18N
        btnBack.setToolTipText("Vorige");
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnPreviousMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnMinus.png"))); // NOI18N
        btnPreviousMonth.setToolTipText("Vorige Maand");
        btnPreviousMonth.setContentAreaFilled(false);
        btnPreviousMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousMonthActionPerformed(evt);
            }
        });
        pnlControls.add(btnPreviousMonth);

        chkCallWorker.setSelected(true);
        chkCallWorker.setText("Oproepkracht");
        chkCallWorker.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkCallWorkerItemStateChanged(evt);
            }
        });
        pnlControls.add(chkCallWorker);

        chkClerk.setSelected(true);
        chkClerk.setText("Baliemedewerker");
        chkClerk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkClerkItemStateChanged(evt);
            }
        });
        pnlControls.add(chkClerk);

        chkMuseumEducator.setSelected(true);
        chkMuseumEducator.setText("Museumdocent");
        chkMuseumEducator.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkMuseumEducatorItemStateChanged(evt);
            }
        });
        pnlControls.add(chkMuseumEducator);

        pnlControls.add(cmbYear);

        pnlControls.add(cmbMonth);

        btnNextMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnPlus.png"))); // NOI18N
        btnNextMonth.setToolTipText("Volgende Maand");
        btnNextMonth.setContentAreaFilled(false);
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });
        pnlControls.add(btnNextMonth);

        btnExcelExport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnExport.png"))); // NOI18N
        btnExcelExport.setToolTipText("Exporteer naar excel");
        btnExcelExport.setContentAreaFilled(false);
        btnExcelExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelExportActionPerformed(evt);
            }
        });

        jspSchedule.setToolTipText("");

        tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSchedule.setToolTipText("Mogelijke invoer: Z, V, C, K, *, X1, X2, X3");
        tblSchedule.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblSchedule.setMaximumSize(new java.awt.Dimension(25000, 17000));
        jspSchedule.setViewportView(tblSchedule);

        tfVoornaam.setText("Voornaam");
        tfVoornaam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfVoornaamFocusGained(evt);
            }
        });
        tfVoornaam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfVoornaamKeyReleased(evt);
            }
        });

        tfAchternaam.setText("Achternaam");
        tfAchternaam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfAchternaamFocusGained(evt);
            }
        });
        tfAchternaam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfAchternaamKeyReleased(evt);
            }
        });

        jLabel1.setText("of personeelsnummer:");

        tfPersoneelsnummer.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tfPersoneelsnummer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPersoneelsnummerKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlControls, javax.swing.GroupLayout.DEFAULT_SIZE, 1310, Short.MAX_VALUE)
                    .addComponent(jspSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 1310, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1001, Short.MAX_VALUE)
                        .addComponent(btnExcelExport)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfVoornaam, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAchternaam, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(tfPersoneelsnummer, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfVoornaam)
                    .addComponent(tfAchternaam)
                    .addComponent(jLabel1)
                    .addComponent(tfPersoneelsnummer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnBack))
                    .addComponent(btnExcelExport))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        RoosterProgramma.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (tblSchedule.getCellEditor() != null) {
            tblSchedule.getCellEditor().stopCellEditing();
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            Employee employee = RoosterProgramma.getInstance().getEmployee(Integer.parseInt(model.getValueAt(i, 0).toString().split(" - ")[0]));
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
                    if (!shouldWork.isEmpty()) {
                        Utils.showMessage("De waarde ingevuld voor " + employee.getFullName() + " op " + date + " is incorrect.", "Incorrecte veldwaarde!", true, null);
                        return;
                    }
                }
            }
        }
        Utils.showMessage("Succesvol opgeslagen.", "Opslaan gelukt!", false, null);
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPreviousMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousMonthActionPerformed
        handleTime(year, month - 1);
    }//GEN-LAST:event_btnPreviousMonthActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed
        handleTime(year, month + 1);
    }//GEN-LAST:event_btnNextMonthActionPerformed

    private void btnExcelExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelExportActionPerformed
        String input = Utils.showFileChooser("Opslaan");
        if (!input.isEmpty()) {
            boolean inverted = Utils.promptQuestion("Wilt u de tabel omgedraaid of precies zoals hierboven?", false, "Zoals hierboven", "Omgedraaid");
            ExcelExporter.Export(tblSchedule, new File(input.contains(".xls") ? input : input + ".xls"), inverted);
        }
    }//GEN-LAST:event_btnExcelExportActionPerformed

    private void tfVoornaamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfVoornaamKeyReleased
        searchTable();
    }//GEN-LAST:event_tfVoornaamKeyReleased

    private void tfAchternaamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAchternaamKeyReleased
        searchTable();
    }//GEN-LAST:event_tfAchternaamKeyReleased

    private void tfPersoneelsnummerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPersoneelsnummerKeyReleased
        searchTable();
    }//GEN-LAST:event_tfPersoneelsnummerKeyReleased

    private void tfVoornaamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfVoornaamFocusGained
        if (tfVoornaam.getText().equals("Voornaam")) {
            tfVoornaam.setText("");
        }
    }//GEN-LAST:event_tfVoornaamFocusGained

    private void tfAchternaamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfAchternaamFocusGained
        if (tfAchternaam.getText().equals("Achternaam")) {
            tfAchternaam.setText("");
        }
    }//GEN-LAST:event_tfAchternaamFocusGained

    private void chkMuseumEducatorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkMuseumEducatorItemStateChanged
        process();
    }//GEN-LAST:event_chkMuseumEducatorItemStateChanged

    private void chkCallWorkerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkCallWorkerItemStateChanged
        process();
    }//GEN-LAST:event_chkCallWorkerItemStateChanged

    private void chkClerkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkClerkItemStateChanged
        process();
    }//GEN-LAST:event_chkClerkItemStateChanged

    private void insertEmployeeIntoTable(Employee employee, int daysOfMonth) {
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

    private void searchTable() {
        removeRows();
        if (!tfPersoneelsnummer.getText().isEmpty()) {
            Employee employee = RoosterProgramma.getInstance().getEmployee(Integer.parseInt(tfPersoneelsnummer.getText()));
            if (employee != null) {
                insertEmployeeIntoTable(employee, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
        } else {
            if (!tfVoornaam.getText().isEmpty() || !tfAchternaam.getText().isEmpty()) {
                String voornaam = tfVoornaam.getText().equals("Voornaam") ? "" : tfVoornaam.getText();
                String achternaam = tfAchternaam.getText().equals("Achternaam") ? "" : tfAchternaam.getText();
                List<Employee> employees = RoosterProgramma.getInstance().searchEmployee(voornaam, achternaam);
                for (Employee employee : employees) {
                    insertEmployeeIntoTable(employee, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                }
            } else {
                process();
            }
        }
    }

    private boolean isValidWorkHour(String shouldWork) {
        return (shouldWork.equalsIgnoreCase("x1")
                || shouldWork.equalsIgnoreCase("x2")
                || shouldWork.equalsIgnoreCase("x3")
                || shouldWork.equalsIgnoreCase("z")
                || shouldWork.equalsIgnoreCase("v")
                || shouldWork.equalsIgnoreCase("c")
                || shouldWork.equalsIgnoreCase("k")
                || shouldWork.equals("*")
                || Utils.isNumeric(shouldWork));
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

    public void removeRows() {
        while (model.getRowCount() != 0) {
            model.removeRow(0);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExcelExport;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnPreviousMonth;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkCallWorker;
    private javax.swing.JCheckBox chkClerk;
    private javax.swing.JCheckBox chkMuseumEducator;
    private javax.swing.JComboBox<Integer> cmbMonth;
    private javax.swing.JComboBox<Integer> cmbYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jspSchedule;
    private javax.swing.JPanel pnlControls;
    private javax.swing.JTable tblSchedule;
    private javax.swing.JTextField tfAchternaam;
    private javax.swing.JFormattedTextField tfPersoneelsnummer;
    private javax.swing.JTextField tfVoornaam;
    // End of variables declaration//GEN-END:variables
}
