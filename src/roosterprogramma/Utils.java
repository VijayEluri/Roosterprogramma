/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roosterprogramma;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import model.Employee.contractTypes;
import model.Employee.employeeTypes;

/**
 *
 * @author Dark
 */
public class Utils {

    private Utils() {
    }

    /**
     *
     * @param strToTest
     * @return
     */
    public static boolean isNumeric(String strToTest) {
        try {
            Double.parseDouble(strToTest);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     *
     * @param calendar
     * @return
     */
    public static boolean isHoliday(Calendar calendar) {
        return (isEaster(calendar)
                || isChristmasSecond(calendar)
                || isFifthOfMay(calendar)
                || isAscensionDay(calendar)
                || isPentecostFirst(calendar)
                || isPentecostSecond(calendar)
                || isGoodFriday(calendar)
                || isQueensDay(calendar));
    }

    // Is het pasen?
    private static boolean isEaster(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && (calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH)
                || calendar.get(Calendar.DAY_OF_MONTH) == getEasterSecond(calendar).get(Calendar.DAY_OF_MONTH)));
    }

    // Zoek de datum van de eerste paasdag
    private static Calendar getEasterFirst(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int b = year / 100;
        int h = (19 * (year % 19) + b - (b / 4) - ((b - ((b + 8) / 25) + 1) / 3) + 15) % 30;
        int l = ((32 + 2 * (b % 4) + 2 * ((year % 100) / 4) - h - ((year % 100) % 4)) % 7);
        int m = ((year % 19) + 11 * h + 22 * l) / 451;
        int easterMonth = (h + l - 7 * m + 114) / 31;
        int easterDay = ((h + l - 7 * m + 114) % 31) + 1;
        Calendar easterFirst = Calendar.getInstance();
        easterFirst.set(year, easterMonth - 1, easterDay);
        return easterFirst;
    }

    // Zoek de datum van de tweede paasdag
    private static Calendar getEasterSecond(Calendar calendar) {
        Calendar easterSecond = getEasterFirst(calendar);
        easterSecond.set(Calendar.DAY_OF_MONTH, easterSecond.get(Calendar.DAY_OF_MONTH) + 1);
        return easterSecond;
    }

    // Is het 2e kerstdag?
    private static boolean isChristmasSecond(Calendar calendar) {
        return (calendar.get(Calendar.MONTH) == 12 && calendar.get(Calendar.DAY_OF_MONTH) == 26);
    }

    // Is het 5 mei?
    private static boolean isFifthOfMay(Calendar calendar) {
        return (calendar.get(Calendar.MONTH) == 4 && calendar.get(Calendar.DAY_OF_MONTH) == 5);
    }

    // Is het hemelvaartsdag?
    private static boolean isAscensionDay(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        easterFirst.add(Calendar.DAY_OF_MONTH, 39);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH));
    }

    // Is het eerste pinksterdag?
    private static boolean isPentecostFirst(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        easterFirst.add(Calendar.DAY_OF_MONTH, 49);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH));
    }

    // Is het tweede pinksterdag?
    private static boolean isPentecostSecond(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        easterFirst.add(Calendar.DAY_OF_MONTH, 50);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH));
    }

    // Is het goede vrijdag?
    private static boolean isGoodFriday(Calendar calendar) {
        Calendar easterFirst = getEasterFirst(calendar);
        easterFirst.add(Calendar.DAY_OF_MONTH, -2);
        return (calendar.get(Calendar.MONTH) == easterFirst.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == easterFirst.get(Calendar.DAY_OF_MONTH));
    }

    // Is het koninginnedag?
    private static boolean isQueensDay(Calendar calendar) {
        return (calendar.get(Calendar.MONTH) == 3
                && calendar.get(Calendar.DAY_OF_MONTH) == 30);
    }

    /**
     *
     * @param input
     * @return
     */
    public static String decodePassword(char[] input) {
        String typedPassword = "";
        for (char output : input) {
            typedPassword += output;
        }
        Arrays.fill(input, '0');
        return typedPassword;
    }

    /**
     *
     * @param question
     * @param warning
     * @param yes
     * @param no
     * @return
     */
    public static boolean promptQuestion(String question, boolean warning, String yes, String no) {
        boolean answer = false;
        Object[] options = {
            yes,
            no
        };
        int choice = JOptionPane.showOptionDialog(
                null,
                question,
                warning ? "Waarschuwing!" : "Gebruikersinteractie vereist",
                JOptionPane.YES_NO_OPTION,
                warning ? JOptionPane.WARNING_MESSAGE : JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (choice == JOptionPane.YES_OPTION) {
            answer = true;
        }
        return answer;
    }

    /**
     *
     * @param message
     * @return
     */
    public static String promptInput(String message) {
        String input = JOptionPane.showInputDialog(
                null,
                message,
                "Input Required!",
                JOptionPane.WARNING_MESSAGE);
        return input;
    }

    /**
     *
     * @param message
     * @param title
     * @param exception
     * @param fatal
     */
    public static void showMessage(String message, String title, String exception, boolean fatal) {
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                exception != null ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        if (exception != null) {
            if (fatal) {
                System.err.println(exception);
                RoosterProgramma.getInstance().shutdown();
            } else {
                writeToLog(exception);
            }
        }
    }

    /**
     *
     * @param message
     * @param identifier
     * @return
     */
    public static String showFileChooser(String message, String identifier) {
        String input = "";
        File startDirectory = FileSystemView.getFileSystemView().getRoots()[0];
        JFileChooser fileChooser = new JFileChooser(startDirectory);
        Calendar calendar = Calendar.getInstance();
        fileChooser.setSelectedFile(new File("export_" + identifier + "_" + calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE) + "_" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR) + ".xls"));
        int saveDialog = fileChooser.showDialog(null, message);
        if (saveDialog == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            input = file.getAbsolutePath();
        }
        return input;
    }

    private static void writeToLog(String message) {
        RoosterProgramma.getQueryManager().addToLog(message);
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    /**
     *
     * @param text
     * @return
     */
    public static String SHA1(String text) {
        String Sha1 = "";
        String value = text.toUpperCase();
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-1");
            byte[] sha1hash;
            md.update(value.getBytes("iso-8859-1"), 0, value.length());
            sha1hash = md.digest();
            Sha1 = convertToHex(sha1hash);
        } catch (NoSuchAlgorithmException ex) {
            writeToLog("Algoritme is onjuist: " + ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            writeToLog("Encoding is onjuist: " + ex.getMessage());
        }
        return Sha1;
    }

    /**
     *
     * @param bool
     * @return
     */
    public static int booleanToInt(boolean bool) {
        return (bool ? 1 : 0);
    }

    /**
     *
     * @param interval
     * @return
     */
    public static Double intervalToDuration(String interval) {
        String pieces[] = interval.split("-");
        String start = pieces[0];
        String end = pieces[1];
        int minutesStart = Integer.parseInt(start.substring(start.length() - 2, start.length())) + Integer.parseInt(start.substring(0, start.length() - 2)) * 60;
        int minutesEnd = Integer.parseInt(end.substring(end.length() - 2, end.length())) + Integer.parseInt(end.substring(0, end.length() - 2)) * 60;
        int duration = (int) ((minutesEnd - minutesStart) / 0.6);
        return duration / 100.0;
    }

    /**
     *
     * @param type
     * @return
     */
    public static contractTypes intToContractType(int type) {
        for (contractTypes tmpType : contractTypes.values()) {
            if (tmpType.ordinal() == type) {
                return tmpType;
            }
        }
        return contractTypes.CALLWORKER;
    }

    /**
     *
     * @param type
     * @return
     */
    public static employeeTypes intToEmployeeType(int type) {
        for (employeeTypes tmpType : employeeTypes.values()) {
            if (tmpType.ordinal() == type) {
                return tmpType;
            }
        }
        return employeeTypes.CLERK;
    }
}
