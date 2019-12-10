package pkgLogic;

import java.time.LocalDate;

public class Payment {
	
	private int PaymentNbr;
	private LocalDate DueDate;
	private double Payment;
	private double AdditionalPayment;
	private double InterestPayment;
	private double Principle;
	private double EndingBalance;
	
	public Payment(double beginningBalance, int paymentNbr, LocalDate dueDate, Loan loan) {
		this.PaymentNbr = paymentNbr;
		this.DueDate = dueDate;
		this.Payment = (beginningBalance > loan.GetPMT())? loan.GetPMT() 
				:(beginningBalance + (beginningBalance*(loan.getInterestRate()/12)));
		this.AdditionalPayment = loan.getAdditionalPayment();
		
		this.InterestPayment = beginningBalance * (loan.getInterestRate()/12);
		
		this.Principle = loan.GetPMT() + loan.getAdditionalPayment() - InterestPayment;
		
		this.EndingBalance = beginningBalance - Principle;
	}
	
	private double getPrinciplePayment() {
		return Principle;
	}

	public int getPaymentNbr() {
		return PaymentNbr;
	}

	public LocalDate getDueDate() {
		return DueDate;
	}
	
	public double getPayment() {
		return Payment;
	}

	public double getAdditionalPayment() {
		return AdditionalPayment;
	}

	public double getInterestPayment() {
		return InterestPayment;
	}
	
	public double getEndingBalance() {
		return EndingBalance;
	}

	
	
}
