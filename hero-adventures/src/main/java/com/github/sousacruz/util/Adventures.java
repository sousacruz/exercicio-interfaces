package com.github.sousacruz.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.github.sousacruz.domain.Adventure;

public class Adventures {
	
	public static final int X = 0;
	public static final int Y = 1;	
	
	public static List<Adventure> loadAventures(String adventureFile) throws FileNotFoundException {
		List<Adventure> adventures = new ArrayList<>();
		
		File file = new File(adventureFile);
		Scanner scanner = new Scanner(file);
		
		String initialPosition;
		String movements;
		
		// Now we iterate through the file line by line, 
		// for each pair we have a hero adventure 
		while(scanner.hasNextLine()){

			initialPosition = scanner.nextLine();
			movements = scanner.nextLine();
			
			int[] coordinates = getCoordinatesFromString(initialPosition);

			adventures.add(new Adventure(coordinates, movements));
				
		}
		scanner.close();
		
		return adventures;
	}
    
	public static int[] getCoordinatesFromString(String position) {
		int[] coordinates = new int[2];
		
		if (Optional.of(position).isPresent()) {
			if (position.contains(".")) {
				String[] xy = position.split("\\.");
				if (xy.length == 2) {
					
					try {
						coordinates[X] = Integer.parseInt(xy[X]);
						coordinates[Y] = Integer.parseInt(xy[Y]);
					} catch (NumberFormatException e) {
						throw new RuntimeException("Invalid coordinates");
					}
					return coordinates;
				}
			}
		}
		
		throw new RuntimeException("Invalid coordinates");
	}
	
	/**
	 * Formats the current position of hero to a String.
	 * 
	 * @return The current coordinates x,y into a String, format, e.g, "3.0".
	 */
	public static String coordinatesToString(int[] coordinates) {
		String position = "0.0";
		if (coordinates.length == 2) {
			position = String.format("%d.%d", coordinates[X], coordinates[Y]);
		}
		return position;
	}
	
}
