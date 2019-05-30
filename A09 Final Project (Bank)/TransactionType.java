/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

public enum TransactionType {
	DEBIT (1), CREDIT (2) ;
	
	private final int value;
	
	private TransactionType (int value){
		this.value = value;
	}
	
	public int valueOf(){
		return value;
	}
	
	public static TransactionType typeOf(int val){
		if(val == 1){
			return DEBIT;
		}
		return CREDIT;
	}
}