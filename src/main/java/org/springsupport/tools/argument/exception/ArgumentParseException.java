package org.springsupport.tools.argument.exception;

/**
 * If the argument analysis fails, an ArgumentParseException is thrown
 * */
public class ArgumentParseException extends Exception {

    private String arg;

    public ArgumentParseException(String message, String arg) {
        super(message);
    }

    public String getFailedParseArg() {
        return arg;
    }

}
