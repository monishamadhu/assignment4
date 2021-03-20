package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction {
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		super.setSourceAccount(sourceAccount);
		super.setTargetAccount(targetAccount);
		super.setAmount(amount);
	}

	@Override
	public void process() throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		if (getAmount()<0){
			NegativeAmountException negativeAmount=new NegativeAmountException();
			throw negativeAmount;				
		} else if (getAmount()>TRANSACTLIMIT) {
			ExceedsFraudSuspicionLimitException exceedFraudSuspicionLimit = new ExceedsFraudSuspicionLimitException();
			throw exceedFraudSuspicionLimit;
		} else if(getAmount() <= TRANSACTLIMIT) {
			if(super.getSourceAccount().getBalance()<getAmount()) {
				ExceedsAvailableBalanceException exceedAvailableBalance = new ExceedsAvailableBalanceException();
				throw exceedAvailableBalance; 
			} else {
				super.getSourceAccount().withdraw(getAmount());
				super.getTargetAccount().deposit(getAmount());
			}
		}		
	}
	private static final double TRANSACTLIMIT = 1000.0;
}
