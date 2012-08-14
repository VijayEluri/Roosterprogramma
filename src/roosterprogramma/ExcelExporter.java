/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roosterprogramma;

import java.io.File;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Dymion
 */
public class ExcelExporter {

    private ExcelExporter() {
    }

    /**
     *
     * @param table
     * @param file
     * @param inverted
     */
    public static void Export(JTable table, File file, boolean inverted) {
        try {
            boolean error = false;
            TableModel model = table.getModel();
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("Blad1", 0);
            WritableCellFormat format = new WritableCellFormat();
            format.setWrap(true);
            for (int k = 0; k < model.getColumnCount(); k++) {
                Label label = new Label(inverted ? k : 0, inverted ? 0 : k, model.getColumnName(k));
                sheet.addCell(label);
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    int column = (inverted ? j : i + 1);
                    int row = (inverted ? i + 1 : j);
                    String value = model.getValueAt(table.convertRowIndexToModel(i), j) == null ? "" : model.getValueAt(table.convertRowIndexToModel(i), j).toString();
                    try {
                        Label label = new Label(column, row, value);
                        label.setCellFormat(format);
                        sheet.addCell(label);
                    } catch (NullPointerException ex) {
                        Utils.showMessage("Er is een fout opgetreden bij het exporteren.", "Fout bij exporteren.", ex.getMessage(), false);
                        System.err.println("Inverted: " + inverted + "\nRow: " + row + "\nColumn: " + column + "\nValue: " + value);
                        error = true;
                    }
                    if (error) {
                        break;
                    }
                }
                if (error) {
                    break;
                }
            }
            if (error) {
                return;
            }
            workbook.write();
            workbook.close();
            Utils.showMessage("Exporteren naar excel bestand is gelukt.", "Succes!", null, false);
        } catch (WriteException ex) {
            Utils.showMessage("Fout opgetreden in de excel module.", "Fout!", ex.getMessage(), false);
        } catch (IOException ex) {
            Utils.showMessage("Fout opgetreden in het lezen/schrijven van het bestand.", "Fout!", ex.getMessage(), false);
        }
    }
}
