package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Testing {

	public static void main(String[] args) {
		/**
		 * This is for me to test something. It might not be what we need <Again, I was testing>
		 */
		try {
			Scanner read = new Scanner(new File("foxandcat.txt"));
			String file;
			//String arr[] = new String[16];
			
			for(int i = 0; i < 16; i++) {
				file = read.nextLine();
				
				//arr[i] = file;
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
