package com.github.sousacruz.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.github.sousacruz.exception.BeyondTheEdgesException;
import com.github.sousacruz.exception.CardNoDataFoundException;

/**
 * 
 * @author herbert.cruz
 *
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
     * @exception  IOException  If {@code dataFile == null}
     */
	public Card(String dataFile) {
		try {
			this.loadData(dataFile);
		} catch (IOException e) {
			e.printStackTrace();
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
     * @exception  IOException  If {@code dataFile == null}
     * @exception  UnsupportedEncodingException  If {@code dataFile == null}
     */
	public void loadData(String dataFile) throws IOException {
		this.card = new HashMap<String,Boolean>();
		
		// if (Optional.of(dataFile).isPresent())

		try (BufferedReader reader = new BufferedReader(new FileReader(dataFile, StandardCharsets.UTF_8))) {
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

	public boolean canMoveTo(String position) {
		if ((this.card == null) || (this.card.isEmpty())) {
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
