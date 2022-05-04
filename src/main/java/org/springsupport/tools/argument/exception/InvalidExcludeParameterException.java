package org.springsupport.tools.argument.exception;

import lombok.Getter;

@Getter
public class InvalidExcludeParameterException extends ArgumentResolveException {

	private String invalidExcludeParams;

	public InvalidExcludeParameterException(String message, String invalidExcludeParams) {
		super(message);
		this.invalidExcludeParams = invalidExcludeParams;
	}

}
