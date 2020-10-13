package com.github.sousacruz.domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.github.sousacruz.exception.ImpenetrableWoodsException;
import com.github.sousacruz.exception.UnsupportedDisplacement;

/**
 * Class for representing a hero. The constructors of this class assume that the
 * hero can be instantiate with or without an adventure setup.
 *
 * @author Herbert Cruz
 */
public class Hero {

	private static final int X = 0;
	private static final int Y = 1;

	private final Card card;
	private final List<Adventure> adventures;
	private Iterator<Adventure> iterAdventures;

	private int[] coordinates;

	public Hero() {
		this(null, null);
	}
	
	public Hero(Card card) {
		this(card, null);
	}

	public Hero(Card card, List<Adventure> adventures) {
		this.card = card;
		this.adventures = adventures;
		if (this.adventures != null) {
			this.iterAdventures = adventures.iterator();
		}
	}

	public List<Adventure> getAdventures() {
		return Collections.unmodifiableList(adventures);
	}

	public int[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	public boolean hasAdventure() {
		if (iterAdventures != null) {
			return iterAdventures.hasNext();
		} else {
			return false;
		}
	}

	/**
	 * A hero has one o more adventures to face, according to user input
	 * file. This method will carry the hero through the map moving his
	 * position until the finish point.
	 * 
	 * @return String  that represents the expected result for a journey
	 * 
	 * @throws UnsupportedDisplacement  if the movements String has any
	 * 				invalid cardinal directions character.
	 */
	public String faceNextAdventure() throws UnsupportedDisplacement {

		if ((iterAdventures != null) && (iterAdventures.hasNext())) {
			Adventure adventure = iterAdventures.next();
			this.setCoordinates(adventure.getInitialCoordinates());
			this.moveTo(adventure.getMovements());

			return String.format("The hero must finish at (%s)", this.coordinatesToString());
		}

		return null;
	}

	/**
	 * Supports the interaction with map elements by reproducing the movements of
	 * the hero on a map. Remember, each character corresponds to the displacement
	 * of a box.
	 * 
	 * @param movements A succession of characters representing the cardinal
	 *                  directions (N, S, E and O)
	 * 
	 * @throws UnsupportedDisplacement If any character in the
	 *                                 <code>movements</code> String does not
	 *                                 corresponding to a valid cardinal direction.
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
	 * Formats the current position of hero to a String.
	 * 
	 * @return The current coordinates x,y into a String, format, e.g, "3.0".
	 */
	private String coordinatesToString() {
		String position = "0.0";
		if (coordinates.length == 2) {
			position = String.format("%d.%d", coordinates[X], coordinates[Y]);
		}
		return position;
	}

	/**
	 * Supports the interaction with map elements by checking the next movement of
	 * the hero against the current map. Remember, the hero cannot go on the squares
	 * occupied by the impenetrable woods.
	 * 
	 * @param coordinateX
	 * @param coordinateY
	 * 
	 * @throws ImpenetrableWoodsException If
	 *                   {@code coordinates x,y leads to impenetrable woods position}
	 */
	private void checkMovement(int coordinateX, int coordinateY) throws ImpenetrableWoodsException {

		if (this.card.canMoveTo(coordinateX, coordinateY)) {
			coordinates[X] = coordinateX;
			coordinates[Y] = coordinateY;
		} else {
			throw new ImpenetrableWoodsException(String.format("%d.%d", coordinateX, coordinateY));
		}
	}

}
