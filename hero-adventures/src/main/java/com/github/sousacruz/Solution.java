package com.github.sousacruz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Solution {

	private static final Card card = new Card("c:/tmp/card.txt"); 
	private static final Logger log = LoggerFactory.getLogger(Solution.class);
	
    public static void main(String[] args) throws FileNotFoundException {
    	
    	if (args.length == 0) {
    		throw new RuntimeException("No input file was informed!");
    	}
    	
    	String inputFile = args[0];
    	if (inputFile.isEmpty())  {
    	}
    	
		File file = new File(inputFile);
		Scanner scanner = new Scanner(file);
		
		String initialPosition;
		String movements;
		
		// Now we iterate through the file line by line, 
		// for each pair we have a hero adventure 
		while(scanner.hasNextLine()){
			initialPosition = scanner.nextLine();
			if (initialPosition.isEmpty()) {
				log.info("Invalid entry! You must indicate the initial position of the Hero at first chunk of data.");
			}
			
			movements = scanner.nextLine();
			if (movements.isEmpty())  {
				log.info("Invalid entry! You must indicate the movements of the Hero at second chunk of data.");
			}
			try {
				Hero hero = new Hero(initialPosition, card);
				hero.moveTo(movements);
				System.out.println("The hero must finish at ("+hero.getCurrentPosition()+")");
			} catch (RuntimeException e) {
				log.error(e.getMessage());
			}
		}
		scanner.close();
    	
    }
}
