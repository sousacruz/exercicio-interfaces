package com.github.sousacruz.domain;

/**
 * Class for representing an adventure of the hero. The constructors of this class
 * assume that the adventures can be instantiate with or without data. 
 * 
 * Adventures objects serves to represents user input data for hero adventures.
 *
 * @author Herbert Cruz
 */
public class Adventure implements Comparable<Adventure>{

	private static final int X = 0;
	private static final int Y = 1;
	
	private final int[] initialCoordinates;
	private final String movements;
		
	public Adventure() {
		this(null, null);
	}
	
	public Adventure(int[] initialCoordinates, String movements) {
		this.initialCoordinates = initialCoordinates;
		this.movements = movements;
	}
	
	public String getMovements() {
		return movements;
	}
	
	public int[] getInitialCoordinates() {
		return initialCoordinates;
	}

	@Override
	public int compareTo(Adventure anotherAdventure) {
		String thisObj = String.format("%d.%d", initialCoordinates[X], initialCoordinates[Y])
				.concat(this.movements);

		int[] thatCoordinates = anotherAdventure.getInitialCoordinates();
		
		String thatObj = String.format("%d.%d", thatCoordinates[X], thatCoordinates[Y])
				.concat(anotherAdventure.getMovements());
		
		return thisObj.compareTo(thatObj);
	}
}
