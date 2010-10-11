/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgets;

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
public class InputKeyListener {
    public InputKeyListener(final JFrame guiobj)
    {
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        AbstractAction escapeAction = new AbstractAction()
        {
            // close the frame when the user presses escape
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        };
        guiobj.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        guiobj.getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }
}
