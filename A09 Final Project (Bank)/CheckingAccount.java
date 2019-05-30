/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

public class CheckingAccount extends Account {
	private double maxOverDraftAmount = 300;
	public CheckingAccount(String accountNumber, AccountTypes accountType, double accountBalance) {
		super(accountNumber, accountType, accountBalance);
		
	}
	
	@Override
	public boolean debit(Account to, double amount) throws Exception{
		if(this.getAccountBalance() - amount >= 0){
			 DB.debitTxn(this, to, amount);
			 this.accountBalance = this.accountBalance - amount;
		}else {
			if(this.getAccountBalance() - amount  < (-1) * maxOverDraftAmount){
				throw new Exception("Reached max over draft limit");
			}else{
				DB.debitTxn(this, to, amount);
				this.accountBalance = this.accountBalance - amount;
				DB.debitTxn(this, to, 25);
				this.accountBalance = this.accountBalance - 25;
			}
		}
		return true;
		
	}
}