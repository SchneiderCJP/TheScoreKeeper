package thescorekeeper.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;

public class StatsSelectionView extends JDialog {

	//The user is able to see a detailed log of the player's stats for a specific game.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//Sets the variables to field so they can be accessed from the controller.
	// Getters and some setters are created for all of them.
	private JLabel GameNameL; // Display's the name of the selected game.
	private JLabel DateL; // Display's the date/time the game took place.
	private JLabel NameL; // Display's user's name.
	private JTextArea gameT; // Text area displaying the player's data from the game.
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatsSelectionView dialog = new StatsSelectionView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public StatsSelectionView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatsSelectionView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setTitle("The Score Keeper");
		setModal(true);
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 508);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		JLabel lblNewLabel_1 = new JLabel("Date & time played:");
		DateL = new JLabel("Date");
		GameNameL = new JLabel("Name");
		JLabel lblNewLabel_1_2 = new JLabel("Game Name:");
		JSeparator separator = new JSeparator();
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(DateL, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(GameNameL, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(DateL)
						.addComponent(lblNewLabel_1_2)
						.addComponent(GameNameL))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
		);
		
		gameT = new JTextArea();
		gameT.setEditable(false);
		gameT.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		gameT.setMargin(new Insets(5, 5, 5, 5));
		gameT.setFont(new Font("Dialog", Font.PLAIN, 12));
		gameT.setBackground(SystemColor.control);
		scrollPane_1.setViewportView(gameT);
		{
			NameL = new JLabel("Name");
			NameL.setHorizontalAlignment(SwingConstants.CENTER);
			NameL.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
			scrollPane.setViewportView(NameL);
		}
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton closeB = new JButton("Close");
				closeB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				closeB.setActionCommand("Cancel");
				buttonPane.add(closeB);
			}
		}
	}


	public JLabel getGameNameL() {
		return GameNameL;
	}

	public void setGameNameL(JLabel gameNameL) {
		GameNameL = gameNameL;
	}

	public JLabel getDateL() {
		return DateL;
	}

	public void setDateL(JLabel dateL) {
		DateL = dateL;
	}

	public JLabel getNameL() {
		return NameL;
	}

	public void setNameL(JLabel nameL) {
		NameL = nameL;
	}

	public JTextArea getGameT() {
		return gameT;
	}

	public void setGameT(JTextArea gameT) {
		this.gameT = gameT;
	}

}
