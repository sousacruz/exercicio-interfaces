package com.github.sousacruz.exception;

public class UnsupportedDisplacement extends Exception {

	private static final long serialVersionUID = 3213208705634854433L;
	
	public static final String UNSUPPORTED_DISPLACEMENT_MESSAGE = "Your movement has an unsupported displacement (%s)";
	 
    public UnsupportedDisplacement(Character displacement) {
        super(String.format(UNSUPPORTED_DISPLACEMENT_MESSAGE, displacement.toString()));
    }

}
