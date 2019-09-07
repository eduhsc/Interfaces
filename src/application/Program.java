package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter contract data");		
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.next());
		System.out.print("Contract Value: ");
		double value = sc.nextDouble();
		System.out.print("Enter number of installments: ");
		int numberInstallments = sc.nextInt();
		
		PaypalService ps = new PaypalService();
		Contract contract = new Contract(number, date, value);
		ContractService cs = new ContractService(ps);
		cs.processContract(contract, numberInstallments);
		System.out.println();
		System.out.println("Installments:");
	    
		for (Installment l: contract.getInstallments()) {
			System.out.println(sdf.format(l.getDueDate())+" - "+ String.format("%.2f",l.getAmount()));
		}
		
		/*		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		Date dataContrato = sdf.parse("30/01/2019");
		cal.setTime(dataContrato);
		int day = cal.get(Calendar.DAY_OF_MONTH);
	  
		System.out.println("Data contrato: " + sdf.format(dataContrato));
		List<Date> list = new ArrayList<>();
		for (int i=1;i<=5;i++) {
			cal.add(Calendar.MONTH, 1);
			int month = cal.get(Calendar.MONTH);
			if ((month+1 != 2) && (cal.get(Calendar.DAY_OF_MONTH) != day)) {
				cal.set(Calendar.DAY_OF_MONTH, day);
			}
			list.add(cal.getTime());
		}
		int i=1;
		for (Date x:list) {
			System.out.println("Boleto #"+i+": "+ sdf.format(x));
			i++;
		}
*/
		sc.close();
	}

}
