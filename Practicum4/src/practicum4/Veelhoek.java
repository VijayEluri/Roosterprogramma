/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practicum4;

/**
 *
 * @author Dark
 */
public class Veelhoek {
    Punt veelhoek[];
    Veelhoek()
    {
        veelhoek = new Punt[3];
        for (int i = 0; i < 3; i++)
            veelhoek[i] = new Punt(0,0);
    }
    Veelhoek(int dimensions)
    {
        veelhoek = new Punt[dimensions];
        for (int i = 0; i < dimensions; i++)
            veelhoek[i] = new Punt(0,0);
    }
    void Print()
    {
        System.out.println("Dimensie: "+veelhoek.length);
        if (veelhoek.length == 3)
            System.out.println("Dit is geen veelhoek maar een driehoek.");
        else if (veelhoek.length < 3)
            System.out.println("Dit is een punt of een lijn.");
        else
        {
            for (int j = 0; j < veelhoek.length; j++)
            {
                System.out.println(veelhoek[j].PrintPunt());
            }
        }
    }
    void SetPunt(int Volgnummer, Punt punt)
    {
        if (Volgnummer <= 0 || Volgnummer > veelhoek.length)
        {
            System.out.println("Error!");
            if (Volgnummer <= 0)
            {
                System.out.println("Volgnummer variabele is kleiner of gelijk aan 0.");
                System.out.println("Volgnummer: "+Volgnummer);
            }
            else
            {
                System.out.println("Volgnummer is groter dan het aantal dimensies van de veelhoek.");
                System.out.println("Volgnummer: "+Volgnummer+"\n Dimensies: "+veelhoek.length);
            }
        }
        else
        {
            veelhoek[Volgnummer-1] = punt;
        }
    }
    void Verschuif(int x, int y)
    {
        for (int i = 0; i < veelhoek.length; i++)
        {
            veelhoek[i].Verschuif(x, y);
        }
    }
}