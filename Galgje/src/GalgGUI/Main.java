package GalgGUI;

import java.io.IOException;

public class Main {

    static GUI guiobj;

    public Main() {
        guiobj = new GUI();
        guiobj.setVisible(true);
        guiobj.setExtendedState(guiobj.MAXIMIZED_BOTH);
    }

    public void MainAction() throws IOException
    {
        char InputChar;
        int state = 0;
        Galg galg = new Galg();
        galg.print(guiobj.GallowsArea, state);
        Word woord = new Word();
        GuessedLetters letters = new GuessedLetters(guiobj.GuessedLetterArea);
        InputKeyListener Listen = new InputKeyListener(guiobj.GuessArea);
        woord.print(guiobj.WordArea);
        while (woord.hasBeenGuessed() == false && state < 9)
        {
            String input = Listen.GetInput();       // Pak de string uit het input boxje
            if (input != null && !input.equalsIgnoreCase(" ")&& !input.equalsIgnoreCase(""))        // Niet allemaal nodig...voor de zekerheid gedaan tijdens testen
            {
                input = input.toLowerCase();
                InputChar = input.charAt(0);        // Alleen de eerste letter gebruiken we
                if (InputChar >= 'a' && InputChar <= 'z')       // Geen vage tekens accepteren...
                {
                    letters.add(woord, InputChar);      // Toevoegen aan het al eerder gebruikte deel
                    letters.print();                    // Weergeven wat we fout geraden hebben
                    if (woord.processGuess(InputChar) == false)     // Voor iedere verkeerde letter tellen we 1 op bij "state" voor de galg
                        state += 1;
                    woord.print(guiobj.WordArea);       // Print het woord voor zover we die hebben in de woord TextArea
                    galg.print(guiobj.GallowsArea, state);      // Teken de galg in de galg TextArea, geef de "state" mee zodat het weet welke versie van de galg hij moet tonen
                }
                Listen.ClearInput();        // We legen de variabele van het Input boxje
            }
        }
        if (state >= 9)
        {
            guiobj.WordArea.setText("YOU FAILED!");
            guiobj.GuessedLetterArea.setText("YOU FAILED!");
            guiobj.GuessArea.setText("YOU FAILED!");
            guiobj.GuessArea.setEditable(false);
        }
        else
        {
            guiobj.WordArea.setText("CONGRATULATIONS!");
            guiobj.GuessedLetterArea.setText("CONGRATULATIONS!");
            guiobj.GuessArea.setText("CONGRATULATIONS!");
            guiobj.GuessArea.setEditable(false);
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.MainAction();
    }
}