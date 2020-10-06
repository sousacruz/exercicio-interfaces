package com.github.sousacruz.domain;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.sousacruz.exception.BeyondTheEdgesException;
import com.github.sousacruz.exception.CardNoDataFoundException;

class CardTests {

	private final Card card = new Card("c:/tmp/card.txt");

	@Test
	void cardMustBeReplacedWithNewDataAfterMultipleLoads() throws IOException {
		Card cardToLoad = new Card();
		Assertions.assertNull(cardToLoad.getCard());

		String fileWithSizeOf400 = "c:/tmp/card-data-size-20x20.txt";
		cardToLoad.loadData(fileWithSizeOf400);
		Assertions.assertEquals(400, cardToLoad.getCard().size());

		String fileWithSizeOf300 = "c:/tmp/card-data-size-30x10.txt";
		cardToLoad.loadData(fileWithSizeOf300);
		Assertions.assertEquals(300, cardToLoad.getCard().size());
	}
	
	
	@Test
	void positionShouldBeInsideTheEdges() {
		Assertions.assertThrows(BeyondTheEdgesException.class, () -> {
			String invalidPosition = "21.28"; // Beyond the edges
			card.canMoveTo(invalidPosition);
		});
	}
	
	@Test
	void cannotMoveWithoutMap() {
		Assertions.assertThrows(CardNoDataFoundException.class, () -> {
			Card cardToLoad = new Card();
			String position = "3.0"; 
			cardToLoad.canMoveTo(position);
		});
	}
	
}
