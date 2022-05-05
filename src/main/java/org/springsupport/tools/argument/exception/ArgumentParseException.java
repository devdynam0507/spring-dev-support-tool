package org.springsupport.tools.argument.exception;

import lombok.Getter;

/**
 * If the argument analysis fails, an ArgumentParseException is thrown
 * */
@Getter
public class ArgumentParseException extends Exception {

    private String arg;

    public ArgumentParseException(String message, String arg) {
        super(message);
    }

}
