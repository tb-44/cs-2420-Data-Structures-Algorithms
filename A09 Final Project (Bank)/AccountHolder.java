/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

import java.util.List;

public class AccountHolder {
	private String name;
	private List<Account> associatedAccounts;
	
	public AccountHolder(String name, List<Account> associatedAccounts){
		this.name = name;
		this.associatedAccounts = associatedAccounts;
	}
	
	public String getName() {
		return name;
	}
	public List<Account> getAssociatedAccounts() {
		return associatedAccounts;
	}
}
