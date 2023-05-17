package thescorekeeper.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.ArrayList;

public class Player {

	//Starts the variables so they can be accessed throughout the methods.
	String name; //The player's name
	String Stats = "Stats:"; // The player's stats that will be stored in their data in the database.
	ArrayList<Double> rPoints = new ArrayList<>(); // An arrayList of all the points the player earns, each index being a round.
	
	public Player(String name) {
		this.name = name;
		
	}
	
	//Getters and setters.
	public double getRPoints(int i) {
		//1 is subtracted because everything in an arraylist starts at 0 vs. 1 
		//The rounds start at 1, so 0 in the arraylist is = 1.
		return rPoints.get(i-1);
	}
	
	public String getName() {
		return name;
	}
	
	public void setRPoints (double points, int i) {
		//Adds points to the arraylist of points.
		rPoints.add(points);
		//Make sures that the points is added to the correct place in the arraylist.
		//1 is subtracted because everything in an arraylist starts at 0 vs. 1 
		//The rounds start at 1, so 0 in the arraylist is = 1.
		rPoints.set(i-1, points);
	}
	
	//Method for getting the total amount of points.
	//Each points of each round added up for a total amount.
	public double getTotal() {
		//A variable is created to be returned.
		double total = 0.0;
		for (double i : rPoints) {
			//Goes through each of the points in the round points arrayList and added them together for the total.
			total = total + i;
		}
		//The total is returned.
		return total;
	}
	
	//Method for adding stats to the stats.
	public void addStats(String stats) {
		//Stats is always added to any stats that has already been added, keeping track of everything that has happened to the player.
		this.Stats = this.Stats + stats;
	}
	
	//Method for storing the stats that have been getting added to throughout the game.
	public void storeStats(String gName, String date) {
		
			//The player's name and data along with the game's name and date/time is added to the playersdata table in the database.
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scorekeeperdata","root","");
				Statement stmt = con.createStatement();
				String sql = "INSERT INTO `player_data` (`ID`,`Name`, `Game_Name`, `DateTime_Played`, `Data`) VALUES (NULL,'"+name+"', '"+gName+"', '"+date+"', '"+Stats+"');";
				stmt.executeUpdate(sql);
				
			} catch (SQLSyntaxErrorException e1) {
				
				//If the player_data table is not found in the database, it is created.
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scorekeeperdata","root","");
				Statement stmt = con.createStatement();
				String sql = "CREATE TABLE player_data (ID int NOT NULL AUTO_INCREMENT, Name VARCHAR(266), Game_Name text, DateTime_Played text, Data text, PRIMARY KEY (ID), FOREIGN KEY (Name) REFERENCES player(Name));";
				stmt.executeUpdate(sql);
				//After the player_data table is created, then the player's information is added to it.
				sql = "INSERT INTO `player_data` (`ID`,`Name`, `Game_Name`, `DateTime_Played`, `Data`) VALUES (NULL,'"+name+"', '"+gName+"', '"+date+"', '"+Stats+"');";
				stmt.executeUpdate(sql);
				
				} catch (Exception e2) {
					System.out.println(e2);
				}
			
			} catch (Exception e1) {
				System.out.println(e1);
				
			}
			
	}
	
	
}
