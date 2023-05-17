package thescorekeeper.models;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//A class that helps with accessing the database.
public class Database {

	//Starts the variable so it can be accessed throughout the methods.
	Connection con; //The database connection.
	
	public Database() {
		
		// Establishes the connection to the database upon creation of the object.
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scorekeeperdata","root","");
		
		} catch (SQLSyntaxErrorException e) {
			//If the database is not found, it is created.
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
				Statement stmt = con.createStatement();
				String sql = "CREATE DATABASE scorekeeperdata;";
				stmt.executeUpdate(sql);
				//Once the database is created, the connection to the database is established.
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scorekeeperdata","root","");
				} catch (Exception e2) {
					
				}
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//Method for executing queries to the database.
	public ResultSet executeQuery(String sql) {
		
		//Creates a result set that will be used to store what is returned in the query.
		//Is set to null incase a value is not returned in the try catch.
		ResultSet rs = null;
		try {
			//Executes the query using the string that was passed in.
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			
		}
		
		//Returns the result set.
		return rs;

		
	}
	
	//Method for executing an update to the database.
	public void executeUpdate(String sql) {
		
		//Takes the passed in string and executes it towards the database as an update.
		try {
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		
		} catch (Exception e) {
			
		}
	}
	
	//Method for registering a player into the database.
	public boolean register_player(String pName) {
		
		//Takes in the data and time and formats it assigning it to a variable.
		SimpleDateFormat dateForm = new SimpleDateFormat("MMMM/dd/Y hh:mm:ss a");
		Date dateData = new Date();
		String date = dateForm.format(dateData);
		
		//Sets a boolean variable to be returned.
		boolean valid = true;
		try {
			//Adds the player's name and the date/time to the database.
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO `player` (`Name`, `DateTime_Added`) VALUES ('"+pName+"', '"+date+"')";
			stmt.executeUpdate(sql);
			
			
			} catch (SQLIntegrityConstraintViolationException e1) {
			
				//if there is already a player with the same name in there, it will through this error.
				//the valid boolean is set to false, meaning this happened and that the player could not be registered.
				valid = false;
				
			} catch (SQLSyntaxErrorException e1) {
				
				//If the player table is not found in the database, it is created.
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scorekeeperdata","root","");
				Statement stmt = con.createStatement();
				String sql = "CREATE TABLE player (Name VARCHAR(266), DateTime_Added text, PRIMARY KEY(Name));";
				stmt.executeUpdate(sql);
				//After the player table is created, then the player's information is added to it.
				sql = "INSERT INTO `player` (`Name`, `DateTime_Added`) VALUES ('"+pName+"', '"+date+"')";
				stmt.executeUpdate(sql);
				
				} catch (Exception e2) {
					System.out.println(e2);
				}
			
			} catch (Exception e1) {
				
			}
		
		//Returns the boolean valid.
		return valid;
	}
	
	
	
	
}
