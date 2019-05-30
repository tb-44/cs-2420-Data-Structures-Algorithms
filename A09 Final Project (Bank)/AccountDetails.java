/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

public class AccountDetails {
	public double getAmount() {
		return amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	private double amount;
	private String accountNumber;
	
	public AccountDetails(String accountNumber, double amount){
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
}
