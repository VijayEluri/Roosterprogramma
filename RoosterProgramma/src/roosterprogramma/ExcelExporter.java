/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package roosterprogramma;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Dymion
 */
public class ExcelExporter {
    public ExcelExporter() {
    }

    public static void Export(JTable table, File file) {
        FileWriter out = null;
        try {
            TableModel model = table.getModel();
            out = new FileWriter(file);
            for (int i = 0; i < model.getColumnCount(); i++) {
                out.write(model.getColumnName(i) + "\t");
            }
            out.write("\n");
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    out.write(model.getValueAt(i, j).toString() + "\t");
                }
                out.write("\n");
            }
            out.close();
            RoosterProgramma.getInstance().showMessage("Exporteren naar excel bestand is gelukt.", "Succes!", false);
        } catch (IOException ex) {
            Logger.getLogger(ExcelExporter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
                file.setReadOnly();
            } catch (IOException ex) {
                Logger.getLogger(ExcelExporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
