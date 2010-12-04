package view;

import main.ServerManager;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel implements MouseListener {
    private final int verticalPosition = 60;
    private final int offset = 40;

    public Menu() {
        super();
        setLayout(null);
        initComponents();
    }

    /** create the gui for this page */
    private void initComponents() {
        addTitle();
        addcategoryItems();
    }

    /** add the page's title */
    private void addTitle() {
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Server Manager");
        lblTitle.setBounds(20, 20, 150, 20);
        lblTitle.setFont(ServerManager.FONT_16_BOLD);
        add(lblTitle);
    }

    /** add the different catergories to the page */
    private void addcategoryItems() {
        JLabel ShowCharacters = new JLabel();
        ShowCharacters.setName("ShowCharacters");
        ShowCharacters.setText("Show Characters");
        ShowCharacters.setBounds(35, verticalPosition + 1 * offset, 200, 20);
        ShowCharacters.setFont(ServerManager.FONT_12_BOLD);
        ShowCharacters.addMouseListener(this);
        ShowCharacters.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(ShowCharacters);

        JLabel ShowCoreStatus = new JLabel();
        ShowCoreStatus.setName("ShowCoreStatus");
        ShowCoreStatus.setText("Show Core Status");
        ShowCoreStatus.setBounds(35, verticalPosition + 2 * offset, 200, 20);
        ShowCoreStatus.setFont(ServerManager.FONT_12_BOLD);
        ShowCoreStatus.addMouseListener(this);
        ShowCoreStatus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(ShowCoreStatus);

        JLabel MakeAccount = new JLabel();
        MakeAccount.setName("MakeAccount");
        MakeAccount.setText("Make a new account");
        MakeAccount.setBounds(35, verticalPosition + 3 * offset, 200, 20);
        MakeAccount.setFont(ServerManager.FONT_12_BOLD);
        MakeAccount.addMouseListener(this);
        MakeAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(MakeAccount);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        JLabel label = (JLabel) event.getSource();
        String LabelName = label.getName();
        if (LabelName.equals("ShowCharacters"))
        {
            ServerManager.getInstance().showPanel(new view.CharacterList());
        }
        else if (LabelName.equals("ShowCoreStatus"))
        {
            ServerManager.getInstance().showPanel(new view.CoreStatus());
        }
        else if (LabelName.equals("MakeAccount"))
        {
            ServerManager.getInstance().showPanel(new view.MakeAccount());
        }
        else
            System.exit(0);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Intentionally left blank.
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        graphics.drawLine(20, 45, ServerManager.ScreenWidth - 20, 45);
    }
}
