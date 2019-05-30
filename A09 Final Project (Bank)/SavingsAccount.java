/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

public class SavingsAccount extends Account {

	public SavingsAccount(String accountNumber, AccountTypes accountType, double accountBalance) {
		super(accountNumber, accountType, accountBalance);

	}

	@Override
	public boolean debit(Account to, double amount) throws Exception {
		if (this.getAccountBalance() - amount >= 0) {
			return super.debit(to, amount);
		}
		throw new Exception("Insufficient funds");
	}
}