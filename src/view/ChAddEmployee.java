/*
 * AddMedewerker.java
 *
 * Created on 19-jan-2011, 23:20:39
 */
package view;

import model.Employee;
import model.Employee.contractTypes;
import model.Employee.employeeTypes;
import roosterprogramma.RoosterProgramma;
import roosterprogramma.Utils;

/**
 *
 * @author Dymion
 */
public class ChAddEmployee extends javax.swing.JPanel {

    private Employee employee = new Employee();
    private boolean isAdd = true;

    /**
     * Creates new form AddMedewerker
     */
    public ChAddEmployee() {
        initComponents();
        clearUI();
        btnSave.setEnabled(false);
    }

    /**
     *
     * @param employee
     */
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

    private void checkEnabled() {
        /*
         * if (!numberInUse() && !nameInUse() && (cbMuseumEducator.isSelected()
         * || cbClerk.isSelected() || cbOther.isSelected()) &&
         * (cbFullTime.isSelected() || ((cbPartTime.isSelected() ||
         * (pnlVacationPercentage.isVisible() &&
         * !tfVacationPercentage.getText().isEmpty())) &&
         * !tfHours.getText().isEmpty() &&
         * Double.parseDouble(tfHours.getText().replace(",", ".")) <= 40.0))) {
         * btnSave.setEnabled(true);
         */
        if (!numberInUse()
                && !nameInUse()
                && (!pnlHours.isVisible() || (pnlHours.isVisible() && !tfHours.getText().isEmpty()))
                && (!pnlVacationPercentage.isVisible() || (pnlVacationPercentage.isVisible() && !tfVacationPercentage.getText().isEmpty()))) {
            btnSave.setEnabled(true);
        } else {
            btnSave.setEnabled(false);
        }
    }

    private boolean nameInUse() {
        if (tfFirstName.getText().isEmpty() || tfFamilyName.getText().isEmpty()) {
            lblNameInUse.setVisible(false);
            return true;
        } else {
            String firstName = tfFirstName.getText();
            String familyName = tfFamilyName.getText();
            Employee existingEmployee = RoosterProgramma.getInstance().getEmployee(firstName, "", familyName);
            if (existingEmployee != null
                    && (isAdd
                    || existingEmployee.getEmployeeNumber() != employee.getEmployeeNumber())) {
                lblNameInUse.setVisible(true);
                return true;
            } else {
                lblNameInUse.setVisible(false);
                return false;
            }
        }
    }

    private boolean numberInUse() {
        if (tfEmployeeNumber.getText().isEmpty() || !Utils.isNumeric(tfEmployeeNumber.getText())) {
            lblNumberInUse.setVisible(false);
            return true;
        } else {
            Employee existingEmployee = RoosterProgramma.getInstance().getEmployee(Integer.parseInt(tfEmployeeNumber.getText()));
            if (existingEmployee != null
                    && (isAdd
                    || (!existingEmployee.getFirstName().equalsIgnoreCase(employee.getFirstName())
                    || !existingEmployee.getFamilyName().equalsIgnoreCase(employee.getFamilyName())))) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        cbClerk = new javax.swing.JCheckBox();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        tfPassword = new javax.swing.JPasswordField();
        lblNameInUse = new javax.swing.JLabel();
        lblNumberInUse = new javax.swing.JLabel();
        pnlExtra = new javax.swing.JPanel();
        pnlHours = new javax.swing.JPanel();
        lblHours = new javax.swing.JLabel();
        tfHours = new javax.swing.JFormattedTextField();
        pnlDays = new javax.swing.JPanel();
        lblInstruction = new javax.swing.JLabel();
        cbMonday = new javax.swing.JCheckBox();
        cbTuesday = new javax.swing.JCheckBox();
        cbWednesday = new javax.swing.JCheckBox();
        cbThursday = new javax.swing.JCheckBox();
        cbFriday = new javax.swing.JCheckBox();
        cbSaturday = new javax.swing.JCheckBox();
        cbSunday = new javax.swing.JCheckBox();
        pnlVacationPercentage = new javax.swing.JPanel();
        lblVacationPercentage = new javax.swing.JLabel();
        tfVacationPercentage = new javax.swing.JFormattedTextField();
        cbMuseumEducator = new javax.swing.JCheckBox();
        lblInsertion = new javax.swing.JLabel();
        tfInsertion = new javax.swing.JTextField();
        cbOther = new javax.swing.JCheckBox();
        cbCPH = new javax.swing.JCheckBox();
        cbCoopery = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        cbFullTime = new javax.swing.JCheckBox();
        cbPartTime = new javax.swing.JCheckBox();
        cbVolunteer = new javax.swing.JCheckBox();
        cbCallWorker = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(153, 204, 255));

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

        cbClerk.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup.add(cbClerk);
        cbClerk.setText("Baliemedewerker");
        cbClerk.setBorder(null);
        cbClerk.setOpaque(false);
        cbClerk.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbClerkStateChanged(evt);
            }
        });

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/btnPrevious.png"))); // NOI18N
        btnCancel.setToolTipText("Vorige");
        btnCancel.setContentAreaFilled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
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

        tfPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfPasswordKeyReleased(evt);
            }
        });

        lblNameInUse.setText("De combinatie voor- en achternaam is al in gebruik!");

        lblNumberInUse.setText("Dat nummer is al in gebruik!");

        pnlExtra.setBackground(new java.awt.Color(153, 204, 255));

        pnlHours.setBackground(new java.awt.Color(153, 204, 255));

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
            .addComponent(lblHours)
            .addComponent(tfHours)
        );
        pnlHoursLayout.setVerticalGroup(
            pnlHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoursLayout.createSequentialGroup()
                .addComponent(lblHours)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlDays.setBackground(new java.awt.Color(153, 204, 255));

        lblInstruction.setText("Niet in te roosteren dagen:");

        cbMonday.setBackground(new java.awt.Color(255, 255, 255));
        cbMonday.setText("Maandag");
        cbMonday.setBorder(null);
        cbMonday.setOpaque(false);

        cbTuesday.setBackground(new java.awt.Color(255, 255, 255));
        cbTuesday.setText("Dinsdag");
        cbTuesday.setBorder(null);
        cbTuesday.setOpaque(false);

        cbWednesday.setBackground(new java.awt.Color(255, 255, 255));
        cbWednesday.setText("Woensdag");
        cbWednesday.setBorder(null);
        cbWednesday.setOpaque(false);

        cbThursday.setBackground(new java.awt.Color(255, 255, 255));
        cbThursday.setText("Donderdag");
        cbThursday.setBorder(null);
        cbThursday.setOpaque(false);

        cbFriday.setBackground(new java.awt.Color(255, 255, 255));
        cbFriday.setText("Vrijdag");
        cbFriday.setBorder(null);
        cbFriday.setOpaque(false);

        cbSaturday.setBackground(new java.awt.Color(255, 255, 255));
        cbSaturday.setText("Zaterdag");
        cbSaturday.setBorder(null);
        cbSaturday.setOpaque(false);

        cbSunday.setBackground(new java.awt.Color(255, 255, 255));
        cbSunday.setText("Zondag");
        cbSunday.setBorder(null);
        cbSunday.setOpaque(false);

        javax.swing.GroupLayout pnlDaysLayout = new javax.swing.GroupLayout(pnlDays);
        pnlDays.setLayout(pnlDaysLayout);
        pnlDaysLayout.setHorizontalGroup(
            pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDaysLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbSunday)
                    .addComponent(cbSaturday)
                    .addComponent(cbFriday)
                    .addComponent(cbMonday)
                    .addComponent(cbTuesday)
                    .addComponent(cbWednesday)
                    .addComponent(cbThursday))
                .addContainerGap())
            .addGroup(pnlDaysLayout.createSequentialGroup()
                .addComponent(lblInstruction)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlDaysLayout.setVerticalGroup(
            pnlDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDaysLayout.createSequentialGroup()
                .addComponent(lblInstruction)
                .addGap(17, 17, 17)
                .addComponent(cbMonday)
                .addGap(18, 18, 18)
                .addComponent(cbTuesday)
                .addGap(18, 18, 18)
                .addComponent(cbWednesday)
                .addGap(18, 18, 18)
                .addComponent(cbThursday)
                .addGap(18, 18, 18)
                .addComponent(cbFriday)
                .addGap(18, 18, 18)
                .addComponent(cbSaturday)
                .addGap(18, 18, 18)
                .addComponent(cbSunday)
                .addContainerGap(380, Short.MAX_VALUE))
        );

        pnlVacationPercentage.setBackground(new java.awt.Color(153, 204, 255));

        lblVacationPercentage.setText("Vakantiepercentage:");

        tfVacationPercentage.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfVacationPercentage.setText("9.615");
        tfVacationPercentage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfVacationPercentageKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlVacationPercentageLayout = new javax.swing.GroupLayout(pnlVacationPercentage);
        pnlVacationPercentage.setLayout(pnlVacationPercentageLayout);
        pnlVacationPercentageLayout.setHorizontalGroup(
            pnlVacationPercentageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblVacationPercentage)
            .addComponent(tfVacationPercentage)
        );
        pnlVacationPercentageLayout.setVerticalGroup(
            pnlVacationPercentageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVacationPercentageLayout.createSequentialGroup()
                .addComponent(lblVacationPercentage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfVacationPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlExtraLayout = new javax.swing.GroupLayout(pnlExtra);
        pnlExtra.setLayout(pnlExtraLayout);
        pnlExtraLayout.setHorizontalGroup(
            pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExtraLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(pnlHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlVacationPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(384, Short.MAX_VALUE))
        );
        pnlExtraLayout.setVerticalGroup(
            pnlExtraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHours, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDays, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlExtraLayout.createSequentialGroup()
                .addComponent(pnlVacationPercentage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        cbMuseumEducator.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup.add(cbMuseumEducator);
        cbMuseumEducator.setText("Museumdocent");
        cbMuseumEducator.setBorder(null);
        cbMuseumEducator.setOpaque(false);
        cbMuseumEducator.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbMuseumEducatorStateChanged(evt);
            }
        });

        lblInsertion.setText("Tussenvoegsel:");

        cbOther.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup.add(cbOther);
        cbOther.setSelected(true);
        cbOther.setText("Anders");
        cbOther.setBorder(null);
        cbOther.setOpaque(false);
        cbOther.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbOtherStateChanged(evt);
            }
        });

        buttonGroup.add(cbCPH);
        cbCPH.setText("CPH");
        cbCPH.setBorder(null);
        cbCPH.setOpaque(false);

        buttonGroup.add(cbCoopery);
        cbCoopery.setText("Kuiperij");
        cbCoopery.setBorder(null);
        cbCoopery.setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        cbFullTime.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cbFullTime);
        cbFullTime.setSelected(true);
        cbFullTime.setText("Fulltime");
        cbFullTime.setBorder(null);
        cbFullTime.setOpaque(false);
        cbFullTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbFullTimeStateChanged(evt);
            }
        });

        cbPartTime.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cbPartTime);
        cbPartTime.setText("Parttime");
        cbPartTime.setBorder(null);
        cbPartTime.setOpaque(false);
        cbPartTime.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbPartTimeStateChanged(evt);
            }
        });

        cbVolunteer.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cbVolunteer);
        cbVolunteer.setText("Vrijwilliger");
        cbVolunteer.setBorder(null);
        cbVolunteer.setOpaque(false);

        cbCallWorker.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(cbCallWorker);
        cbCallWorker.setText("Oproepkracht");
        cbCallWorker.setBorder(null);
        cbCallWorker.setOpaque(false);
        cbCallWorker.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbCallWorkerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbVolunteer)
                    .addComponent(cbPartTime)
                    .addComponent(cbFullTime)
                    .addComponent(cbCallWorker))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cbFullTime)
                .addGap(18, 18, 18)
                .addComponent(cbPartTime)
                .addGap(18, 18, 18)
                .addComponent(cbCallWorker)
                .addGap(18, 18, 18)
                .addComponent(cbVolunteer)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInsertion)
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
                        .addGap(506, 521, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbClerk)
                                    .addComponent(cbOther)
                                    .addComponent(cbMuseumEducator)
                                    .addComponent(cbCPH)
                                    .addComponent(cbCoopery))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlExtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCancel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 930, Short.MAX_VALUE)
                                .addComponent(btnSave)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbMuseumEducator)
                        .addGap(18, 18, 18)
                        .addComponent(cbClerk)
                        .addGap(18, 18, 18)
                        .addComponent(cbCPH)
                        .addGap(18, 18, 18)
                        .addComponent(cbCoopery)
                        .addGap(18, 18, 18)
                        .addComponent(cbOther)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlExtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnSave))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        RoosterProgramma.getInstance().showPanel(RoosterProgramma.getInstance().getPrevPanel());
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int employeeNumber = Integer.parseInt(tfEmployeeNumber.getText());
        String firstName = tfFirstName.getText();
        String insertion = tfInsertion.getText();
        String familyName = tfFamilyName.getText();
        String password = Utils.decodePassword(tfPassword.getPassword());
        password = !password.isEmpty() ? Utils.SHA1(password) : "(NULL)";
        contractTypes contractType = contractTypes.CALLWORKER;
        employeeTypes employeeType = employeeTypes.CLERK;
        if (cbFullTime.isSelected()) {
            contractType = contractTypes.FULLTIME;
        } else if (cbPartTime.isSelected()) {
            contractType = contractTypes.PARTTIME;
        } else if (cbCallWorker.isSelected()) {
            contractType = contractTypes.CALLWORKER;
        } else if (cbVolunteer.isSelected()) {
            contractType = contractTypes.VOLUNTEER;
        }

        if (cbClerk.isSelected()) {
            employeeType = employeeTypes.CLERK;
        } else if (cbMuseumEducator.isSelected()) {
            employeeType = employeeTypes.MUSEUMEDUCATOR;
        } else if (cbCoopery.isSelected()) {
            employeeType = employeeTypes.KUIPERIJ;
        } else if (cbCPH.isSelected()) {
            employeeType = employeeTypes.CPH;
        } else if (cbOther.isSelected()) {
            employeeType = employeeTypes.OTHER;
        }
        boolean monday = cbMonday.isSelected();
        boolean tuesday = cbTuesday.isSelected();
        boolean wednesday = cbWednesday.isSelected();
        boolean thursday = cbThursday.isSelected();
        boolean friday = cbFriday.isSelected();
        boolean saturday = cbSaturday.isSelected();
        boolean sunday = cbSunday.isSelected();
        Double vacationPercentage = cbCallWorker.isSelected() ? Double.parseDouble(tfVacationPercentage.getText().replace(",", ".")) : 0.0;
        Double contractHours = cbFullTime.isSelected() ? 40.0 : Double.parseDouble(tfHours.getText().replace(",", "."));
        if (isAdd) {
            employee = new Employee(employeeNumber, firstName, insertion, familyName, password,
                    contractType, employeeType, contractHours, vacationPercentage,
                    false, monday, wednesday, wednesday, thursday, friday, saturday, sunday);
            RoosterProgramma.getQueryManager().addEmployee(employee);
        } else {
            employee.setFirstName(firstName);
            employee.setInsertion(insertion);
            employee.setFamilyName(familyName);
            employee.setPassword(password);
            employee.setContractType(contractType);
            employee.setEmployeeType(employeeType);
            employee.setContractHours(contractHours);
            employee.setVacationPercentage(vacationPercentage);
            employee.setWorkMonday(monday);
            employee.setWorkTuesday(tuesday);
            employee.setWorkWednesday(wednesday);
            employee.setWorkThursday(thursday);
            employee.setWorkFriday(friday);
            employee.setWorkSaturday(saturday);
            employee.setWorkSunday(sunday);
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
        tfVacationPercentage.setText(tfVacationPercentage.getText().replace(".", ","));
        checkEnabled();
    }//GEN-LAST:event_tfVacationPercentageKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbCPH;
    private javax.swing.JCheckBox cbCallWorker;
    private javax.swing.JCheckBox cbClerk;
    private javax.swing.JCheckBox cbCoopery;
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
    private javax.swing.JCheckBox cbVolunteer;
    private javax.swing.JCheckBox cbWednesday;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEmployeeNumber;
    private javax.swing.JLabel lblFamilyName;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblHours;
    private javax.swing.JLabel lblInsertion;
    private javax.swing.JLabel lblInstruction;
    private javax.swing.JLabel lblNameInUse;
    private javax.swing.JLabel lblNumberInUse;
    private javax.swing.JLabel lblVacationPercentage;
    private javax.swing.JLabel lblWachtwoord;
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
