package com.meritamerica.assignment4;

import java.util.ArrayList;

public class FraudQueue {
	FraudQueue() {
		
	}
	public void addTransaction(Transaction transaction) {
		this.transactionList.add(transaction);
	}
	public Transaction[] getTransaction() {
		return (Transaction[]) transactionList.toArray();
	}
	ArrayList<Transaction>transactionList = new ArrayList<Transaction>();
}

