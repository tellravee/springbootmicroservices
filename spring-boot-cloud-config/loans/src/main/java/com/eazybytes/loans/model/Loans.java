package com.eazybytes.loans.model;

import javax.persistence.Column;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Columns;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Loans {
	@Column(name="customer_id" )
	private int customerId;
	@Override
	public String toString() {
		return "Loans [customerId=" + customerId + ", startDt=" + startDt + ", loan_type=" + loan_type + ", total_loan="
				+ total_loan + ", amountPaid=" + amountPaid + ", outstandingAmount=" + outstandingAmount + ", createDt="
				+ createDt + ", loanNumber=" + loanNumber + "]";
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public LocalDate getStartDt() {
		return startDt;
	}
	public void setStartDt(LocalDate startDt) {
		this.startDt = startDt;
	}
	public String getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}
	public int getTotal_loan() {
		return total_loan;
	}
	public void setTotal_loan(int total_loan) {
		this.total_loan = total_loan;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public int getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(int outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public LocalDate getCreateDt() {
		return createDt;
	}
	public void setCreateDt(LocalDate createDt) {
		this.createDt = createDt;
	}
	public int getLoanNumber() {
		return loanNumber;
	}
	public void setLoanNumber(int loanNumber) {
		this.loanNumber = loanNumber;
	}
	@Column(name="start_dt")
	private LocalDate startDt;
	@Column(name="loan_type")
	private String loan_type;
	@Column(name="totalLoan")
	private int total_loan;
	@Column(name="amount_paid")
	private int amountPaid;
	@Column(name="outstanding_amount")
	private int outstandingAmount;
	@Column(name="create_dt")
	private LocalDate createDt;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="loan_number")
	private int loanNumber;
}
