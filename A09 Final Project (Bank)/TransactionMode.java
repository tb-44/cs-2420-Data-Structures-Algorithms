/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

public enum TransactionMode {
	CASH(1), CHECK(2), MONEYORDER(3), TRANSFER(4);
	
	private final int value;
	
	private TransactionMode(int value){
		this.value = value;
	}
	
	public int valueOf(){
		return value;
	}
	
	public static TransactionMode modeOf(int val){
		if(val == 1){
			return CASH;
		} else if (val == 2){
			return CHECK;
		} else if (val == 3){
			return MONEYORDER;
		}
		return TRANSFER;
	}

}