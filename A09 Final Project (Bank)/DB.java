/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DB {
	private static String rootfolder = "OOPSBank";
	private static String lastusedAccountID = "lastusedAccountID.txt";

	private static void setRootFolder() throws IOException {
		File rootFolder = new File(rootfolder);

		if (!rootFolder.exists()) {
			rootFolder.mkdir();
		}
	}

	private static AccountDetails readAccountDetailsFile(String accountFolderPath) throws Exception {
		String path = accountFolderPath + "\\accdetails.txt";
		if (!(new File(path).exists())) {
			throw new Exception("Wrong file path for finding account details");
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			int i = 1;
			double tempBalance = 0;
			String accountNumber = "";
			while ((line = br.readLine()) != null) {
				if (i == 1) {
					accountNumber = line.trim();
				}
				if (i == 2) {
					tempBalance = Double.parseDouble(line);
				}
				i++;
			}
			return new AccountDetails(accountNumber, tempBalance);
		} finally {
			if (null != br) {
				br.close();
				br = null;
			}
		}
	}

	public static Account getAccountDetails(String accountNumber) throws Exception {
		Account account = null;
		File oopsBankFolder = new File(rootfolder);
		File[] userFolders = oopsBankFolder.listFiles();
		for (File userFolder : userFolders) {
			if (userFolder.isDirectory()) {
				File[] actFolders = userFolder.listFiles();
				for (File actFolder : actFolders) {
					if (actFolder.isDirectory()) {
						AccountDetails details = readAccountDetailsFile(actFolder.getAbsolutePath());
						account = new Account(accountNumber, AccountTypes.AccountTypesFromString(actFolder.getName()),
								details.getAmount());
						String name = userFolder.getName();
						List<Account> associatedAccounts = new ArrayList<Account>();
						File[] acctFolders = userFolder.listFiles();
						for (File accountFolder : acctFolders) {
							if (accountFolder.isDirectory()) {
								AccountDetails actdetails = readAccountDetailsFile(accountFolder.getAbsolutePath());
								Account acc = new Account(actdetails.getAccountNumber(),
										AccountTypes.AccountTypesFromString(accountFolder.getName()),
										actdetails.getAmount());
								associatedAccounts.add(acc);
							}
						}
						AccountHolder accountHolder = new AccountHolder(name, associatedAccounts);
						account.setAccountHolder(accountHolder);
					}
				}
			}
		}
		if (account == null) {
			throw new Exception("Account does not exist");
		}
		return account;
	}

	// 20141110103045;CR;CHECK;100.05;189;WELLSFARGO
	private static void saveTxn(String loginname, AccountTypes accountTypes, BankTransaction txn) throws Exception {
		setRootFolder();
		File loginFolder = new File(rootfolder + "\\" + loginname);
		if (!loginFolder.exists()) {
			throw new Exception("Login name does not exist;create one");
		}
		File accountFolder = new File(rootfolder + "\\" + loginname + "\\" + accountTypes.toString());
		if (!accountFolder.exists()) {
			throw new Exception("This user already has " + accountTypes.toString());
		}

		dbWriter(rootfolder + "\\" + loginname + "\\" + accountTypes.toString() + "\\txn.txt", txn.toDBString());// builder.toString());

	}

	// 19/11/14 00:12:02;1;1;100.0
	public static List<BankTransaction> readtxn(String loginname, AccountTypes accountTypes) throws Exception {
		setRootFolder();
		File loginFolder = new File(rootfolder + "\\" + loginname);
		if (!loginFolder.exists()) {
			throw new Exception("Login name does not exist;create one");
		}
		File accountFolder = new File(rootfolder + "\\" + loginname + "\\" + accountTypes.toString());
		if (!accountFolder.exists()) {
			throw new Exception("This user already has " + accountTypes.toString());
		}

		BufferedReader br = null;
		List<BankTransaction> transactions = new ArrayList<BankTransaction>();
		try {
			br = new BufferedReader(new FileReader(rootfolder + "\\" + loginname + "\\" + accountTypes.toString()
					+ "\\txn.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(";")) {
					String[] data = line.split(";");
					Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					cal.setTime(sdf.parse(data[0]));

					TransactionType type = TransactionType.typeOf(Integer.parseInt(data[1]));
					TransactionMode mode = TransactionMode.modeOf(Integer.parseInt(data[2]));
					double amt = Double.parseDouble(data[3]);

					BankTransaction transaction = new BankTransaction(cal, type, mode, amt);
					transactions.add(transaction);
				}
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return transactions;
	}

	private static void dbWriter(String filename, String content) throws IOException {
		File file = new File(filename);
		PrintWriter writer = null;
		writer = new PrintWriter(new FileWriter(file, true));
		try {
			writer.println(content);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static String createAccount(String loginname, AccountTypes accountTypes) throws Exception {
		setRootFolder();
		File loginFolder = new File(rootfolder + "\\" + loginname);
		if (!loginFolder.exists()) {
			throw new Exception("Login name does not exist;create one");
		}
		File accountFolder = new File(rootfolder + "\\" + loginname + "\\" + accountTypes.toString());
		if (accountFolder.exists()) {
			throw new Exception("This user already has " + accountTypes.toString());
		}
		File userFile = new File(rootfolder + "\\" + lastusedAccountID);
		if (!userFile.exists()) {
			userFile.createNewFile();
			dbWriter(userFile.getAbsolutePath(), "1000");
		}
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(userFile.getAbsolutePath()));
			String line;
			int lastID = 1000;
			boolean once = false;
			while ((line = br.readLine()) != null && !once) {
				once = true;
				lastID = Integer.parseInt(line);
			}
			if (br != null) {
				br.close();
				br = null;
			}
			userFile.delete();
			// Files.delete(userFile.getAbsoluteFile().toPath());
			userFile.createNewFile();
			lastID++;
			dbWriter(userFile.getAbsolutePath(), String.valueOf(lastID));

			accountFolder.mkdir();
			File actDetails = new File(rootfolder + "\\" + loginname + "\\" + accountTypes.toString()
					+ "\\accdetails.txt");
			actDetails.createNewFile();
			dbWriter(actDetails.getAbsolutePath(), String.valueOf(lastID));
			dbWriter(actDetails.getAbsolutePath(), "0.0");
			return String.valueOf(lastID);
		} finally {
			if (br != null) {
				br.close();
			}
		}
	}

	public static void createUserAccount(String loginname, String username, String password) throws Exception {
		setRootFolder();
		File userFile = new File(rootfolder + "\\" + loginname);
		if (userFile.exists()) {
			throw new Exception("LoginName " + loginname + " already exists");
		} else {
			userFile.mkdir();
			File up = new File(rootfolder + "\\" + loginname + "\\up.txt");
			up.createNewFile();
			dbWriter(up.getAbsolutePath(), username);
			dbWriter(up.getAbsolutePath(), password);
		}
	}

	public static boolean logon(String loginname, String password) throws Exception {
		setRootFolder();
		File userFile = new File(rootfolder + "\\" + loginname);
		if (!userFile.exists()) {
			throw new Exception("Invalid Login, check your username/password");
		}
		File up = new File(rootfolder + "\\" + loginname + "\\up.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(up.getAbsolutePath()));
			String line;
			int i = 1;
			while ((line = br.readLine()) != null) {
				if (i == 2) {
					if (line.compareTo(password) != 0) {
						throw new Exception("Invalid Login, check your username/password");
					}
					return true;
				}
				i++;
			}
		} finally {
			if (br != null) {
				br.close();
			}
		}
		return false;
	}

	public static void creditTxn(Account from, Account to, double amount) throws Exception {
		if (from.getAccountType() == AccountTypes.TEMPTXN) {
			if (((TempTxn) from).getTxnMode() == TransactionMode.CASH) {
				findFolder(to, new OnFoundFolder() {

					@Override
					public void onFoundFolder(File acctDetailsFile, File userDir, File accountFile,
							double accountBalance) throws Exception {
						acctDetailsFile.delete();
						acctDetailsFile.createNewFile();
						dbWriter(acctDetailsFile.getAbsolutePath(), to.getAccountNumber());
						dbWriter(acctDetailsFile.getAbsolutePath(),
								String.valueOf(accountBalance + from.getAccountBalance()));
						BankTransaction btn =new BankTransaction(from, to, TransactionType.CREDIT, ((TempTxn) from).getTxnMode(), amount); 
								//new BankTransaction(TransactionType.CREDIT,
								//((TempTxn) from).getTxnMode(), from.getAccountBalance());
						saveTxn(userDir.getName(), AccountTypes.AccountTypesFromString(accountFile.getName()), btn);
					}
				});
			}

		} else {
			if (from.getAccountType().compareTo(AccountTypes.TEMPTXN) != 0
					&& to.getAccountType().compareTo(AccountTypes.TEMPTXN) != 0) {
				findFolder(to, new OnFoundFolder() {

					@Override
					public void onFoundFolder(File acctDetailsFile, File userDir, File accountFile,
							double accountBalance) throws Exception {
						acctDetailsFile.delete();
						acctDetailsFile.createNewFile();
						dbWriter(acctDetailsFile.getAbsolutePath(), to.getAccountNumber());
						dbWriter(acctDetailsFile.getAbsolutePath(), String.valueOf(accountBalance + amount));
						BankTransaction btn = new BankTransaction(from, to, TransactionType.CREDIT, TransactionMode.TRANSFER, amount);
								
								//TransactionType.CREDIT, TransactionMode.TRANSFER,
								//from.getAccountBalance());
						saveTxn(userDir.getName(), AccountTypes.AccountTypesFromString(accountFile.getName()), btn);
					}
				});
			}

		}
	}

	public static boolean debitTxn(Account from, Account to, final double amount) throws Exception {
		if (to.getAccountType() == AccountTypes.TEMPTXN) {
			if (((TempTxn) to).getTxnMode() == TransactionMode.CASH) {
				findFolder(from, new OnFoundFolder() {

					@Override
					public void onFoundFolder(File acctDetailsFile, File userDir, File accountFile,
							double accountBalance) throws Exception {
						acctDetailsFile.delete();
						acctDetailsFile.createNewFile();
						dbWriter(acctDetailsFile.getAbsolutePath(), from.getAccountNumber());
						dbWriter(acctDetailsFile.getAbsolutePath(),
								String.valueOf(accountBalance - to.getAccountBalance()));
						BankTransaction btn = new BankTransaction(from, to, TransactionType.DEBIT, ((TempTxn) to).getTxnMode(), amount);
								//new BankTransaction(TransactionType.DEBIT, ((TempTxn) to).getTxnMode(), to.getAccountBalance());
						saveTxn(userDir.getName(), AccountTypes.AccountTypesFromString(accountFile.getName()), btn);

					}
				});
			}
		} else {
			if (from.getAccountType().compareTo(AccountTypes.TEMPTXN) != 0
					&& to.getAccountType().compareTo(AccountTypes.TEMPTXN) != 0) {
				findFolder(from, new OnFoundFolder() {

					@Override
					public void onFoundFolder(File acctDetailsFile, File userDir, File accountFile,
							double accountBalance) throws Exception {
						acctDetailsFile.delete();
						acctDetailsFile.createNewFile();
						dbWriter(acctDetailsFile.getAbsolutePath(), from.getAccountNumber());
						double newBalance = accountBalance - amount;
						dbWriter(acctDetailsFile.getAbsolutePath(), String.valueOf(newBalance));
						BankTransaction btn = new BankTransaction(from, to, TransactionType.DEBIT, TransactionMode.TRANSFER, amount);
						//new BankTransaction(TransactionType.DEBIT, TransactionMode.TRANSFER, from.getAccountBalance());
						saveTxn(userDir.getName(), AccountTypes.AccountTypesFromString(accountFile.getName()), btn);
					}
				});
			}

		}
		return false;
	}

	private static void findFolder(Account account, OnFoundFolder folderFind) throws Exception {
		boolean found = false;
		boolean searchComplete = false;
		File file = new File(rootfolder);
		String[] names = file.list();
		int index = 0;
		while (!found && !searchComplete) {
			File userDir = new File(rootfolder + "\\" + names[index]);
			if (userDir.isDirectory())// userdirectory
			{
				String[] accounts = userDir.list();
				int actIndex = 0;
				while (actIndex <= (accounts.length - 1) && !found) {
					File accountFile = new File(userDir.getAbsolutePath() + "\\" + accounts[actIndex]);
					if (accountFile.isDirectory()) {
						File acctDetailsFile = new File(accountFile.getAbsolutePath() + "\\accdetails.txt");
						BufferedReader br = null;
						try {
							br = new BufferedReader(new FileReader(acctDetailsFile.getAbsolutePath()));
							String line;
							int i = 1;
							double tempBalance = 0.0;
							while ((line = br.readLine()) != null) {
								if (i == 1) {
									if (line.compareTo(account.getAccountNumber()) == 0) {
										found = true;
									}
								}
								if (i == 2 && found) {
									tempBalance = Double.parseDouble(line);
								}
								i++;
							}
							if (found) {
								br.close();
								folderFind.onFoundFolder(acctDetailsFile, userDir, accountFile, tempBalance);
							}
						} finally {
							if (br != null) {
								br.close();
							}
						}
					}
					actIndex++;
				}
			}
			index++;
			if (index == names.length && !found) {
				throw new Exception("No such target account");
			}
		}

	}

	public static List<Account> getAccountSummary(String loginname) throws Exception {
		setRootFolder();
		List<Account> accounts = new ArrayList<Account>();

		File userFolder = new File(rootfolder + "\\" + loginname);
		if (!userFolder.exists()) {
			throw new Exception("User does not exist");
		}
		File[] acctFolders = userFolder.listFiles();
		for (File acctFolder : acctFolders) {
			if (acctFolder.isDirectory()) {
				AccountDetails accountDetails = readAccountDetailsFile(acctFolder.getAbsolutePath());
				if (acctFolder.getName().compareTo("Checking") == 0) {
					CheckingAccount account = new CheckingAccount(accountDetails.getAccountNumber(),
							AccountTypes.AccountTypesFromString(acctFolder.getName()), accountDetails.getAmount());
					accounts.add(account);
				}
				if (acctFolder.getName().compareTo("Saving") == 0) {
					SavingsAccount account = new SavingsAccount(accountDetails.getAccountNumber(),
							AccountTypes.AccountTypesFromString(acctFolder.getName()), accountDetails.getAmount());
					accounts.add(account);
				}

			}
		}
		return accounts;
	}
	public static boolean hasTransaction(String loginname, AccountTypes accountTypes) throws Exception{
		setRootFolder();
		File userFolder = new File(rootfolder + "\\" + loginname);
		if (!userFolder.exists()) {
			throw new Exception("User does not exist");
		}
		File accountFolder = new File(userFolder.getAbsolutePath() + "\\" + accountTypes.toString());
		if(!accountFolder.exists()){
			throw new Exception("User does not have " + accountTypes.toString());
		}
		File textFile = new File(accountFolder.getAbsolutePath() + "\\txn.txt");
		if(textFile.exists()){
			return true;
		}
		return false;
	} 
}