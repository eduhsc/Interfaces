package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.entities.Employee;

public class Program3 {

	public static void main(String[] args) {
		
		List<Employee> list = new ArrayList<>();
		String path = "c:\\temp\\in.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String employeeCsv = br.readLine();
			while (employeeCsv != null) {
				String[] fields = employeeCsv.split(",");
				list.add(new Employee(fields[0], Double.parseDouble(fields[1])));
				Collections.sort(list);
				employeeCsv = br.readLine();
			}
			
			for (Employee c: list) {
				System.out.println(c.getName()+ " - " + c.getSalary());
			}
		}
		catch (IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}

}
