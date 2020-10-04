package com.github.sousacruz;

public class Hero {

	private String currentPosition;
	private Card card;
	private int x;
	private int y;
	
	Hero() {}
	
	Hero(String currentPosition) {
		this.setCurrentPosition(currentPosition);
	}
	
	Hero(String currentPosition, Card card) {
		this(currentPosition);
		this.card = card;
	}

	public String getCurrentPosition() {
		this.currentPosition = this.x + "." + this.y;
		return currentPosition;
	}
	
	public void setCurrentPosition(String currentPosition) {
		if (!currentPosition.contains(".")) {
			throw new RuntimeException("Invalid coordinates");
		}
		
		String[] coordinates = currentPosition.split("\\.");
		this.x = Integer.parseInt(coordinates[0]);
		this.y = Integer.parseInt(coordinates[1]);

		this.currentPosition = currentPosition;
	}


	/**
	 * Movement of the hero is defined as a succession of characters representing
	 * the cardinal directions (N, S, E and O, note that O accounts for West).
	 * 
	 */
	public void moveTo(String movements) {
		int coordinateX = this.x;
		int coordinateY = this.y;
		
		for (int i=0; i<movements.length(); i++) {
			if (movements.charAt(i) == 'N') {
				coordinateY--;
				this.checkMovement(coordinateX, coordinateY);
			} else if (movements.charAt(i) == 'S') {
				coordinateY++;
				this.checkMovement(coordinateX, coordinateY);
			} else if (movements.charAt(i) == 'E') {
				coordinateX++;
				this.checkMovement(coordinateX, coordinateY);
			} else if (movements.charAt(i) == 'O') {
				coordinateX--;
				this.checkMovement(coordinateX, coordinateY);
			} else {
				throw new RuntimeException("Invalid movement direction!");
			}
		}
		this.setCurrentPosition(coordinateX + "." + coordinateY); 
	}

	private void checkMovement(int coordinateX, int coordinateY) {
		String position = coordinateX + "." + coordinateY; 
		if (!this.card.canMoveTo(position)) {
			throw new RuntimeException("The hero cannot go on the squares occupied by the impenetrable woods. # symbol has found at position ("+position+")");	
		}
	}

}

