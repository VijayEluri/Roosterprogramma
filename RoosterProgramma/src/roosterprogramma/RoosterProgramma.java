/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package roosterprogramma;

import connectivity.Dbmanager;
import connectivity.QueryManager;
import connectivity.UpdateChecker;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import model.Employee;
import view.Login;

/**
 *
 * @author Dark
 */
public class RoosterProgramma {

    private static RoosterProgramma instance = new RoosterProgramma();
    private static JFrame mainWindow;
    public static final String NAME = "Uren Verantwoording";
    public static final Font FONT_10_PLAIN = new Font("Verdana", Font.PLAIN, 10);
    public static final Font FONT_10_BOLD = new Font("Verdana", Font.BOLD, 10);
    public static final Font FONT_12_BOLD = new Font("Verdana", Font.BOLD, 12);
    public static final Font FONT_16_BOLD = new Font("Verdana", Font.BOLD, 16);
    public static final Font FONT_25_BOLD = new Font("Verdana", Font.BOLD, 25);
    private Dbmanager dbManager;
    private QueryManager queryManager;

    public int ScreenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public int ScreenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

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
        // create and initialize the connectivity
        dbManager = new Dbmanager();
        dbManager.openConnection();
        queryManager = new QueryManager(dbManager);
    }

    public void startup() {
        mainWindow = new JFrame("UrenVerantwoording");
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setUndecorated(true);

        InputKeyListener keylisten = new InputKeyListener(mainWindow);
        keylisten.start();

        mainWindow.getContentPane().setLayout(new BorderLayout());

        UpdateChecker check = new UpdateChecker();
        showPanel(new Login(check.checkForUpdate()));

        mainWindow.setVisible(true);
    }

    public void shutdown() {
        mainWindow.setVisible(false);
        mainWindow.dispose();
        dbManager.closeConnection();
        System.exit(0);
    }
}
