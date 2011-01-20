package roosterprogramma;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 *
 * @author Dark
 */
public class InputKeyListener extends Thread{
    JFrame main;
    
    InputKeyListener(JFrame main)
    {
        this.main = main;
    }

    @Override
    public void run()
    {
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        AbstractAction escapeAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                RoosterProgramma.getInstance().shutdown();
            }
        };
        this.main.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "Exit");
        this.main.getRootPane().getActionMap().put("Exit", escapeAction);
    }
}