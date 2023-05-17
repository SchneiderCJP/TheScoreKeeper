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
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import java.awt.Toolkit;

public class GameRoundView extends JDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	//Sets the variables to field so they can be accessed from the controller.
    // Getters and some setters are created for all of them.
	HashMap<String, JRadioButton> pButtons = new HashMap<String, JRadioButton>(); //Hashmap for the radio buttons to select which player won the round.
	HashMap<String, JLabel> pLabels = new HashMap<String, JLabel>(); // Hashmap for the labels to describe which radio button or spinner belongs to which player.
	HashMap<String, JSpinner> pSpinners = new HashMap<String, JSpinner>(); // Hashmap for the spinners to add points to each player each round.
	HashMap<String, JPanel> pPanels = new HashMap<String, JPanel>(); // Hashmap for the panels holding each players label and spinner or radio button.
	private JButton addB; //The add rounds button.
	private JButton subB; // The subtrack rounds button.
	private JPanel gamePanel; // the game panel where all the labels and spinners or radio buttons will be placed on.
	private JButton ERButton; // End round button.
	private JButton EGButton; // End game button.
	private JLabel directL; // The directions.
	private JLabel RoundL; // The label displaying what round the user is on out of how many total rounds there are.
	private JLabel NameL; // The name of the game.


	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			GameRoundView dialog = new GameRoundView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public GameRoundView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameRoundView.class.getResource("/thescorekeeper/resources/Icon.png")));
		setTitle("The Score Keeper");
		setAutoRequestFocus(false);
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
			@Override
			public void windowClosing(WindowEvent e) {
	
			}
		});
		setBounds(100, 100, 488, 462);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		NameL = new JLabel("Game Name");
		NameL.setFont(new Font("Trebuchet MS", Font.BOLD, 22));
		NameL.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator = new JSeparator();
		
		RoundL = new JLabel("Round");
		RoundL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		RoundL.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JSeparator separator_1 = new JSeparator();
		
		directL = new JLabel("Select the winner(s) of the round.");
		directL.setHorizontalAlignment(SwingConstants.CENTER);
		
		gamePanel = new JPanel();
		gamePanel.setBorder(null);
		
		addB = new JButton("Add a round");
		
		addB.setActionCommand("OK");
		
		subB = new JButton("Subtract a round");
	
		subB.setActionCommand("OK");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
						.addComponent(NameL, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(RoundL, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(addB, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
									.addGap(27))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(subB, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
									.addGap(18))))
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
						.addComponent(directL, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(NameL)
					.addGap(11)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(addB)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(subB))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(RoundL)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(directL)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addGap(50))
		);
		 
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				ERButton = new JButton("End Round");
				
				ERButton.setActionCommand("OK");
				buttonPane.add(ERButton);
				getRootPane().setDefaultButton(ERButton);
			}
			{
				EGButton = new JButton("End Game");
				EGButton.setActionCommand("Cancel");
				buttonPane.add(EGButton);
			}
		}
	}


	public JLabel getRoundL() {
		return RoundL;
	}


	public void setRoundL(JLabel roundL) {
		RoundL = roundL;
	}


	public JLabel getNameL() {
		return NameL;
	}


	public void setNameL(JLabel nameL) {
		NameL = nameL;
	}


	public HashMap<String, JRadioButton> getpButtons() {
		return pButtons;
	}


	public void setpButtons(HashMap<String, JRadioButton> pButtons) {
		this.pButtons = pButtons;
	}


	public HashMap<String, JLabel> getpLabels() {
		return pLabels;
	}


	public void setpLabels(HashMap<String, JLabel> pLabels) {
		this.pLabels = pLabels;
	}


	public HashMap<String, JSpinner> getpSpinners() {
		return pSpinners;
	}


	public void setpSpinners(HashMap<String, JSpinner> pSpinners) {
		this.pSpinners = pSpinners;
	}


	public HashMap<String, JPanel> getpPanels() {
		return pPanels;
	}


	public void setpPanels(HashMap<String, JPanel> pPanels) {
		this.pPanels = pPanels;
	}


	public JButton getAddB() {
		return addB;
	}


	public void setAddB(JButton addB) {
		this.addB = addB;
	}


	public JButton getSubB() {
		return subB;
	}


	public void setSubB(JButton subB) {
		this.subB = subB;
	}


	public JPanel getGamePanel() {
		return gamePanel;
	}


	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}


	public JButton getERButton() {
		return ERButton;
	}


	public void setERButton(JButton eRButton) {
		ERButton = eRButton;
	}


	public JButton getEGButton() {
		return EGButton;
	}


	public void setEGButton(JButton eGButton) {
		EGButton = eGButton;
	}
	
	public JLabel getDirectL() {
		return directL;
	}


	public void setDirectL(JLabel directL) {
		this.directL = directL;
	}
}
