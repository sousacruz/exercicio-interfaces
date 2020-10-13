package com.github.sousacruz.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;

import com.github.sousacruz.exception.BeyondTheEdgesException;
import com.github.sousacruz.exception.CardNoDataFoundException;
import com.github.sousacruz.util.Adventures;

/**
 * Class for representing a map. The constructors of this class assume that the
 * map can be instantiate with or without data.
 *
 * @author Herbert Cruz
 */
public class Card {
	
	private char[][] card;
	
    /**
     * Creates a card that have no data yet.
     *
     * <p> You can load data lately using
     * <code>{@link Card#loadData(String) loadData}</code> method.
     * 
     */
	public Card() {
		this(null);
	}
	
    /**
     * Creates a card with a initial data.
     *
     * <p> You can load data lately using
     * 
     * @param  dataFile	the full name of a text file in UTF-8 format with card data 
     *
     * @exception  IOException  if its have any problem to access {@code dataFile}
     */
	public Card(String dataFile) {
		if (dataFile != null) {
			try {
				this.loadData(dataFile);
			} catch (IOException e) {
				// TODO At this moment there is no specification about writing exceptions on stdout.
			}
		}
	}
	
	public char[][] getCard() {
		return this.card;
	}
	
    /**
     * Loads a card data from a informed text file.
     *
     * @param  dataFile	the full name of a text file in UTF-8 format with card data 
     *
     * @exception  IOException  if occurs any problem trying to read the {@code dataFile}
     * 
     * @exception  UnsupportedEncodingException  if informed {@code dataFile} does not
     * 					match to the expected format UTF-8.
     */
	public void loadData(String dataFile) throws IOException {
		
		if (dataFile != null) {
			
			try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(dataFile))) {
				lineNumberReader.skip(Long.MAX_VALUE);
				int lines = lineNumberReader.getLineNumber() + 1;
				this.card = new char[lines][];
			}

			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				String line;
				int coordinateY = 0;
				while ((line = reader.readLine()) != null) {
					this.card[coordinateY++] = line.toCharArray();
				}
			}
		}
	}

	/**
	 * Verify movements on the map. The verification occurs by checking 
	 * the whether informed position corresponds to a box where the hero
	 * can move. 
	 * 
	 * @param coordinateX  the coordinate X to be validated against the map
	 * 
   	 * @param coordinateY  the coordinate Y to be validated against the map
   	 * 
	 * @return true 	when the informed position corresponds a space
	 * 					character on the map
	 * 
	 * @exception CardNoDataFoundException	if the verification has 
	 * 					called when card data was not loaded yet.
	 */
	public boolean canMoveTo(int coordinateX, int coordinateY) {
		if (this.card == null) {
			throw new CardNoDataFoundException("This card have no data yet!");
		}
		
		if ((coordinateY > this.card.length) || (coordinateX > this.card[coordinateY].length)) {
			throw new BeyondTheEdgesException(String.format("%d.%d", coordinateX, coordinateY));
		}
		return this.card[coordinateY][coordinateX] != Adventures.IMPENETRABLE_WOODS;
	}
}
