package com.github.sousacruz.domain;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.sousacruz.exception.BeyondTheEdgesException;
import com.github.sousacruz.exception.CardNoDataFoundException;

/**
 * Tests rules and overriding data load of class Card.
 * 
 * @author herbert.cruz
 *
 */
class CardTests {

	@Test
	void cardMustBeReplacedWithNewDataAfterMultipleLoads() throws IOException {
		Card cardToLoad = new Card();
		Assertions.assertNull(cardToLoad.getCard());

		String fileWithSizeOf400 = "c:/tmp/card-data-size-20x20.txt";
		cardToLoad.loadData(fileWithSizeOf400);
		Assertions.assertEquals(20, cardToLoad.getCard().length);

		String fileWithSizeOf300 = "c:/tmp/card-data-size-30x10.txt";
		cardToLoad.loadData(fileWithSizeOf300);
		Assertions.assertEquals(10, cardToLoad.getCard().length);
	}
	
	
	@Test
	void positionShouldBeInsideTheEdges() {
		Assertions.assertThrows(BeyondTheEdgesException.class, () -> {
			String cardFile = "c:/tmp/card.txt";
			Card card = new Card(cardFile);
			card.canMoveTo(21, 28);
		});
	}
	
	@Test
	void cannotMoveWithoutMap() {
		Assertions.assertThrows(CardNoDataFoundException.class, () -> {
			Card cardToLoad = new Card();
			cardToLoad.canMoveTo(3, 0);
		});
	}
	
}
