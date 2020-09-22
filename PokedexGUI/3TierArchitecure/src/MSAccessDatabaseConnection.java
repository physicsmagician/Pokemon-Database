import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
 
public class MSAccessDatabaseConnection {
 
    public static Connection getConnection(String msAccDB) {
 
        // variables
        Connection connection = null;
 
        // Step 1: Loading or registering Oracle JDBC driver class
        try {
 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) {
 
            System.out.println("Problem in loading or "
                    + "registering MS Access JDBC driver");
            cnfex.printStackTrace();
        }
 
        // Step 2: Opening database connection
        try {
 
            String dbURL = "jdbc:ucanaccess://" + msAccDB; 
 
            // Step 2.A: Create and get connection using DriverManager class
            connection = DriverManager.getConnection(dbURL); 
 
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    
        return connection;
    }
    
    
    public static void closeConnection(Connection connection) {
        try {            
        	if(null != connection) {
                // and then finally close connection
                connection.close();
            }
        }
	    catch (SQLException sqlex) {
	        sqlex.printStackTrace();
	    }
    }
    
}
