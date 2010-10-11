/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GalgGUI;

import java.applet.*;
import java.awt.event.*;
import javax.swing.JTextField;

public class InputKeyListener extends Applet
    implements KeyListener
{
    JTextField GuessBox;
    String Input;
    InputKeyListener(JTextField GuessBox)
    {
        this.GuessBox = GuessBox;
        GuessBox.addKeyListener(this);
    }
    public String GetInput()
    {
        return this.Input;
    }
    public void ClearInput()
    {
        this.Input = "";
    }
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ENTER)
        {
            String Guess = GuessBox.getText();
            GuessBox.setText("");
            this.Input = Guess;
        }
        else if (key == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
    }
}