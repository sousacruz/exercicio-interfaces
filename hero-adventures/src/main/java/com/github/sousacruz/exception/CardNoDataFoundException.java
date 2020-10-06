package com.github.sousacruz.exception;

public class CardNoDataFoundException extends RuntimeException {

	private static final long serialVersionUID = 3503024857694523572L;

	public CardNoDataFoundException(String msg) {
        super(msg);
    }

}