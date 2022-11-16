package com.eazybytes.accounts.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDetails {
	private Accounts accounts;
	@Override
	public String toString() {
		return "CustomerDetails [accounts=" + accounts + ", loans=" + loans + ", cards=" + cards + "]";
	}
	public Accounts getAccounts() {
		return accounts;
	}
	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}
	public List<Loans> getLoans() {
		return loans;
	}
	public void setLoans(List<Loans> loans) {
		this.loans = loans;
	}
	public List<Cards> getCards() {
		return cards;
	}
	public void setCards(List<Cards> cards) {
		this.cards = cards;
	}
	private List<Loans> loans;
	private List<Cards> cards;
}
