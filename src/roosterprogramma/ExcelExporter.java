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
import jxl.write.Label;
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

    public static void Export(JTable table, File file, boolean inverted) {
        try {
            TableModel model = table.getModel();
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("Blad1", 0);
            for (int k = 0; k < model.getColumnCount(); k++) {
                Label label = new Label(k, 0, model.getColumnName(k));
                sheet.addCell(label);
            }
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Label label = new Label((inverted ? j : i + 1), (inverted ? i + 1 : j), model.getValueAt(i, j).toString());
                    sheet.addCell(label);
                }
            }
            workbook.write();
            workbook.close();
            Utils.showMessage("Exporteren naar excel bestand is gelukt.", "Succes!", false, null);
        } catch (WriteException ex) {
            Utils.showMessage("Fout opgetreden in de excel module.", "Fout!", true, ex.getMessage());
        } catch (IOException ex) {
            Utils.showMessage("Fout opgetreden in het lezen/schrijven van het bestand.", "Fout!", true, ex.getMessage());
        }
    }
}
