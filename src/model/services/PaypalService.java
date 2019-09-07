package model.services;

public class PaypalService implements OnlinePaymentService {

	public double interest(double amount, int months) {
		
		return (amount * months)/100;
	}
	
	public double paymentFee(double amount) {
		double tax = 2.00;
		return (amount * tax)/100;
	}
}
