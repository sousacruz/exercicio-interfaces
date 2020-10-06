package com.github.sousacruz.exception;

public class BeyondTheEdgesException extends RuntimeException {
 
	private static final long serialVersionUID = 6758508631643582041L;
	
	public static final String BEYOND_THE_EDGES_MESSAGE = "The hero cannot go beyond the edges of the map. Invalid position (%s)";
 
    public BeyondTheEdgesException(String position) {
        super(String.format(BEYOND_THE_EDGES_MESSAGE, position));
    }

}
