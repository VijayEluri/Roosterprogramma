package distance;

/**
 * Main class used during PP part 6
 * @author Ralph Benjamin Ruijs
 */
public class Main {

    /**
     * Print out the distance between the old and new
     * location of Informatica
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("The distance\n");

        Address Weesperzijde = new Address("Weesperzijde 190", 4.916103, 52.346165);
        Address Duivendrechtsekade = new Address("Duivendrechtsekade 38", 4.928804, 52.336911);

        Duivendrechtsekade.Print(Weesperzijde, Duivendrechtsekade);
        System.out.println("is: " + Duivendrechtsekade.CalcDistance(Weesperzijde, Duivendrechtsekade) + " km");

        System.out.println("\n\n\n\n\n");           // Seperator

        // Test met 3 adressen

        Address MijnHuis = new Address("Welvaren 25", 4.934567, 52.125465);

        Duivendrechtsekade.Print(Weesperzijde, Duivendrechtsekade, MijnHuis);
        System.out.println("is: " + Duivendrechtsekade.CalcDistance(Weesperzijde, Duivendrechtsekade, MijnHuis) + " km");
    }
}
