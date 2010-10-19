/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practicum4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Dark
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String input_string = null;
        System.out.println("Wat wil je maken? een punt of een veelhoek?");
        do
        {
            input_string = reader.readLine();
            if (input_string.equals("Punt") || input_string.equals("punt"))
            {
                System.out.println("Punt 1:");
                Punt Punt1 = new Punt(0,0);
                Punt1.Punt(5, 10);                //zet de coordinaten van Punt1
                System.out.println(Punt1.PrintPunt());
                Punt1.Verschuif(10, 0);           // x + 10, y + 0
                System.out.println(Punt1.PrintPunt());
                System.out.println("\nPunt 2:");
                Punt Punt2 = new Punt(0,0);
                Punt2.Punt(15,30);                //zet de coordinaten van Punt2
                System.out.println(Punt2.PrintPunt());
            }
            else if (input_string.equals("Veelhoek") || input_string.equals("veelhoek"))
            {
                Punt punt1 = new Punt(5, 13);
                Veelhoek test = new Veelhoek(5);
                test.SetPunt(3, punt1);         // Op de derde plaats vullen we onze punt in.
                test.Print();
                test.Verschuif(5, 10);          // verschuift de x_coord 5 naar rechts en de y_coord 10 omhoog
                test.Print();
            }
            else
            {
                System.out.println("Sorry, maar "+input_string+" stond er niet tussen."); //als je iets anders dan punt/veelhoek intypt geeft hij dit
                input_string = null;
            }
        } while (input_string == null);
    }
}