package com.meritamerica.assignment4;

public class DepositTransaction extends Transaction{
	DepositTransaction(BankAccount targetAccount, double amount){
		super.setTargetAccount(targetAccount);
		super.setAmount(amount);
	}

	@Override
	public void process() throws NegativeAmountException, ExceedsFraudSuspicionLimitException {
		
			if (getAmount()<0){
				NegativeAmountException negativeAmount=new NegativeAmountException();
				throw negativeAmount;				
			} else if (getAmount()>TRANSACTLIMIT) {
				ExceedsFraudSuspicionLimitException exceedFraudSuspicionLimit = new ExceedsFraudSuspicionLimitException();
				throw exceedFraudSuspicionLimit;
			} else if(getAmount()<=TRANSACTLIMIT) {
				super.getTargetAccount().addTransaction(this);
			}
	}
	private static final double TRANSACTLIMIT = 1000.0;
}

