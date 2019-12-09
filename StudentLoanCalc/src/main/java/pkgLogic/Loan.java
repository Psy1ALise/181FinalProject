package pkgLogic;

import java.util.ArrayList;
import org.apache.poi.ss.formula.functions.FinanceLib;
import java.time.LocalDate;

public class Loan {
	private LocalDate startDate;
	private double LoanAmount;
	private double LoanBalanceEnd;
	private double InterestRate;
	private int Term;
	private double AdditionalPayment;
	private boolean bCompoundingOption;
	
	private ArrayList<Payment> loanPayments = new ArrayList<Payment>();
	
	public Loan(LocalDate startDate, double loanAmount, double interestRate, double additionalPayment, int term) {
		this.startDate = startDate;
		this.LoanAmount = loanAmount;
		this.InterestRate = interestRate;
		this.AdditionalPayment = additionalPayment;
		this.Term = term;
		this.bCompoundingOption = false;
		this.LoanBalanceEnd = 0;
		
		double RemainingBalance = LoanAmount;
		int PaymentCnt = 1;
		
		while(RemainingBalance >= this.GetPMT()) {
			Payment payment = new Payment(RemainingBalance,PaymentCnt++,startDate.plusMonths(1),this);
			RemainingBalance = payment.getEndingBalance();
			loanPayments.add(payment);
		}
	}
	
	public LocalDate getstartDate() {
		return startDate;
	}
	
	public double GetPMT() {
		return Math.abs(FinanceLib.pmt(this.getInterestRate()/12,this.Term,this.LoanAmount,this.LoanBalanceEnd,this.bCompoundingOption));
	}
	
	public boolean isbCompoundingOption() {
		return bCompoundingOption;
	}
	
	public double getInterestRate() {
		return InterestRate;
	}

	public double getAdditionalPayment() {
		return AdditionalPayment;
	}
}
