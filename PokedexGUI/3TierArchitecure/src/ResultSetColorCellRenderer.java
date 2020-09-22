import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ResultSetColorCellRenderer extends DefaultTableCellRenderer {

	private Color GREY = new Color(191,191,191);
	private Color LIGHT_BLUE = new Color(223,223,255);
	
	private Font BOLD_FONT = new JLabel().getFont().deriveFont(Font.BOLD, 16);
	private Font REGULAR_FONT = new JLabel().getFont().deriveFont(Font.PLAIN, 12);
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		setForeground(Color.BLACK);

		if (row == 0) {
			setBackground(new Color(223, 223, 223));
			setFont(BOLD_FONT);
		} else if (row % 2 == 1) {
			setBackground(Color.WHITE);			
			setFont(REGULAR_FONT);
		} else {
			setBackground(LIGHT_BLUE);
			setFont(REGULAR_FONT);
		}
		
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);           
	}
}
