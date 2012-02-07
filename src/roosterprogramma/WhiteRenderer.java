/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roosterprogramma;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Dark
 */
public class WhiteRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean isFocused, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, isFocused, row, column);
        if (!isSelected && !isFocused) {
            cell.setBackground(Color.white);
        }
        return cell;
    }
}