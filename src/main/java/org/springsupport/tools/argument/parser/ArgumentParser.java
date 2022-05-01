package org.springsupport.tools.argument.parser;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springsupport.tools.argument.exception.ArgumentParseException;

public interface ArgumentParser<Out> {

    /**
     * Check the arguments when called main method
     *
     * @param args: Command-Line arguments
     * */
    boolean canParse(@NotNull String... args);

    /**
     * Parses arguments. If an invalid argument is entered,
     * throw an ArgumentParseException exception.
     *
     * @param args: Command-Line arguments
     * */
    List<Out> parse(@NotNull String... args) throws ArgumentParseException;

}
