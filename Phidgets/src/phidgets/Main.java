package phidgets;

import com.phidgets.*;          // Phidget library

public class Main {

    Phidget phidget;
    static GUI guiobj;
    static InterfaceKitPhidget interf;
    static String str;
    static VLC rc;
    static long x;
    long sensor1;       // "Vorige" sensor
    long sensor2;       // "Select" sensor
    long sensor3;       // "Volgende" sensor
    long sensor4;       // Afstands sensor
    boolean IsPlaying = false;
    int Count;

    public Main() {
        guiobj = new GUI();
        guiobj.setVisible(true);
        guiobj.setExtendedState(guiobj.MAXIMIZED_BOTH);     // Volledige scherm zetten van de GUI
        rc = new VLC("5.79.80.32", 1234);       // Verbinding met de VLC van Jimmy via Hamachi
        rc.start();     // Start de VLC thread
        try {
            interf = new InterfaceKitPhidget();
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
        MakeConnection();
    }

    private void PlayVLC()
    {
        if (this.IsPlaying == false)        // Als we nog niet aan het afspelen zijn
        {
            this.IsPlaying = true;      // Zeg dat we aan het afspelen zijn
            rc.control.play();      // Speel af
            rc.control.fullscreen();        // In volledig scherm
        }
    }
    public void ReadSensors() {
        SlideShow SlideSensors = new SlideShow();       // Start de Diavoorstelling controls
        while (true) {
            try {
                sensor1 = readSensorValue(1);       // Lees de waardes van de sensoren
                sensor2 = readSensorValue(2);
                sensor3 = readSensorValue(3);
                sensor4 = readSensorValue(4);
                String output = null;
                SlideSensors.HandleContent(guiobj.ImageLabel, guiobj.TextArea);     // Regel de inhoud van de dia (zowel tekst als plaatjes)
                InputKeyListener Keylistener = new InputKeyListener(guiobj);        // Check voor de Escape toets om de applicatie te sluiten
                PlayVLC();      // Gebruik functie PlayVLC (Speel af als je dat nog niet doet)
                if(sensor4 > 90)        // Als een persoon voor de sensor langs loopt
                {
                    rc.control.add("C:/Users/Jimmy/Desktop/ProjectExpo/presentatie/2.wmv");
                    Count = 0;      // Zet de "geen mensen in de buurt" teller op 0
                }
                else
                {
                    Count += 1;     // Tel er 1 bij op als er geen mensen voor langs lopen
                    if (Count > 120)     // Als we 30 seconden lang geen beweging hebben
                    {
                        rc.control.add("C:/Users/Jimmy/Desktop/ProjectExpo/presentatie/1.wmv");
                        Count = 0;      // Begin opnieuw te tellen
                    }
                }
                if(sensor1 == 0)        // Als de "vorige" knop is ingedrukt
                {
                    output = SlideSensors.HandlePrevSlide();        // Doe de actie voor de vorige knop, en krijg een String output terug
                }
                else if(sensor3 == 0)// Als de "volgende" knop is ingedrukt (met else if gedaan zodat ze niet tegelijk werken)
                {
                    output = SlideSensors.HandleNextSlide();// Doe de actie voor de volgende knop, en krijg een String output terug
                }
                if(sensor2 == 0)// Als de "select" knop is ingedrukt
                {
                    SlideSensors.HandleSelect();
                }
                if (output != null)     // als de output niet leeg was (dus of de vorige OF de volgende knop was ingedrukt)
                    System.out.println(output);
                Thread.sleep(250);      // wacht een kwart seconde (dit is de gevoeligheid)
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.ReadSensors();
    }

    /**
     * Make connection with the phidget webservice
     */
    private void MakeConnection() {

        try {
            interf.openAny("localhost", 5001);
            interf.waitForAttachment(10000);  // 1 second
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
    }// end of phidgetmakeConnection()

    public long readSensorValue(int nr) {
        long value = -1;
        try {
            value = interf.getSensorValue(nr);
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void setOutputValue(int nr, Boolean state) {
        try {
            interf.setOutputState(nr, state);
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
    }
}