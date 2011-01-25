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
        return translated;
    }
}
