/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GalgGUI;

import javax.swing.JTextArea;

/**
 *
 * @author Dark
 */
public class Galg {
    int state = 0;
    public void add()
    {
        state += 1;
    }
    public void print(JTextArea GalgArea, int state)
    {
        switch (state)
        {
            case 0:
            {
                GalgArea.setText(
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|___"
                );
                break;
            }
            case 1:
            {
                GalgArea.setText(
                    "____\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|___"
                );
                break;
            }
            case 2:
            {
                GalgArea.setText(
                    "____\n" +
                    "|/\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|___"
                );
                break;
            }
            case 3:
            {
                GalgArea.setText(
                    "____\n" +
                    "|/ |\n" +
                    "|\n" +
                    "|\n" +
                    "|\n" +
                    "|___"
                );
                break;
            }
            case 4:
            {
                GalgArea.setText(
                    "____\n" +
                    "|/ |\n" +
                    "|  O\n" +
                    "|\n" +
                    "|\n" +
                    "|___"
                );
                break;
            }
            case 5:
            {
                GalgArea.setText(
                    "____\n" +
                    "|/ |\n" +
                    "|  O\n" +
                    "|  |\n" +
                    "|\n" +
                    "|___"
                );
                break;
            }
            case 6:
            {
                GalgArea.setText(
                    "____\n" +
                    "|/ |\n" +
                    "|  O\n" +
                    "| /|\n" +
                    "|\n" +
                    "|___"
                );
                break;
            }
            case 7:
            {
                GalgArea.setText(
                    "____\n" +
                    "|/ |\n" +
                    "|  O\n" +
                    "| /|\\\n" +
                    "|\n" +
                    "|___"
                );
                break;
            }
            case 8:
            {
                GalgArea.setText(
                    "____\n" +
                    "|/ |\n" +
                    "|  O\n" +
                    "| /|\\\n" +
                    "| /\n" +
                    "|___"
                );
                break;
            }
            case 9:
            {
                GalgArea.setText(
                    "____\n" +
                    "|/ |\n" +
                    "|  O\n" +
                    "| /|\\\n" +
                    "| / \\\n" +
                    "|___"
                );
                break;
            }
            default:
                break;
        }
    }
}
