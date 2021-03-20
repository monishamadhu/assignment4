package com.meritamerica.assignment4;

import java.util.Date;
import java.util.StringTokenizer;

public abstract class Transaction {
	public BankAccount getSourceAccount() {
		return this.sourceAccount;
	}
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount=sourceAccount;
	}
	public BankAccount getTargetAccount() {
		return this.targetAccount;
	}
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount= targetAccount;
	}
	public double getAmount() {
		return this.amount;
	}
	public void setAmount(double amount) {
		this.amount=amount;
	}
	public java.util.Date getTransactionDate(){
		return this.date ;
	}
	public void setTransactionDate(java.util.Date date) {
		this.date =date;
	}
	public String writeToString() {
		String transactString = getSourceAccount().getAccountNumber()+","+getTargetAccount().getAccountNumber()+","+getAmount()+","+getTransactionDate(); 
		System.out.println("transactString: "+transactString);
		return transactString;
	}
	public static Transaction readFromString(String transactionDataString) {
		//-1,1,1000.0,01/01/2020    if first number is -1, implies it's a deposit transact or withdraw transact
		//1,2,100,01/03/2020		and if it's not -1, it's the source account number

		StringTokenizer token = new StringTokenizer(transactionDataString, ",");
		int index = Integer.parseInt(token.nextToken());
		long accNum=Long.parseLong(token.nextToken());
		double amount = Double.parseDouble(token.nextToken());
		Date date = new Date(token.nextToken());
		BankAccount account = MeritBank.getBankAccount(accNum);
		
		if(index == -1){
			if(amount>0){
				DepositTransaction depositTransact = new DepositTransaction(account,amount);
				return depositTransact;				
			}
			if(amount<0) {
				WithdrawTransaction withdrawTransact = new WithdrawTransaction(account,Math.abs(amount));
				return withdrawTransact;
			}
		}
		if (index != -1) {
			BankAccount sourceAcc = MeritBank.getBankAccount(index);
			TransferTransaction transferTransact = new TransferTransaction(sourceAcc, account, amount);
			return transferTransact;
		}
		return null;
	}
	
	/*public boolean isProcessedByFraudTeam() {
		
	}
	public void setProcessedByFraudTeam(boolean isProcessed) {
		
	}*/
	public abstract void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;
	
	public String getRejectionReason() {
		return this.reason;
	}
	public void setRejectionReason(String reason) {
		this.reason = reason;
	}
 private java.util.Date date;
 private double amount;
 private BankAccount sourceAccount;
 private BankAccount targetAccount;
 private String reason;
}
