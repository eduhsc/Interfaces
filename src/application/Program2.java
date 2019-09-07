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

public class Program2 {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		String path = "c:\\temp\\in.txt";
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String name = br.readLine();
			while (name != null) {
				list.add(name);
				Collections.sort(list);
				name = br.readLine();
			}
			
			for (String c: list) {
				System.out.println(c);
			}
		}
		catch (IOException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}

}
