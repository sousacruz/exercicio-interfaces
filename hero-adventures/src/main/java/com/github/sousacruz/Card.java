package com.github.sousacruz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Card {
	
	private final int CARRIAGE_RETURN = 13;
	private final int LINE_FEED = 10;
	
	private Map<String,Boolean> card = new HashMap<String,Boolean>();
	
	Card() {}
	
	Card(String dataFile) {
		try {
			this.loadCard(dataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Boolean> getCard() {
		return Collections.unmodifiableMap(this.card);
	}
	
	public void loadCard(String dataFile) throws IOException {
        
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
			throw new RuntimeException("The map text file must be in UTF-8 format");
		}

	}

	public boolean canMoveTo(String position) {
		if (this.card.size() == 0) {
			throw new RuntimeException("This card have no data yet!");
		}
		Boolean isValid = this.card.get(position);
		
		if (isValid != null) {
			return isValid.booleanValue();
		} else {
			throw new RuntimeException("The hero cannot go beyond the edges of the map.");
		}
	}
	
}
