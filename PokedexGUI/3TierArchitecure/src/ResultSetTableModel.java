import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class ResultSetTableModel extends DefaultTableModel {

	public void setRowColour(int row, Color c) {
//        rowColours.set(row, c);
        fireTableRowsUpdated(row, row);
    }

    public Color getRowColour(int row) {
    	if ((row % 2) == 0) {
    		return Color.RED;    	
    	}
    	else {
    		return Color.BLUE;
    	}
    }

    @Override
    public int getRowCount() {
        return super.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return super.getColumnCount();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return super.getValueAt(row, column);    	
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       //set all cells to be non-editable
    	return false;
    }
}