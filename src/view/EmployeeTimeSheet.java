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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import model.WorkHours;
import roosterprogramma.RoosterProgramma;
import roosterprogramma.Translater;
import roosterprogramma.Utils;

/**
 *
 * @author Dark
 */
public class EmployeeTimeSheet extends javax.swing.JPanel {

    private Employee employee;
    private DefaultTableModel model;
    private Calendar calendar = Calendar.getInstance();
    private int year, month, modifier = 0;
    private DecimalFormat format = new DecimalFormat("0.00");
    private ItemListener changeListener = new ItemListener() {

        @Override
        public void itemStateChanged(ItemEvent e) {
            int selectedYear = Integer.parseInt(cmbYear.getSelectedItem().toString());
            int selectedMonth = Integer.parseInt(cmbMonth.getSelectedItem().toString());
            handleTime(selectedYear, selectedMonth);
        }
    };

    /**
     * Creates new form medewerkerInfo
     *
     * @param employee
     * @param year
     * @param month
     */
    public EmployeeTimeSheet(Employee employee, int year, int month) {
        this.employee = employee;
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        this.year = year;
        this.month = month;
        initComponents();
        initializeTable();
        fillInfoTable();
        fillVerantwoordingTable();
        fillBoxes();
        calculateTotal();
        calculateVacation();
    }

    private void initializeTable() {

        tblTimesheet.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                calculateVacation();
                calculateTotal();
            }
        });
        tblTimesheet.setModel(new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return !model.getColumnName(colIndex).contains("Compensatie") && colIndex != 0 && !model.getColumnName(colIndex).equals("Ingeroosterd") && !model.getColumnName(colIndex).equals("Totaal") && !model.getValueAt(rowIndex, 0).toString().equals("Totaal");
            }
        });
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

    private void fillInfoTable() {
        ((DefaultTableModel) tblEmployeeInformation.getModel()).addRow(new Object[]{
                    employee.getEmployeeNumber(),
                    employee.getFirstName(),
                    employee.getFamilyName(),
                    employee.isFullTime(),
                    employee.isPartTime(),
                    employee.isCallWorker()
                });
    }

    private void fillVerantwoordingTable() {
        model = (DefaultTableModel) tblTimesheet.getModel();
        model.addColumn("Dag van de Maand");
        if (employee.isClerk() || employee.isMuseumEducator() || employee.isCallWorker()) {
            model.addColumn("Ingeroosterd");
        } else {
            modifier = 1;
        }
        model.addColumn("Gewerkt");
        model.addColumn("Vakantie");
        model.addColumn("ADV");
        model.addColumn("Ziek");
        model.addColumn("Speciaal Verlof");
        model.addColumn("Opg. compensatie");
        model.addColumn("Totaal");
        model.addColumn("Opmerking");
        model.addColumn("Compensatie 150");
        model.addColumn("Compensatie 200");
        int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysOfMonth; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            WorkHours hour = RoosterProgramma.getQueryManager().getWorkHours(employee.getEmployeeNumber(), getYear() + "-" + getMonth() + "-" + getDay());
            Object[] fields = (employee.isClerk() || employee.isMuseumEducator() || employee.isCallWorker()) ? new Object[11] : new Object[10];
            fields[0] = calendar.get(Calendar.DAY_OF_MONTH) + " - " + Translater.Translate(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
            fields[1] = hour.getShouldWorkHours();
            if (employee.isClerk() || employee.isMuseumEducator() || employee.isCallWorker()) {
                fields[2] = (hour.getWorked() == 0.0 ? "" : hour.getWorked());
            }
            fields[3 - modifier] = (hour.getVacation() == 0.0 ? "" : hour.getVacation());
            fields[4 - modifier] = (hour.getADV() == 0.0 ? "" : hour.getADV());
            fields[5 - modifier] = (hour.getIllness() == 0.0 ? "" : hour.getIllness());
            fields[6 - modifier] = (hour.getLeave() == 0.0 ? "" : hour.getLeave());
            fields[7 - modifier] = (hour.getWithdrawnCompensation() == 0.0 ? "" : hour.getWithdrawnCompensation());
            fields[9 - modifier] = hour.getComment();
            model.addRow(fields);
        }
        Object[] fields = (employee.isClerk() || employee.isMuseumEducator() || employee.isCallWorker()) ? new Object[]{"Totaal", 0, 0, 0, 0, 0, 0, 0, 0} : new Object[]{"Totaal", 0, 0, 0, 0, 0, 0, 0};
        model.addRow(fields);
        for (int i = 1; i <= (11 - modifier); i++) {
            tblTimesheet.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        tblTimesheet.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblTimesheet.getColumnModel().getColumn(9 - modifier).setPreferredWidth(400);
    }

    private void calculateVacation() {
        if (employee.isCallWorker()) {
            double gewerkt = 0;
            double ziekte = 0;
            if (model.getValueAt(model.getRowCount() - 1, 2).toString() != null && !model.getValueAt(model.getRowCount() - 1, 2).toString().isEmpty()) {
                gewerkt = Double.parseDouble(model.getValueAt(model.getRowCount() - 1, 2).toString().replace(",", "."));
            }
            if (model.getValueAt(model.getRowCount() - 1, 5).toString() != null && !model.getValueAt(model.getRowCount() - 1, 5).toString().isEmpty()) {
                ziekte = Double.parseDouble(model.getValueAt(model.getRowCount() - 1, 5).toString().replace(",", "."));
            }
            double vakantieUren = Double.valueOf((gewerkt + ziekte) * (employee.getVacationPercentage() / 100));
            lblVacationHours.setText(Double.toString(vakantieUren));
        } else {
            lblVacationHours.setVisible(false);
            lblExpVacationHours.setVisible(false);
        }
    }

    private void calculateTotal() {
        int tmpModifier = 0;
        if (employee.isCallWorker() || employee.isClerk() || employee.isMuseumEducator()) {
            tmpModifier = 1;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            double totalHours = 0;
            for (int j = 1 + tmpModifier; j < model.getColumnCount() - 4; j++) {
                if (model.getValueAt(i, j) != null && !model.getValueAt(i, j).toString().isEmpty()) {
                    totalHours += verifyInput(i, j);
                }
            }
            model.setValueAt(totalHours, i, model.getColumnCount() - 4);
        }
        for (int k = 1; k < model.getColumnCount() - 4; k++) {
            double totalHours = 0;
            for (int l = 0; l < model.getRowCount() - 1; l++) {
                if (model.getValueAt(l, k) != null && !model.getValueAt(l, k).toString().isEmpty()) {
                    totalHours += verifyInput(l, k);

                    if (model.getColumnName(k).toString().equals("Gewerkt")) {
                        if (model.getValueAt(l, k) != null && !model.getValueAt(l, k).toString().isEmpty()) {
                            double gewerkt = Double.parseDouble(model.getValueAt(l, k).toString().replace(",", "."));
                            String day = model.getValueAt(l, 0).toString().split(" - ")[0];
                            Calendar today = Calendar.getInstance();
                            today.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), Integer.parseInt(day));
                            if (Utils.isHoliday(today)) {       // 200%
                                model.setValueAt(format.format(gewerkt), l, model.getColumnCount() - 1);
                            } else if (today.get(Calendar.DAY_OF_WEEK) == 2) {       // 150%
                                model.setValueAt(format.format(gewerkt / 2), l, model.getColumnCount() - 2);
                            }
                        }
                    }
                }
            }
            model.setValueAt(totalHours, model.getRowCount() - 1, k);
        }
        tblTimesheet.repaint();
    }

    private double verifyInput(int row, int column) {
        String value = model.getValueAt(row, column).toString().replace(",", ".");
        Double hours = 0.0;
        if (Utils.isNumeric(value)) {
            hours = Double.parseDouble(value);
        } else {
            Utils.showMessage("Fout opgetreden, foutieve invoer (" + value + ") in veld (" + row + ", " + column + ")", "Fout!", null, false);
        }
        return hours;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblExpVacationHours = new javax.swing.JLabel();
        lblVacationHours = new javax.swing.JLabel();
        jspTimesheet = new javax.swing.JScrollPane();
        tblTimesheet = new javax.swing.JTable();
        jspEmployeeInformation = new javax.swing.JScrollPane();
        tblEmployeeInformation = new javax.swing.JTable();
        pnlDateSelect = new javax.swing.JPanel();
        btnPreviousMonth = new javax.swing.JButton();
        cmbYear = new javax.swing.JComboBox<Integer>();
        cmbMonth = new javax.swing.JComboBox<Integer>();
        btnNextMonth = new javax.swing.JButton();

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnPrevious.png"))); // NOI18N
        btnBack.setToolTipText("Vorige");
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnSave.png"))); // NOI18N
        btnSave.setToolTipText("Opslaan");
        btnSave.setContentAreaFilled(false);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblExpVacationHours.setText("Opgebouwde vakantieuren:");

        lblVacationHours.setText("0");

        tblTimesheet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTimesheet.getTableHeader().setReorderingAllowed(false);
        jspTimesheet.setViewportView(tblTimesheet);

        jspEmployeeInformation.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tblEmployeeInformation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Personeelsnummer", "Voornaam", "Achternaam", "Fulltime", "Parttime", "Oproepkracht", "Noodhulp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
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
        jspEmployeeInformation.setViewportView(tblEmployeeInformation);

        btnPreviousMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnMinus.png"))); // NOI18N
        btnPreviousMonth.setToolTipText("Vorige maand");
        btnPreviousMonth.setContentAreaFilled(false);
        btnPreviousMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousMonthActionPerformed(evt);
            }
        });
        pnlDateSelect.add(btnPreviousMonth);

        pnlDateSelect.add(cmbYear);

        pnlDateSelect.add(cmbMonth);

        btnNextMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnPlus.png"))); // NOI18N
        btnNextMonth.setToolTipText("Volgende maand");
        btnNextMonth.setContentAreaFilled(false);
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });
        pnlDateSelect.add(btnNextMonth);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspEmployeeInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
                    .addComponent(jspTimesheet)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addGap(18, 18, 18)
                        .addComponent(lblExpVacationHours)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblVacationHours)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave))
                    .addComponent(pnlDateSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspEmployeeInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTimesheet, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBack)
                        .addComponent(btnSave))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblExpVacationHours)
                        .addComponent(lblVacationHours)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (tblTimesheet.getCellEditor() != null) {
            tblTimesheet.getCellEditor().stopCellEditing();
        }
        if (isCorrectlyFilled()) {
            ArrayList<Integer> failures = new ArrayList<Integer>();
            for (int i = 0; i < model.getRowCount() - 1; i++) {
                String date = getYear() + "-" + getMonth() + "-" + model.getValueAt(i, 0).toString().split(" - ")[0];
                WorkHours hour = RoosterProgramma.getQueryManager().getWorkHours(employee.getEmployeeNumber(), date);
                for (int j = 0; j < model.getColumnCount() - 1; j++) {
                    String value = model.getValueAt(i, j).toString();
                    String columnName = model.getColumnName(j);
                    if (model.getColumnName(1).equals("Ingeroosterd") && !model.getValueAt(i, j).toString().isEmpty()) {
                        if (model.getColumnName(j).equals("Gewerkt")) {
                            hour.setWorked(value.isEmpty() ? 0 : Double.parseDouble(value.replace(",", ".")));
                        } else if (columnName.equals("Compensatie 150")) {
                            hour.setCompensation150(value.isEmpty() ? 0 : Double.parseDouble(value.replace(",", ".")));
                        } else if (columnName.equals("Compensatie 200")) {
                            hour.setCompensation200(value.isEmpty() ? 0 : Double.parseDouble(value.replace(",", ".")));
                        } else if (columnName.equals("Vakantie")) {
                            hour.setVacation(value.isEmpty() ? 0 : Double.parseDouble(value.replace(",", ".")));
                        } else if (columnName.equals("ADV")) {
                            hour.setADV(value.isEmpty() ? 0 : Double.parseDouble(value.replace(",", ".")));
                        } else if (columnName.equals("Ziek")) {
                            hour.setIllness(value.isEmpty() ? 0 : Double.parseDouble(value.replace(",", ".")));
                        } else if (columnName.equals("Speciaal Verlof")) {
                            hour.setLeave(value.isEmpty() ? 0 : Double.parseDouble(value.replace(",", ".")));
                        } else if (columnName.equals("Opg. compensatie")) {
                            hour.setWithdrawnCompensation(value.isEmpty() ? 0 : Double.parseDouble(value.replace(",", ".")));
                        } else if (columnName.equals("Opmerking")) {
                            hour.setComment(value);
                        }
                    }
                }
                if (!hour.equals(new WorkHours(employee.getEmployeeNumber(), date))) {
                    if (!RoosterProgramma.getQueryManager().updateWorkHours(hour)) {
                        failures.add(i);
                    }
                }
            }
            if (failures.isEmpty()) {
                Utils.showMessage("Succesvol opgeslagen.", "Opslaan gelukt!", null, false);
            } else {
                String errors = "";
                for (int failure : failures) {
                    errors += "\nRij " + failure;
                }
                Utils.showMessage("Opslaan van de volgende rijen is niet gelukt: " + errors, "Fout!", null, false);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPreviousMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousMonthActionPerformed
        handleTime(year, month - 1);
    }//GEN-LAST:event_btnPreviousMonthActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed
        handleTime(year, month + 1);
    }//GEN-LAST:event_btnNextMonthActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        RoosterProgramma.getInstance().showPanel(new EmployeeOverview(false));
    }//GEN-LAST:event_btnBackActionPerformed

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

    private boolean isCorrectlyFilled() {
        for (int i = 0; i < tblTimesheet.getRowCount(); i++) {
            String ingeroosterd = model.getValueAt(i, 1).toString();
            if (!ingeroosterd.isEmpty() && Utils.isNumeric(ingeroosterd)) {
                String shouldWork = model.getValueAt(i, 1).toString().replace(",", ".");
                double haveWorked = Double.parseDouble(model.getValueAt(i, tblTimesheet.getColumnCount() - 4).toString().replace(",", "."));
                if (Utils.isNumeric(shouldWork)) {
                    if (haveWorked < Double.parseDouble(shouldWork)) {
                        String[] pieces = model.getValueAt(i, 0).toString().split(" - ");
                        Utils.showMessage("De urenverantwoording voor " + pieces[1] + " de " + pieces[0] + "e komt niet overeen met de ingeroosterde uren.", "Foutieve urenverantwoording.", null, false);
                        return false;
                    }
                }
            } else if (!model.getValueAt(i, model.getColumnCount() - 4).toString().equals("0.0")) {
                Utils.showMessage("Bij de " + (i + 1) + "e staan verantwoorde uren maar u bent op die dag niet ingeroosterd.", "Foutieve urenverantwoording.", null, false);
                return false;
            } else {
                Utils.showMessage("Bij de " + (i + 1) + "e staat een teken, geen getal.\nAlleen in het rooster hebben letters betekenis.", "Foutieve urenverantwoording.", null, false);
                return false;
            }
        }
        return true;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnPreviousMonth;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<Integer> cmbMonth;
    private javax.swing.JComboBox<Integer> cmbYear;
    private javax.swing.JScrollPane jspEmployeeInformation;
    private javax.swing.JScrollPane jspTimesheet;
    private javax.swing.JLabel lblExpVacationHours;
    private javax.swing.JLabel lblVacationHours;
    private javax.swing.JPanel pnlDateSelect;
    private javax.swing.JTable tblEmployeeInformation;
    private javax.swing.JTable tblTimesheet;
    // End of variables declaration//GEN-END:variables
}
