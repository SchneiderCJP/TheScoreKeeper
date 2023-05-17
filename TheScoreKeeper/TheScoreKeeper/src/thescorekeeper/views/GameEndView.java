package thescorekeeper.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameEndView extends JDialog {

	//After the game end, the winner, second place, third place and some stats are displayed in this view.
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Sets the variables to field so they can be accessed from the controller.
	// Getters and some setters are created for all of them.
	private final JPanel contentPanel = new JPanel();
	private JLabel wTitleL; //Title announcing the winner.
	private JLabel winnerL; // Winner(s) name(s).
	private JLabel secondPL; // Second place(s) name(s).
	private JLabel thirdPL; // Third place(s) name(s).
	private JLabel hpTitleL; // Highest Points tile label.
	private JLabel lpTitleL; // Lowest Points tile label.
	private JLabel hpL; // Highest Points.
	private JLabel lpL; // Lowest Points.
	private JLabel totalPL; // Total amount of players.
	private JLabel roundsPL; // Total amount of rounds.
	private JButton MMenuB; // Return to main menu button.
	private JButton gameDB; // Advanced game details button.
	private JButton newGameB; // New game button.
	private Clip music; // Victory music to be played.
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			GameEndView dialog = new GameEndView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GameEndView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameEndView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setTitle("The Score Keeper");
		setModal(true);
		setAutoRequestFocus(false);
		setBounds(100, 100, 601, 395);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		JSeparator separator_1 = new JSeparator();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBorder(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBorder(null);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GameEndView.class.getResource("/thescorekeeper/resources/Trophy.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 286, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane_2)
								.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 112, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		hpTitleL = new JLabel("Highest amount of points earned:");
		hpTitleL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lpTitleL = new JLabel("Lowest amount of points earned:");
		lpTitleL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Total players:");
		lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("Total rounds played:");
		lblNewLabel_5_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBorder(null);
		
		JScrollPane scrollPane_4_1 = new JScrollPane();
		scrollPane_4_1.setBorder(null);
		
		JScrollPane scrollPane_4_2 = new JScrollPane();
		scrollPane_4_2.setBorder(null);
		
		JScrollPane scrollPane_4_3 = new JScrollPane();
		scrollPane_4_3.setBorder(null);
		
		totalPL = new JLabel("New label");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(hpTitleL, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
								.addComponent(lpTitleL, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane_4_3)
								.addComponent(scrollPane_4_2, GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
							.addGap(25))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_5_1_1_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane_4_1, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel_5_1_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(totalPL, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)))
					.addGap(37))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(hpTitleL, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_4_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_4_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(lpTitleL, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5_1_1)
								.addComponent(totalPL, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane_4_1)
								.addComponent(lblNewLabel_5_1_1_1, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		
		roundsPL = new JLabel("New label");
		scrollPane_4_1.setViewportView(roundsPL);
		
		hpL = new JLabel("New label");
		scrollPane_4_3.setViewportView(hpL);
		
		lpL = new JLabel("New label");
		scrollPane_4_2.setViewportView(lpL);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Second Place:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		
		JLabel lblNewLabel_2_1 = new JLabel("Third Place:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBorder(null);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING)
						.addComponent(lblNewLabel_2_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane_5, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		thirdPL = new JLabel("THIRD");
		scrollPane_5.setViewportView(thirdPL);
		thirdPL.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
		thirdPL.setHorizontalAlignment(SwingConstants.CENTER);
		
		secondPL = new JLabel("SECOND");
		scrollPane.setViewportView(secondPL);
		secondPL.setFont(new Font("Trebuchet MS", Font.PLAIN, 19));
		secondPL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setLayout(gl_panel);
		
		winnerL = new JLabel("WINNER");
		winnerL.setFont(new Font("Trebuchet MS", Font.PLAIN, 38));
		winnerL.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_3.setViewportView(winnerL);
		
		wTitleL = new JLabel("The winner of this game is:");
		wTitleL.setFont(new Font("Trebuchet MS", Font.PLAIN, 31));
		wTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setViewportView(wTitleL);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			newGameB = new JButton("Create a new game");
			newGameB.setActionCommand("Cancel");
			buttonPane.add(newGameB);
			{
				gameDB = new JButton("View advanced game details");
				gameDB.setActionCommand("OK");
				buttonPane.add(gameDB);
				getRootPane().setDefaultButton(gameDB);
			}
			{
				MMenuB = new JButton("Return to main menu");
				MMenuB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						music.stop();
						dispose();
					}
				});
				MMenuB.setActionCommand("Cancel");
				buttonPane.add(MMenuB);
			}
		}
	}
	
	//Method for starting the victory music and having it play.
	public void playMusic () {
		
		try {
			File musicLoc = new File("src/thescorekeeper/resources/Victory Music.wav");
			if (musicLoc.exists()) {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicLoc);
				music = AudioSystem.getClip();
				music.open(audioIn);
				music.start();
			}
		
			
		}catch (Exception e) {
			System.out.println(e);
		}
	
	}

	public JLabel getwTitleL() {
		return wTitleL;
	}

	public JLabel getWinnerL() {
		return winnerL;
	}

	public JLabel getSecondPL() {
		return secondPL;
	}

	public JLabel getThirdPL() {
		return thirdPL;
	}
	public Clip getMusic() {
		return music;
	}

	public JLabel getHpTitleL() {
		return hpTitleL;
	}

	public JLabel getLpTitleL() {
		return lpTitleL;
	}

	public JLabel getHpL() {
		return hpL;
	}

	public JLabel getLpL() {
		return lpL;
	}

	public JLabel getTotalPL() {
		return totalPL;
	}

	public JLabel getRoundsPL() {
		return roundsPL;
	}

	public JButton getMMenuB() {
		return MMenuB;
	}

	public JButton getGameDB() {
		return gameDB;
	}

	public JButton getNewGameB() {
		return newGameB;
	}
}
