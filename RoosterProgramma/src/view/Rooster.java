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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Employee;
import model.WorkHours;
import roosterprogramma.RoosterProgramma;

/**
 *
 * @author Dark
 */
public class Rooster extends javax.swing.JPanel {

    private DefaultTableModel model;

    /** Creates new form Rooster */
    public Rooster() {
        initComponents();
        process();
    }

    private void process() {
        model = (DefaultTableModel) tblSchedule.getModel();
        model.addColumn("Naam");
        Calendar calendar = Calendar.getInstance();
        lblYear.setText(Integer.toString(calendar.get(Calendar.YEAR)));
        lblMonth.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));
        int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= daysOfMonth; i++)
        {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            model.addColumn(calendar.get(Calendar.DAY_OF_MONTH));
        }
        for (Employee employee : RoosterProgramma.getQueryManager().getEmployees())
        {
            Object[] fields = new Object[daysOfMonth+1];
            fields[0] = employee.getFirstName() + " " + employee.getFamilyName();
            for (int i = 1; i <= daysOfMonth; i++)
            {
                calendar.set(Calendar.DAY_OF_MONTH, i);
                handleField(calendar, employee, fields);
            }
            model.addRow(fields);
        }
    }

    private void handleField(Calendar calendar, Employee employee, Object[] fields) {
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        String month = Integer.toString(calendar.get(Calendar.MONTH)+1).length() < 2 ? "0" + Integer.toString(calendar.get(Calendar.MONTH)+1) : Integer.toString(calendar.get(Calendar.MONTH)+1);
        String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)).length() < 2 ? "0" + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) : Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        WorkHours hour = employee.getWorkHours(year + "-" + month + "-" + day);
        fields[calendar.get(Calendar.DAY_OF_MONTH)] = hour.getShouldWork();
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
        pnlLegenda = new javax.swing.JScrollPane();
        pnlColors = new javax.swing.JPanel();
        pnlRed = new javax.swing.JPanel();
        pnlGreen = new javax.swing.JPanel();
        lblVacation = new javax.swing.JLabel();
        lblIllness = new javax.swing.JLabel();
        lblPregnancy = new javax.swing.JLabel();
        pnlPurple = new javax.swing.JPanel();
        lblYear = new javax.swing.JLabel();
        lblMonth = new javax.swing.JLabel();

        tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
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

        pnlRed.setBackground(new java.awt.Color(255, 0, 0));
        pnlRed.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout pnlRedLayout = new javax.swing.GroupLayout(pnlRed);
        pnlRed.setLayout(pnlRedLayout);
        pnlRedLayout.setHorizontalGroup(
            pnlRedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        pnlRedLayout.setVerticalGroup(
            pnlRedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        pnlGreen.setBackground(new java.awt.Color(0, 204, 0));
        pnlGreen.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout pnlGreenLayout = new javax.swing.GroupLayout(pnlGreen);
        pnlGreen.setLayout(pnlGreenLayout);
        pnlGreenLayout.setHorizontalGroup(
            pnlGreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        pnlGreenLayout.setVerticalGroup(
            pnlGreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        lblVacation.setText("Vakantie");

        lblIllness.setText("Ziekteverlof");

        lblPregnancy.setText("Zwangerschapsverlof");

        pnlPurple.setBackground(new java.awt.Color(204, 0, 204));
        pnlPurple.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout pnlPurpleLayout = new javax.swing.GroupLayout(pnlPurple);
        pnlPurple.setLayout(pnlPurpleLayout);
        pnlPurpleLayout.setHorizontalGroup(
            pnlPurpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        pnlPurpleLayout.setVerticalGroup(
            pnlPurpleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlColorsLayout = new javax.swing.GroupLayout(pnlColors);
        pnlColors.setLayout(pnlColorsLayout);
        pnlColorsLayout.setHorizontalGroup(
            pnlColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlColorsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlColorsLayout.createSequentialGroup()
                        .addComponent(pnlRed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblVacation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                        .addComponent(pnlPurple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPregnancy)
                        .addGap(136, 136, 136))
                    .addGroup(pnlColorsLayout.createSequentialGroup()
                        .addComponent(pnlGreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblIllness)
                        .addContainerGap(399, Short.MAX_VALUE))))
        );
        pnlColorsLayout.setVerticalGroup(
            pnlColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlColorsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPurple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblVacation)
                        .addComponent(lblPregnancy))
                    .addComponent(pnlRed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlColorsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlGreen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIllness))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pnlLegenda.setViewportView(pnlColors);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                    .addComponent(pnlLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 710, Short.MAX_VALUE)
                        .addComponent(btnSave))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblYear)
                        .addGap(18, 18, 18)
                        .addComponent(lblMonth)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYear)
                    .addComponent(lblMonth))
                .addGap(18, 18, 18)
                .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlLegenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        
        Calendar calendar = Calendar.getInstance();
        String month = Integer.toString(calendar.get(Calendar.MONTH)+1);
        String year = Integer.toString(calendar.get(Calendar.YEAR));
        for (int i = 0; i < model.getRowCount(); i++)
        {
            String[] pieces = model.getValueAt(i, 0).toString().split(" ");
            String firstName = pieces[0];
            String familyName = pieces[1];
            Employee employee = RoosterProgramma.getQueryManager().getEmployee(firstName, familyName);
            for (int j = 1; j < model.getColumnCount(); j++)
            {
                String day = model.getColumnName(j);
                WorkHours hours = employee.getWorkHours(year + "-" + month + "-" + day);
                double shouldWork = Double.parseDouble(model.getValueAt(i, j).toString());
                hours.setShouldWork(shouldWork);
                hours.update();
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void promptWarning() {
        int choice = JOptionPane.showConfirmDialog(
            null,
            "",
            "Waarschuwing!",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION)
        {

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblIllness;
    private javax.swing.JLabel lblMonth;
    private javax.swing.JLabel lblPregnancy;
    private javax.swing.JLabel lblVacation;
    private javax.swing.JLabel lblYear;
    private javax.swing.JPanel pnlColors;
    private javax.swing.JPanel pnlGreen;
    private javax.swing.JScrollPane pnlLegenda;
    private javax.swing.JPanel pnlPurple;
    private javax.swing.JPanel pnlRed;
    private javax.swing.JScrollPane pnlTable;
    private javax.swing.JTable tblSchedule;
    // End of variables declaration//GEN-END:variables

}
