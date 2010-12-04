package view;

import main.ServerManager;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.PlayerCharacter;

public class CharacterView extends JPanel implements MouseListener {
    private final int verticalPosition = 60;
    private final int offset = 40;
    private PlayerCharacter CurrentChar;

    public CharacterView(PlayerCharacter Character) {
        super();
        this.CurrentChar = Character;
        setLayout(null);
        initComponents();
    }

    /** create the gui for this page */
    private void initComponents() {
        addTitle();
        addcharacterInfo();
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
    private void addcharacterInfo() {
        String[] values = CurrentChar.getAllValues().split(":");
        for (int i = 0; i < values.length; i++)
        {
            JLabel lblGuid = new JLabel();
            lblGuid.setText(values[i]);
            lblGuid.setBounds(120, verticalPosition + i * offset, 200, 20);
            lblGuid.setFont(ServerManager.FONT_12_BOLD);
            //lblGuid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(lblGuid);
        }
        String[] variables = CurrentChar.getAllVariables().split(":");
        for (int j = 0; j < variables.length; j++)
        {
            JLabel lblGuid = new JLabel();
            lblGuid.setText(variables[j]);
            lblGuid.setBounds(25, verticalPosition + j * offset, 200, 20);
            lblGuid.setFont(ServerManager.FONT_12_BOLD);
            //lblGuid.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            add(lblGuid);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Intentionally left blank.
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
        graphics.drawLine(100, 60, 100, ServerManager.ScreenHeight - 20);
    }
}
