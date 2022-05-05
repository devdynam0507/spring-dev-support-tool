package org.springsupport.tools.argument.exception;

import lombok.Getter;

@Getter
public class LanguageNotSupportException extends ArgumentResolveException {

    private String invalidLang;

    public LanguageNotSupportException(String message, String invalidLang) {
        super(message);
        this.invalidLang = invalidLang;
    }

}
