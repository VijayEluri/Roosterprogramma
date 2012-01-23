package roosterprogramma;

import connectivity.Dbmanager;
import connectivity.QueryManager;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.Employee;
import model.Settings;
import view.Login;

/**
 *
 * @author Dark
 */
public class RoosterProgramma {

    private static RoosterProgramma instance = new RoosterProgramma();
    private static JFrame mainWindow;
    public static final String NAME = "Rooster Programma";
    private Dbmanager dbManager;
    private QueryManager queryManager;
    private Settings settings;
    private Employee employee;
    private ArrayList<Employee> employees;

    public static RoosterProgramma getInstance() {
        return instance;
    }

    public static QueryManager getQueryManager() {
        return getInstance().queryManager;
    }

    public static void main(String args[]) {
        final RoosterProgramma applicatie = RoosterProgramma.getInstance();
        applicatie.startup();
    }

    public void showPanel(JPanel panel) {
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
        mainWindow.getContentPane().validate();
        mainWindow.getContentPane().repaint();
    }

    public void startup() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Utils.showMessage("Fout opgetreden, kan het thema van het programma niet starten.", "Fout!", true, ex.getMessage());
        }
        dbManager = new Dbmanager();
        dbManager.openConnection();
        queryManager = new QueryManager(dbManager);
        mainWindow = new JFrame(NAME);
        mainWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int shutdown = JOptionPane.showConfirmDialog(null, "Weet u zeker dat u het programma wilt afsluiten?\nAlle niet-opgeslagen gegevens zullen verloren gaan...", "Afsluiten", JOptionPane.YES_NO_OPTION);
                if (shutdown == JOptionPane.YES_OPTION) {
                    shutdown();
                }
            }
        });
        mainWindow.getContentPane().setLayout(new BorderLayout());
        showPanel(new Login());
        mainWindow.setVisible(true);
    }

    public void shutdown() {
        mainWindow.setVisible(false);
        mainWindow.dispose();
        dbManager.closeConnection();
        System.exit(0);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void initializeEmployees() {
        this.employees = getQueryManager().getEmployees();
    }

    public void initializeSettings() {
        this.settings = getQueryManager().getSettings();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public Employee getEmployee(int id) {
        for (Employee tmpEmployee : employees) {
            if (tmpEmployee.getEmployeeNumber() == id) {
                return tmpEmployee;
            }
        }
        return null;
    }

    public Employee getEmployee(String firstname, String insertion, String familyname) {
        for (Employee tmpEmployee : employees) {
            if (tmpEmployee.getFirstName().equalsIgnoreCase(firstname) && tmpEmployee.getFamilyName().equalsIgnoreCase(familyname)) {
                if (insertion == null || tmpEmployee.getInsertion().equalsIgnoreCase(insertion)) {
                    return tmpEmployee;
                }
            }
        }
        return null;
    }

    public ArrayList<Employee> searchEmployee(String firstname, String familyname) {
        firstname = firstname.toLowerCase();
        familyname = familyname.toLowerCase();

        ArrayList<Employee> result = new ArrayList<Employee>();
        for (Employee tmpEmployee : employees) {
            String tmpFirstname = tmpEmployee.getFirstName().toLowerCase();
            String tmpFamilyname = tmpEmployee.getFamilyName().toLowerCase();
            if (!firstname.isEmpty()) {
                if (tmpFirstname.contains(firstname) && (familyname.isEmpty() || tmpFamilyname.contains(familyname))) {
                    result.add(tmpEmployee);
                }
            } else if (!familyname.isEmpty()) {
                if (tmpFamilyname.contains(familyname)) {
                    result.add(tmpEmployee);
                }
            } else {
                return getEmployees();
            }
        }
        return result;
    }
}