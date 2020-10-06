package com.github.sousacruz.exception;

public class ImpenetrableWoodsException extends RuntimeException {
	 
	private static final long serialVersionUID = -7388956815245558575L;

	public static final String BEYOND_THE_EDGES_MESSAGE = "The hero cannot go on the squares occupied by the impenetrable woods. # symbol has found at position (%s)";
 
    public ImpenetrableWoodsException(String position) {
        super(String.format(BEYOND_THE_EDGES_MESSAGE, position));
    }

}
