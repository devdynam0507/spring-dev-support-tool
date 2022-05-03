package org.springsupport.tools.argument.exception;

import lombok.Getter;

@Getter
public class InvalidNameArgumentException extends ArgumentResolveException {

	private String invalidName;

	public InvalidNameArgumentException(String message, String invalidName) {
		super(message);
		this.invalidName = invalidName;
	}

}
