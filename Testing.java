package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Testing {
	public static void main(String[] args) {
		try {
			Scanner read = new Scanner(new File("foxandcat.txt"));
			String file;
			
			// Reading the file
			for(int i = 0; i < 16; i++) {
				file = read.nextLine();
				System.out.println(file);
			}
			// Closing the Scanner class
			read.close();
		}
		
		catch(FileNotFoundException ex){
			System.out.println("foxandcat.txt not found! " + ex);
		}
	}	
}
