/*
 * AddMedewerker.java
 *
 * Created on 19-jan-2011, 23:20:39
 */
package view;

import connectivity.ShaEncrypt;
import model.Employee;
import roosterprogramma.RoosterProgramma;

/**
 *
 * @author Dymion
 */
public class ChAddEmployee extends javax.swing.JPanel {

    private Employee employee = new Employee();
    private boolean isAdd = true;

    /** Creates new form AddMedewerker */
    public ChAddEmployee() {
        initComponents();
        clearUI();
        btnSave.setEnabled(false);
    }

    public ChAddEmployee(Employee employee) {
        this.employee = employee;
        this.isAdd = false;
        initComponents();
        clearUI();
        fillFields();
        checkEnabled();
    }
    
    private void clearUI() {
        pnlHours.setVisible(false);
        pnlDays.setVisible(false);
        pnlVacationPercentage.setVisible(false);
        lblNameInUse.setVisible(false);
        lblNumberInUse.setVisible(false);
    }

    private void fillFields() {
        tfEmployeeNumber.setText(Integer.toString(employee.getEmployeeNumber()));
        tfFirstName.setText(employee.getFirstName());
        tfInsertion.setText(employee.getInsertion());
        tfFamilyName.setText(employee.getFamilyName());
        if (employee.isClerk()) {
            cbClerk.setSelected(true);
        } else if (employee.isMuseumEducator()) {
            cbMuseumEducator.setSelected(true);
        } else {
            cbOther.setSelected(true);
        }
        if (employee.isPartTime()) {
            cbPartTime.setSelected(true);
            tfHours.setText(Double.toString(employee.getContractHours()));
        } else if (employee.isCallWorker()) {
            cbCallWorker.setSelected(true);
            tfHours.setText(Double.toString(employee.getContractHours()));
        } else {
            cbFullTime.setSelected(true);
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

        buttonGroup = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        lblEmployeeNumber = new javax.swing.JLabel();
        lblFirstName = new javax.swing.JLabel();
        lblFamilyName = new javax.swing.JLabel();
        lblWachtwoord = new javax.swing.JLabel();
        tfEmployeeNumber = new javax.swing.JTextField();
        tfFirstName = new javax.swing.JTextField();
        tfFamilyName = new javax.swing.JTextField();
        lblClerk = new javax.swing.JLabel();
        cbClerk = new javax.swing.JCheckBox();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        tfPassword = new javax.swing.JPasswordField();
        lblNameInUse = new javax.swing.JLabel();
        lblNumberInUse = new javax.swing.JLabel();
        pnlExtra = new javax.swing.JPanel();
        cbCallWorker = new javax.swing.JCheckBox();
        lblCallWorker = new javax.swing.JLabel();
        cbPartTime = new javax.swing.JCheckBox();
        lblPartTime = new javax.swing.JLabel();
        cbFullTime = new javax.swing.JCheckBox();
        lblFullTime = new javax.swing.JLabel();
        pnlHours = new javax.swing.JPanel();
        lblHours = new javax.swing.JLabel();
        tfHours = new javax.swing.JFormattedTextField();
        pnlDays = new javax.swing.JPanel();
        lblInstruction = new javax.swing.JLabel();
        lblMonday = new javax.swing.JLabel();
        cbMonday = new javax.swing.JCheckBox();
        lblTuesday = new javax.swing.JLabel();
        cbTuesday = new javax.swing.JCheckBox();
        cbWednesday = new javax.swing.JCheckBox();
        lblWednesday = new javax.swing.JLabel();
        lblThursday = new javax.swing.JLabel();
        cbThursday = new javax.swing.JCheckBox();
        cbFriday = new javax.swing.JCheckBox();
        lblFriday = new javax.swing.JLabel();
        lblSaturday = new javax.swing.JLabel();
        cbSaturday = new javax.swing.JCheckBox();
        cbSunday = new javax.swing.JCheckBox();
        lblSunday = new javax.swing.JLabel();
        pnlVacationPercentage = new javax.swing.JPanel();
        lblVacationPercentage = new javax.swing.JLabel();
        tfVacationPercentage = new javax.swing.JFormattedTextField();
        cbMuseumEducator = new javax.swing.JCheckBox();
        lblMuseumEducator = new javax.swing.JLabel();
        lblInsertion = new javax.swing.JLabel();
        tfInsertion = new javax.swing.JTextField();
        lblOther = new javax.swing.JLabel();
        cbOther = new javax.swing.JCheckBox();

        lblEmployeeNumber.setText("PersoneelsNummer:");

        lblFirstName.setText("Voornaam:");

        lblFamilyName.setText("Achternaam:");

        lblWachtwoord.setText("Wachtwoord:");

        tfEmployeeNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEmployeeNumberKeyReleased(evt);
            }
        });

        tfFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFirstNameKeyReleased(evt);
            }
        });

        tfFamilyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFamilyNameKeyReleased(evt);
            }
        });

        lblClerk.setText("Baliemedewerker:");

        buttonGroup.add(cbClerk);
        cbClerk.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbClerkStateChanged(evt);
            }
        });

        btnCancel.setText("Annuleren");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnSave.setText("Opslaan");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPasswordKeyReleased(evt);
            }
        });

        lblNameInUse.setText("De combinatie voor- en achternaam is al in gebruik!");

        lblNumberInUse.setText("Dat nummer is al in gebruik!");

        buttonGroup1.add(cbCallWorker);
        cbCallWorker.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbCallWorkerStateChanged(evt);
            }
        });

        lblCallWorker.setText("Oproepkracht:");

        buttonGroup1.add(cbPartTime);
        cbPartTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbPartTimeStateChanged(evt);
            }
        });

        lblPartTime.setText("Parttime:");

        buttonGroup1.add(cbFullTime);
        cbFullTime.setSelected(true);
        cbFullTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbFullTimeStateChanged(evt);
            }
        });

        lblFullTime.setText("Fulltime:");

        lblHours.setText("Minimum aantal uren per week:");

        tfHours.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfHoursKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlHoursLayout = new javax.swing.GroupLayout(pnlHours);
        pnlHours.setLayout(pnlHoursLayout);
        pnlHoursLayout.setHorizontalGroup(
            pnlHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoursLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHours)
                .addGap(18, 18, 18)
                .addComponent(tfHours, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHoursLayout.setVerticalGroup(
            pnlHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoursLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHours)
                    .addComponent(tfHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(456, Short.MAX_VALUE))
        );

        lblInstruction.setText("Niet in te roosteren dagen:");

        lblMonday.setText("Maandag");

        lblTuesday.setText("Dinsdag");

        lblWednesday.setText("Woensdag");

        lblThursday.setText("Donderdag");

        lblFriday.setText("Vrijdag");

        lblSaturday.setText("Zaterdag");

        lblSunday.setText("Zondag");

        javax.swing.GroupLayout pnlDaysLayout = new javax.swing.GroupLayout(pnlDays);
        pnlDays.setLayout(pnlDaysLayout);
        pnlDaysLayout.setHorizontalGroup(
            pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDaysLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInstruction, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDaysLayout.createSequentialGroup()
                        .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThursday)
                            .addComponent(lblWednesday)
                            .addComponent(lblTuesday)
                            .addComponent(lblMonday)
                            .addComponent(lblFriday)
                            .addComponent(lblSaturday)
                            .addComponent(lblSunday))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSunday)
                            .addComponent(cbSaturday)
                            .addComponent(cbFriday)
                            .addComponent(cbMonday)
                            .addComponent(cbTuesday)
                            .addComponent(cbWednesday)
                            .addComponent(cbThursday))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDaysLayout.setVerticalGroup(
            pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDaysLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInstruction)
                .addGap(18, 18, 18)
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMonday)
                    .addComponent(cbMonday))
                .addGap(18, 18, 18)
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTuesday)
                    .addComponent(cbTuesday))
                .addGap(18, 18, 18)
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWednesday)
                    .addComponent(cbWednesday))
                .addGap(18, 18, 18)
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThursday)
                    .addComponent(cbThursday))
                .addGap(18, 18, 18)
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFriday)
                    .addComponent(cbFriday))
                .addGap(18, 18, 18)
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSaturday)
                    .addComponent(cbSaturday))
                .addGap(18, 18, 18)
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSunday)
                    .addComponent(cbSunday))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        lblVacationPercentage.setText("Vakantiepercentage:");

        tfVacationPercentage.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        tfVacationPercentage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfVacationPercentageKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlVacationPercentageLayout = new javax.swing.GroupLayout(pnlVacationPercentage);
        pnlVacationPercentage.setLayout(pnlVacationPercentageLayout);
        pnlVacationPercentageLayout.setHorizontalGroup(
            pnlVacationPercentageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacationPercentageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblVacationPercentage)
                .addGap(18, 18, 18)
                .addComponent(tfVacationPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlVacationPercentageLayout.setVerticalGroup(
            pnlVacationPercentageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacationPercentageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlVacationPercentageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVacationPercentage)
                    .addComponent(tfVacationPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(445, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlExtraLayout = new javax.swing.GroupLayout(pnlExtra);
        pnlExtra.setLayout(pnlExtraLayout);
        pnlExtraLayout.setHorizontalGroup(
            pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExtraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlExtraLayout.createSequentialGroup()
                        .addGroup(pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFullTime)
                            .addComponent(lblPartTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbFullTime)
                            .addComponent(cbPartTime)))
                    .addGroup(pnlExtraLayout.createSequentialGroup()
                        .addComponent(lblCallWorker)
                        .addGap(18, 18, 18)
                        .addComponent(cbCallWorker)))
                .addGap(18, 18, 18)
                .addComponent(pnlHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlVacationPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(284, Short.MAX_VALUE))
        );
        pnlExtraLayout.setVerticalGroup(
            pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHours, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlExtraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFullTime)
                    .addComponent(cbFullTime))
                .addGap(18, 18, 18)
                .addGroup(pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPartTime)
                    .addComponent(cbPartTime))
                .addGap(18, 18, 18)
                .addGroup(pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCallWorker)
                    .addComponent(lblCallWorker))
                .addContainerGap(377, Short.MAX_VALUE))
            .addComponent(pnlDays, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlExtraLayout.createSequentialGroup()
                .addComponent(pnlVacationPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        buttonGroup.add(cbMuseumEducator);
        cbMuseumEducator.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbMuseumEducatorStateChanged(evt);
            }
        });

        lblMuseumEducator.setText("Museumdocent:");

        lblInsertion.setText("Tussenvoegsel:");

        lblOther.setText("Anders:");

        buttonGroup.add(cbOther);
        cbOther.setSelected(true);
        cbOther.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbOtherStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlExtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInsertion)
                            .addComponent(lblMuseumEducator)
                            .addComponent(lblClerk)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbMuseumEducator)
                                    .addComponent(cbClerk)))
                            .addComponent(lblOther)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(cbOther))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEmployeeNumber)
                                            .addComponent(lblFirstName))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tfFirstName, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfEmployeeNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                                            .addComponent(tfInsertion, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfFamilyName, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfPassword, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addComponent(lblFamilyName)
                                    .addComponent(lblWachtwoord))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblNumberInUse)
                                    .addComponent(lblNameInUse))))
                        .addGap(506, 506, 506))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 972, Short.MAX_VALUE)
                        .addComponent(btnSave)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeNumber)
                    .addComponent(tfEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumberInUse))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstName)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInsertion)
                    .addComponent(tfInsertion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFamilyName)
                    .addComponent(tfFamilyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNameInUse))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWachtwoord)
                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMuseumEducator)
                    .addComponent(cbMuseumEducator))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblClerk)
                    .addComponent(cbClerk))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOther)
                    .addComponent(cbOther))
                .addGap(18, 18, 18)
                .addComponent(pnlExtra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        RoosterProgramma.getInstance().showPanel(new EmployeeOverview(true));
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int employeeNumber = Integer.parseInt(tfEmployeeNumber.getText());
        String firstName = tfFirstName.getText();
        String insertion = tfInsertion.getText();
        String familyName = tfFamilyName.getText();
        String password = RoosterProgramma.getInstance().decodePassword(tfPassword.getPassword());
        boolean fullTime = cbFullTime.isSelected();
        boolean partTime = cbPartTime.isSelected();
        boolean callWorker = cbCallWorker.isSelected();
        boolean clerk = cbClerk.isSelected();
        boolean museumEducator = cbMuseumEducator.isSelected();
        Double vacationPercentage = 0.0;
        Double contractHours = 40.0;    // ToDo : Vervangen met fulltime ? 40.0 : Double.parseDouble(tfHours.getText());
        if (!fullTime) {
            contractHours = Double.parseDouble(tfHours.getText());
        }
        if (callWorker) {
            vacationPercentage = Double.parseDouble(tfVacationPercentage.getText());
        }

        if (isAdd) {
            Employee newEmployee = new Employee(employeeNumber, firstName, insertion, familyName, ShaEncrypt.SHA1(password), fullTime, partTime, callWorker, clerk, museumEducator, contractHours, vacationPercentage, false);
            RoosterProgramma.getQueryManager().addEmployee(newEmployee);
        } else {
            employee.setFirstName(firstName);
            employee.setInsertion(insertion);
            employee.setFamilyName(familyName);
            employee.setPassword(ShaEncrypt.SHA1(password));
            employee.setFullTime(fullTime);
            employee.setPartTime(partTime);
            employee.setCallWorker(callWorker);
            employee.setClerk(clerk);
            employee.setMuseumEducator(museumEducator);
            employee.setContractHours(contractHours);
            employee.setVacationPercentage(vacationPercentage);
            employee.update();
        }
        RoosterProgramma.getInstance().showPanel(new EmployeeOverview(true));

    }//GEN-LAST:event_btnSaveActionPerformed

    private void tfEmployeeNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmployeeNumberKeyReleased
        checkEnabled();
    }//GEN-LAST:event_tfEmployeeNumberKeyReleased

    private void tfFirstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFirstNameKeyReleased
        checkEnabled();
    }//GEN-LAST:event_tfFirstNameKeyReleased

    private void tfFamilyNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFamilyNameKeyReleased
        checkEnabled();
    }//GEN-LAST:event_tfFamilyNameKeyReleased

    private void tfPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPasswordKeyReleased
        checkEnabled();
    }//GEN-LAST:event_tfPasswordKeyReleased

    private void cbFullTimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbFullTimeStateChanged
        checkEnabled();
        handlePanels();
    }//GEN-LAST:event_cbFullTimeStateChanged

    private void cbPartTimeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbPartTimeStateChanged
        checkEnabled();
        handlePanels();
    }//GEN-LAST:event_cbPartTimeStateChanged

    private void cbCallWorkerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbCallWorkerStateChanged
        checkEnabled();
        handlePanels();
    }//GEN-LAST:event_cbCallWorkerStateChanged

    private void cbClerkStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbClerkStateChanged
        checkEnabled();
        handlePanels();
    }//GEN-LAST:event_cbClerkStateChanged

    private void cbMuseumEducatorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbMuseumEducatorStateChanged
        checkEnabled();
        handlePanels();
    }//GEN-LAST:event_cbMuseumEducatorStateChanged

    private void cbOtherStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbOtherStateChanged
        checkEnabled();
        handlePanels();
    }//GEN-LAST:event_cbOtherStateChanged

    private void tfHoursKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHoursKeyReleased
        checkEnabled();
    }//GEN-LAST:event_tfHoursKeyReleased

    private void tfVacationPercentageKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfVacationPercentageKeyReleased
        if (evt.getKeyChar() == '.') {
            tfVacationPercentage.setText(tfVacationPercentage.getText().replace(".", ","));
        }
        checkEnabled();
    }//GEN-LAST:event_tfVacationPercentageKeyReleased

    private void checkEnabled() {
        if (!numberInUse()
                && !nameInUse()
                && (!RoosterProgramma.getInstance().decodePassword(tfPassword.getPassword()).isEmpty() || !isAdd)
                && (cbMuseumEducator.isSelected() || cbClerk.isSelected() || cbOther.isSelected())
                && (cbFullTime.isSelected()
                || ((cbPartTime.isSelected()
                || (cbCallWorker.isSelected()
                && !tfVacationPercentage.getText().isEmpty()))
                && !tfHours.getText().isEmpty()
                && Integer.parseInt(tfHours.getText()) <= 40))) {
            btnSave.setEnabled(true);
        } else {
            btnSave.setEnabled(false);
        }
    }

    private boolean nameInUse() {
        if (tfFirstName.getText().isEmpty() || tfFamilyName.getText().isEmpty()) {
            lblNameInUse.setVisible(false);
            return true;
        }
        String firstName = tfFirstName.getText().toLowerCase();
        String familyName = tfFamilyName.getText().toLowerCase();
        Employee existingEmployee = RoosterProgramma.getQueryManager().getEmployee(firstName, "", familyName);
        if (existingEmployee.getFirstName().toLowerCase().equals(firstName)
                && existingEmployee.getFamilyName().toLowerCase().equals(familyName)
                && (isAdd
                || existingEmployee.getEmployeeNumber() != employee.getEmployeeNumber())) {
            lblNameInUse.setVisible(true);
            return true;
        } else {
            lblNameInUse.setVisible(false);
            return false;
        }
    }
    
    private boolean numberInUse() {
        if (tfEmployeeNumber.getText().isEmpty() || !RoosterProgramma.getInstance().isNumeric(tfEmployeeNumber.getText())) {
            lblNumberInUse.setVisible(false);
            return true;
        } else {
            Employee existingEmployee = RoosterProgramma.getQueryManager().getEmployee(Integer.parseInt(tfEmployeeNumber.getText()));
            if (existingEmployee.getEmployeeNumber() == Integer.parseInt(tfEmployeeNumber.getText())
                    && (isAdd
                    || (!existingEmployee.getFirstName().toLowerCase().equals(employee.getFirstName().toLowerCase())
                    || !existingEmployee.getFamilyName().toLowerCase().equals(employee.getFamilyName().toLowerCase())))) {
                lblNumberInUse.setVisible(true);
                return true;
            } else {
                lblNumberInUse.setVisible(false);
                return false;
            }
        }
    }

    private void handlePanels() {
        pnlDays.setVisible(cbClerk.isSelected() || cbCallWorker.isSelected() || cbMuseumEducator.isSelected());
        pnlHours.setVisible(cbCallWorker.isSelected() || cbPartTime.isSelected());
        pnlVacationPercentage.setVisible(cbCallWorker.isSelected());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbCallWorker;
    private javax.swing.JCheckBox cbClerk;
    private javax.swing.JCheckBox cbFriday;
    private javax.swing.JCheckBox cbFullTime;
    private javax.swing.JCheckBox cbMonday;
    private javax.swing.JCheckBox cbMuseumEducator;
    private javax.swing.JCheckBox cbOther;
    private javax.swing.JCheckBox cbPartTime;
    private javax.swing.JCheckBox cbSaturday;
    private javax.swing.JCheckBox cbSunday;
    private javax.swing.JCheckBox cbThursday;
    private javax.swing.JCheckBox cbTuesday;
    private javax.swing.JCheckBox cbWednesday;
    private javax.swing.JLabel lblCallWorker;
    private javax.swing.JLabel lblClerk;
    private javax.swing.JLabel lblEmployeeNumber;
    private javax.swing.JLabel lblFamilyName;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblFriday;
    private javax.swing.JLabel lblFullTime;
    private javax.swing.JLabel lblHours;
    private javax.swing.JLabel lblInsertion;
    private javax.swing.JLabel lblInstruction;
    private javax.swing.JLabel lblMonday;
    private javax.swing.JLabel lblMuseumEducator;
    private javax.swing.JLabel lblNameInUse;
    private javax.swing.JLabel lblNumberInUse;
    private javax.swing.JLabel lblOther;
    private javax.swing.JLabel lblPartTime;
    private javax.swing.JLabel lblSaturday;
    private javax.swing.JLabel lblSunday;
    private javax.swing.JLabel lblThursday;
    private javax.swing.JLabel lblTuesday;
    private javax.swing.JLabel lblVacationPercentage;
    private javax.swing.JLabel lblWachtwoord;
    private javax.swing.JLabel lblWednesday;
    private javax.swing.JPanel pnlDays;
    private javax.swing.JPanel pnlExtra;
    private javax.swing.JPanel pnlHours;
    private javax.swing.JPanel pnlVacationPercentage;
    private javax.swing.JTextField tfEmployeeNumber;
    private javax.swing.JTextField tfFamilyName;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JFormattedTextField tfHours;
    private javax.swing.JTextField tfInsertion;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JFormattedTextField tfVacationPercentage;
    // End of variables declaration//GEN-END:variables
}
