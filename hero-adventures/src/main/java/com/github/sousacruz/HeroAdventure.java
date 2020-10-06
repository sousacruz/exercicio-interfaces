package com.github.sousacruz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sousacruz.domain.Card;
import com.github.sousacruz.domain.Hero;
import com.github.sousacruz.exception.UnsupportedDisplacement;

public class HeroAdventure {

	private static final Card card = new Card("c:/tmp/card.txt"); 
	private static final Logger log = LoggerFactory.getLogger(HeroAdventure.class);
	
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
			movements = scanner.nextLine();
			
			try {
				int[] coordinates = getCoordinatesFromString(initialPosition);

				Hero hero = new Hero(coordinates, card);
				hero.moveTo(movements);
				
				System.out.println("The hero must finish at ("+hero.coordinatesToString()+")");
			
			} catch (RuntimeException | UnsupportedDisplacement e) {
				log.error(e.getMessage());
			}
		}
		scanner.close();
    	
    }
    
	private static int[] getCoordinatesFromString(String position) {
		int[] coordinates = new int[2];
		
		if (Optional.of(position).isPresent()) {
			if (position.contains(".")) {
				String[] xy = position.split("\\.");
				if (xy.length == 2) {
					
					try {
						coordinates[0] = Integer.parseInt(xy[0]);
						coordinates[1] = Integer.parseInt(xy[1]);
					} catch (NumberFormatException e) {
						throw new RuntimeException("Invalid coordinates");
					}
					return coordinates;
				}
			}
		}
		
		throw new RuntimeException("Invalid coordinates");
	}
}
