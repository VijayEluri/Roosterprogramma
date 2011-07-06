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

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JTable;
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
        calculateTotal();
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
        tblTimeSheet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int modifier = 0;
        model = (DefaultTableModel) tblTimeSheet.getModel();
        DefaultTableModel modelCompensation = (DefaultTableModel) tblCompensation.getModel();
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
        int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysOfMonth; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            WorkHours hour = RoosterProgramma.getQueryManager().getWorkHours(employee.getEmployeeNumber(), getYear() + "-" + getMonth() + "-" + getDay());
            Object[] fields;
            if (employee.isClerk() || employee.isMuseumEducator() || employee.isCallWorker()) {
                fields = new Object[11];
            } else {
                fields = new Object[10];
            }
            fields[0] = calendar.get(Calendar.DAY_OF_MONTH) + " - " + translater.Translate(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
            fields[1] = hour.getShouldWorkHours();
            if (employee.isClerk() || employee.isMuseumEducator() || employee.isCallWorker()) {
                fields[2] = (hour.getWorked() == 0.0 ? "" : hour.getWorked());
            }
            fields[3 - modifier] = (hour.getVacation() == 0.0 ? "" : hour.getVacation());
            fields[4 - modifier] = (hour.getADV() == 0.0 ? "" : hour.getADV());
            fields[5 - modifier] = (hour.getIllness() == 0.0 ? "" : hour.getIllness());
            fields[6 - modifier] = (hour.getLeave() == 0.0 ? "" : hour.getLeave());
            fields[7 - modifier] = (hour.getProject() == 0.0 ? "" : hour.getProject());
            fields[8 - modifier] = 0;
            model.addRow(fields);
            modelCompensation.addRow(new Object[] {
                (hour.getCompensation150() == 0.0 ? "" : hour.getCompensation150()),
                (hour.getCompensation200() == 0.0 ? "" : hour.getCompensation200())
            });
        }
        Object[] fields;
        if (employee.isClerk() || employee.isMuseumEducator() || employee.isCallWorker()) {
            fields = new Object[]{"Totaal", 0, 0, 0, 0, 0, 0, 0, 0};
        } else {
            fields = new Object[]{"Totaal", 0, 0, 0, 0, 0, 0, 0};
        }
        model.addRow(fields);
        for (int i = 1; i <= (8 - modifier); i++) {
            tblTimeSheet.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        tblTimeSheet.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblTimeSheet.getColumnModel().getColumn(9-modifier).setPreferredWidth(400);
        modelCompensation.addRow(new Object[]{0, 0});
    }

    private void calculateTotal() {
        int modifier = 0;
        if (employee.isCallWorker() || employee.isClerk() || employee.isMuseumEducator()) {
            modifier = 1;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            double totalHours = 0;
            for (int j = 1 + modifier; j < model.getColumnCount() - 2; j++) {
                if (model.getValueAt(i, j) != null && !model.getValueAt(i, j).toString().isEmpty()) {
                    if (model.getValueAt(i, j).toString().equalsIgnoreCase("x1")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX1Duration();
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("x2")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX2Duration();
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("x3")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX3Duration();
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("v")) {

                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("z")) {

                    } else {
                        totalHours += Double.parseDouble(model.getValueAt(i, j).toString());
                    }
                } else {
                }
            }
            model.setValueAt(totalHours, i, model.getColumnCount() - 2);
        }
        for (int k = 1; k < model.getColumnCount() - 1; k++) {
            double totalHours = 0;
            for (int l = 0; l < model.getRowCount() - 1 - modifier; l++) {
                if (model.getValueAt(l, k) != null && !model.getValueAt(l, k).toString().isEmpty()) {
                    if (model.getValueAt(l, k).toString().equalsIgnoreCase("x1")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX1Duration();
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("x2")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX2Duration();
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("x3")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX3Duration();
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("v")) {

                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("z")) {

                    } else {
                        totalHours += Double.parseDouble(model.getValueAt(l, k).toString());
                    }
                }
            }
            model.setValueAt(totalHours, model.getRowCount() - 1, k);
        }
        tblTimeSheet.repaint();
        if (employee.isCallWorker()) {
            double gewerkt = model.getValueAt(model.getRowCount()-1, 2).toString().isEmpty() ? 0 : Double.parseDouble(model.getValueAt(model.getRowCount()-1, 2).toString());
            double ziekte = model.getValueAt(model.getRowCount()-1, 7).toString().isEmpty() ? 0 : Double.parseDouble(model.getValueAt(model.getRowCount()-1, 7).toString());
            String vakantieUren = Double.toString(Double.valueOf((new DecimalFormat("#.##")).format((gewerkt + ziekte) * (employee.getVacationPercentage()/100)).replace(",", ".")));
            lblVacationHours.setText(vakantieUren);
        } else {
            pnlVacationHours.setVisible(false);
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
        jspEmployeeInformation = new javax.swing.JScrollPane();
        tblEmployeeInformation = new javax.swing.JTable();
        jspTimeSheet = new javax.swing.JScrollPane();
        tblTimeSheet = new javax.swing.JTable();
        lblMonth = new javax.swing.JLabel();
        btnPreviousMonth = new javax.swing.JButton();
        btnNextMonth = new javax.swing.JButton();
        pnlDateSelect = new javax.swing.JPanel();
        cmbYear = new javax.swing.JComboBox();
        cmbMonth = new javax.swing.JComboBox();
        btnGo = new javax.swing.JButton();
        pnlVacationHours = new javax.swing.JPanel();
        lblExpVacationHours = new javax.swing.JLabel();
        lblVacationHours = new javax.swing.JLabel();
        jspCompensation = new javax.swing.JScrollPane();
        tblCompensation = new javax.swing.JTable();

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
        jspEmployeeInformation.setViewportView(tblEmployeeInformation);

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
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblTimeSheetFocusLost(evt);
            }
        });
        tblTimeSheet.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                tblTimeSheetCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jspTimeSheet.setViewportView(tblTimeSheet);

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

        lblExpVacationHours.setText("Opgebouwde vakantieuren:");

        lblVacationHours.setText("<uren>");

        javax.swing.GroupLayout pnlVacationHoursLayout = new javax.swing.GroupLayout(pnlVacationHours);
        pnlVacationHours.setLayout(pnlVacationHoursLayout);
        pnlVacationHoursLayout.setHorizontalGroup(
            pnlVacationHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacationHoursLayout.createSequentialGroup()
                .addComponent(lblExpVacationHours)
                .addGap(18, 18, 18)
                .addComponent(lblVacationHours)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlVacationHoursLayout.setVerticalGroup(
            pnlVacationHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacationHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblExpVacationHours)
                .addComponent(lblVacationHours))
        );

        tblCompensation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Compensatie 150", "Compensatie 200"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspCompensation.setViewportView(tblCompensation);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(pnlDateSelect, javax.swing.GroupLayout.DEFAULT_SIZE, 1018, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jspEmployeeInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMonth)
                                    .addComponent(btnPreviousMonth))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 814, Short.MAX_VALUE)
                                .addComponent(btnNextMonth))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlVacationHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 614, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jspTimeSheet, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspCompensation, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspEmployeeInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDateSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNextMonth)
                    .addComponent(btnPreviousMonth))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspCompensation, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(lblMonth)
                    .addComponent(jspTimeSheet, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlVacationHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        if (isCorrectlyFilled()) {
            for (int i = 0; i < model.getRowCount() - 1; i++) {
                WorkHours hour = RoosterProgramma.getQueryManager().getWorkHours(employee.getEmployeeNumber(), getYear() + "-" + getMonth() + "-" + model.getValueAt(i, 0).toString().split(" - ")[0]);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    if (model.getColumnName(j).equals("Gewerkt")) {
                        hour.setWorked(model.getValueAt(i, j).toString().equals("") ? 0 : Double.parseDouble(model.getValueAt(i, j).toString()));
                    } else if (model.getColumnName(j).equals("Compensatie 150")) {
                        hour.setCompensation150(model.getValueAt(i, j).toString().equals("") ? 0 : Double.parseDouble(model.getValueAt(i, j).toString()));
                    } else if (model.getColumnName(j).equals("Compensatie 200")) {
                        hour.setCompensation200(model.getValueAt(i, j).toString().equals("") ? 0 : Double.parseDouble(model.getValueAt(i, j).toString()));
                    } else if (model.getColumnName(j).equals("Vakantie")) {
                        hour.setVacation(model.getValueAt(i, j).toString().equals("") ? 0 : Double.parseDouble(model.getValueAt(i, j).toString()));
                    } else if (model.getColumnName(j).equals("ADV")) {
                        hour.setADV(model.getValueAt(i, j).toString().equals("") ? 0 : Double.parseDouble(model.getValueAt(i, j).toString()));
                    } else if (model.getColumnName(j).equals("Ziek")) {
                        hour.setIllness(model.getValueAt(i, j).toString().equals("") ? 0 : Double.parseDouble(model.getValueAt(i, j).toString()));
                    } else if (model.getColumnName(j).equals("Speciaal Verlof")) {
                        hour.setLeave(model.getValueAt(i, j).toString().equals("") ? 0 : Double.parseDouble(model.getValueAt(i, j).toString()));
                    } else if (model.getColumnName(j).equals("Project")) {
                        hour.setProject(model.getValueAt(i, j).toString().equals("") ? 0 : Double.parseDouble(model.getValueAt(i, j).toString()));
                    }
                }
                RoosterProgramma.getQueryManager().updateWorkHours(hour);
            }
            RoosterProgramma.getInstance().showMessage("Succesvol opgeslagen.", "Opslaan gelukt!", false);
        }
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

    private void tblTimeSheetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblTimeSheetFocusLost
        calculateTotal();
    }//GEN-LAST:event_tblTimeSheetFocusLost

    private void tblTimeSheetCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tblTimeSheetCaretPositionChanged
        calculateTotal();
    }//GEN-LAST:event_tblTimeSheetCaretPositionChanged

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
        boolean correct = true;
        for(int i = 0; i < tblTimeSheet.getRowCount(); i++) {
            if (!model.getValueAt(i, 1).toString().isEmpty()) {
                double shouldWork = Double.parseDouble(model.getValueAt(i, 1).toString());
                double haveWorked = Double.parseDouble(model.getValueAt(i, tblTimeSheet.getColumnCount()-2).toString());
                if (haveWorked < shouldWork) {
                    String[] pieces = model.getValueAt(i, 0).toString().split(" - ");
                    RoosterProgramma.getInstance().showMessage("De urenverantwoording voor " + pieces[1] + " de " + pieces[0] + "e komt niet overeen met de ingeroosterde uren.", "Foutieve urenverantwoording.", true);
                    correct = false;
                    break;
                }
            }
        }
        return correct;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnPreviousMonth;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbMonth;
    private javax.swing.JComboBox cmbYear;
    private javax.swing.JScrollPane jspCompensation;
    private javax.swing.JScrollPane jspEmployeeInformation;
    private javax.swing.JScrollPane jspTimeSheet;
    private javax.swing.JLabel lblExpVacationHours;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblVacationHours;
    private javax.swing.JPanel pnlDateSelect;
    private javax.swing.JPanel pnlVacationHours;
    private javax.swing.JTable tblCompensation;
    private javax.swing.JTable tblEmployeeInformation;
    private javax.swing.JTable tblTimeSheet;
    // End of variables declaration//GEN-END:variables
}
