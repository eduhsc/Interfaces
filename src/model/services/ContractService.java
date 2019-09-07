package model.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	private List<Installment> list = new ArrayList();
	//private Installment;
	private OnlinePaymentService ps;
	
	public ContractService() {
		
	}
	
	public ContractService(OnlinePaymentService ps) {
		this.ps = ps;
	}


	public void processContract(Contract contract, int months) {
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		//Setando a data do contrato
		cal.setTime(contract.getDate());
		int day = cal.get(Calendar.DAY_OF_MONTH); //Obtendo o dia da data de contrato
	  
		//System.out.println("Data contrato: " + sdf.format(dataContrato));
		double valorSemTax = contract.getTotalValue()/months;
		for (int i=1;i<=months;i++) {
			cal.add(Calendar.MONTH, 1);
			int month = cal.get(Calendar.MONTH);
			if ((month+1 != 2) && (cal.get(Calendar.DAY_OF_MONTH) != day)) {
				cal.set(Calendar.DAY_OF_MONTH, day);
			}
			//Setando a data vencimento da fatura
			//installment.setDueDate(cal.getTime());
			//Calculando valor da fatura
			double valorComTax = valorSemTax;
			double taxInterest = ps.interest(valorSemTax, i);
			valorComTax += taxInterest;
			double taxPayFee = ps.paymentFee(valorComTax);
			valorComTax += taxPayFee;
			Installment installment = new Installment(cal.getTime(), valorComTax);
			//installment.setAmount(valorComTax);
			list.add(installment);
		}
		contract.setInstallments(list);

	}
}
