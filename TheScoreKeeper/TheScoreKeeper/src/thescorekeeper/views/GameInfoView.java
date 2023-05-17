package thescorekeeper.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.Insets;

public class GameInfoView extends JDialog {

	/**
	 * 
	 */
	
	//Lists the information for a game, displayed for the user to review.
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//Sets the variables to field so they can be accessed from the controller.
	// Getters and some setters are created for all of them.
	private JTextArea gameT; // Display's the game data in a text area.
	private JLabel DateL; // Display's the date & time the game was played.
	private JLabel nameL; // Display's the game's name.

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
			GameInfoView dialog = new GameInfoView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GameInfoView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameInfoView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setTitle("The Score Keeper");
		setAutoRequestFocus(false);
		setModal(true);
		setBounds(100, 100, 549, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane();
		setLocationRelativeTo(null);
		scrollPane.setBorder(null);
		JLabel lblNewLabel_1 = new JLabel("Date & time played:");
		DateL = new JLabel("Date");
		JSeparator separator = new JSeparator();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(DateL, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(DateL))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
		);
		
		gameT = new JTextArea();
		gameT.setMargin(new Insets(5, 5, 5, 5));
		gameT.setText("Won the first round\r\nWon the second round\r\nLost the third round\r\nWon the first round\r\nWon the second round\r\nLost the third round\r\nWon the first round\r\nWon the second round\r\nLost the third round\r\nWon the first round\r\nWon the second round\r\nLost the third round\r\nLost the third round\r\nWon the first round\r\nWon the second round\r\nLost the third round\r\nWon the first round\r\nWon the second round\r\nLost the third round");
		gameT.setFont(new Font("Dialog", Font.PLAIN, 12));
		gameT.setBackground(SystemColor.control);
		gameT.setEditable(false);
		scrollPane_1.setViewportView(gameT);
		{
			nameL = new JLabel("Name");
			nameL.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
			nameL.setHorizontalAlignment(SwingConstants.CENTER);
			scrollPane.setViewportView(nameL);
		}
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Close");
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

	public JTextArea getGameT() {
		return gameT;
	}

	public void setGameT(JTextArea gameT) {
		this.gameT = gameT;
	}

	public JLabel getDateL() {
		return DateL;
	}

	public void setDateL(JLabel dateL) {
		DateL = dateL;
	}

	public JLabel getNameL() {
		return nameL;
	}

	public void setNameL(JLabel nameL) {
		this.nameL = nameL;
	}

}
