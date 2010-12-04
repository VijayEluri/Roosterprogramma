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
public class GuessedLetters {
    String GuessedString = "";
    JTextArea GuessedLetterArea;
    GuessedLetters(JTextArea GuessedLetterArea)
    {
        this.GuessedLetterArea = GuessedLetterArea;
    }
    public void print()
    {
        String GuessedLetters = "";
        for (int i = 0; i < this.GuessedString.length(); i++)
        {
            char PartOfGuess = this.GuessedString.charAt(i);
            GuessedLetters += (PartOfGuess + "\n");
        }
        GuessedLetterArea.setText(GuessedLetters);
    }
    public boolean add(Word woord, char guess)
    {
        this.GuessedString += guess;
        if (woord.processGuess(guess) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}