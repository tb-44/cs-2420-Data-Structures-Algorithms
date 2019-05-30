/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

public enum AccountTypes {
	CHECKING("Checking"), SAVINGS("Saving"), TEMPTXN("Temptxn");
	
	private AccountTypes(String accountDisplayName){
		this.displayName = accountDisplayName;
	}
	
	private String displayName;
	
	@Override
	public String toString(){
		return displayName;
	}
	
	public static AccountTypes AccountTypesFromString(String type){
		if(type.compareTo("Checking") == 0){
			return CHECKING;
		}
		return SAVINGS;
	}
}