import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * A JTable used to display a SQL ResultSet.
 * @author fahdshariff
 * http://fahdshariff.blogspot.com/2010/02/display-any-resultset-in-jtable.html
 *
 */
public class ResultSetTable extends JTable{

	private final DefaultTableModel dataModel;
	private final ResultSetColorCellRenderer myRenderer;
	
	public void setResultSet(ResultSet rs) {

		try {
			dataModel.setRowCount(0);
			dataModel.setColumnCount(0);
			rs.beforeFirst();
			
			//create an array of column names
			ResultSetMetaData metaData = rs.getMetaData();
			int colCount = metaData.getColumnCount();

			String[] columnNames = new String[colCount];
			for (int i = 1; i <= colCount; i++) {
				columnNames[i - 1] = metaData.getColumnName(i);
			}
			dataModel.setColumnIdentifiers(columnNames);

			//create header row
			String[] headerData = new String[colCount];
			for (int i = 1; i <= colCount; i++) {
				headerData[i - 1] = metaData.getColumnName(i);
			}
			dataModel.addRow(headerData);
			
			//now populate the data			
			while (rs.next()) {
				String[] rowData = new String[colCount];
				for (int i = 1; i <= colCount; i++) {
					rowData[i - 1] = rs.getString(i);
				}
				dataModel.addRow(rowData);
			}
			
			for (int i = 0; i < colCount; i++) {
				this.getColumnModel().getColumn(i).setCellRenderer(myRenderer);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void refreshResultSet(ResultSet rs) {

		//super();
		ResultSetTableModel dataModel = new ResultSetTableModel();
		ResultSetColorCellRenderer myRenderer = new ResultSetColorCellRenderer();
		this.setModel(dataModel);
		
		if (rs != null) {
			setResultSet(rs);
		}

		try {
			//create an array of column names
			ResultSetMetaData mdata = rs.getMetaData();
			int colCount = mdata.getColumnCount();
			String[] colNames = new String[colCount];
			for (int i = 1; i <= colCount; i++) {
				colNames[i - 1] = mdata.getColumnName(i);
			}
			dataModel.setColumnIdentifiers(colNames);

			//create header row
			String[] headerData = new String[colCount];
			for (int i = 1; i <= colCount; i++) {
				headerData[i - 1] = mdata.getColumnName(i);
			}
			dataModel.addRow(headerData);
			
			//now populate the data			
			while (rs.next()) {
				String[] rowData = new String[colCount];
				for (int i = 1; i <= colCount; i++) {
					rowData[i - 1] = rs.getString(i);
				}
				dataModel.addRow(rowData);
			}
			
			for (int i = 0; i < colCount; i++) {
				this.getColumnModel().getColumn(i).setCellRenderer(myRenderer);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally{
			try {
				rs.close();
			}
			catch (SQLException ignore) {
			}
		}
	}
	
	public ResultSetTable(ResultSet rs) {

		super();
		dataModel = new ResultSetTableModel();
		myRenderer = new ResultSetColorCellRenderer();
		this.setModel(dataModel);
		
		if (rs != null) {
			setResultSet(rs);
		}

//		try {
//			//create an array of column names
//			ResultSetMetaData mdata = rs.getMetaData();
//			int colCount = mdata.getColumnCount();
//			String[] colNames = new String[colCount];
//			for (int i = 1; i <= colCount; i++) {
//				colNames[i - 1] = mdata.getColumnName(i);
//			}
//			dataModel.setColumnIdentifiers(colNames);
//
//			//create header row
//			String[] headerData = new String[colCount];
//			for (int i = 1; i <= colCount; i++) {
//				headerData[i - 1] = mdata.getColumnName(i);
//			}
//			dataModel.addRow(headerData);
//			
//			//now populate the data			
//			while (rs.next()) {
//				String[] rowData = new String[colCount];
//				for (int i = 1; i <= colCount; i++) {
//					rowData[i - 1] = rs.getString(i);
//				}
//				dataModel.addRow(rowData);
//			}
//			
//			for (int i = 0; i < colCount; i++) {
//				this.getColumnModel().getColumn(i).setCellRenderer(myRenderer);
//			}
//			
//			
//		}
//
//		finally{
//			try {
//				rs.close();
//			}
//			catch (SQLException ignore) {
//			}
//		}
	}
}
