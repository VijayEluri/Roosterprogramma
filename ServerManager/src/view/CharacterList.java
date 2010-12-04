package view;

import connectivity.QueryManager;
import main.ServerManager;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.PlayerCharacter;

public class CharacterList extends JPanel implements MouseListener {
    private final int verticalPosition = 60;
    private final int offset = 40;

    private QueryManager queryManager = ServerManager.getQueryManager();
    private List<PlayerCharacter> characters = queryManager.getCharacters();

    public CharacterList() {
        super();
        setLayout(null);
        initComponents();
    }

    /** create the gui for this page */
    private void initComponents() {
        addTitle();
        addCharacters();
    }

    /** add the page's title */
    private void addTitle() {
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Server Manager");
        lblTitle.setBounds(20, 20, 150, 20);
        lblTitle.setFont(ServerManager.FONT_16_BOLD);
        add(lblTitle);
    }

    private void addCharacters() {
        if (characters.size() <= 0)
        {
            JLabel lblDot = new JLabel("\u2022");
            lblDot.setFont(ServerManager.FONT_12_BOLD);
            lblDot.setBounds(20, verticalPosition + 1 * offset, 10, 20);
            add(lblDot);

            JLabel lblCharacter = new JLabel();
            lblCharacter.setName("Empty");
            lblCharacter.setText("You have no characters in your database!");
            lblCharacter.setBounds(35, verticalPosition + 1 * offset, 500, 20);
            lblCharacter.setFont(ServerManager.FONT_12_BOLD);
            add(lblCharacter);
        }
        else
        {
            for (int i = 0; i < characters.size(); i++)
            {
                PlayerCharacter character = characters.get(i);
                JLabel lblDot = new JLabel("\u2022");
                lblDot.setFont(ServerManager.FONT_12_BOLD);
                lblDot.setBounds(20, verticalPosition + i * offset, 10, 20);
                add(lblDot);

                JLabel lblCharacter = new JLabel();
                lblCharacter.setName(String.valueOf(character.getCharguid()));
                lblCharacter.setText(character.getCharname());
                lblCharacter.setBounds(35, verticalPosition + i * offset, 200, 20);
                lblCharacter.setFont(ServerManager.FONT_12_BOLD);
                lblCharacter.addMouseListener(this);
                lblCharacter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                add(lblCharacter);
            }
        }
    }

    private PlayerCharacter getPlayerByName(String name)
    {
        PlayerCharacter character = null;
        for (int i = 0; i < characters.size(); i++) {
            character = characters.get(i);
            if (character.getCharname().equals(name))
                break;
        }
        return character;
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        JLabel label = (JLabel) event.getSource();
        ServerManager.getInstance().showPanel(new view.CharacterView(getPlayerByName(label.getName())));
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
