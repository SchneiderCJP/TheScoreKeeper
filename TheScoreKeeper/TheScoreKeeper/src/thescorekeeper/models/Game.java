package thescorekeeper.models;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

public class Game {

	//Starts all the variables so they can be accessed throughout the methods.
	String name; // The name of the game.
	int rounds = 1; // The total amount of rounds for the game.
	int currentRound = 1; // The current round the user would be on while playing.
	double pointsPR = 1.0; // The amount of points given per round.
	String date; // The date/time the game is started.
	String storeData = ""; //The stats accumulated throughout the rounds of the game.
	String gameData =""; // The information about the game (name,rounds,date,etc.) to be added to the stats.
	String winners = ""; //The winner(s).
	String secondP = ""; // The second place(s).
	String thirdP = ""; // The third place(s).
	double hpoints = 0; // The highest amount of points earned in total.
	double hpoints2 = 0; // The second highest amount of points earned in total.
	double hpoints3 = 0; // The third highest amount of points earned in total.
	double lpoints; // The lowest amount of points earned in total.
	int totalWinners = 0; // The total amount of winners.
	Boolean pPerRound; // If points per round option is selected, if it true that means it is selected. If false, it means it is winner per round.
	HashMap<String, Player> players = new HashMap<String, Player>(); //Hashmap to store all the player objects to their string name.
	String playersListed = ""; //A list of all the players.
	int totalPlayers; // Total number of players who played.

	//The game object is created with information passed in from the game menu view.
	public Game (String name, Boolean pPerRound, int rounds, double pointPR,  HashMap<String, Player> players) {
		this.name = name;
		this.rounds = rounds;
		this.pointsPR = pointPR;
		this.pPerRound = pPerRound;
		//The current date and time is formatted and stored to a variable.
		SimpleDateFormat dateForm = new SimpleDateFormat("MMMM/dd/Y");
		SimpleDateFormat dateFormTime =  new SimpleDateFormat("hh:mm:ss a");
		Date dateData = new Date();
		Date timeData = new Date();
		this.date = dateForm.format(dateData) + " at "+ dateFormTime.format(timeData);
		
		this.players = players;
		
		//Adds the new game into the database to the game_match table.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scorekeeperdata","root","");
			Statement stmt = con.createStatement();
			String sql = "INSERT INTO `game_match` (`Game_ID`, `Game_DateTime`, `Game_Name`, `Rounds`, `PointsPR`, `Winner`, `Data`, `HighestPoints`, `LowestPoints`, `SecondPlace`, `ThirdPlace`, `Players`) "
					+ "VALUES (NULL, '"+this.date+"', '"+this.name+"', '"+this.rounds+"', '"+this.pointsPR+"', '', '','0.0','0.0','','','');";
			stmt.executeUpdate(sql);
		
			} catch (SQLSyntaxErrorException e) {
				
				// If the game_match table is not found in the database, then it is created.
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scorekeeperdata","root","");
				Statement stmt = con.createStatement();
				String sql = "CREATE TABLE game_match(Game_ID int NOT NULL AUTO_INCREMENT, Game_DateTime text, Game_Name text, Rounds int, PointsPR double, Winner text, Data text, HighestPoints double, LowestPoints double, SecondPlace text, ThirdPlace text, Players text, PRIMARY KEY (Game_ID));";
				stmt.executeUpdate(sql);
				// Once the game_match table is created, the game's information is then added to the table.
				sql = "INSERT INTO `game_match` (`Game_ID`, `Game_DateTime`, `Game_Name`, `Rounds`, `PointsPR`, `Winner`, `Data`, `HighestPoints`, `LowestPoints`, `SecondPlace`, `ThirdPlace`, `Players`) "
						+ "VALUES (NULL, '"+this.date+"', '"+this.name+"', '"+this.rounds+"', '"+this.pointsPR+"', '', '','0.0','0.0','','','');";
				stmt.executeUpdate(sql);
				} catch (Exception e2) {
					
				}
			}catch (Exception e1) {
				System.out.println(e1);
			}
		
		
	}
	
	//Getters for the variables.
	public int getTotalPlayers() {
		return totalPlayers;
	}
	
	public String getWinners() {
		return winners;
	}

	public String getSecondP() {
		return secondP;
	}

	public String getThirdP() {
		return thirdP;
	}

	public double getHpoints() {
		return hpoints;
	}

	public double getLpoints() {
		return lpoints;
	}

	public int getTotalWinners() {
		return totalWinners;
	}

	public String getName() {
		return name;
	}
	
	public String getDate() {
		return date;
	}

	public int getRounds() {
		return rounds;
	}
	
	public int getCurrentRound() {
		if (currentRound <= 0) {
			return (1);
		} else {
		return currentRound;
		}
	}
	
	public HashMap<String,Player> getPlayers(){
		return players;
	}
	
	public Boolean getpPerRound() {
		return pPerRound;
	}
	
	public Double getPointsPR() {
		return pointsPR;
	}
	
	
	//Method for when a round of the game has ended.
	public void endRound() {
		
		//Adds a message to the stats.
		storeData = storeData + "\nRound:" + currentRound;
		
		//Sets variables to hold information for the winners, the losers, and the stats.
		//This is so that when it is added to storeData, it is in order.
		String won = "";
		String lose = "";
		String stats = "";
		
		//If the game is winner per round (means points per round is false), then the correct information is added to the strings and their own stats.
		if (pPerRound == false) {
			for (String i : players.keySet()) {
				if (players.get(i).getRPoints(currentRound) > 0.0) {
					players.get(i).addStats("\nWinner of round "+currentRound+".");
					won = won + "\n"+ i + (" won the round.");
				} else {
					players.get(i).addStats("\nLost round " + currentRound + ".");
					lose = lose + "\n"+ i + (" lost the round.");
				}
				
			}
			
			//The losers and winners information is added to the overall data to be stored.
			storeData = storeData + lose + won;
		
		//If the game is points per round (means points per round is true), then the correct information is added to the strings.
		} else {
			
			//Go through each player and adds how much points each player earned during the round to the overrall game stats and their own stats.
			for (String i : players.keySet()) {
				players.get(i).addStats("\nEarned " + players.get(i).getRPoints(currentRound) + " in round " + currentRound + ".");
				stats = stats + "\n"+ i + " earned " + players.get(i).getRPoints(currentRound) + " during the round.";
			}
			
			//Finds the highest amount of points a player earned in the round.
			double highest = 0.0;
			ArrayList<String> hplist = new ArrayList<>();
			String highestp = "";
			for (Player i : players.values()) {
				if (i.getRPoints(currentRound) > highest) {
					highest = i.getRPoints(currentRound);
				}
			}
			
			//Adds who earned the highest points in the round to the overrall game stats to be stored and their own stats.
			for (Player i : players.values()) {
				if (i.getRPoints(currentRound) == highest) {
					hplist.add(i.getName());
					i.addStats("\nEarned the highest amount of points in round " + currentRound + ".");
				}
			}
			
			for (int i = 0; i < hplist.size(); i++) {
				if (i == 0) {
					highestp = highestp + hplist.get(0);
				} else if (i == hplist.size()-1) {
					highestp = highestp + " & "+ hplist.get(i);
				} else {
					highestp = highestp + ", " + hplist.get(i);
				}
			}
			//Adds all the data collected to the overrall game stats to be stored.
			storeData = storeData + stats + "\n" + highestp + " earned the most points this round.";
			
		}
		
		//Increments the current round unless it is the last round.
		if (currentRound != rounds) {
			currentRound++;
		}
		
		//Separates the each of the round's data by adding a long line.
		storeData = storeData + "\n_________________________";
		
	}
	
	//Method for subtracting a round from the overall game.
	public void subtractRound() {
		
		//Decreases the amount of total rounds there are of the game by one.
		rounds--;
		
		//Checks to see if the rounds is still greater or equal to currentRound, if it is not, a round won't be subtracted.
		if (rounds >= currentRound) {
			//Adds this action to the overall data to be stored.
			storeData = storeData + "\n*A round was subtracted*";
		}
		
		//Checks to see if the current round is greater than the amount rounds and keeps it there, this prevents the number of rounds from becoming too small.
		// Rounds being smaller than current round would not make sense.
		if (currentRound > rounds) {
			rounds = currentRound;
		}
	}
	
	//Method for adding a round to the overall game.
	public void addRound() {
		//Adds this action to the overall data to be stored.
		storeData = storeData + "\n*An additional round was added*";
		//Increases the amount of rounds there are of the game by one.
		rounds++;
	}
	
	// Method for when the game has ended.
	public void endGame() {
		
		//Ends the last round of the game.
		endRound();
		
		//Takes the amount of players in the hashmap as the total amount of players.
		totalPlayers = players.size();
		
		//If the game was ended early, this checks to see how many rounds were played and assigns it to rounds.
		if (currentRound != rounds) {
			rounds = currentRound-1;
		}
		
		//ArrayLists to hold all the winners,second places,and third places.
		ArrayList<String> wList = new ArrayList<>();
		ArrayList<String> sList = new ArrayList<>();
		ArrayList<String> tList = new ArrayList<>();
		
		//Goes through the player's total scores and finds the highest.
		for (Player i : players.values()){
			if (i.getTotal() > hpoints) {
				hpoints = i.getTotal();
			}
		}
		
		//Goes through the player's total scores and finds the second highest.
		for (Player i : players.values()){
			if (i.getTotal() > hpoints2 && i.getTotal() < hpoints) {
				hpoints2 = i.getTotal();
			}
		}
		
		//Goes through the player's total scores and finds the third highest.
		for (Player i : players.values()){
			if (i.getTotal() > hpoints3 && i.getTotal() < hpoints && i.getTotal() < hpoints2 ) {
				hpoints3 = i.getTotal();
			}
		}
			
		
		//Sets the lowest point variable to the highest point.
		lpoints = hpoints;
		
		//Goes through each player's total points and compares it to the lowest point.
		//If the player's points is lower, that number becomes the lowest point.
		//This is used to find the lowest amount of points earned in the game.
		for (Player i : players.values()){
			if (i.getTotal() < lpoints) {
				lpoints = i.getTotal();
			}
		}
	
		//Goes through each of the players.
		for (String i : players.keySet()){
			
			//If the players total points is equal to the highest points earned, the player is a winner.
			//This info is added to their stats and the player is added to the winners list.
			//The amount of total winners is also increased by one.
			//The player's method to store their stats is also called passing in the game's name and date/time.
			if (players.get(i).getTotal() == hpoints) {
				totalWinners++;
				wList.add(i);
				players.get(i).addStats("\n______________________\nCame in first place & won the game.");
				players.get(i).storeStats(name,date);
			
			//If the players total points is equal to the second highest points earned, the player is in second place.
			//This info is added to their stats and the player is added to the second place list.
			//The player's method to store their stats is also called passing in the game's name and date/time.
			} else if (players.get(i).getTotal() == hpoints2) {
				sList.add(i);
				players.get(i).addStats("\n______________________\nCame in second place.");
				players.get(i).storeStats(name,date);
			
			//If the players total points is equal to the third highest points earned, the player is in third place.
			//This info is added to their stats and the player is added to the third place list.
			//The player's method to store their stats is also called passing in the game's name and date/time.
			} else if (players.get(i).getTotal() == hpoints3) {
				tList.add(i);
				players.get(i).addStats("\n______________________\nCame in third place.");
				players.get(i).storeStats(name,date);
			}else {
			//If the player did not come in first,second,or third, the player lost the game.
			//This info is added to their stats.
			//The player's method to store their stats is also called passing in the game's name and date/time.
				players.get(i).addStats("\n______________________\nLost the game.");
				players.get(i).storeStats(name,date);
			}
		}
		
		//Goes through all the players and adds them to players list in a string properly with commands and a &.
		int r = 0;
		for (Player i : players.values()){
			if (r == 0) {
				playersListed = playersListed + i.getName();
			} else if (r == players.size()-1 && players.size() > 2) {
				playersListed = playersListed + ", & "+ i.getName();
			} else if (r == players.size()-1 && players.size() <= 2) {
				playersListed = playersListed + " & "+ i.getName();
			} else {
				playersListed = playersListed + ", " + i.getName();
			}
			
			r++;
		}
		
		
		
		//Goes through all the winners and adds them to winners list in a string properly with commands and a &.
		for (int i = 0; i < wList.size(); i++) {
			if (i == 0) {
				winners = winners + wList.get(0);
			} else if (i == wList.size()-1 && wList.size() <= 2 ) {
				winners = winners + " & "+ wList.get(i);
			} else if (i == wList.size()-1 && wList.size() > 2 ) {
				winners = winners + ", & "+ wList.get(i);
			} else {
				winners = winners + ", " + wList.get(i);
			}
		}
		
		//Goes through all the second place and adds them to second place list in a string properly with commands and a &.
		for (int i = 0; i < sList.size(); i++) {
			if (i == 0) {
				secondP = secondP + sList.get(0);
			} else if (i == sList.size()-1 && sList.size() <= 2) {
				secondP = secondP + " & "+ sList.get(i);
			} else if (i == sList.size()-1 && sList.size() > 2) {
				secondP = secondP + ", & "+ sList.get(i);
			} else {
				secondP = secondP + ", " + sList.get(i);
			}
		}
		
		//Goes through all the third place and adds them to third place list in a string properly with commands and a &.
		for (int i = 0; i < tList.size(); i++) {
			if (i == 0) {
				thirdP = thirdP + tList.get(0);
			} else if (i == tList.size()-1 && tList.size() <= 2) {
				thirdP = thirdP + " & "+ tList.get(i);
			} else if (i == tList.size()-1 && tList.size() > 2) {
				thirdP = thirdP + ", & "+ tList.get(i);
			} else {
				thirdP = thirdP + ", " + tList.get(i);
			}
		}
		
		//Adds the players listed to the game's data that is to be stored.
		storeData = storeData + "\n" + playersListed + " played " + rounds + " rounds of " + name + ".";
		
		//The winners,second place, and third place along with the highest, second highest, third highest, and lowest points is added to the game's data that is to be stored.
		//Depending on if the game is points per round or winner per round, the appropriate message is added.
		if (pPerRound == false) {
			storeData = storeData + "\n" + winners + " won the game winning "+hpoints+" total rounds.";
			
			if (sList.size() != 0) {
			storeData = storeData + "\n" + secondP + " came in second place winning "+ hpoints2 + " total rounds.";
			}
			
			if (tList.size()!=0) {
			storeData = storeData + "\n" + thirdP + " came in third place winning "+ hpoints3 + " total rounds.";
			}
			storeData = storeData + "\nThe lowest amount of rounds won was " + lpoints;
		} else {
			storeData = storeData + "\n" + winners + " earned the highest amount of points of " + hpoints+ " throughout the game and won.";
			
			if (sList.size() != 0) {
			storeData = storeData + "\n" + secondP + " earned the second highest amount of points of " + hpoints2+ " throughout the game.";
			}
			
			if (tList.size() != 0) {
			storeData = storeData + "\n" + thirdP + " earned the third highest amount of points of " + hpoints3+ " throughout the game.";
			}
			
			storeData = storeData + "\nThe lowest amount of points earned throughout the game was " + lpoints;
			
		}
		
		//The game data is set adding the name, data & time and rounds.
		gameData = gameData + name + "\nDate & Time: " + date + "\nRounds: " + rounds + "\n______________________";
		
		//The game data is added to the data to be stored first then the rest of the data is added.
		storeData = gameData + storeData;
		
		//The winners, players, third place, second place, lowest points, highest points, data, and rounds is updated in the database.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scorekeeperdata","root","");
			Statement stmt = con.createStatement();
			String sql = "UPDATE `game_match` SET `Winner` = '"+winners+"', `Players` = '"+playersListed+"', `ThirdPlace` = '"+thirdP+"', `SecondPlace` = '"+secondP+"', `LowestPoints` = '"+lpoints+"', `HighestPoints` = '"+hpoints+"', `Data` = '"+storeData+"', `Rounds` = '"+rounds+"'  WHERE `game_match`.`Game_DateTime` = '"+date+"' AND `Game_Name` = '"+name+"';";
			stmt.executeUpdate(sql);
		
			}
			
			catch (Exception e1) {
				System.out.println(e1);
			}
		
		
	}
	
}
