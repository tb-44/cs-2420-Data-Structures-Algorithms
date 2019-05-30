/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/

package msbBank;

public class Account {

	private String accountNumber;
	private AccountTypes accountType;
	protected double accountBalance;
	private AccountHolder accountHolder;

	public String getAccountNumber() {
		return accountNumber;
	}

	public AccountTypes getAccountType() {
		return accountType;
	}

	public double getAccountBalance() {
		return Math.round(accountBalance * 100.0) / 100.0;
	}

	public Account(String accountNumber, AccountTypes accountType, double accountBalance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	public void credit(Account from, double amount) throws Exception{
		DB.creditTxn(from, this, amount);
		this.accountBalance = this.accountBalance + amount;
	}
	
	public boolean debit(Account to, double amount) throws Exception{
		boolean ret = DB.debitTxn(this, to, amount);
		this.accountBalance = this.accountBalance - amount;
		return ret;
		
	}
}