/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

import java.util.List;

public class BankApp {

	public static void mainA(String[] args) {
		// Creating a user account with the bank. Go to project folder\OOPSBank.
		// Then you should see
		// folder with name madana and it has a file ( up.txt ). First line in
		// that file is user name and second line is password.
		// The first argument of DB.createUserAccount is loginname( a unique one
		// ) and second argument is user name( Which can be repeated ). and
		// third argument is password
		try {
			DB.createUserAccount("madana", "madana", "1234abc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Creating a user account with the bank with same login name should
		// throw exception. No two user should have same login name
		try {
			DB.createUserAccount("madana", "madana", "1234abc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Checking if a login is successful. First argument is loginname and
		// second one is password. If successful it returns true. Else throws
		// exception
		try {
			DB.logon("michael", "1234abc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Checking if a login is failed due to wrong password
		try {
			DB.logon("michael", "ssds1234abc");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Creating Checking and Savings account for user madana. Go to project
		// folder\OOPSBank\madana. The you see two folders - Checking and
		// Savings.
		// Checking & Savings folder contains accdetails.txt. accdetails.txt
		// contains account number( a unique number) and the second line is the
		// balance
		try {
			DB.createAccount("madana", AccountTypes.SAVINGS);
			DB.createAccount("madana", AccountTypes.CHECKING);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// To ensure not to create another checking and savings accoutn for the
		// same user. It should throw exception
		try {
			DB.createAccount("madana", AccountTypes.SAVINGS);
			DB.createAccount("madana", AccountTypes.CHECKING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Making a cash deposit. The example shows a cash deposit.Go to project
		// folder\OOPSBank\madana\Checking. In accdetails.txt should have value
		// 1778.0 as balance.
		// Also a new file txn.txt is created with a single line which tells
		// about the transactions
		TempTxn cashDeposit = TempTxn.createCashTxn(1778.00);
		Account targetAcct = new Account("1002", AccountTypes.CHECKING, 0.0);
		try {
			DB.creditTxn(cashDeposit, targetAcct, cashDeposit.getAccountBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Making another cash deposit. The example shows a cash deposit.Go to
		// project folder\OOPSBank\madana\Checking. In accdetails.txt should
		// have value 5000 ( 1778 + 3222 ) as balance
		// Also txn.txt is updated with another line which tells about the
		// transactions
		// First argument is account number, then checking/saving
		TempTxn acashDeposit = TempTxn.createCashTxn(3222.00);
		Account atargetAcct = new Account("1002", AccountTypes.CHECKING, 0.0);
		try {
			DB.creditTxn(acashDeposit, atargetAcct, acashDeposit.getAccountBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Trying to make a transaction to a non existing account should throw
		// exception
		TempTxn atcashDeposit = TempTxn.createCashTxn(3222.00);
		Account attargetAcct = new Account("10005", AccountTypes.CHECKING, 0.0);
		try {
			DB.creditTxn(atcashDeposit, attargetAcct, atcashDeposit.getAccountBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Making another cash deposit. The example shows a cash deposit.Go to
		// project folder\OOPSBank\madana\Checking. In accdetails.txt should
		// have value 5000 ( 1778 + 3222 ) as balance
		// Also txn.txt is updated with another line which tells about the
		// transactions
		// First argument is account number, then checking/saving
		TempTxn atrcashDeposit = TempTxn.createCashTxn(6000.00);
		Account atrtargetAcct = new Account("1002", AccountTypes.CHECKING, 0.0);
		try {
			// DB.debitTxn(atargetAcct, acashDeposit);
			DB.creditTxn(atrcashDeposit, atrtargetAcct, atrcashDeposit.getAccountBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// This shows all the transactions happened on madana's checking
		// account. It should have two transactions
		try {
			List<BankTransaction> txns = DB.readtxn("madana", AccountTypes.CHECKING);
			for (BankTransaction txn : txns) {
				System.out.println(txn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			Account account = DB.getAccountDetails("1002");
			System.out.println(account.getAccountNumber());
			System.out.println((account.getAccountHolder().getName()));
			System.out.println((account.getAccountHolder().getAssociatedAccounts().get(0).getAccountType()));
			System.out.println((account.getAccountHolder().getAssociatedAccounts().get(0).getAccountNumber()));
			System.out.println((account.getAccountHolder().getAssociatedAccounts().get(0).getAccountBalance()));
			System.out.println((account.getAccountHolder().getAssociatedAccounts().get(1).getAccountType()));
			System.out.println((account.getAccountHolder().getAssociatedAccounts().get(1).getAccountNumber()));
			System.out.println((account.getAccountHolder().getAssociatedAccounts().get(1).getAccountBalance()));
			System.out.println(account.getAccountType());
			System.out.println(account.getAccountBalance());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<Account> accounts = DB.getAccountSummary("madana");
			for (Account accnt : accounts) {
				System.out.println(accnt.getAccountType());
				System.out.println(accnt.getAccountBalance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// Creating a user account with the bank. Go to project folder\OOPSBank.
		// Then you should see
		// folder with name madana and it has a file ( up.txt ). First line in
		// that file is user name and second line is password.
		// The first argument of DB.createUserAccount is loginname( a unique one
		// ) and second argument is user name( Which can be repeated ). and
		// third argument is password
		/*
		 * try { DB.createUserAccount("madana", "madana", "1234abc"); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
		// Creating Checking and Savings account for user "madana". 
		// You see two folders - Checking and
		// Savings.
		// Checking & Savings folder contains accdetails.txt. accdetails.txt
		// contains account number( a unique number) and the second line is the
		// balance
		/*
		 * try { DB.createAccount("madana", AccountTypes.SAVINGS);
		 * DB.createAccount("madana", AccountTypes.CHECKING); } catch (Exception
		 * e) { e.printStackTrace(); }
		 */
		try {
			List<BankTransaction> txns = DB.readtxn("madana", AccountTypes.CHECKING);
			for (BankTransaction txn : txns) {
				System.out.println(txn.getCalendar().getTime() + " " + txn.getTxnMode() + " " + txn.getTxnType() + " "
						+ txn.getAmount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * SimpleDateFormat formatter = new
		 * SimpleDateFormat("dd/MM/yy HH:mm:ss"); try { Date parsedDate =
		 * formatter.parse("24/11/14 23:47:53"); System.out.println(parsedDate);
		 * } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}
}