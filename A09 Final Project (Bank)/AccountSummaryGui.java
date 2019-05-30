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
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

@SuppressWarnings("unused")
public class AccountSummaryGui extends JFrame {
	
	private static final long serialVersionUID = -4697443021177884714L;
	private TransferGUI tfrGUI;
	private JPanel contentPane;
	private CheckingDetailsGui myCheckingDetailsGui;
	private SavingsDetailsGui mySavingsDetailsGui;
	private JLabel lblCheckingNumber = new JLabel("CheckingNumber");
	private JLabel lblCheckingBalance = new JLabel("CheckingBalance");
	private JLabel lblSavingsBalance = new JLabel("SavingBalance");
	private JLabel lblSavingsNumber = new JLabel("SavingNumber");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountSummaryGui frame = new AccountSummaryGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public AccountSummaryGui() throws Exception {
		setBackground(new Color(255, 255, 255));
		setSize(new Dimension(770, 440));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setOpaque(true);
		tabbedPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel accountSummaryTabPanel = new JPanel();
		accountSummaryTabPanel.setBackground(new Color(98, 102, 199));
		tabbedPane.addTab("Account Summary", null, accountSummaryTabPanel, null);

		JPanel transactionTabPanel = new JPanel();
		transactionTabPanel.setLayout(new BorderLayout());
		transactionTabPanel.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("Transaction", null, transactionTabPanel, null);
		tabbedPane.setEnabledAt(1, true);

		tfrGUI = new TransferGUI(this, BankGui.gettextFieldUserName());
		transactionTabPanel.add(tfrGUI);
		accountSummaryTabPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(98, 102, 199));
		accountSummaryTabPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 4, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(98, 102, 199));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblAccountType = new JLabel("Account Type");
		lblAccountType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccountType.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblAccountType);
		lblAccountType.setForeground(new Color(255, 255, 255));

		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccountNumber.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblAccountNumber);
		lblAccountNumber.setForeground(new Color(255, 255, 255));

		JLabel lblAccountBalance = new JLabel("Account Balance");
		lblAccountBalance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccountBalance.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblAccountBalance);
		lblAccountBalance.setForeground(new Color(255, 255, 255));

		JLabel lblEmpty = new JLabel("");
		lblEmpty.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblEmpty);
		lblEmpty.setForeground(new Color(255, 255, 255));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(98, 102, 199));
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblSaving = new JLabel("Saving");
		lblSaving.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSaving.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblSaving);
		lblSaving.setForeground(new Color(255, 255, 255));
		lblSavingsNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSavingsNumber.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblSavingsNumber);
		lblSavingsNumber.setForeground(new Color(255, 255, 255));
		lblSavingsBalance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSavingsBalance.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblSavingsBalance);
		lblSavingsBalance.setForeground(new Color(255, 255, 255));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(98, 102, 199));
		panel_3.add(panel_5);
		panel_5.setLayout(null);

		JButton btnSavingsDetails = new JButton("Savings Details");
		btnSavingsDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSavingsDetails.setBounds(10, 26, 150, 42);
		panel_5.add(btnSavingsDetails);
		btnSavingsDetails.setBackground(new Color(52, 56, 156));
		btnSavingsDetails.setForeground(new Color(255, 255, 255));
		btnSavingsDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mySavingsDetailsGui = new SavingsDetailsGui();
				setVisible(false);
				mySavingsDetailsGui.setVisible(true);
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(98, 102, 199));
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblChecking = new JLabel("Checking");
		lblChecking.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChecking.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblChecking);
		lblChecking.setForeground(new Color(255, 255, 255));
		lblCheckingNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCheckingNumber.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblCheckingNumber);
		lblCheckingNumber.setForeground(new Color(255, 255, 255));
		lblCheckingBalance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCheckingBalance.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblCheckingBalance);
		lblCheckingBalance.setForeground(new Color(255, 255, 255));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(98, 102, 199));
		panel_4.add(panel_6);
		panel_6.setLayout(null);

		JButton btnCheckingDetails = new JButton("Checking Details");
		btnCheckingDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCheckingDetails.setBounds(10, 11, 150, 42);
		panel_6.add(btnCheckingDetails);
		btnCheckingDetails.setBackground(new Color(52, 56, 156));
		btnCheckingDetails.setForeground(new Color(255, 255, 255));
		btnCheckingDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				myCheckingDetailsGui = new CheckingDetailsGui();
				setVisible(false);
				myCheckingDetailsGui.setVisible(true);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(98, 102, 199));
		accountSummaryTabPanel.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AccountSummaryGui.class.getResource("BanSmallt.jpg")));
		panel.add(lblNewLabel);

		updateSummary();
	}

	public void updateSummary() {
		try {

			List<Account> accounts = DB.getAccountSummary(BankGui.gettextFieldUserName());
			CheckingAccount checkingAccount = null;
			SavingsAccount savingsAccount = null;
			for (Account account : accounts) {
				if (account.getAccountType() == AccountTypes.CHECKING) {
					checkingAccount = (CheckingAccount) account;
				}
				if (account.getAccountType() == AccountTypes.SAVINGS) {
					savingsAccount = (SavingsAccount) account;
				}
			}
			lblCheckingNumber.setText(String.valueOf(checkingAccount.getAccountNumber()));
			lblCheckingBalance.setText(String.valueOf(checkingAccount.getAccountBalance()));

			lblSavingsBalance.setText(String.valueOf(savingsAccount.getAccountBalance()));
			lblSavingsNumber.setText(String.valueOf(savingsAccount.getAccountNumber()));

		} catch (Exception e) {
		}
	}

	public TransferGUI getTfrGUI() {
		return tfrGUI;
	}
}