package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pkgLogic.Loan;
import pkgLogic.Payment;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField AdditionalPayment;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private Label lblTotalInterest;
	
	@FXML 
	private Label lblRatePerMonth;
	
	@FXML
	private TableView<Payment> tvResults;
	
	@FXML
	private TableColumn<Payment, Integer> colPaymentNumber;

	@FXML
	private TableColumn<Payment, Double> colPayment;
	
	@FXML
	private TableColumn<Payment, Double> colInterest;
	
	@FXML
	private TableColumn<Payment, LocalDate> colDate;
	
	@FXML
	private TableColumn<Payment, Double> colAdditionalPMT;
	
	@FXML
	private TableColumn<Payment, Double> colPrinciple;
	
	@FXML
	private TableColumn<Payment, Double> colBalance;
	

	
	private ObservableList<Payment> paymentList = FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colPaymentNumber.setCellValueFactory(new PropertyValueFactory<>("paymentNbr"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
		colPayment.setCellValueFactory(new PropertyValueFactory<>("Payment"));
		colInterest.setCellValueFactory(new PropertyValueFactory<>("Interest"));
		colAdditionalPMT.setCellValueFactory(new PropertyValueFactory<>("AdditionalPayment"));
		colPrinciple.setCellValueFactory(new PropertyValueFactory<>("Principle"));
		colBalance.setCellValueFactory(new PropertyValueFactory<>("Balance"));
		tvResults.setItems(paymentList);
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());	
		double interestRate = Double.parseDouble(InterestRate.getText());
		int term = Integer.parseInt(NbrOfYears.getText());
		double additionalPayment = Double.parseDouble(AdditionalPayment.getText());
		LocalDate Date = PaymentStartDate.getValue();
		boolean fft = true;
		double futureValue = 0;
		Loan myloan = new Loan(Date, dLoanAmount, interestRate, additionalPayment, term);
		
		lblTotalPayments.setText(myloan.getTotalPayment());	
		lblTotalInterest.setText(myloan.getTotalInterest());
		tvResults.setItems(paymentList);
		paymentList.addAll();
	
		
	}
	
}
