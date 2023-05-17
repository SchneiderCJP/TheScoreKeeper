package thescorekeeper.views;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Toolkit;

public class AboutView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea txtrCreatorSchneiderCampfort; // Text area containing all the about details.

	//About View is created displaying the name of the creator and the date it was launched.
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutView dialog = new AboutView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void launch() {
		try {
			AboutView dialog = new AboutView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutView() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setModal(true);
		setAutoRequestFocus(false);
		setTitle("The Score Keeper");
		setBounds(100, 100, 434, 128);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			txtrCreatorSchneiderCampfort = new JTextArea();
			txtrCreatorSchneiderCampfort.setEditable(false);
			txtrCreatorSchneiderCampfort.setFont(new Font("Verdana", Font.PLAIN, 14));
			txtrCreatorSchneiderCampfort.setBackground(SystemColor.menu);
			txtrCreatorSchneiderCampfort.setText("Created by Schneider Campfort Jean-Pierre\r\nLaunched 04/24/2023\r\n");
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrCreatorSchneiderCampfort, GroupLayout.PREFERRED_SIZE, 346, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrCreatorSchneiderCampfort, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
