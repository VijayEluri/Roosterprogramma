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
    private int year, month, modifier = 0;
    private DecimalFormat format = new DecimalFormat("0.00");

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
        tblTimeSheet.setModel(new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return !model.getColumnName(colIndex).contains("Compensatie") && colIndex != 0 && !model.getColumnName(colIndex).equals("Ingeroosterd") && !model.getColumnName(colIndex).equals("Totaal") && !model.getValueAt(rowIndex, 0).toString().equals("Totaal");
            }
        });
        Translater translater = new Translater();
        model = (DefaultTableModel) tblTimeSheet.getModel();
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
            fields[9 - modifier] = (hour.getCompensation150() == 0.0 ? "" : hour.getCompensation150());
            fields[10 - modifier] = (hour.getCompensation200() == 0.0 ? "" : hour.getCompensation200());
            model.addRow(fields);
        }
        Object[] fields = (employee.isClerk() || employee.isMuseumEducator() || employee.isCallWorker()) ? new Object[]{"Totaal", 0, 0, 0, 0, 0, 0, 0, 0} : new Object[]{"Totaal", 0, 0, 0, 0, 0, 0, 0};
        model.addRow(fields);
        for (int i = 1; i <= (11 - modifier); i++) {
            tblTimeSheet.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        tblTimeSheet.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblTimeSheet.getColumnModel().getColumn(9 - modifier).setPreferredWidth(400);
    }

    private void calculateTotal() {
        int tmpModifier = 0;
        if (employee.isCallWorker() || employee.isClerk() || employee.isMuseumEducator()) {
            tmpModifier = 1;
        }
        for (int i = 0; i < model.getRowCount(); i++) {     // ToDo : Samenvoegen met die hieronder
            double totalHours = 0;
            for (int j = 1 + tmpModifier; j < model.getColumnCount() - 4; j++) {
                if (model.getValueAt(i, j) != null && !model.getValueAt(i, j).toString().isEmpty()) {
                    if (model.getValueAt(i, j).toString().equalsIgnoreCase("x1")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX1Duration();
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("x2")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX2Duration();
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("x3")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX3Duration();
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("v")) {
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("z")) {
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("c")) {
                    } else if (model.getValueAt(i, j).toString().equalsIgnoreCase("k")) {
                        totalHours += 4.5;
                    } else if (model.getValueAt(i, j).toString().equals("*")) {
                    } else {
                        totalHours += Double.parseDouble(model.getValueAt(i, j).toString());
                    }
                }
            }
            model.setValueAt(totalHours, i, model.getColumnCount() - 4);
        }
        for (int k = 1; k < model.getColumnCount() - 4; k++) {      // ToDo : Samenvoegen met die hierboven
            double totalHours = 0;
            for (int l = 0; l < model.getRowCount() - 1; l++) {
                if (model.getValueAt(l, k) != null && !model.getValueAt(l, k).toString().isEmpty()) {
                    if (model.getValueAt(l, k).toString().equalsIgnoreCase("x1")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX1Duration();
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("x2")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX2Duration();
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("x3")) {
                        totalHours += RoosterProgramma.getInstance().getSettings().getX3Duration();
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("v")) {
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("z")) {
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("c")) {
                    } else if (model.getValueAt(l, k).toString().equalsIgnoreCase("k")) {
                        totalHours += 4.5;
                    } else if (model.getValueAt(l, k).toString().equals("*")) {
                    } else {
                        totalHours += Double.parseDouble(model.getValueAt(l, k).toString());
                    }

                    if (model.getColumnName(k).toString().equals("Gewerkt")) {
                        if (model.getValueAt(l, k) != null && !model.getValueAt(l, k).toString().equals("")) {
                            double gewerkt = Double.parseDouble(model.getValueAt(l, k).toString());
                            String day = model.getValueAt(l, 0).toString().split(" - ")[0];
                            Calendar today = Calendar.getInstance();
                            today.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), Integer.parseInt(day));
                            if (RoosterProgramma.getInstance().isHoliday(today)) {       // 200%
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
        if (employee.isCallWorker()) {
            double gewerkt = model.getValueAt(model.getRowCount() - 3, 2).toString().isEmpty() ? 0 : Double.parseDouble(model.getValueAt(model.getRowCount() - 3, 2).toString());
            double ziekte = model.getValueAt(model.getRowCount() - 3, 7).toString().isEmpty() ? 0 : Double.parseDouble(model.getValueAt(model.getRowCount() - 3, 7).toString());
            String vakantieUren = Double.toString(Double.valueOf((new DecimalFormat("#.##")).format((gewerkt + ziekte) * (employee.getVacationPercentage() / 100)).replace(",", ".")));
            lblVacationHours.setText(vakantieUren);
        } else {
            pnlVacationHours.setVisible(false);
        }
        tblTimeSheet.repaint();
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
        tblEmployeeInformation.setEnabled(false);
        tblEmployeeInformation.setFocusable(false);
        tblEmployeeInformation.setRequestFocusEnabled(false);
        jspEmployeeInformation.setViewportView(tblEmployeeInformation);

        tblTimeSheet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblTimeSheet.setRowSelectionAllowed(false);
        tblTimeSheet.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblTimeSheetMouseMoved(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jspTimeSheet, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)))
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
        RoosterProgramma.getInstance().showPanel(new EmployeeOverview(false));
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (isCorrectlyFilled()) {
            for (int i = 0; i < model.getRowCount() - 3; i++) {
                WorkHours hour = RoosterProgramma.getQueryManager().getWorkHours(employee.getEmployeeNumber(), getYear() + "-" + getMonth() + "-" + model.getValueAt(i, 0).toString().split(" - ")[0]);
                for (int j = 0; j < model.getColumnCount() - 1; j++) {
                    if (model.getColumnName(1).equals("Ingeroosterd") && !model.getValueAt(i, 1).toString().equals("")) {
                        System.out.println("Saving...");
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

    private void tblTimeSheetMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTimeSheetMouseMoved
        calculateTotal();
    }//GEN-LAST:event_tblTimeSheetMouseMoved

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
        for (int i = 0; i < tblTimeSheet.getRowCount(); i++) {
            String ingeroosterd = model.getValueAt(i, 1).toString();
            if (!ingeroosterd.isEmpty() && !ingeroosterd.toString().equals("")
                    && (ingeroosterd.equalsIgnoreCase("v")
                    || ingeroosterd.equalsIgnoreCase("z")
                    || ingeroosterd.equalsIgnoreCase("c")
                    || ingeroosterd.equalsIgnoreCase("k")
                    || ingeroosterd.equalsIgnoreCase("*"))) {
                double shouldWork = Double.parseDouble(model.getValueAt(i, 1).toString());
                double haveWorked = Double.parseDouble(model.getValueAt(i, tblTimeSheet.getColumnCount() - 4).toString());
                if (haveWorked < shouldWork) {
                    String[] pieces = model.getValueAt(i, 0).toString().split(" - ");
                    RoosterProgramma.getInstance().showMessage("De urenverantwoording voor " + pieces[1] + " de " + pieces[0] + "e komt niet overeen met de ingeroosterde uren.", "Foutieve urenverantwoording.", true);
                    correct = false;
                    break;
                }
            } else if (!model.getValueAt(i, model.getColumnCount() - 4).toString().equals("0.0")) {
                correct = false;
                RoosterProgramma.getInstance().showMessage("Bij de " + (i + 1) + "e staan verantwoorde uren maar u bent op die dag niet ingeroosterd.", "Foutieve waarde", true);
                break;
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
    private javax.swing.JScrollPane jspEmployeeInformation;
    private javax.swing.JScrollPane jspTimeSheet;
    private javax.swing.JLabel lblExpVacationHours;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblVacationHours;
    private javax.swing.JPanel pnlDateSelect;
    private javax.swing.JPanel pnlVacationHours;
    private javax.swing.JTable tblEmployeeInformation;
    private javax.swing.JTable tblTimeSheet;
    // End of variables declaration//GEN-END:variables
}
