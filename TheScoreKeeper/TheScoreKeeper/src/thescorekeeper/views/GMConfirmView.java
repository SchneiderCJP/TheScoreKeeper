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
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GMConfirmView extends JDialog {

	//A confirmation window that pops up prompting the user to confirm that they want to proceed with ending the game.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//Sets the variable to field so it can be accessed from the controller.
	//Getter was created for it.
	private JButton startGB; //start game button

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GMConfirmView dialog = new GMConfirmView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JButton getStartGB() {
		return startGB;
	}

	/**
	 * Create the dialog.
	 */
	public GMConfirmView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GMConfirmView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setTitle("The Score Keeper");
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationByPlatform(true);
		setLocationRelativeTo(null);
		setModal(true);
		setBounds(100, 100, 336, 153);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("Are you sure you want to start this game?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				startGB = new JButton("Start Game");
				startGB.setActionCommand("OK");
				buttonPane.add(startGB);
				getRootPane().setDefaultButton(startGB);
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
}
