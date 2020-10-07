package com.github.sousacruz.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.github.sousacruz.exception.BeyondTheEdgesException;
import com.github.sousacruz.exception.CardNoDataFoundException;

/**
 * Class for representing a map. The constructors of this class assume that the
 * map can be instantiate with or without data.
 *
 * @author Herbert Cruz
 */
public class Card {
	
	private final int CARRIAGE_RETURN = 13;
	private final int LINE_FEED = 10;
	
	private Map<String,Boolean> card;
	
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
		if (Objects.nonNull(dataFile)) {
			try {
				this.loadData(dataFile);
			} catch (IOException e) {
				// TODO At this moment there is no specification about writing exceptions on stdout.
			}
		}
	}
	
	public Map<String, Boolean> getCard() {
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
		this.card = new HashMap<String,Boolean>();
		
		if (Objects.nonNull(dataFile)) {
			
			try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
				int codePoint;
				int coordinateX = 0;
				int coordinateY = 0;
				String specificPosition;
				
				while ((codePoint = reader.read()) >= 0) {
					
					if (codePoint != CARRIAGE_RETURN && codePoint != LINE_FEED) {
						specificPosition = coordinateX + "." + coordinateY;						
						// Now we put map text data into our HashMap,  
						// indicating through which one the hero can move
						this.card.put(specificPosition, Character.isWhitespace(codePoint));
						coordinateX++;
						
					} else if (codePoint == CARRIAGE_RETURN) {
						
						// For each line, we must restart X counter and increment Y counter
						// in order to represent our map correctly
						coordinateX=0;
						coordinateY++;
					}
				}
				
			} catch (UnsupportedEncodingException e) {
				throw new UnsupportedEncodingException("The map text file must be in UTF-8 format. "+e.getMessage());
			}

		}


	}

	/**
	 * Verify movements on the map. The verification occurs by checking 
	 * the whether informed position corresponds to a box where the hero
	 * can move. 
	 * 
	 * @param position	the coordinates to be validated against the map
	 * 
	 * @return true 	when the informed position corresponds a space
	 * 					character on the map
	 * 
	 * @exception CardNoDataFoundException	if the verification has 
	 * 					called when card data was not loaded yet.
	 * 
	 * @exception BeyondTheEdgesException 	if the informed position
	 * 					does not match with coordinates inside the map.
	 */
	public boolean canMoveTo(String position) {
		if (Objects.isNull(this.card)) {
			throw new CardNoDataFoundException("This card have no data yet!");
		}
		Boolean isValid = this.card.get(position);
		
		if (isValid != null) {
			return isValid.booleanValue();
		} else {
			throw new BeyondTheEdgesException(position);
		}
	}
	
}
