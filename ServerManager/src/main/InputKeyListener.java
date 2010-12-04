/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

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
    public void run()
    {
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);       // Als de escape toets was ingedrukt
        AbstractAction escapeAction = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);     // Sluit de applicatie
            }
        };
        this.main.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        this.main.getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }
}
