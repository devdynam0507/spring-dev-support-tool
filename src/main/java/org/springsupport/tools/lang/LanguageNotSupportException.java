package org.springsupport.tools.lang;

/**
 * SpringSupportTools not support language exception
 * */
public class LanguageNotSupportException extends Exception {

	private String invalidLanguageType;

	public LanguageNotSupportException(String message, String invalidLanguageType) {
		super(message);
		this.invalidLanguageType = invalidLanguageType;
	}

}
