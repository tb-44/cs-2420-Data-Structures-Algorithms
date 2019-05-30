/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class BankGui extends JFrame {

	private static final long serialVersionUID = 1780230051070163673L;
	private JPanel contentPane;
	private static JTextField textFieldUserName;
	private static JTextField textFieldPassword;
	@SuppressWarnings("unused")
	private AccountSummaryGui myAccountSummaryGui;
	@SuppressWarnings("unused")
	private CheckingDetailsGui myCheckingDetailsGui;
	@SuppressWarnings("unused")
	private SavingsDetailsGui mySavingsDetailsGui;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankGui frame = new BankGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BankGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BankGui.class.getResource("IconSmall.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(770, 440));
		// this.setIconImage(image);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(98, 102, 199));
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BankGui.class.getResource("BanSmallt.jpg")));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(98, 102, 199));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblUsername = new JLabel("User Name");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblUsername);

		textFieldUserName = new JTextField();
		textFieldUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldUserName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(textFieldUserName);
		textFieldUserName.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblPassword);

		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(textFieldPassword);
		textFieldPassword.setColumns(10);

		JButton btnLogIn = new JButton("Log in");
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.setBackground(new Color(52, 56, 156));
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldUserName.getText() != null && !textFieldUserName.getText().trim().isEmpty()
						&& textFieldPassword.getText() != null && !textFieldPassword.getText().trim().isEmpty()) {
					try {
						DB.logon(textFieldUserName.getText(), textFieldPassword.getText());
						myCheckingDetailsGui = new CheckingDetailsGui();
						mySavingsDetailsGui = new SavingsDetailsGui();
						AccountSummaryGui myAccountSummaryGui = new AccountSummaryGui();
						myAccountSummaryGui.setVisible(true);
						setVisible(false);

					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JLabel("Invalid login credentials"),
								"Invalid login credentials");
					}
				} else {
					JOptionPane.showMessageDialog(new JTextField("Enter loginname and password"),
							"Enter loginname and password");
				}
			}
		});
		panel_1.add(btnLogIn);

		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.setBackground(new Color(52, 56, 156));
		btnCreateNewUser.setForeground(Color.WHITE);
		btnCreateNewUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textFieldUserName.getText() != null && !textFieldUserName.getText().trim().isEmpty()
						&& textFieldPassword.getText() != null && !textFieldPassword.getText().trim().isEmpty()) {
					boolean userCreationStatus = false;
					try {
						DB.createUserAccount(textFieldUserName.getText(), textFieldUserName.getText(),
								textFieldPassword.getText());
						userCreationStatus = true;
					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JTextField("User already exists"), "User already exist");
					}
					if (userCreationStatus) {
						try {
							DB.createAccount(textFieldUserName.getText(), AccountTypes.SAVINGS);
							DB.createAccount(textFieldUserName.getText(), AccountTypes.CHECKING);
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(
									new JTextField("Something went wrong while creating accounts"),
									"Something went wrong while creating accounts");
						}
						JOptionPane
								.showMessageDialog(new JLabel("Please login to proceed"),
										"Successfully created user along with checking and savings accounts.Please login to proceed");
					}
				} else {
					JOptionPane.showMessageDialog(new JTextField("Enter loginname and password"),
							"Enter loginname and password");
				}
			}
		});
		panel_1.add(btnCreateNewUser);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(98, 102, 199));
		contentPane.add(panel_2, BorderLayout.CENTER);

		JLabel lblImagemsb = new JLabel("");
		lblImagemsb.setIcon(new ImageIcon(BankGui.class.getResource("msb.jpg")));
		panel_2.add(lblImagemsb);
	}

	public static String gettextFieldUserName() {
		return textFieldUserName.getText();
	}

	public static String gettextFieldPassword() {
		return textFieldPassword.getText();
	}
}
