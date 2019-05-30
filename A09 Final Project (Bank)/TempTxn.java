/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

public class TempTxn extends Account {

	private TransactionMode txnMode;
	private String instNumID;

	public TransactionMode getTxnMode() {
		return txnMode;
	}

	public String getInstNumID() {
		return instNumID;
	}

	private TempTxn(double accountBalance) {
		super(" ", AccountTypes.TEMPTXN, accountBalance);

	}

	public static TempTxn createCashTxn(double amount) {
		TempTxn temp = new TempTxn(amount);
		temp.txnMode = TransactionMode.CASH;
		return temp;
	}

	public static TempTxn createCheckTxn(double amount, String chkNum) {
		TempTxn temp = new TempTxn(amount);
		temp.txnMode = TransactionMode.CHECK;
		temp.instNumID = chkNum;
		return temp;
	}

	public static TempTxn createMOTxn(double amount, String MoNum) {
		TempTxn temp = new TempTxn(amount);
		temp.txnMode = TransactionMode.MONEYORDER;
		temp.instNumID = MoNum;
		return temp;
	}
}