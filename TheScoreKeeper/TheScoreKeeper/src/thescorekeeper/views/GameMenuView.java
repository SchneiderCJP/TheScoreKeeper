package thescorekeeper.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Toolkit;

public class GameMenuView extends JDialog {

	//Creates a game menu where the user can visually input and make selections to create the game they would like to track.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//Sets the variables to field so they can be accessed from the controller.
	// Getters and some setters are created for all of them.
	private JTextField gName; // Label displaying the game's name.
	private JSpinner rounds; // Spinner determining the number of rounds.
	private JRadioButton WinnerPR; //Radio button, if selected means game is winner per round.
	private JRadioButton PointsPR; //Radio button, if selected means game is points per round.
	private final ButtonGroup buttonGroup = new ButtonGroup(); // Button group for the WinnerPR and PointsPR, grouping them together so only one can be selected at a time.
	private JSpinner pPerRound; //Determines the amount of points given out per round.
	private JList<String> plist; // List of all players registered in the application.
	private JList<String> addedplist; // List of players added to the game to play.
	private JButton createB; // Create the game button
	private DefaultListModel<String> addedlistModel = new DefaultListModel<String>(); // List model for the players added to the game.


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			GameMenuView dialog = new GameMenuView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	


	/**
	 * Create the dialog.
	 */
	public GameMenuView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameMenuView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setAutoRequestFocus(false);
				
		
		
		
		
	
		setTitle("The Score Keeper");
		setResizable(false);
		setBounds(100, 100, 520, 587);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Create a new game");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		setLocationRelativeTo(null);
		JSeparator separator = new JSeparator();
		JLabel lblNewLabel_1 = new JLabel("Game Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		gName = new JTextField();
		gName.setColumns(10);
		
		WinnerPR = new JRadioButton("Winner per round");
		
		WinnerPR.setSelected(true);
		buttonGroup.add(WinnerPR);
		WinnerPR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		PointsPR = new JRadioButton("Points per round");
		

		buttonGroup.add(PointsPR);
		PointsPR.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1_1 = new JLabel("Points per round:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		pPerRound = new JSpinner();
		pPerRound.setEnabled(false);
		pPerRound.setModel(new SpinnerNumberModel(1.0, 1.0, null, 1.0));
		
		WinnerPR.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
					if (WinnerPR.isSelected()) {
						pPerRound.setEnabled(false);
						pPerRound.setValue(1.0);
					} else {
						pPerRound.setEnabled(true);
					}
			}	
		});
		
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblNewLabel_2 = new JLabel("Players to be added to the game:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel_2_1 = new JLabel("Players playing:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Number of rounds:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		rounds = new JSpinner();
		rounds.setModel(new SpinnerNumberModel(1, 1, null,1));
		
		JLabel lblNewLabel_2_2 = new JLabel("Select on a player to add them to the game");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Select on a player to remove them to the game");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("Create a new game");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(gName, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
							.addComponent(WinnerPR, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(PointsPR, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addGap(63))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1)
							.addGap(6)
							.addComponent(rounds, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(291))
						.addGroup(Alignment.LEADING, gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblNewLabel_2_2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
									.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
									.addComponent(scrollPane_1, 0, 0, Short.MAX_VALUE))))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(pPerRound, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(gName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(rounds, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(WinnerPR)
						.addComponent(PointsPR, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(pPerRound, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_1)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_2)
						.addComponent(lblNewLabel_2_2_1))
					.addContainerGap())
		);
		
		
		
		addedplist = new JList<String>();
		scrollPane_1.setViewportView(addedplist);
	
		
		scrollPane.setViewportView(plist);
		
		plist = new JList<String>();
		plist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(plist);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				createB = new JButton("Create Game");
				
				createB.setActionCommand("OK");
				buttonPane.add(createB);
				getRootPane().setDefaultButton(createB);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	public JTextField getgName() {
		return gName;
	}


	public void setgName(JTextField gName) {
		this.gName = gName;
	}


	public JSpinner getRounds() {
		return rounds;
	}


	public void setRounds(JSpinner rounds) {
		this.rounds = rounds;
	}


	public JRadioButton getWinnerPR() {
		return WinnerPR;
	}


	public void setWinnerPR(JRadioButton winnerPR) {
		WinnerPR = winnerPR;
	}


	public JSpinner getpPerRound() {
		return pPerRound;
	}


	public void setpPerRound(JSpinner pPerRound) {
		this.pPerRound = pPerRound;
	}


	public JList<String> getPlist() {
		return plist;
	}


	public void setPlist(JList<String> plist) {
		this.plist = plist;
	}


	public JList<String> getAddedplist() {
		return addedplist;
	}


	public void setAddedplist(JList<String> addedplist) {
		this.addedplist = addedplist;
	}

	public JButton getCreateB() {
		return createB;
	}


	public void setCreateB(JButton createB) {
		this.createB = createB;
	}


	public DefaultListModel<String> getAddedlistModel() {
		return addedlistModel;
	}


	public JRadioButton getPointsPR() {
		return PointsPR;
	}





	public void setPointsPR(JRadioButton pointsPR) {
		PointsPR = pointsPR;
	}





	public void setAddedlistModel(DefaultListModel<String> addedlistModel) {
		this.addedlistModel = addedlistModel;
	}
}
