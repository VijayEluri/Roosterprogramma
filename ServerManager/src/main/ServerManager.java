package main;

import connectivity.Dbmanager;
import connectivity.QueryManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Administrator
 */
public final class ServerManager {

    public static int ScreenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static int ScreenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    /** Define name*/
    public static final String NAME = "Server Manager";
    /** static fonts which are used within the application */
    public static final Font FONT_10_PLAIN = new Font("Verdana", Font.PLAIN, 10);
    public static final Font FONT_10_BOLD = new Font("Verdana", Font.BOLD, 10);
    public static final Font FONT_12_BOLD = new Font("Verdana", Font.BOLD, 12);
    public static final Font FONT_16_BOLD = new Font("Verdana", Font.BOLD, 16);
    /** database manager */
    private Dbmanager dbManager;
    private QueryManager queryManager;
    /** the main window */
    private JFrame mainWindow;
    /** singleton of the application */
    private static ServerManager instance = new ServerManager();

    private ServerManager() {
    }

    public void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Error setting LookAndFeelClassName: " + e);
        }
        // create and initialize the connectivity
        dbManager = new Dbmanager();
        queryManager = new QueryManager(dbManager);
    }

    public void startup() {
        mainWindow = new JFrame(NAME);
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setUndecorated(true);

        InputKeyListener keylisten = new InputKeyListener(mainWindow);
        keylisten.start();

        mainWindow.getContentPane().setLayout(new BorderLayout());
        showPanel(new view.Menu());
        mainWindow.setVisible(true);
    }

    public void showPanel(JPanel panel) {
        mainWindow.getContentPane().removeAll();
        mainWindow.getContentPane().add(panel, BorderLayout.CENTER);
        mainWindow.getContentPane().validate();
        mainWindow.getContentPane().repaint();
    }

    public void exit() {
        mainWindow.setVisible(false);
        shutdown();
    }

    private void shutdown() {
        mainWindow.dispose();
        dbManager.closeConnection();
    }

    /**
     * @return the instance of this class
     */
    public static ServerManager getInstance() {
        return instance;
    }

    /**
     * @return the queryManager
     */
    public static QueryManager getQueryManager() {
        return getInstance().queryManager;
    }

    public static void main(String args[]) {
        final ServerManager application = ServerManager.getInstance();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    application.initialize();
                    application.startup();
                } catch (Exception e) {
                    System.out.println("Application" + application.getClass().getName() + "failed to launch");
                }
            }
        });
    }
}
