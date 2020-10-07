package com.github.sousacruz.domain;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.github.sousacruz.exception.ImpenetrableWoodsException;
import com.github.sousacruz.exception.UnsupportedDisplacement;
import com.github.sousacruz.util.Adventures;

/**
 * Tests rules from class Hero Exceptions 
 * 
 * @author herbert.cruz
 *
 */
class HeroTests {

	private static final String cardFile = "c:/tmp/card.txt";
	private static final String inputFile = "c:/tmp/input.txt";
	
	private static Card card;
	private static Hero hero;
	
	@BeforeAll
	private static void setup() throws FileNotFoundException {
		List<Adventure> adventures = Adventures.loadAventures(inputFile);
		card = new Card(cardFile);
		hero = new Hero(card, adventures);
	}
	
	@Test
	void heroCannotGoOnTheSquaresOccupiedByTheImpenetrableWoods() {
		Assertions.assertThrows(ImpenetrableWoodsException.class, () -> {
			int[] coordinates = {3, 0}; 
			Hero anotherHero = new Hero(card);
			anotherHero.setCoordinates(coordinates);
			String movements = "SO"; // That leads to an impenetrable woods
			anotherHero.moveTo(movements);
		});
	}

	@Test
	void movementsShouldBeAValidCardinalRepresentation() {
		Assertions.assertThrows(UnsupportedDisplacement.class, () -> {
			int[] coordinates = {3, 0}; 
			Hero anotherHero = new Hero(card);
			anotherHero.setCoordinates(coordinates);
			String movements = "WWW"; // West is represented by 'O' instead
			anotherHero.moveTo(movements);
		});
	}
	
	@Test
	void mustFinishAtCoordinates92() throws UnsupportedDisplacement {
		String result;

		result = hero.faceNextAdventure();
		Assertions.assertEquals("The hero must finish at (9.2)", result);

		result = hero.faceNextAdventure();
		Assertions.assertEquals("The hero must finish at (16.19)", result);
	}

	
}
