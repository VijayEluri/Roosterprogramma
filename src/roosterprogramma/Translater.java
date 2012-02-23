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

    /**
     *
     * @param strToTranslate
     * @return
     */
    public static String Translate(String strToTranslate) {
        String translated;
        if (strToTranslate.equals("Monday")) {
            translated = "Maandag";
        } else if (strToTranslate.equals("Tuesday")) {
            translated = "Dinsdag";
        } else if (strToTranslate.equals("Wednesday")) {
            translated = "Woensdag";
        } else if (strToTranslate.equals("Thursday")) {
            translated = "Donderdag";
        } else if (strToTranslate.equals("Friday")) {
            translated = "Vrijdag";
        } else if (strToTranslate.equals("Saturday")) {
            translated = "Zaterdag";
        } else if (strToTranslate.equals("Sunday")) {
            translated = "Zondag";
        } else if (strToTranslate.equals("January")) {
            translated = "Januari";
        } else if (strToTranslate.equals("February")) {
            translated = "Februari";
        } else if (strToTranslate.equals("March")) {
            translated = "Maart";
        } else if (strToTranslate.equals("April")) {
            translated = "April";
        } else if (strToTranslate.equals("May")) {
            translated = "Mei";
        } else if (strToTranslate.equals("June")) {
            translated = "Juni";
        } else if (strToTranslate.equals("July")) {
            translated = "Juli";
        } else if (strToTranslate.equals("August")) {
            translated = "Augustus";
        } else if (strToTranslate.equals("September")) {
            translated = "September";
        } else if (strToTranslate.equals("October")) {
            translated = "Oktober";
        } else if (strToTranslate.equals("November")) {
            translated = "November";
        } else if (strToTranslate.equals("December")) {
            translated = "December";
        } else {
            Utils.showMessage("Fout opgetreden, missende vertaling voor woord: " + strToTranslate, "Fout!", null, false);
            translated = strToTranslate;
        }

        return translated;
    }

    private Translater() {
    }
}
