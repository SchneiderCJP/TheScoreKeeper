package thescorekeeper.controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import thescorekeeper.models.Database;
import thescorekeeper.models.Game;
import thescorekeeper.models.Player;
import thescorekeeper.views.AboutView;
import thescorekeeper.views.GMConfirmView;
import thescorekeeper.views.GameEndConfirmView;
import thescorekeeper.views.GameEndView;
import thescorekeeper.views.GameInfoView;
import thescorekeeper.views.GameMenuView;
import thescorekeeper.views.GameRoundView;
import thescorekeeper.views.MainMenuView;
import thescorekeeper.views.NewPlayerView;
import thescorekeeper.views.PastGamesView;
import thescorekeeper.views.PlayerStatsView;
import thescorekeeper.views.PlayersView;
import thescorekeeper.views.StatsSelectionView;

public class Controller {
	
	// Starts all the views and models so they can be accessed throughout the methods.
	private MainMenuView MMenu = new MainMenuView();
	private PastGamesView PGView;
	private AboutView AView = new AboutView();
	private NewPlayerView NPView;
	private PlayersView PView;
	private Database DB = new Database();
	private GameMenuView GMView;
	private GameRoundView GRView;
	private StatsSelectionView SSView;
	private GameEndView GEView;
	private Game game;
	private GMConfirmView GMCView;
	private GameEndConfirmView GECView;
	
	// The controller initializes by initializing the main menu.
	public void init_controller() {
		
	init_mainMenu();
		
		
	}
	
	// The main menu is initialized.
	private void init_mainMenu() {
		
		// All the buttons on the main menu are set to run a specific method when they are clicked.
		MMenu.getGameStartB().addActionListener(e -> init_GameMenuView());
		MMenu.getAboutB().addActionListener(e -> init_AboutView());
		MMenu.getNewPlayerB().addActionListener(e -> init_NewPlayerView());
		MMenu.getPlayersB().addActionListener(e -> init_PlayersView());
		MMenu.getPastGamesB().addActionListener(e -> init_PastGamesView());
		//Sets the mute button to run the toggle music method.
		MMenu.getMusicB().addActionListener(e-> toggle_Music());
		//Starts the background music for the application.
		MMenu.playMusic();
		// Sets the menu to come up.
		MMenu.setVisible(true);
		
	}

	//Method for turning on and off the music of the application.
	
	private void toggle_Music() {
		
		//If the music is playing and the user presses the mute button, the music should be stopped.
		if (MMenu.getMusicplaying() == true) {
			MMenu.setMusicplaying(false);
			MMenu.getMusic().stop();
			//The icon changes to show the user the appropriate image.
			MMenu.getMusicB().setIcon(new ImageIcon(MainMenuView.class.getResource("/thescorekeeper/resources/unmuted.png")));
			
		//If the music is not playing and the user presses the unmute button, the music should start.
		} else {
			MMenu.setMusicplaying(true);
			MMenu.getMusic().start();
			//The icon changes to show the user the appropriate image.
			MMenu.getMusicB().setIcon(new ImageIcon(MainMenuView.class.getResource("/thescorekeeper/resources/muted.png")));
		}
		
	}
	
	// Initializes the view to view the list of past games played.
	private void init_PastGamesView() {
	
		// Creates a new PastGamesView object, so it is a new one each time it is initialized.
		PGView = new PastGamesView();
		
		// Grabs the lists of games played from the data base and organizes it into a list model.
		// When it is added to the list model, it is added with it saying date & time: game name.
		ResultSet rs = DB.executeQuery("SELECT * FROM `game_match` ORDER BY `Game_ID` DESC");
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		
		try {
			
			while(rs.next()) {
				listModel.addElement(rs.getString(2)+ ": "+ rs.getString(3));
				//Adds the list to the JList in the PastGamesView.
				PGView.setgListModel(listModel);
			}
		} catch (SQLException e) {
			
		}
		
		// Sets it so that when the game selection is made the initialize game info view method is called.
		PGView.getgList().addListSelectionListener(new ListSelectionListener() {

	        public void valueChanged(ListSelectionEvent e) {
	            if(!e.getValueIsAdjusting()) {
	            	init_GameInfoView();
	            }
	        }
	    });
		
		// Makes the past game view visible.
		PGView.setVisible(true);
		PGView.setModal(true);
		
	}
	
	// Initializes the game info view to display the data of the game selected.
	private void init_GameInfoView() {
		
		// Creates a new GameInfoView object, so it is a new one each time it is initialized.
		GameInfoView GIView = new GameInfoView();
		
		//Gets the selected choice and splits it up to search the date/time and name in the database.
		String selected = PGView.getgList().getSelectedValue();
		String[] selectedArr = selected.split(": ", 2);
		GIView.getNameL().setText(selectedArr[1]);
		GIView.getDateL().setText(selectedArr[0]);
		
		//Searches for the data in the database according to the name and date/time and assigns it to the result set.
		ResultSet rs = DB.executeQuery("SELECT `Data` FROM `game_match` WHERE `Game_Name` = '"+selectedArr[1]+"' AND `Game_DateTime` = '"+selectedArr[0]+"'");
		
		//Uses the result set to display the data to the JTextArea in the view.
		try {
			 while(rs.next()) {
					GIView.getGameT().setText(rs.getString(1));
				}
			 } catch (Exception e) {
				 
			 }
			 
		//Sets the GameInfoView to visble.
			GIView.setVisible(true);
			GIView.setModal(true);
		
	}

	//Initializes the Players View that displays the list of players registered in the database for the application.
	private void init_PlayersView() {
		
		// Creates a new PlayersView object, so it is a new one each time it is initialized.
		PView = new PlayersView();

		// Grabs the lists of players the data base and organizes it into a list model.
		// When it is added to the list model, it is added with the each player's name.
		ResultSet rs = DB.executeQuery("SELECT * FROM `player`");
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		try {
			
			while(rs.next()) {
				listModel.addElement(rs.getString(1));
				// Sets the players view JList to the list model with all the names of the players.
				PView.setgListModel(listModel);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Sets it so that when the player selection is made the initialize initialize player stats view method is called.
		PView.getgList().addListSelectionListener(new ListSelectionListener() {

	        public void valueChanged(ListSelectionEvent e) {
	            if(!e.getValueIsAdjusting()) {
	            	init_PlayerStatsView();
	            }
	        }
	    });
		
		// Makes the players view visible for the player to use.
		PView.setVisible(true);
		PView.setModal(true);
		
		
		
		
		
	}

	// Initializes the Player Stats View that displays all the games that a selected player has stats for.
	private void init_PlayerStatsView() {
		
		// Creates a new PlayerStatsView object, so it is a new one each time it is initialized.
		PlayerStatsView PSView = new PlayerStatsView();
		
		//Gets the name of the player selected in the players view and assigns it to a variable.
		String name = PView.getgList().getSelectedValue();
		
		//Sets the name JLabel in the PlayerStatsView to the player's name using the name variable/
		PSView.getNameL().setText(name);
		
		//Retrieves the names of of the games that player has stats from and the date/time.
		ResultSet rs1 = DB.executeQuery("SELECT `Game_Name` , `DateTime_Played` FROM `player_data` WHERE `Name` = '"+name+"' ORDER BY `ID` DESC");
		//Retrieves date that the user was registered in the application.
		ResultSet rs2 = DB.executeQuery("SELECT `DateTime_Added` FROM `player` WHERE `Name` = '"+name+"'");
		
		// Creates a listModel to add the game names and date to, to display to the user for selection.
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try {
			
			while(rs1.next()) {
				listModel.addElement(rs1.getString(2) + ": "+ rs1.getString(1));
				// Sets the ListModel to the JList in Players Stats View that displays all the games for the user to select from.
				PSView.setStatsLModel(listModel);
			}
			
			while (rs2.next()) {
				// Sets the member since JLabel in the PlayerStatsView to the player's date added from the database.
				PSView.getDateL().setText(rs2.getString(1));
			}
		} catch (SQLException e) {
			
		}
		
		//Sets the JList (with the games list) so that when a game is selected, it will initialize the StatsSelectionView passing in the the selected game and the player's name.
		PSView.getStatsL().addListSelectionListener(new ListSelectionListener() {

	        public void valueChanged(ListSelectionEvent e) {
	            if(!e.getValueIsAdjusting()) {
	            	init_StatsSelectionView(PSView.getStatsL().getSelectedValue().toString(), name);
	            }
	        }
	    });
		
		// Sets the players view to visible for the player to use.
		PSView.setVisible(true);
		PSView.setModal(true);
		
	}

	//Initializes the StatsSelectionView that displays a player's stats for a specific game selected.
	private void init_StatsSelectionView(String selected, String pName) {
		
		// Creates a new PlayerStatsView object, so it is a new one each time it is initialized.
		SSView = new StatsSelectionView();
		
		//Takes the game name and date from selected passed in and separates it into an array.
		 String[] selectedArr = selected.split(": ", 2);
		 
		 //Uses the values of the game name and date to retrieve the data from the database.
		 ResultSet rs = DB.executeQuery("SELECT `Data` FROM `player_data` WHERE `Game_Name` = '"+selectedArr[1]+"' AND `DateTime_Played` = '"+selectedArr[0]+"' AND `Name` = '"+pName+"'");
		 
		 //Sets the text of the StatsSelectionView to display the game's name, the games date and the player's name.
		 SSView.getGameNameL().setText(selectedArr[1]);
		 SSView.getDateL().setText(selectedArr[0]);
		 SSView.getNameL().setText(pName);
		 
		 // Uses the data retrieved from the database and displays it via the text area in StatsSelectionView.
		 try {
		 while(rs.next()) {
				SSView.getGameT().setText(rs.getString(1));
			}
		 } catch (Exception e) {
			 
		 }
		 
		 // Makes the StatsSelection visible for the user to use.
		SSView.setVisible(true);
		SSView.setModal(true);
		 
		 
	}

	//Initializes the NewPlayerView that allows the user to input the name of a new player.
	private void init_NewPlayerView() {
		
		// Creates a new NewPlayerView object, so it is a new one each time it is initialized.
		NPView = new NewPlayerView();
		
		//Sets the create player button so that when it is clicked, the register player method is called.
		NPView.getCreateB().addActionListener(e -> register_Player());
		
		// Makes the NewPlayerView visible for the user to use.
		NPView.setVisible(true);
		NPView.setModal(true);
	}
	
	//A method for registering a player to the database of the application
	private void register_Player() {
		
		// In case the color of the text field background in the NewPlayerView is red from an incorrect response, it sets it back to normal.
		NPView.getpName().setBackground(new Color(255, 255, 255));
		
		//Gets the input of the user from the text field and assigns it to a variable.
		String name = NPView.getpName().getText();
		
		//Registers the players's name to the database.
		// If the players's name is not in the database, it will add it to it and return true to the boolean variable.
		// If the player's is in the database, it will not be able to add it and return false.
		Boolean valid = DB.register_player(name);
		
		//If valid is true that means the player's name was registered and will display in the NewPlayerView a profiles been created message, along with making sure the text field background is the correct color.
		if (valid == true) {
			NPView.getpName().setBackground(new Color(255, 255, 255));
			NPView.getpMessage().setText(name+"'s profile has been created.");
		
		// If valid is not true that means the player's name matched one in the database and could not be registered.
		// This will cause a message to be displayed in the NewPlayerView informing the user and asking them to try a different name.
		// It will also set the background of the text field to be turned red, indicating something is wrong.
		} else {
			NPView.getpMessage().setText(name+" is already in the system, please try a different name.");
			NPView.getpName().setBackground(new Color(255, 192, 203));
		}
		
		// Resets the text field back to being blank.
		NPView.getpName().setText("");	
		
	}

	//Initializes the about view.
	private void init_AboutView() {
		
		// Sets the about view to be visible and be displayed for the user.
		AView.setVisible(true);
		AView.setModal(true);
	}
	
	//Initializes the GameMenuView allowing the user to make selections, setting up the game they will be tracking.
	private void init_GameMenuView() {
		
		// Creates a new GameMenuView object, so it is a new one each time it is initialized.
		GMView = new GameMenuView();
		
		//Sets it so that when the JList containing all the added players is selected, it runs the refreshAddedplist method, which refreshes the values in the JList.
		GMView.getAddedplist().addListSelectionListener(new ListSelectionListener() {

	        public void valueChanged(ListSelectionEvent e) {
	            if(!e.getValueIsAdjusting()) {
	            	refreshAddedplist();
	            }
	        }
	    });
		
		//Sets it so that when the JList containing all the players in the database is selected, it runs the refreshPlist method, which refreshes the values in the JList.
		GMView.getPlist().addListSelectionListener(new ListSelectionListener() {

	        public void valueChanged(ListSelectionEvent e) {
	            if(!e.getValueIsAdjusting()) {
	            	refreshPlist();
	            }
	        }
	    });
		
		//Loads the list of players in the JList containing all the lists of players to add to the game.
		GMView.getPlist().setModel(loadPlist());
		
		//Sets the create game button to run the method to initiate the Game View Confirm View, to ask the user to further confirm the creation of the game.
		GMView.getCreateB().addActionListener(e -> init_GMConfirmView());
		
		//If the game menu is called from the game end view, it will dispose the game end view.
		if (GEView != null) {
			GEView.dispose();
			GEView = null;
		}
		
		//Sets the game menu visible for the user to use.
		GMView.setVisible(true);
		
		
	}
	
	//Initializes the Game View Confirm View, to ask the user to further confirm the creation of the game.
	private void init_GMConfirmView() {
		// Creates a new GMConfirmView object, so it is a new one each time it is initialized.
		GMCView = new GMConfirmView();
		//Sets the start game button to initiate the game round and start the overall game tracking.
		GMCView.getStartGB().addActionListener(e -> init_GameRound());
		GMCView.setVisible(true);
	}

	//Initializes the GameRoundView and begins the round for a game using the information the user inputed into the GameMenuView
	private void init_GameRound() {
		
		// Creates a new GameRoundView object, so it is a new one each time it is initialized for a new game.
		GRView = new GameRoundView();
		
		//Retrieves the total amount of rounds and the points per round from the GameMenuView and assigns it to variables.
		int rTotal = (Integer) GMView.getRounds().getValue();
		double pTotal = (Double) GMView.getpPerRound().getValue();
		
		//A hashmap is created to hold the player objects representing the players in the game.
		//The hashmap allows for each object to be called using the players name as the key.
		HashMap<String, Player> pMap = new HashMap<String, Player>();
		
		//Takes all the players in the JList containing all the players the user selected to play and adds them to the hashmap.
		for (int i = 0; i < GMView.getAddedlistModel().getSize(); i++) {
			
			pMap.put(GMView.getAddedlistModel().get(i), new Player(GMView.getAddedlistModel().get(i)));
		}
		
		//Creates a new game object passing in information from GameMenuView into the game class and assigns it to the private game object.
		game = new Game(GMView.getgName().getText(), GMView.getPointsPR().isSelected(), rTotal, pTotal, pMap);
		
		//Creates a default game panel for the player selections to be filled.
		GRView.getGamePanel().setLayout(new GridLayout(1, 0, 0, 0));
		GRView.getGamePanel().setBorder(null);
		
		//If the game is a winner per round (points per round == false), it will display each of the players with a radio button in the game panel.
		//This will allow the user to be able to select each winner individually, representing who won the round.
		if (game.getpPerRound() == false) {
			
			JScrollPane scrollPane = new JScrollPane();
			GRView.getGamePanel().add(scrollPane);
			
			JPanel gamePanel2 = new JPanel();
			gamePanel2.setBorder(null);
			scrollPane.setViewportView(gamePanel2);
			scrollPane.setBorder(null);
			
			for (String i : game.getPlayers().keySet()) {
				  GRView.getpButtons().put(i, new JRadioButton(i));
			}
			
			
	        gamePanel2.setLayout(new GridLayout(GRView.getpButtons().size()/2, GRView.getpButtons().size()/2));
	        
	        for (JRadioButton i : GRView.getpButtons().values()) {
	        	i.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        	i.setHorizontalAlignment(SwingConstants.CENTER);
	            gamePanel2.add(i);
	        }
	        
	        //Sets the directs to the appropriate message for the game type.
	        GRView.getDirectL().setText("Please select the winner(s) of the round:");
	        
	    //If the game is points per round, it will display each of the players with a JSpinner in the game panel.
	    //This will allow the user to input individual scores for each of the players.
	    //The spinners will also be defaulted to increase in increments matching the specified points per round (only for convenience, they will still be able to specify a different amount).
		} else {
			
			JScrollPane scrollPane = new JScrollPane();
			GRView.getGamePanel().add(scrollPane);
			
			JPanel gamePanel2 = new JPanel();
			gamePanel2.setBorder(null);
			scrollPane.setViewportView(gamePanel2);
			scrollPane.setBorder(null);
			
			gamePanel2.setPreferredSize(new Dimension(150,150));
			gamePanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5 ));
			
			GRView.getDirectL().setText("Input the points earned by each player this round.");
			for (String i : game.getPlayers().keySet()) {
				  GRView.getpLabels().put(i, new JLabel(i+":"));
				  GRView.getpSpinners().put(i, new JSpinner());
				  GRView.getpSpinners().get(i).setModel(new SpinnerNumberModel(0.0, 0.0, null, game.getPointsPR()));
				  GRView.getpSpinners().get(i).setMaximumSize(new Dimension(2, 2));
				  GRView.getpSpinners().get(i).setMinimumSize(new Dimension(2, 2));
				  GRView.getpPanels().put(i, new JPanel());
				  GRView.getpPanels().get(i).setBorder(null);
				  GRView.getpPanels().get(i).add(GRView.getpLabels().get(i));
				  GRView.getpPanels().get(i).add(GRView.getpSpinners().get(i));
				  gamePanel2.add(GRView.getpPanels().get(i));	
		}
			//Sets the directs to the appropriate message for the game type.
	        GRView.getDirectL().setText("Please input the amount of points earned by each player:");
			
		}
		
		//Sets the buttons to run the method that controls each individual round of the game.
		//Each button will pass in a keyword, specifying what the button would do to the round.
		GRView.getERButton().addActionListener(e -> round("next"));
		GRView.getAddB().addActionListener(e -> round("add"));
		GRView.getSubB().addActionListener(e -> round("sub"));
		//Sets the end game button to run the method to initialize the Game End Confirm View, to ask the user to further confirm the end of the game.
		GRView.getEGButton().addActionListener(e -> init_GameEndConfirmView());
		
		//Sets the name of the game displayed in game round view to the game's name.
		GRView.getNameL().setText(game.getName());
		//Begins the first round by calling the round method.
		round("");
				
		//Closes the GMConfirm View.
		if (GMCView != null) {
			GMCView.dispose();
		}
		
		//Closes the GameMenuView.
		GMView.dispose();
		
		//Sets the game round view to being visible.
		GRView.setVisible(true);

		
	}
	//Initializes the Game End Confirm View, to ask the user to further confirm the end of the game.
	private void init_GameEndConfirmView() {
		// Creates a new GameEndConfirmView object, so it is a new one each time it is initialized.
		GECView = new GameEndConfirmView();
		//Sets the end game button to initiate the end of the game and finish the overall game tracking.
		GECView.getEndGB().addActionListener(e -> endGame());
		GECView.setVisible(true);
	}

	//The method for ending the game.
	private void endGame() {
		
		//If the game is a winner per round (points per round == false), it will go through and see if the player's radio button is selected.
		// the winner per round is on the 1 point scale, meaning they get 1 point if they win a round or 0 if they lose a round.
		// If the button is selected, the player will receive 1.0 point for the round and the button will be reset back to being unselected.
		// If the button is not selected, the player will receive 0.0 points for the round.
		// Each of the player's points is stored in each of their separate object variables for each round.
		if (game.getpPerRound() == false) {
			for (String i : game.getPlayers().keySet()) {
				 if (GRView.getpButtons().get(i).isSelected()==true) {
					 game.getPlayers().get(i).setRPoints(1.0, game.getCurrentRound());
					 GRView.getpButtons().get(i).setSelected(false);
				 } else {
					 game.getPlayers().get(i).setRPoints(0.0, game.getCurrentRound());
				 }
			 }
			
		//If the game is points per round, it will go through and store each of the player's points from the values of their spinners and store them in their object variables.
		// It will then go and reset each of the spinners to 0.0 values.
		} else {
			for (String i : game.getPlayers().keySet()) {
				game.getPlayers().get(i).setRPoints((Double) GRView.getpSpinners().get(i).getValue(), game.getCurrentRound());
				GRView.getpSpinners().get(i).setValue(0.0);
			}
		}
		
		//The end game method in the game object is called.
		game.endGame();
		
		//The method to initialize the GameEndView is called.
		init_GameEndView();
		
		
	}
	
	//Initializes the GameEndView displaying the winners of the game, along with other stats about the game played.
	private void init_GameEndView() {
		
		// Creates a new GameEndView object, so it is a new one each time it is initialized for the end of a game.
		GEView = new GameEndView();
		
		//Checks to see if there is more winners to know if the label should be plural or singular and displays the correct one in the game end view.
		if (game.getTotalWinners() > 1) {
			GEView.getwTitleL().setText("The winners of this game is:");
		} else {
			GEView.getwTitleL().setText("The winner of this game is:");
		}
		
		//Checks to see if points per round is false or not and displays the appropriate message in the game end view.
		if (game.getpPerRound() == false) {
			GEView.getHpTitleL().setText("Highest amount of rounds won:");
			GEView.getLpTitleL().setText("Lowest amount of rounds won:");
		} else {
			GEView.getHpTitleL().setText("Highest amount of points earned:");
			GEView.getLpTitleL().setText("Lowest amount of points earned:");
		}
		
		//Retrieves the winner(s) from the game object and displays it in the game end view.
		GEView.getWinnerL().setText(game.getWinners());
		//Retrieves the second place(s) from the game object and displays it in the game end view.
		GEView.getSecondPL().setText(game.getSecondP());
		//Retrieves the third place(s) from the game object and displays it in the game end view.
		GEView.getThirdPL().setText(game.getThirdP());
		//Retrieves the highest points earned from the game object and displays it in the game end view.
		GEView.getHpL().setText(Double.toString(game.getHpoints()));
		//Retrieves the lowest points earned from the game object and displays it in the game end view.
		GEView.getLpL().setText(Double.toString(game.getLpoints()));
		//Retrieves the total amount of players from the game object and displays it in the game end view.
		GEView.getTotalPL().setText(Integer.toString(game.getTotalPlayers()));
		//Retrieves the total rounds played from the game object and displays it in the game end view.
		GEView.getRoundsPL().setText(Integer.toString(game.getRounds()));
		
		//Sets the game data button to call the method to load the game data into a Game Info View for the user to retrieve more details about the game played.
		GEView.getGameDB().addActionListener(e -> load_gameD());
		//Sets the new game button to call the method to initialize the game menu.
		GEView.getNewGameB().addActionListener(e -> init_GameMenuView());
		
		
		//Closes the game round view, since the game is over.
		GRView.dispose();
		
		//Disposes the game end confirm view.
		if (GECView != null) {
			GECView.dispose();
		}
		
		MMenu.getMusic().stop();
		GEView.playMusic();
		
		//Sets the game end view to be seen so that the user may see everything displayed.
		GEView.setVisible(true);

		
	}

	//The method for loading the game object's data into a game info view once it has ended.
	private void load_gameD() {
		
		// Creates a new GameMenuView object, so it is a new one each time it is initialized.
		GameInfoView GIView = new GameInfoView();
		
		//Sets the game info view's game name and date to the game object's name and date.
		GIView.getNameL().setText(game.getName());
		GIView.getDateL().setText(game.getDate());
		
		//Retrieves the game's data from the database using the game's name and date to match it.
		ResultSet rs = DB.executeQuery("SELECT `Data` FROM `game_match` WHERE `Game_Name` = '"+game.getName()+"' AND `Game_DateTime` = '"+game.getDate()+"'");
		
		//Uses the result set from the database to display the game's data to a text area.
		try {
			 while(rs.next()) {
					GIView.getGameT().setText(rs.getString(1));
				}
			 } catch (Exception e) {
				 
			 }
		
		//Sets the game info view to visible for the user to view the game's info.
		GIView.setVisible(true);
		GIView.setModal(true);
	}

	//The method for controlling each of the individual rounds of the game.
	private void round(String phase) {
		
		//If the method is passed in with an "add", that means the user added a round to the game.
		//A round is added to the game object, incrementing the total rounds to be played by one.
		if (phase.equals("add")) {
			game.addRound();
		//If the method is passed in with a "sub", that means the user subtracted a round to the game.
		//A round is subtracted to the game object, decrementing the total rounds to be played by one.
		} else if (phase.equals("sub")) {
			game.subtractRound();
		//If the method is passed in with a "next", that means the user has moved to the next round.
		} else if (phase.equals("next")) {
			//If the game is a winner per round (points per round == false), it will go through and see if the player's radio button is selected.
			// the winner per round is on the 1 point scale, meaning they get 1 point if they win a round or 0 if they lose a round.
			// If the button is selected, the player will receive 1.0 point for the round and the button will be reset back to being unselected.
			// If the button is not selected, the player will receive 0.0 points for the round.
			// Each of the player's points is stored in each of their separate object variables for each round.
			if (game.getpPerRound() == false) {
				for (String i : game.getPlayers().keySet()) {
					 if (GRView.getpButtons().get(i).isSelected()==true) {
						 game.getPlayers().get(i).setRPoints(1.0, game.getCurrentRound());
						 GRView.getpButtons().get(i).setSelected(false);
					 } else {
						 game.getPlayers().get(i).setRPoints(0.0, game.getCurrentRound());
					 }
				 }	
				
			//If the game is points per round, it will go through and store each of the player's points from the values of their spinners and store them in their object variables.
			// It will then go and reset each of the spinners to 0.0 values.
			} else {
				for (String i : game.getPlayers().keySet()) {
					game.getPlayers().get(i).setRPoints((Double) GRView.getpSpinners().get(i).getValue(), game.getCurrentRound());
					GRView.getpSpinners().get(i).setValue(0.0);
				}
			}
			
			//The game object's end round method is called.
			game.endRound();
			
		}
		
		//The number of rounds left out of the total amount of rounds is displayed in the game round view.
		String roundD = "Round " + game.getCurrentRound() + " of " + game.getRounds();
		// If the determination of the plural or singular of rounds is calculated by the number of rounds. 
		if (game.getRounds() <= 1) {
			roundD = roundD + " round";
		} else {
			roundD = roundD + " rounds";
		}
		GRView.getRoundL().setText(roundD);
		
		//If the current round is greater or equal than the total rounds, then end round button is unable to be selected.
		//So the user will only be able to select the end round button if it is not the last round that the user is on.
		// If it is the last round that the user is on, they are forced to end the game.
		if (game.getCurrentRound() >= game.getRounds()) {
			GRView.getERButton().setEnabled(false);
		} else if (game.getCurrentRound() < game.getRounds())	{
			GRView.getERButton().setEnabled(true);
		}
		
		// If the current round is greater or equal than the total rounds, then the user will not be able to subtract a round.
		// The user is unable to subtract a round that they are currently on or a round that they have already played.
		// To disallow a user from subtracting the round, the subtract button will be unable to be clicked.
		if (game.getCurrentRound() >= game.getRounds()) {
			GRView.getSubB().setEnabled(false);
		} else {
			GRView.getSubB().setEnabled(true);
		}

	}
	
	//The method for to refresh the JList with the players added to play the game in the game menu view.
	private void refreshAddedplist() {

		//Go through the the list model of added players and removes the one matching the selected player.
		//This basically makes it so that when a player selects a name of the list, that players name is removed from it.
		//Removing the player means that the player will no longer be a player in the game.
		for (int i = 0; i < GMView.getAddedlistModel().getSize(); i++) {
			
			if ( GMView.getAddedlistModel().get(i).equals(GMView.getAddedplist().getSelectedValue())){
				 GMView.getAddedlistModel().remove(i);
			}
		}
		//Sets the updated list model to the JList with the players added in game menu view.
		GMView.getAddedplist().setModel(GMView.getAddedlistModel());
	}

	//The method for to refresh the JList with all the players to be added to the game in the game menu view.
	private void refreshPlist() {
		
		
		boolean valid = true;
		for (int i = 0; i < GMView.getAddedlistModel().getSize(); i++) {
			
			if (GMView.getAddedlistModel().get(i).equals(GMView.getPlist().getSelectedValue())){
				valid = false;
			}
		}
		
		if (valid == true) {
			GMView.getAddedlistModel().addElement(GMView.getPlist().getSelectedValue());
			GMView.getAddedplist().setModel(GMView.getAddedlistModel());
		}
		
	}

	//The method used for loading the list of players to the JList in the game menu view.
	private DefaultListModel<String> loadPlist() {
		
		//Creates a list model for the players to be added to and to be returned.
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		//Sends a query to the DB object to retrieve all the players.
		ResultSet rs = DB.executeQuery("SELECT * FROM `player`");
		
		try {
		//Adds all the player's names to the list model.
		while(rs.next()) {
			listModel.addElement(rs.getString(1));
		}
		} catch (Exception e) {
			
		}
		//Returns the list model.
		return listModel;
	}
	
}

