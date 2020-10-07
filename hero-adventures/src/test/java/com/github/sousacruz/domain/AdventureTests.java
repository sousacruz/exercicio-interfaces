package com.github.sousacruz.domain;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.sousacruz.util.Adventures;

/**
 * Tests static method from Adventures class.
 * 
 * @author herbert.cruz
 *
 */
class AdventureTests {

	@Test
	void shouldHaveTwoAdventures() throws IOException {
		String fileWithTwoAdventures = "c:/tmp/input.txt";
		List<Adventure> adventures = Adventures.loadAventures(fileWithTwoAdventures);
		Assertions.assertEquals(2, adventures.size());
	}
	
	
	@Test
	void shouldConvertStringToIntegerCoordinatesArray() {
		String position = "9.2";
		int[] coordinates = Adventures.getCoordinatesFromString(position);
		Assertions.assertEquals(9, coordinates[0]);
		Assertions.assertEquals(2, coordinates[1]);
	}
	
	@Test
	void shouldConvertIntegerCoordinatesArrayToString() {
		int[] coordinates = {9, 2};
		String position = Adventures.coordinatesToString(coordinates);
		Assertions.assertEquals("9.2", position);
	}
	
}
