/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BankTransaction {

	private Account from;
	private Account to;
	private TransactionType txnType;
	private TransactionMode txnMode;
	private double amount;
	private Calendar calendar = Calendar.getInstance();
	
	public BankTransaction(TransactionType txnType, TransactionMode txnMode, double amount ){
		this.txnType = txnType;
		this.txnMode = txnMode;
		this.amount = amount;
	}
	
	public BankTransaction(Calendar cal, TransactionType txnType, TransactionMode txnMode, double amount ){
		this.txnType = txnType;
		this.txnMode = txnMode;
		this.amount = amount;
		this.calendar = cal;
	}
	
	public BankTransaction(Account from, Account to, TransactionType txnType, TransactionMode txnMode, double amount) {
		this.from = from;
		this.to = to;
		this.txnType = txnType;
		this.txnMode = txnMode;
		this.amount = amount;
	}

	public Calendar getCalendar(){
		return calendar;
	}
	
	@Override
	public String toString() {
		Calendar cal = calendar;
		cal.add(Calendar.DATE, 1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String calst = format1.format(Calendar.getInstance().getTime());

		return calst + " " + txnType + " " + txnMode + " " + amount;
	}

	public String toDBString() {
		StringBuilder builder = new StringBuilder();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		builder.append(df.format(Calendar.getInstance().getTime()));
		builder.append(";");
		builder.append(txnType.valueOf());
		builder.append(";");
		builder.append(txnMode.valueOf());
		builder.append(";");
		builder.append(amount);
		
		if (txnMode == TransactionMode.CHECK){
			if (from instanceof TempTxn){
				builder.append(";");
				builder.append(((TempTxn)from).getInstNumID());
			}else if (to instanceof TempTxn){
				builder.append(";");
				builder.append(((TempTxn)to).getInstNumID());
			}
		
		}
		if (txnMode == TransactionMode.TRANSFER){
			builder.append(";");
			builder.append(from.getAccountNumber());
		}

		return builder.toString();
	}

	public Account getFrom() {
		return from;
	}

	public Account getTo() {
		return to;
	}

	public TransactionType getTxnType() {
		return txnType;
	}

	public TransactionMode getTxnMode() {
		return txnMode;
	}

	public double getAmount() {
		return amount;
	}
}