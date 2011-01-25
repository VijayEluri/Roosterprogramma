/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package roosterprogramma;

/**
 *
 * @author Dark
 */
public class Translater {
    public Translater () {
    }

    public String Translate(String strToTranslate) {
        String translated = "";
        // Days of week
        if (strToTranslate.equals("Monday"))
            translated = "Maandag";
        if (strToTranslate.equals("Tuesday"))
            translated = "Dinsdag";
        if (strToTranslate.equals("Wednesday"))
            translated = "Woensdag";
        if (strToTranslate.equals("Thursday"))
            translated = "Donderdag";
        if (strToTranslate.equals("Friday"))
            translated = "Vrijdag";
        if (strToTranslate.equals("Saturday"))
            translated = "Zaterdag";
        if (strToTranslate.equals("Sunday"))
            translated = "Zondag";
        // End of Days of week
        // Months
        if (strToTranslate.equals("January"))
            translated = "Januari";
        if (strToTranslate.equals("February"))
            translated = "Februari";
        if (strToTranslate.equals("March"))
            translated = "Maart";
        if (strToTranslate.equals("April"))         // Zelfde
            translated = "April";
        if (strToTranslate.equals("May"))
            translated = "Mei";
        if (strToTranslate.equals("June"))
            translated = "Juni";
        if (strToTranslate.equals("July"))
            translated = "Juli";
        if (strToTranslate.equals("August"))
            translated = "Augustus";
        if (strToTranslate.equals("September"))     // Zelfde
            translated = "September";
        if (strToTranslate.equals("October"))
            translated = "Oktober";
        if (strToTranslate.equals("November"))      // Zelfde
            translated = "November";
        if (strToTranslate.equals("December"))      // Zelfde
            translated = "December";
        // End of Months

        if (translated.isEmpty())
        {
            System.out.println("Error:  " + strToTranslate + " returns an empty translation!");
            translated = strToTranslate;
        }

        return translated;
    }
}
