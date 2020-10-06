package com.github.sousacruz.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.sousacruz.exception.ImpenetrableWoodsException;
import com.github.sousacruz.exception.UnsupportedDisplacement;

class HeroTests {

	private final Card card = new Card("c:/tmp/card.txt");

	@Test
	void heroCannotGoOnTheSquaresOccupiedByTheImpenetrableWoods() {
		Assertions.assertThrows(ImpenetrableWoodsException.class, () -> {
			int[] coordinates = {3, 0}; 
			Hero hero = new Hero(coordinates, card);
			String movements = "SO"; // That leads to an impenetrable woods
			hero.moveTo(movements);
		});
	}

	@Test
	void movementsShouldBeAValidCardinalRepresentation() {
		Assertions.assertThrows(UnsupportedDisplacement.class, () -> {
			int[] coordinates = {3, 0}; 
			Hero hero = new Hero(coordinates, card);
			String movements = "WWW"; // West is represented by 'O' instead
			hero.moveTo(movements);
		});
	}

	
}
