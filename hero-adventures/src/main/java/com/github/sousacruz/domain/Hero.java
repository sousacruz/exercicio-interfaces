package com.github.sousacruz.domain;

import com.github.sousacruz.exception.ImpenetrableWoodsException;
import com.github.sousacruz.exception.UnsupportedDisplacement;

/**
 * Convenience class for reading character files.  The constructors of this
 * class assume that the default character encoding and the default byte-buffer
 * size are appropriate.  To specify these values yourself, construct an
 * InputStreamReader on a FileInputStream.
 *
 * <p><code>FileReader</code> is meant for reading streams of characters.
 * For reading streams of raw bytes, consider using a
 * <code>FileInputStream</code>.
 *
 *
 * @author      Herbert Cruz
 */
public class Hero {
	
	private static final int X = 0;
	private static final int Y = 1;

	private final Card card;
	private int[] coordinates;
	
	public Hero() {
		this(null, null);
	}
	
	public Hero(int[] coordinates, Card card) {
		this.coordinates = coordinates;
		this.card = card;
	}

	public int[] getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Formats the current position of hero to a String.
	 * 
	 * @return 	The current coordinates x,y into a String,
	 * 			format, e.g, "3.0".
	 */
	public String coordinatesToString() {
		String position = "0.0";
		if (coordinates.length == 2) {
			position = String.format("%d.%d", coordinates[X], coordinates[Y]);
		}
		return position;
	}
	
	/**
	 * Supports the interaction with map elements by reproducing the movements
	 * of the hero on a map. Remember, each character corresponds to the 
	 * displacement of a box.
	 * 
	 * @param movements	A succession of characters representing the cardinal 
	 *					directions (N, S, E and O)
	 * 
	 * @throws UnsupportedDisplacement	If any character in the <code>movements</code>
	 *					String does not corresponding to a valid cardinal direction.
	 */
	public void moveTo(String movements) throws UnsupportedDisplacement {
		
		int coordinateX = coordinates[X];
		int coordinateY = coordinates[Y];
		
		for (Character movement : movements.toCharArray()) {
			
			switch (movement) {
			case 'N':
				coordinateY--;
				this.checkMovement(coordinateX, coordinateY);
				break;
				
			case 'S':
				coordinateY++;
				this.checkMovement(coordinateX, coordinateY);
				break;
				
			case 'E':
				coordinateX++;
				this.checkMovement(coordinateX, coordinateY);
				break;
				
			case 'O':
				coordinateX--;
				this.checkMovement(coordinateX, coordinateY);
				break;
				
			default:
				throw new UnsupportedDisplacement(movement);
			}
			
		}
		
	}

	/**
	 * Supports the interaction with map elements by checking the next movement
	 * of the hero against the current map. Remember, the hero cannot go on the
	 * squares occupied by the impenetrable woods.
	 * 
	 * @param coordinateX
	 * @param coordinateY
	 * 
	 * @throws ImpenetrableWoodsException If {@code coordinates x,y leads to impenetrable woods position }
	 */
	private void checkMovement(int coordinateX, int coordinateY) throws ImpenetrableWoodsException {

		String position = String.format("%d.%d", coordinateX, coordinateY); 
		
		if (this.card.canMoveTo(position)) {
			coordinates[X] = coordinateX;
			coordinates[Y] = coordinateY;
		} else {
			throw new ImpenetrableWoodsException(position);	
		}
	}

}

