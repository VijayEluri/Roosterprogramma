/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package roosterprogramma;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Dymion
 */
public class ExcelExporter {
    public ExcelExporter() { }
    public void exportTable(JTable table, File file) throws IOException {
        TableModel model = table.getModel();
        FileWriter out = new FileWriter(file);

        for(int i=0; i < model.getColumnCount(); i++) {
            out.write(model.getColumnName(i) + "\t");
        }
        out.write("\n");

        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
                out.write(model.getValueAt(i,j).toString()+"\t");
            }
            out.write("\n");
        }
        out.close();
        String succes = "Exporteren naar excel bestand is gelukt.";
        JOptionPane.showMessageDialog(null, succes);
    }
}
