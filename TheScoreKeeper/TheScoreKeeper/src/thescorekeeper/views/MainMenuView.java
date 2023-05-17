package thescorekeeper.views;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class MainMenuView extends JFrame {

	
	// A main menu allowing the user to select where in the application they would like to access.
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneMain;
	//Sets the variables to field so they can be accessed from the controller.
	// Getters and some setters are created for all of them.
	private JButton pastGamesB; // View past games button.
	private JButton newPlayerB; // Add a new player button.
	private JButton gameStartB; // Create a new game button.
	private JButton aboutB; // About button.
	private JButton playersB; // View all players button.
	private JButton musicB; // Toggle music button.
	private boolean musicplaying = true; // boolean keeping track if music is playing.
	private Clip music; // The background music.
	

	

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public MainMenuView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuView.class.getResource("/thescorekeeper/resources/Icon.png")));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
		}
		initComponents();
		
		
		
	}
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++
	// Method for initializing and creating components.
	// ++++++++++++++++++++++++++++++++++++++++++++++++++

	private void initComponents() {
		
		
		setTitle("The Score Keeper");
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 562);
		contentPaneMain = new JPanel();
		contentPaneMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMain);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME TO:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 11));
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		
		ImageIcon logo = new ImageIcon(MainMenuView.class.getResource("/thescorekeeper/resources/logo.png"));
		logo.setImage(logo.getImage().getScaledInstance(460, 115,Image.SCALE_SMOOTH));
		JLabel picLabel = new JLabel(logo);
		
		JPanel panel = new JPanel();
		// lblNewLabel_4.setIcon(new ImageIcon(MainMenuView.class.getResource("/thescorekeeper/resources/logo.png")));
		GroupLayout gl_contentPaneMain = new GroupLayout(contentPaneMain);
		gl_contentPaneMain.setHorizontalGroup(
			gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPaneMain.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
						.addComponent(picLabel, GroupLayout.PREFERRED_SIZE, 501, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		gl_contentPaneMain.setVerticalGroup(
			gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPaneMain.createSequentialGroup()
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPaneMain.createSequentialGroup()
							.addGap(77)
							.addComponent(lblNewLabel_3))
						.addGroup(gl_contentPaneMain.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(picLabel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPaneMain.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPaneMain.createSequentialGroup()
							.addGap(23)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPaneMain.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(92, Short.MAX_VALUE))
		);
		
		
		this.gameStartB = new JButton("Start a new game");
		
		gameStartB.setHorizontalTextPosition(SwingConstants.CENTER);
		
		this.newPlayerB = new JButton("Add a new player");
		newPlayerB.setHorizontalTextPosition(SwingConstants.CENTER);
		
		this.pastGamesB = new JButton("View Past Games");
		
		pastGamesB.setHorizontalTextPosition(SwingConstants.CENTER);
		
		this.playersB = new JButton("View Players");
		
		playersB.setHorizontalTextPosition(SwingConstants.CENTER);
		
		this.aboutB = new JButton("About");
		aboutB.setHorizontalTextPosition(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2 = new JLabel("Select an option:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator_2 = new JSeparator();
		
		JLabel lblNewLabel = new JLabel("MAIN MENU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		
		JSeparator separator = new JSeparator();
		separator.setSize(new Dimension(4, 4));
		
		musicB = new JButton("");
		musicB.setIcon(new ImageIcon(MainMenuView.class.getResource("/thescorekeeper/resources/muted.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
				.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(178)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(gameStartB, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(aboutB, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
								.addComponent(musicB, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
							.addComponent(playersB, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addComponent(pastGamesB, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
							.addComponent(newPlayerB, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addComponent(gameStartB, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(newPlayerB, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pastGamesB, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(playersB, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(musicB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(aboutB, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
					.addGap(31))
		);
		panel.setLayout(gl_panel);
		contentPaneMain.setLayout(gl_contentPaneMain);
		
	}
	
	// ++++++++++++++++++++++++++++++++++++++++++++++++++
	// Method for creating all events.
	// ++++++++++++++++++++++++++++++++++++++++++++++++++
	
	// Method for playing the background music, looping it in the background of the game as players play. (they have the option to mute it)>
	public void playMusic () {
		
		try {
			File musicLoc = new File("src/thescorekeeper/resources/Background Music.wav");
			if (musicLoc.exists()) {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicLoc);
				music = AudioSystem.getClip();
				music.open(audioIn);
				music.start();
				music.setLoopPoints(0, -1);
			}
		
			
		}catch (Exception e) {
			System.out.println(e);
		}
	
	}
	public JPanel getContentPaneMain() {
		return contentPaneMain;
	}

	public void setContentPaneMain(JPanel contentPaneMain) {
		this.contentPaneMain = contentPaneMain;
	}

	public JButton getPastGamesB() {
		return pastGamesB;
	}

	public void setPastGamesB(JButton pastGamesB) {
		this.pastGamesB = pastGamesB;
	}

	public JButton getNewPlayerB() {
		return newPlayerB;
	}

	public void setNewPlayerB(JButton newPlayerB) {
		this.newPlayerB = newPlayerB;
	}

	public JButton getGameStartB() {
		return gameStartB;
	}

	public Clip getMusic() {
		return music;
	}

	public JButton getMusicB() {
		return musicB;
	}

	public boolean getMusicplaying() {
		return musicplaying;
	}

	public void setMusicplaying(boolean musicplaying) {
		this.musicplaying = musicplaying;
	}

	public void setGameStartB(JButton gameStartB) {
		this.gameStartB = gameStartB;
	}

	public JButton getAboutB() {
		return aboutB;
	}

	public void setAboutB(JButton aboutB) {
		this.aboutB = aboutB;
	}

	public JButton getPlayersB() {
		return playersB;
	}

	public void setPlayersB(JButton playersB) {
		this.playersB = playersB;
	}
}
