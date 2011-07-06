package roosterprogramma;

import connectivity.Dbmanager;
import connectivity.QueryManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
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
    public static final String NAME = "Rooster Programma";      // ToDo : Setting ervan maken?
    public static final Font FONT_10_PLAIN = new Font("Verdana", Font.PLAIN, 10);
    public static final Font FONT_10_BOLD = new Font("Verdana", Font.BOLD, 10);
    public static final Font FONT_12_BOLD = new Font("Verdana", Font.BOLD, 12);
    public static final Font FONT_16_BOLD = new Font("Verdana", Font.BOLD, 16);
    public static final Font FONT_25_BOLD = new Font("Verdana", Font.BOLD, 25);
    private Dbmanager dbManager;
    private QueryManager queryManager;
    private Settings settings;
    private Employee employee;
    public int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

    public static RoosterProgramma getInstance() {
        return instance;
    }

    public static QueryManager getQueryManager() {
        return getInstance().queryManager;
    }

    public static void main(String args[]) {
        final RoosterProgramma applicatie = RoosterProgramma.getInstance();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    applicatie.initialize();
                    applicatie.startup();
                } catch (Exception e) {
                    System.out.println("Application" + applicatie.getClass().getName() + "failed to launch");
                }
            }
        });
    }

    public void showPanel(JPanel panel) {
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
        mainWindow.getContentPane().validate();
        mainWindow.getContentPane().repaint();
    }

    public void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting LookAndFeelClassName: " + e);
        }
        dbManager = new Dbmanager();
        dbManager.openConnection();
        queryManager = new QueryManager(dbManager);
        settings = getQueryManager().getSettings();
    }

    public void startup() {
        mainWindow = new JFrame(NAME);
        mainWindow.setSize(screenWidth, screenHeight);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                shutdown();
            }
        });

        InputKeyListener keylisten = new InputKeyListener(mainWindow);
        keylisten.start();

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

    public String decodePassword(char[] input) {
        String typedPassword = "";
        for (char output : input) {
            typedPassword += output;
        }
        Arrays.fill(input, '0');
        return typedPassword;
    }

    public boolean promptQuestion(String question, boolean warning, String yes, String no) {
        boolean answer = false;
        Object[] options = {
            yes,
            no
        };
        int choice = JOptionPane.showOptionDialog(
                null, // frame
                question,
                warning ? "Waarschuwing!" : "Gebruikersinteractie vereist",
                JOptionPane.YES_NO_OPTION,
                warning ? JOptionPane.WARNING_MESSAGE : JOptionPane.QUESTION_MESSAGE,
                null, // custom icon
                options,
                options[0] // default button
                );
        if (choice == JOptionPane.YES_OPTION) {
            answer = true;
        }
        return answer;
    }

    public String promptInput(String message) {
        String input = JOptionPane.showInputDialog(
                null,
                message,
                "Input Required!",
                JOptionPane.WARNING_MESSAGE);
        return input;
    }

    public void showMessage(String message, String title, boolean error) {
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                error ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
    }

    public String showFileChooser(String message) {
        String input = "";
        File startDirectory = FileSystemView.getFileSystemView().getRoots()[0];
        JFileChooser fileChooser = new JFileChooser(startDirectory);
        int saveDialog = fileChooser.showDialog(null, message);
        if (saveDialog == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            input = file.getAbsolutePath();
        }
        return input;
    }

    public static model.Employee getEmployee() {
        return getInstance().employee;
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

    public boolean isNumeric(String strToTest) {
        boolean numeric = true;
        try {
            Double.parseDouble(strToTest);
        } catch (NumberFormatException nfe) {
            numeric = false;
        }
        return numeric;
    }

    public boolean isHoliday(Calendar calendar) {
        return (isEaster(calendar)
                || isChristmasSecond(calendar)
                || isFifthOfMay(calendar)
                || isAscensionDay(calendar)
                || isPentecostFirst(calendar)
                || isPentecostSecond(calendar)
                || isGoodFriday(calendar)
                || isQueensDay(calendar));
    }

    private boolean isEaster(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && (calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH)
                || calendar.get(Calendar.DAY_OF_MONTH) == getEasterSecond(calendar).get(Calendar.DAY_OF_MONTH)));
    }

    private Calendar getEasterFirst(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int b = year / 100;
        int h = (19 * (year % 19) + b - (b / 4) - ((b - ((b + 8) / 25) + 1) / 3) + 15) % 30;
        int l = ((32 + 2 * (b % 4) + 2 * ((year % 100) / 4) - h - ((year % 100) % 4)) % 7);
        int m = ((year % 19) + 11 * h + 22 * l) / 451;
        int easterMonth = (h + l - 7 * m + 114) / 31;
        int easterDay = ((h + l - 7 * m + 114) % 31) + 1;
        Calendar easterFirst = Calendar.getInstance();
        easterFirst.set(year, easterMonth, easterDay);
        return easterFirst;
    }

    private Calendar getEasterSecond(Calendar calendar) {
        Calendar easterSecond = getEasterFirst(calendar);
        easterSecond.set(Calendar.DAY_OF_MONTH, easterSecond.get(Calendar.DAY_OF_MONTH) + 1);
        return easterSecond;
    }

    private boolean isChristmasSecond(Calendar calendar) {
        return (calendar.get(Calendar.MONTH) == 12 && calendar.get(Calendar.DAY_OF_MONTH) == 26);
    }

    private boolean isFifthOfMay(Calendar calendar) {
        return (calendar.get(Calendar.MONTH) == 5 && calendar.get(Calendar.DAY_OF_MONTH) == 5);
    }

    private boolean isAscensionDay(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        easterFirst.add(Calendar.DAY_OF_MONTH, 39);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH));
    }

    private boolean isPentecostFirst(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        easterFirst.add(Calendar.DAY_OF_MONTH, 49);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH));
    }

    private boolean isPentecostSecond(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        easterFirst.add(Calendar.DAY_OF_MONTH, 50);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH));
    }

    private boolean isGoodFriday(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        easterFirst.add(Calendar.DAY_OF_MONTH, -1);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH));
    }

    private boolean isQueensDay(Calendar calendar) {
        return (calendar.get(Calendar.MONTH) == 4
                && calendar.get(Calendar.DAY_OF_MONTH) == 30);
    }
}