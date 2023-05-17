package thescorekeeper.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Rectangle;

public class PlayersView extends JDialog {

	//Allows the user to see all the players in the system and select which of them they would like to view more info about.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();

	//Sets the variables to field so they can be accessed from the controller.
    // Getters and some setters are created for all of them.
	private JList<String> gList = new JList<String>(); //List of players that have been created in the application.
	private JButton closeB; // The cancel button.

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PlayersView dialog = new PlayersView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void launch() {
		try {
			PlayersView dialog = new PlayersView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PlayersView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PlayersView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setTitle("The Score Keeper");
		setModal(true);
		setAutoRequestFocus(false);
		
		setBounds(100, 100, 372, 518);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("Players");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JSeparator separator = new JSeparator();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(new Rectangle(5, 5, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Click on a player's name to view more information.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1)
					.addGap(10)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gList.setBackground(SystemColor.control);
		
		gList.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		scrollPane.setViewportView(gList);
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
	
	public JList<String> getgList() {
		return gList;
	}

	public void setgList(JList<String> gList) {
		this.gList = gList;
	}
	
	public void setgListModel(DefaultListModel<String> model) {
		gList.setModel(model);
	}

	public JButton getCloseB() {
		return closeB;
	}

	public void setCloseB(JButton closeB) {
		this.closeB = closeB;
	}
	public JPanel getContentPanel() {
		return contentPanel;
	}

}