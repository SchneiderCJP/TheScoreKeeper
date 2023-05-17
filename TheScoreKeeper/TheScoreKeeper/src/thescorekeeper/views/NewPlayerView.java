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
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class NewPlayerView extends JDialog {

	// Allows the user to create a new player profile using a user friendly window.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//Sets the variables to field so they can be accessed from the controller.
	// Getters and some setters are created for all of them.
	private JTextField pName; // The user's input area of the player's name.
	private JLabel pMessage; // The display message .
	private JButton createB; // Create profile button.
	private JButton cancelB; // Cancel button.
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewPlayerView dialog = new NewPlayerView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	/**
	 * Create the dialog.
	 */
	public NewPlayerView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(NewPlayerView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setAutoRequestFocus(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});
		setTitle("The Score Keeper");
		setBounds(100, 100, 504, 206);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("New Player Profile");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JSeparator separator = new JSeparator();
		JLabel lblNewLabel_1 = new JLabel("Player's Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		pName = new JTextField();
		pName.setColumns(10);
		
		pMessage = new JLabel("");
		pMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(pMessage, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
								.addComponent(pName, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(pName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(pMessage)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				createB = new JButton("Create");				
				createB.setActionCommand("OK");
				buttonPane.add(createB);
				getRootPane().setDefaultButton(createB);
			}
			{
				JButton cancelB = new JButton("Cancel");
				cancelB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
				cancelB.setActionCommand("Cancel");
				buttonPane.add(cancelB);
			}
		}
	}
	
	public JTextField getpName() {
		return pName;
	}



	public void setpName(JTextField pName) {
		this.pName = pName;
	}



	public JLabel getpMessage() {
		return pMessage;
	}



	public void setpMessage(JLabel pMessage) {
		this.pMessage = pMessage;
	}



	public JButton getCreateB() {
		return createB;
	}



	public void setCreateB(JButton createB) {
		this.createB = createB;
	}



	public JButton getCancelB() {
		return cancelB;
	}



	public void setCancelB(JButton cancelB) {
		this.cancelB = cancelB;
	}


}
