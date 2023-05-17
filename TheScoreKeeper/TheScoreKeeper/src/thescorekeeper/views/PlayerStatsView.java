package thescorekeeper.views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class PlayerStatsView extends JDialog {
	
	//Allows the user to see all the games that the player has stats for and select which one they would like to see more details for.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Sets the variables to field so they can be accessed from the controller.
    // Getters and some setters are created for all of them.
	private JPanel buttonPane; // Panel holding the cancel button.
	private JList<String> statsL; // List holding all the names of the games that the player has stats for.
	private JLabel DateL; // Displays the date of when the player was registered.
	private JLabel NameL; // Displays the player's name.

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public PlayerStatsView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlayerStatsView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setAutoRequestFocus(false);
		setModal(true);
		setTitle("The Score Keeper");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 471);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
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
		setLocationRelativeTo(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		JLabel lblNewLabel_1 = new JLabel("Member since: ");
		
		DateL = new JLabel("date");
		
		JSeparator separator = new JSeparator();
		
		JLabel lblNewLabel_1_1 = new JLabel("Select a game you'd like stats on for this player.");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator_1 = new JSeparator();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(DateL, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
					.addGap(10))
				.addComponent(buttonPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(DateL))
					.addGap(7)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		statsL = new JList<String>();
		statsL.setBackground(SystemColor.control);
		scrollPane_2.setViewportView(statsL);
		
		NameL = new JLabel("Name");
		NameL.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		NameL.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(NameL);
		getContentPane().setLayout(groupLayout);
	}

	public JList<String> getStatsL() {
		return statsL;
	}

	public void setStatsL(JList<String> statsL) {
		this.statsL = statsL;
	}
	
	public void setStatsLModel(DefaultListModel<String> model) {
		statsL.setModel(model);
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
}
