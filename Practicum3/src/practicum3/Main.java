/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practicum3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 *
 * @author Dark
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Dit is de practicum opdracht van Giedo Terol (ICT101).");
        System.out.println("Dit script heeft de volgende opties:");
        System.out.println("a = Opdracht A, b = Opdracht B en c = Opdracht C.");
        System.out.println("Typ nu de letter van de opdracht die u wilt zien gevolgd door het drukken op de ENTER toets.\n");
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String InputString = reader.readLine();
        DecimalFormat TweeCijferig = new DecimalFormat("#.##");
        if (InputString != null && (InputString.equals("a") || InputString.equals("A")))
        {
            int Lening = 100000;
            float RentePercentage = 9.0f;
            float Aflossing = 15000f;
            float Schuld = Lening;
            int Jaar = 1;
            float Rente;
            System.out.println("Jaar\tSchuld\tRentepercentage\tRente\tAflossing");
            while (Schuld > 0.0)
            {
                Rente = Schuld * (RentePercentage / 100);
                if ((Schuld + Rente - Aflossing) < 0)
                    Aflossing = Schuld + Rente;
                System.out.printf(Jaar + "\t€ " + TweeCijferig.format(Schuld) + "\t" + RentePercentage + "\t€ " + TweeCijferig.format(Rente) + "\t€ " + TweeCijferig.format(Aflossing));
                Schuld = Schuld + Rente - Aflossing;
                Jaar++;
            }
        }
        else if (InputString != null && (InputString.equals("b") || InputString.equals("B")))
        {
            int Lening = 100000;
            double KortingsMoment = 0.25;
            float RenteVerlaging = 0.5f;
            float RentePercentage = 9.0f;
            float Aflossing = 15000f;
            float Schuld = Lening;
            int Jaar = 1;
            float Rente;
            int Count = 0;
            System.out.println("Jaar\tSchuld\tRentepercentage\tRente\tAflossing");
            while (Schuld > 0.0)
            {
                Rente = Schuld * (RentePercentage / 100);
                if ((Schuld <= ((1 - KortingsMoment) * Lening)) && (Count < 1))
                {
                    Count++;
                    RentePercentage = RentePercentage - RenteVerlaging;
                }
                if ((Schuld + Rente - Aflossing) < 0)
                    Aflossing = Schuld + Rente;
                System.out.println(Jaar + "\t€ " + TweeCijferig.format(Schuld) + "\t" + RentePercentage + "\t€ " + TweeCijferig.format(Rente) + "\t€ " + Aflossing);
                Schuld = Schuld + Rente - Aflossing;
                Jaar += 1;
            }
        }
        else if (InputString != null && (InputString.equals("c") || InputString.equals("C")))
        {
            int Lening = 100000;
            double KortingsMoment = 0.25;
            float RenteVerlaging = 0.5f;
            float RentePercentage = 9.0f;
            float RenteAftrek = 1.0f;
            int MomentRenteAftrek = 9;
            float Aflossing = 15000f;
            float Schuld = Lening;
            int Jaar = 1;
            float Rente;
            int Count = 0;
            System.out.println("Jaar\tSchuld\tRentepercentage\tRente\tAflossing");
            while (Schuld > 0.0)
            {
                Rente = Schuld * (RentePercentage / 100);
                if ((Schuld <= ((1 - KortingsMoment) * Lening)) && (Count < 1))
                {
                    Count++;
                    RentePercentage = RentePercentage - RenteVerlaging;
                }
                if (Jaar == MomentRenteAftrek)
                {
                    RentePercentage = RentePercentage - RenteAftrek;
                }
                if ((Schuld + Rente - Aflossing) < 0)
                    Aflossing = Schuld + Rente;
                System.out.println(Jaar + "\t€ " + Schuld + "\t" + RentePercentage + "\t€ " + Rente + "\t€ " + Aflossing);
                Schuld = Schuld + Rente - Aflossing;
                Jaar += 1;
            }
        }
        else
        {
            System.out.println("Last time i checked, the string entered by you (" + InputString + ") wasn't in the list of options...");
            System.out.println("Bovenstaande zin heb ik in het engels gedaan omdat de nederlandse vertaling gewoon niet klinkt...");
        }
    }
}