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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CheckingDetailsGui extends JFrame {

	private static final long serialVersionUID = -1134348179831430381L;
	private JPanel contentPane;
	private AccountSummaryGui myAccountSummaryGui;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckingDetailsGui frame = new CheckingDetailsGui();
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
	public CheckingDetailsGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(770, 440));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(98, 102, 199));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblSavings = new JLabel("Checking Details");
		lblSavings.setBorder(new EmptyBorder(5, 0, 7, 0));
		lblSavings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSavings.setForeground(new Color(255, 255, 255));
		lblSavings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel.add(lblSavings);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CheckingDetailsGui.class.getResource("BanSmallt.jpg")));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));

		JTextArea txtrAmount = new JTextArea();
		txtrAmount.setForeground(Color.WHITE);
		txtrAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrAmount.setBackground(new Color(179, 179, 224));
		txtrAmount.setBorder(new LineBorder(Color.BLACK));
		txtrAmount.setText("  Amount\r\n");
		panel_1.add(txtrAmount);

		JTextArea txtrMode = new JTextArea();
		txtrMode.setForeground(Color.WHITE);
		txtrMode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrMode.setBackground(new Color(179, 179, 224));
		txtrMode.setBorder(new LineBorder(Color.BLACK));
		txtrMode.setText("  Mode\r\n");
		panel_1.add(txtrMode);

		JTextArea txtrType = new JTextArea();
		txtrType.setForeground(Color.WHITE);
		txtrType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrType.setBackground(new Color(179, 179, 224));
		txtrType.setBorder(new LineBorder(Color.BLACK));
		txtrType.setText("  Type\r\n");
		panel_1.add(txtrType);

		JTextArea txtrDate = new JTextArea();
		txtrDate.setForeground(Color.WHITE);
		txtrDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrDate.setBackground(new Color(179, 179, 224));
		txtrDate.setBorder(new LineBorder(Color.BLACK));
		txtrDate.setText("  Date\r\n");
		panel_1.add(txtrDate);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(98, 102, 199));
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JButton btnAccountSummary = new JButton("Back to Account Summary");
		btnAccountSummary.setForeground(new Color(255, 255, 255));
		btnAccountSummary.setBackground(new Color(52, 56, 156));
		panel_2.add(btnAccountSummary);
		btnAccountSummary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAccountSummary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					myAccountSummaryGui = new AccountSummaryGui();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				setVisible(false);
				myAccountSummaryGui.setVisible(true);

			}
		});

		try {
			if (DB.hasTransaction(BankGui.gettextFieldUserName(), AccountTypes.SAVINGS)) {
				List<BankTransaction> txns = DB.readtxn(BankGui.gettextFieldUserName(), AccountTypes.CHECKING);
				for (BankTransaction txn : txns) {
					txtrDate.append("\n  " + String.valueOf(txn.getCalendar().getTime()) + "\n");
					txtrType.append("\n  " + String.valueOf(txn.getTxnType()) + "\n");
					txtrMode.append("\n  " + String.valueOf(txn.getTxnMode()) + "\n");
					txtrAmount.append("\n  " + String.valueOf(txn.getAmount()) + "\n");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}