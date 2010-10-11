package GalgGUI;

import java.util.Random;
import javax.swing.JTextArea;

/**
 * Word is the class used to store and represent
 * a word for the game Hangman. The class only
 * supports a single word; "hangman".
 * 
 * @author Ralph Benjamin Ruijs
 */
public class Word {

    // The word that has to be guessed
    private char[] word;
    String PrintWord;

    /* An array denoting whether the corresponding character
       has been guessed.
       guessed[i] == true means that word[i] has been guessed */
    private boolean[] guessed;
    /**
     * Create a new instance of this class.
     * The word will be set to "hangman".
     */
    public Word() {
        String RandomString = "";
        Random generator = new Random();
        int RandomInt = 0;
        for (int i = 0; i < 20; i++)
        {
            RandomInt = generator.nextInt(12);
            System.out.println(RandomInt);
        }
        switch (RandomInt)
        {
            case 1:
                RandomString = "coder";
                break;
            case 2:
                RandomString = "autoventieldopjesfabriek";
                break;
            case 3:
                RandomString = "psyche";
                break;
            case 4:
                RandomString = "bushalte";
                break;
            case 5:
                RandomString = "gymzaal";
                break;
            case 6:
                RandomString = "hippopotomonstrosesquipedaliofobie";
                break;
            case 7:
                RandomString = "minderwaardigheidscomplex";
                break;
            case 8:
                RandomString = "vinylvloer";
                break;
            case 9:
                RandomString = "desoxyribonucleinezuur";
                break;
            case 10:
                RandomString = "homofiel";
                break;
            case 11:
            default:
                RandomString = "baarmoederhalskankerprik";
                break;
        }
        if (!RandomString.equals(""))
            this.word = RandomString.toCharArray(); // convert the string to an array
        this.guessed = new boolean[this.word.length]; // default initialised to false
    }

    /**
     * Print the word to System.out formatted like:
     * h.n...n
     */
    public void print(JTextArea WordArea) {
        PrintWord = "";
        for (int i = 0; i < word.length; i++) {
            if (guessed[i]) {
                PrintWord += word[i];
            } else {
                PrintWord += '_';
            }
        }
        PrintWord += '\n';
        WordArea.setText(PrintWord);
    }

    /**
     * Process a guess (character) against the word. If the
     * character occurs in the word, its corresponding
     * boolean will be set to true.
     *
     * @param guess, the character to process
     * @return true if the character occurs in the word,
     *         false otherwise
     */
    public boolean processGuess(char guess)
    {
        boolean success = false;
        for (int i = 0; i < word.length; i++)
        {
            char ch = word[i]; // for every char in the word
            if (ch == guess)
            { // check if char matches the guess
                guessed[i] = true;
                success = true;
            }
        }
        return success;
    }

    /**
     * Find out if all the letters of the word have
     * been guessed.
     * @return true if the whole word has been guessed,
     *         false otherwise
     */
    public boolean hasBeenGuessed() {
        for (boolean charGuessed : guessed) {
            if (!charGuessed) { // if one of the chars has not been guessed
                return false; // complete word has not been guessed yet
            }
        }
        return true; // all chars have been guessed -> word has been guessed
    }

    public char[] GetWord()
    {
        return this.word;
    }
}
