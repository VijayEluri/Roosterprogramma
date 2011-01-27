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
public class Rooster extends javax.swing.JPanel {

    private DefaultTableModel model;
    private Translater translater = new Translater();
    private Calendar calendar = Calendar.getInstance();
    private int year, month;

    /** Creates new form Rooster */
    public Rooster(int year, int month) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month-1);
        this.year = year;
        this.month = month;
        initComponents();
        process();
        fillBoxes();
    }

    private void fillBoxes() {
        for (int i = -20; i <= 20; i++)
        {
            cmbYear.addItem(calendar.get(Calendar.YEAR)+i);
        }
        cmbYear.setSelectedItem(year);
        for (int j = 1; j <= 12; j++)
        {
            cmbMonth.addItem(j);
        }
        cmbMonth.setSelectedItem(month);
    }

    private void process() {
        model = (DefaultTableModel) tblSchedule.getModel();
        model.addColumn("Naam");
        model.addColumn("ContractUren");
        int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysOfMonth; i++)
        {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            String day = translater.Translate(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH));
            model.addColumn(i + " - " + day.substring(0, 2));
        }
        for (Employee employee : RoosterProgramma.getQueryManager().getEmployees())
        {
            Object[] fields = new Object[daysOfMonth+2];
            if (!employee.getInsertion().isEmpty())
            {
                fields[0] = employee.getFirstName() + " " + employee.getInsertion() + " " + employee.getFamilyName();
            }
            else
            {
                fields[0] = employee.getFirstName() + " " + employee.getFamilyName();
            }
            fields[1] = employee.getContractHours();
            for (int i = 1; i <= daysOfMonth; i++)
            {
                calendar.set(Calendar.DAY_OF_MONTH, i);
                handleField(calendar, employee, fields);
            }
            model.addRow(fields);
        }
    }

    private void handleField(Calendar calendar, Employee employee, Object[] fields) {
        WorkHours hour = employee.getWorkHours(getDate(calendar));
        fields[calendar.get(Calendar.DAY_OF_MONTH)+1] = hour.getShouldWork();
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

        pnlTable = new javax.swing.JScrollPane();
        tblSchedule = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNextMonth = new javax.swing.JButton();
        btnPreviousMonth = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cmbYear = new javax.swing.JComboBox();
        cmbMonth = new javax.swing.JComboBox();
        btnGo = new javax.swing.JButton();

        tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSchedule.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        pnlTable.setViewportView(tblSchedule);

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

        btnNextMonth.setText("Volgende Maand");
        btnNextMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMonthActionPerformed(evt);
            }
        });

        btnPreviousMonth.setText("Vorige Maand");
        btnPreviousMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousMonthActionPerformed(evt);
            }
        });

        jPanel1.add(cmbYear);

        jPanel1.add(cmbMonth);

        btnGo.setText("Ga");
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        jPanel1.add(btnGo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 710, Short.MAX_VALUE)
                        .addComponent(btnSave))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPreviousMonth)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextMonth)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextMonth)
                    .addComponent(btnPreviousMonth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnBack))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        RoosterProgramma.getInstance().showPanel(new MainMenu());
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        for (int i = 0; i < model.getRowCount(); i++)
        {
            String[] pieces = model.getValueAt(i, 0).toString().split(" ");
            String firstName = pieces[0];
            String insertion = "";
            for (int k = 1; k < pieces.length-1; k++)
            {
                insertion += pieces[k] + " ";
            }
            if (!insertion.isEmpty())
            {
                insertion.substring(0, insertion.length()-1);
            }
            String familyName = pieces[pieces.length-1];
            Employee employee = RoosterProgramma.getQueryManager().getEmployee(firstName, insertion, familyName);
            for (int j = 2; j < model.getColumnCount(); j++)
            {
                pieces = model.getColumnName(j).split(" - ");
                String day = pieces[0];
                WorkHours hours = employee.getWorkHours(year + "-" + getMonth() + "-" + day);
                hours.setShouldWork(model.getValueAt(i, j).toString());
                hours.update();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPreviousMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousMonthActionPerformed
        handleTime(year, month-1);
    }//GEN-LAST:event_btnPreviousMonthActionPerformed

    private void btnNextMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMonthActionPerformed
        handleTime(year, month+1);
    }//GEN-LAST:event_btnNextMonthActionPerformed

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed
        int selectedYear = Integer.parseInt(cmbYear.getSelectedItem().toString());
        int selectedMonth = Integer.parseInt(cmbMonth.getSelectedItem().toString());
        handleTime(selectedYear, selectedMonth);
    }//GEN-LAST:event_btnGoActionPerformed

    private void handleTime(int year, int month) {
        if (month == 0)
        {
            month = 12;
            year -= 1;
        }
        else if (month == 13)
        {
            month = 1;
            year += 1;
        }
        RoosterProgramma.getInstance().showPanel(new Rooster(year, month));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnGo;
    private javax.swing.JButton btnNextMonth;
    private javax.swing.JButton btnPreviousMonth;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbMonth;
    private javax.swing.JComboBox cmbYear;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane pnlTable;
    private javax.swing.JTable tblSchedule;
    // End of variables declaration//GEN-END:variables

}
