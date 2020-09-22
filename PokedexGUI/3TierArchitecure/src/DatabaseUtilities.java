import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtilities {

    public static void closeConnection(Connection connection) {
        try {            
        	if(null != connection) {
                connection.close();
            }
        }
	    catch (SQLException sqlex) {
	        sqlex.printStackTrace();
	    }
    }
    
    public static void closeQuery(ResultSet resultSet) {
        try {
			if (resultSet != null) {
				Statement statement = resultSet.getStatement();
				if (statement != null) {
					statement.close();
				}
			    resultSet.close();
			}
		} catch (SQLException e) {
			//SQL exception; handle but also log
			e.printStackTrace();
		}
    }
    
    public static ResultSet runQuery(Connection connection, String sql) throws SQLException{
    	ResultSet resultSet = null;
    	Statement statement = null;

    	// Create JDBC Statement 
		statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		if (statement != null) {
    		// Execute SQL; retrieve data into ResultSet
			try {
				resultSet = statement.executeQuery(sql);
    		} catch (net.ucanaccess.jdbc.UcanaccessSQLException e) {
    			//SQL exception; handle but also log
    			e.printStackTrace();
    		} 
		}
		
        return resultSet;
    }
    
    public static boolean execute(Connection connection, String sql) throws SQLException {

    	Statement statement = null;
    	boolean result = false;

    	// Create JDBC Statement 
    	statement = connection.createStatement();

    	if (statement != null) {
    		// Execute SQL; retrieve data into ResultSet
    		try {
				result = statement.execute(sql);
    		} catch (net.ucanaccess.jdbc.UcanaccessSQLException e) {
    			//SQL exception; handle but also log
    			e.printStackTrace();
    		} 
    		
    	}

    	return result;
    }
    
    public static void printResultSetMeta(ResultSet resultSet) {
    	if (resultSet != null) {
    		try {
    			ResultSetMetaData meta = resultSet.getMetaData();
    			for (int col = 1; col <= meta.getColumnCount(); col++) {
    				System.out.println(String.format("%3d Name = %25s Type = %10s Size = %2d", col,meta.getColumnName(col) , meta.getColumnTypeName(col), meta.getColumnDisplaySize(col)));
    			}
    			meta.getColumnCount();
    			
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {
    		System.out.println("resultSet is null");
    	}
    }

    public static int printResultSet(ResultSet resultSet) {
    	return printResultSet(resultSet, 65536, 255);
    }
    
    public static int printResultSet(ResultSet resultSet, int maxRows, int maxCols) {
    	
    	int count = 0;
    	
    	if (resultSet != null) {
    		try {
    			// processing returned data and printing into console
    			resultSet.beforeFirst();
    			ResultSetMetaData meta = resultSet.getMetaData();
    			while(resultSet.next() && count < maxRows) {
    				count++;
    				for (int col = 1; col <= meta.getColumnCount() && col < maxCols; col++) {
    					Object value = resultSet.getObject(col);
    					
    					if (col > 1) {
    						System.out.print("\t");
    					}    					
    					if (meta.getColumnTypeName(col).contains("OTHER")) {
    						System.out.print("(OTHER)");
    					}
    					else if (value != null) {
        					System.out.print(value.toString());
    					}
    					else {
    						System.out.print("\t");
    					}
    				}
    				System.out.println();
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {
    		System.out.println("resultSet is null");
    	}
    	
    	return count;
    	    	
    }
    
    public static int getResultSetColCount(ResultSet resultSet) {
    	int cols = 0;
    	try {
    		cols = resultSet.getMetaData().getColumnCount();
			
		} catch (SQLException e) {			
		}
    	return cols;
    }
    
    
    public static long getResultSetRowCount(ResultSet resultSet) {
    	
    	int count = 0;
    	
    	if (resultSet != null) {
    		try {
    			// processing returned data and printing into console
    			resultSet.beforeFirst();
    			while(resultSet.next()) {
    				count++;
    			}
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {
    		System.out.println("resultSet is null");
    	}
    	
    	return count;
    	    	
    }
        
}
