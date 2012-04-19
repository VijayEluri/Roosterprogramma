package roosterprogramma;

import connectivity.Dbmanager;
import connectivity.QueryManager;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
    /**
     *
     */
    public static final String NAME = "Rooster Programma";
    private Dbmanager dbManager;
    private QueryManager queryManager;
    private Settings settings;
    private Employee currentUser;
    private JPanel prevPanel;
    private HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>();

    /**
     *
     * @return
     */
    public static RoosterProgramma getInstance() {
        return instance;
    }

    /**
     *
     * @return
     */
    public static QueryManager getQueryManager() {
        return getInstance().getDynamicQueryManager();
    }

    public QueryManager getDynamicQueryManager() {
        return queryManager;
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        final RoosterProgramma applicatie = RoosterProgramma.getInstance();
        applicatie.startup();
    }

    /**
     *
     * @param panel
     * @param prevPanel
     */
    public void showPanel(JPanel panel, JPanel prevPanel) {
        this.prevPanel = prevPanel;
        showPanel(panel);
    }

    /**
     *
     * @param panel
     */
    public void showPanel(JPanel panel) {
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
        mainWindow.getContentPane().validate();
        mainWindow.getContentPane().repaint();
    }

    /**
     *
     * @return
     */
    public JPanel getPrevPanel() {
        return this.prevPanel;
    }

    /**
     *
     */
    public void startup() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            Utils.showMessage("Fout opgetreden, kan het thema van het programma niet starten.", "Fout!", ex.getMessage(), false);
        }
        dbManager = new Dbmanager();
        dbManager.openConnection();
        queryManager = new QueryManager(dbManager);
        mainWindow = new JFrame(NAME);
        mainWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

    /**
     *
     */
    public void shutdown() {
        mainWindow.setVisible(false);
        mainWindow.dispose();
        dbManager.closeConnection();
        System.exit(0);
    }

    /**
     *
     * @return
     */
    public Employee getCurrentUser() {
        return currentUser;
    }

    /**
     *
     * @param employee
     */
    public void setCurrentUser(Employee employee) {
        this.currentUser = employee;
    }

    /**
     *
     * @return
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     *
     * @param settings
     */
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    /**
     *
     */
    public void initializeEmployees() {
        for (Employee tmpEmployee : getQueryManager().getEmployees()) {
            employees.put(tmpEmployee.getEmployeeNumber(), tmpEmployee);
        }
    }

    /**
     *
     */
    public void initializeSettings() {
        this.settings = getQueryManager().getSettings();
    }

    /**
     *
     * @return
     */
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        for (Employee tmpEmployee : employees.values()) {
            employeeList.add(tmpEmployee);
        }
        return sort(employeeList);
    }

    /**
     *
     * @param id
     * @return
     */
    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    /**
     *
     * @param firstname
     * @param insertion
     * @param familyname
     * @return
     */
    public Employee getEmployee(String firstname, String insertion, String familyname) {
        for (Employee tmpEmployee : employees.values()) {
            if (tmpEmployee.getFirstName().equalsIgnoreCase(firstname) && tmpEmployee.getFamilyName().equalsIgnoreCase(familyname)) {
                if (insertion == null || tmpEmployee.getInsertion().equalsIgnoreCase(insertion)) {
                    return tmpEmployee;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param firstname
     * @param familyname
     * @return
     */
    public ArrayList<Employee> searchEmployee(String firstname, String familyname) {
        String newFirstname = firstname.toLowerCase();
        String newFamilyname = familyname.toLowerCase();

        ArrayList<Employee> result = new ArrayList<Employee>();
        for (Employee tmpEmployee : employees.values()) {
            String tmpFirstname = tmpEmployee.getFirstName().toLowerCase();
            String tmpFamilyname = tmpEmployee.getFamilyName().toLowerCase();
            if (!newFirstname.isEmpty()) {
                if (tmpFirstname.contains(newFirstname) && (newFamilyname.isEmpty() || tmpFamilyname.contains(newFamilyname))) {
                    result.add(tmpEmployee);
                }
            } else if (!newFamilyname.isEmpty()) {
                if (tmpFamilyname.contains(newFamilyname)) {
                    result.add(tmpEmployee);
                }
            } else {
                return getEmployees();
            }
        }
        return sort(result);
    }

    /**
     *
     * @param employee
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee.getEmployeeNumber());
    }

    /**
     *
     * @param employee
     */
    public void addEmployee(Employee employee) {
        employees.put(employee.getEmployeeNumber(), employee);
    }

    /**
     *
     * @param employee
     */
    public void changeEmployee(Employee employee) {
        removeEmployee(employee);
        addEmployee(employee);
    }

    public ArrayList<Employee> sort(ArrayList<Employee> employees) {
        Collections.sort(employees, new Comparator<Employee>() {

            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getEmployeeNumber() - o2.getEmployeeNumber();
            }
        });
        return employees;
    }
}