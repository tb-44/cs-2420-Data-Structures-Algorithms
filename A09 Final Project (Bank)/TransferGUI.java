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
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class TransferGUI extends JPanel {
	
	private static final long serialVersionUID = -2526656253576955491L;
	private JTextField intTfrAmt;
	private String loginname = "madana";
	private List<Account> accounts = null;
	private JTextField cashTxnAmtField;
	@SuppressWarnings("unused")
	private AccountSummaryGui accountSummaryGui;

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TransferGUI(AccountSummaryGui accountSummaryGui, String loginName) throws Exception {
		this.loginname = loginName;
		this.accountSummaryGui = accountSummaryGui;
		accounts = DB.getAccountSummary(loginname);
		List<String> accountNames = new ArrayList<String>();
		for (Account account : accounts) {
			String accountName = account.getAccountType().toString();
			accountNames.add(accountName);
		}
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(770, 440));
		// this.setLayout(new GridLayout(2, 0, 0, 0));
		this.setLayout(new BorderLayout(5, 5));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setBackground(new Color(255, 255, 255));
		this.add(tabbedPane, BorderLayout.CENTER);

		JPanel cashTxnpanel = new JPanel();
		cashTxnpanel.setBackground(new Color(98, 102, 199));
		tabbedPane.addTab("Cash Transaction", null, cashTxnpanel, null);
		cashTxnpanel.setLayout(null);

		JLabel lblCashTransaction = new JLabel("Cash Transaction");
		lblCashTransaction.setForeground(new Color(255, 255, 255));
		lblCashTransaction.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCashTransaction.setBounds(10, 11, 198, 28);
		cashTxnpanel.add(lblCashTransaction);

		JLabel lblAction = new JLabel("Action");
		lblAction.setForeground(new Color(255, 255, 255));
		lblAction.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAction.setBounds(10, 91, 64, 30);
		cashTxnpanel.add(lblAction);

		JComboBox cashTxnActionField = new JComboBox();
		cashTxnActionField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cashTxnActionField.setModel(new DefaultComboBoxModel(new String[] { "Deposit", "Withdrawal" }));
		cashTxnActionField.setBounds(112, 91, 161, 30);
		cashTxnpanel.add(cashTxnActionField);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAmount.setForeground(new Color(255, 255, 255));
		lblAmount.setBounds(10, 131, 64, 30);
		cashTxnpanel.add(lblAmount);

		cashTxnAmtField = new JTextField();
		cashTxnAmtField.setBackground(Color.WHITE);
		cashTxnAmtField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cashTxnAmtField.setBounds(112, 132, 96, 30);
		cashTxnpanel.add(cashTxnAmtField);
		cashTxnAmtField.setColumns(10);

		JButton submitButton = new JButton("Submit");
		submitButton.setBackground(new Color(52, 56, 156));
		submitButton.setForeground(new Color(255, 255, 255));
		submitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

		submitButton.setBounds(240, 131, 96, 30);
		cashTxnpanel.add(submitButton);

		JLabel lblAccount = new JLabel("Account");
		lblAccount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccount.setForeground(new Color(255, 255, 255));
		lblAccount.setBounds(10, 50, 64, 30);
		cashTxnpanel.add(lblAccount);

		JComboBox cashTxnAccountComboBox = new JComboBox(accountNames.toArray());
		cashTxnAccountComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cashTxnAccountComboBox.setBounds(112, 50, 161, 30);
		cashTxnpanel.add(cashTxnAccountComboBox);

		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String accountName = (String) cashTxnAccountComboBox.getSelectedItem();
				String actionMode = (String) cashTxnActionField.getSelectedItem();

				try {
					double amount = Double.parseDouble(cashTxnAmtField.getText());
					Account to = null;
					for (Account account : accounts) {
						if (account.getAccountType().toString().compareTo(accountName) == 0) {
							to = account;
						}
					}
					try {
						TempTxn cashDeposit = TempTxn.createCashTxn(amount);

						if (actionMode.compareTo("Deposit") == 0) {
							to.credit(cashDeposit, amount);
							JOptionPane.showMessageDialog(new JLabel("Exception"), "Successfully " + actionMode
									+ "ed amount " + amount + " to " + accountName + " account . The new balance is "
									+ to.getAccountBalance());
							accountSummaryGui.updateSummary();
						} else {
							to.debit(cashDeposit, amount);
							JOptionPane.showMessageDialog(new JLabel("Exception"),
									"Successfully withdrew amount " + amount + " from " + accountName
											+ " account . The new balance is " + to.getAccountBalance());
							accountSummaryGui.updateSummary();
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JLabel("Exception"), e.getMessage());
					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(new JLabel("Message"), "Enter a numerical value");
				}

			}
		});

		JPanel borderPanel = new JPanel();
		borderPanel.setBackground(new Color(98, 102, 199));
		borderPanel.setLayout(new BorderLayout());
		JPanel InternalTfrPanel = new JPanel();
		InternalTfrPanel.setBackground(new Color(98, 102, 199));

		tabbedPane.addTab("Internal Transfer", null, borderPanel, "Use this tab for making money transfer between a user's accounts.\r\n");

		GridLayout internaltfrGridLayout = new GridLayout(6, 2, 0, 0);
		InternalTfrPanel.setLayout(internaltfrGridLayout);
		borderPanel.add(InternalTfrPanel, BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("Internal Money Transfer");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(98, 102, 199));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		InternalTfrPanel.add(lblNewLabel);
		JLabel lblNewLabel_3 = new JLabel("");
		InternalTfrPanel.add(lblNewLabel_3);

		JLabel intTfrFrom = new JLabel("From");
		intTfrFrom.setForeground(new Color(255, 255, 255));
		intTfrFrom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		intTfrFrom.setBorder(new EmptyBorder(6, 20, 6, 6));
		InternalTfrPanel.add(intTfrFrom);

		JComboBox intTfrAcctsFromCmBx = new JComboBox(accountNames.toArray());
		intTfrAcctsFromCmBx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		intTfrAcctsFromCmBx.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		InternalTfrPanel.add(intTfrAcctsFromCmBx);

		JLabel intTfrlblTo = new JLabel("To");
		intTfrlblTo.setForeground(new Color(255, 255, 255));
		intTfrlblTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		intTfrlblTo.setBorder(new EmptyBorder(6, 20, 6, 6));
		InternalTfrPanel.add(intTfrlblTo);

		JComboBox intTfrAcctsToCmBx = new JComboBox(accountNames.toArray());
		intTfrAcctsToCmBx.setFont(new Font("Tahoma", Font.PLAIN, 14));
		intTfrAcctsToCmBx.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		InternalTfrPanel.add(intTfrAcctsToCmBx);

		JLabel intTfrAmtLbl = new JLabel("Amount");
		intTfrAmtLbl.setForeground(new Color(255, 255, 255));
		intTfrAmtLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		intTfrAmtLbl.setBorder(new EmptyBorder(6, 20, 6, 6));
		InternalTfrPanel.add(intTfrAmtLbl);

		intTfrAmt = new JTextField();
		intTfrAmt.setBackground(new Color(255, 255, 255));
		intTfrAmt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		intTfrAmt.setBorder(new CompoundBorder(new EmptyBorder(6, 6, 6, 6), new EtchedBorder(EtchedBorder.LOWERED,
				null, new Color(102, 51, 51))));
		InternalTfrPanel.add(intTfrAmt);
		intTfrAmt.setColumns(10);

		JButton InternalTfrButton = new JButton("Submit");
		InternalTfrButton.setBackground(new Color(52, 56, 156));
		InternalTfrButton.setForeground(new Color(255, 255, 255));
		InternalTfrButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		InternalTfrButton.setBorder(new LineBorder(new Color(255, 255, 255)));
		InternalTfrButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String frmCmbBx = (String) intTfrAcctsFromCmBx.getSelectedItem();
				String toCmbBx = (String) intTfrAcctsToCmBx.getSelectedItem();

				if (frmCmbBx.compareTo(toCmbBx) == 0) {
					JOptionPane.showMessageDialog(new JLabel("Error!"), "From and To Accounts should not be the same ");
				} else {
					try {
						double amount = Double.parseDouble(intTfrAmt.getText());
						Account fromAccount = null;
						Account toAccount = null;
						for (Account account : accounts) {
							if (account.getAccountType().toString().compareTo(frmCmbBx) == 0) {
								fromAccount = account;
							} else {
								if (account.getAccountType().toString().compareTo(toCmbBx) == 0) {
									toAccount = account;
								}
							}
						}
						try {
							fromAccount.debit(toAccount, amount);
							toAccount.credit(fromAccount, amount);
							JOptionPane.showMessageDialog(new JLabel("Exception"), "Successfully transferred amount "
									+ amount + " from account " + frmCmbBx + " to " + toCmbBx + ". The new balance in "
									+ frmCmbBx + " is " + fromAccount.getAccountBalance() + " and new balance in "
									+ toCmbBx + " is " + toAccount.getAccountBalance() + ".");
							accountSummaryGui.updateSummary();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(new JLabel("Exception"), e.getMessage());
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(new JLabel("Message"), "Enter a numerical value");
					}
				}
			}
		});
		InternalTfrPanel.add(InternalTfrButton);
	}
}