import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokedexMethods {

	String query = "";
	public Connection connection = MSAccessDatabaseConnection.getConnection("pokedex.accdb");;
	public ResultSetTable tblPrimary;
	public ResultSetTable tblSecondary;
	boolean booleanResultSet;
	ResultSet resultSet = null;
	FileReader fileReader;
	public ArrayList<Integer> IDSList = new ArrayList<Integer>();
	public ArrayList<Integer> pokemonMovesIDSList = new ArrayList<Integer>();
	
	public ArrayList<Integer> getIdsList(boolean primaryRecord) {
		if (primaryRecord) {
			query = "SELECT ID FROM Pokemon";
		} else {
			query = "SELECT ID FROM Moves";
		}
		try {
			resultSet = DatabaseUtilities.runQuery(connection, query);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		}
		
		try {
			while (resultSet.next()) {
				IDSList.add(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return IDSList;
	}
	public ArrayList<Integer> getRelationshipIDS(Object ID) {
		query = "SELECT MovesID FROM PokemonMoves WHERE PokemonID='" + ID + "';";
		try {
			resultSet = DatabaseUtilities.runQuery(connection, query);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		}
		
		try {
			while (resultSet.next()) {
				IDSList.add(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return IDSList;
	}
	public boolean isType1(int ID, Object Type1) {
		query = "SELECT Type2 FROM Pokemon"
				+ "WHERE ID='" + ID + "';";
		boolean type1IsType2 = false;
		try {
			resultSet = DatabaseUtilities.runQuery(connection, query);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		}
		try {
			if (resultSet.getObject(ID) == Type1) {
				type1IsType2 = true;
			} else {
				type1IsType2 = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return type1IsType2;
	}
	public Object getAllPrimaryID() {
		query = "SELECT ID FROM Pokemon";
		try {
			resultSet = DatabaseUtilities.runQuery(connection, query);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		}
		
		return DatabaseUtilities.printResultSet(resultSet);
	}
	public Object getAllSecondaryID() {
		query = "SELECT ID FROM Moves";
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();	
		}
		return booleanResultSet;
	}
	public Object getPokemon(int iD)         //e.g. Student getStudent(String studentID)
	{
		query = "SELECT Name FROM POKEMON"
				+ "WHERE ID='" + iD +"';";
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return booleanResultSet;
	}
	public Object getHP(int iD)     //e.g. Class getCourse(String classID)
	{
		query = "SELECT HP FROM Moves"
				+ "WHERE ID='" + iD + "';";
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return booleanResultSet;
	}
	public Object getAllSecondaryRecords(String primaryID)
	{
		return null;
	}
	public void modifyPrimaryRecord(Object ID, String name, Object type1, Object type2, String height, String weight)          //e.g. modifyStudent(Student student) 
	{
		query = "UPDATE Pokemon";
		if (name != null) {
			query += "  SET Name='" + name + "'"
					+ "WHERE ID='" + ID +"';";
		} else if (type1 != null) {
			System.out.println("TYPE1 NOT NULL: CHANGE COMMENCE");
			query += "  SET Type='" + type1 + "'"
					+ "WHERE ID='" + ID +"';";
		} else if (type2 != null) {
			query += "  SET Type2='" + type2 + "'"
					+ "WHERE ID='" + ID +"';";
		} else if (height != null) {
			query += "  SET Height='" + height + "'"
					+ "WHERE ID='" + ID +"';";
		} else {
			query += "  SET Weight='" + weight + "'"
					+ "WHERE ID='" + ID +"';";
		}
		System.out.println(query);
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public void modifySecondaryRecord(Object ID, String name, Object type, String power, String pp, Object effect)      //e.g. modifyCourse(Course course)
	{
		query = "UPDATE Moves";
		if (name != null) {
			query += "  SET Name = '" + name + "'"
					+ "WHERE ID = '" + ID +"';";
		} else if (type != null) {
			query += "  SET Type='" + type + "'"
					+ "WHERE ID='" + ID +"';";
		} else if (power != null) {
			query += "  SET Power='" + power + "'"
					+ "WHERE ID='" + ID +"';";
		} else if (pp != null) {
			query += "  SET PP='" + pp + "'"
					+ "WHERE ID='" + ID +"';";
		} else {
			query += "  SET Effect='" + effect + "'"
					+ "WHERE ID='" + ID +"';";
		} 
	
		System.out.println(query);
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void deletePrimaryRecord(Object primaryID)
	{
		query = "DELETE FROM Pokemon WHERE ID='" + primaryID + "';";
		System.out.println(query);
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void deleteSecondaryRecord(Object secondaryID)
	{
		query = "DELETE FROM Moves WHERE ID='" + secondaryID + "';";
		System.out.println(query);
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void addPrimaryRecord(String name, Object type1, Object type2, double height, double weight)
	{
		query = "INSERT INTO Pokemon (Name, Type, Type2, Height, Weight)" + 
				"VALUES ('" + name + "', '" + type1 + "', '" + type2 
				+  "', '" + height + "', '" + weight + "');";
		
		System.out.println(query);
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void addSecondaryRecord(String string, Object type, double power, Object pp, Object effect)
	{
		query = "INSERT INTO Moves (Name, Type, Power, PP, Effect)" + 
				"VALUES ('" + string + "', '" + type + "', '" + power + "', '" + pp 
				+  "', '" + effect + "');";
		System.out.println(query);
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void addRelationship(Object primaryID, Object secondaryID)     //creates a relationship between the two records
	{
		query = "INSERT INTO PokemonMoves (PokemonID, MovesID)" + 
				"VALUES ('" + primaryID + "', '" + secondaryID + "');";
		System.out.println(query);
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void deleteRelationship(Object primaryID, Object secondaryID)  //removes a relationship between the two records
	{
		query = "DELETE FROM PokemonMoves WHERE PokemonID='" + primaryID + "' AND MovesID='" + secondaryID + "';";
		System.out.println(query);
		try {
			booleanResultSet = DatabaseUtilities.execute(connection, query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	/**
	 * Must import correct file for database in the form: Name, Type, Type2, Height, Weight
	 * Do not include the names for the columns
	 * @param fileName
	 */
	public void importPrimaryRecords(String fileName)
	{
		String text = "";
		String[] fields = null;
		int count = 0;
		//ArrayList<Pokemon> csvPokemon = new ArrayList<Pokemon>();
		BufferedReader inputStream = null;
		if (fileName != null) {
			try {
				inputStream = new BufferedReader(new FileReader(fileName));
				String l;
				 while ((l = inputStream.readLine()) != null) {
					 if (text == "") {
						 text = l;
						 fields = l.split(",");
						 this.addPrimaryRecord(fields[0], fields[1], fields[2], 
									Double.parseDouble(fields[3]), Double.parseDouble(fields[4]));
						count++;
					 }  else {
						 text += "\r\n" + l;
						 fields = l.split(",");
						 this.addPrimaryRecord(fields[0], fields[1], fields[2], 
									Double.parseDouble(fields[3]), Double.parseDouble(fields[4]));
					 }
		         }
				
			  } catch (FileNotFoundException e) {
				  text = "";
				e.printStackTrace();
			} catch (IOException e) {
				  text = "";
				e.printStackTrace();
			}
			
            if (inputStream != null) {
                try {
					inputStream.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
            }
		} 
        
	}
	/**
	 * Must import correct file for database in the form: Name, Type, Power, PP, Effect
	 * @param fileName
	 */
	public void importSecondaryRecords(String fileName)
	{
		String text = "";
		String[] fields = null;
		int count = 0;
		//ArrayList<Pokemon> csvPokemon = new ArrayList<Pokemon>();
		BufferedReader inputStream = null;
		if (fileName != null) {
			try {
				inputStream = new BufferedReader(new FileReader(fileName));
				String l;
				 while ((l = inputStream.readLine()) != null) {
					 if (text == "") {
						 text = l;
						 fields = l.split(",");
						 this.addSecondaryRecord(fields[0], fields[1],  Double.parseDouble(fields[2]), 
									Double.parseDouble(fields[3]), fields[4]);
						count++;
					 }  else {
						 text += "\r\n" + l;
						 fields = l.split(",");
						 this.addSecondaryRecord(fields[0], fields[1], Double.parseDouble(fields[2]), 
									Double.parseDouble(fields[3]), fields[4]);
					 }
		         }
				
			  } catch (FileNotFoundException e) {
				  text = "";
				e.printStackTrace();
			} catch (IOException e) {
				  text = "";
				e.printStackTrace();
			}
			
            if (inputStream != null) {
                try {
					inputStream.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
            }
		} 
	}
}
