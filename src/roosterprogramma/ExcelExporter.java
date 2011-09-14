/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roosterprogramma;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            if (inverted) {
                for (int k = 0; k < model.getColumnCount(); k++) {
                    Label label = new Label(k, 0, model.getColumnName(k));
                    sheet.addCell(label);
                }
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Label label = new Label(j, i + 1, model.getValueAt(i, j).toString());
                        sheet.addCell(label);
                    }
                }
            } else {
                for (int k = 0; k < model.getColumnCount(); k++) {
                    Label label = new Label(0, k, model.getColumnName(k));
                    sheet.addCell(label);
                }
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Label label = new Label(i + 1, j, model.getValueAt(i, j).toString());
                        sheet.addCell(label);
                    }
                }
            }
            workbook.write();
            workbook.setProtected(true);    // Niet zeker of hij hier goed staat...maar deze zou de file "read-only" moeten maken
            workbook.close();
            RoosterProgramma.getInstance().showMessage("Exporteren naar excel bestand is gelukt.", "Succes!", false);
        } catch (WriteException ex) {
            Logger.getLogger(ExcelExporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
