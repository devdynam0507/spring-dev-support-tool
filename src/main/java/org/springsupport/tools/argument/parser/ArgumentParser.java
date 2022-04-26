package org.springsupport.tools.argument.parser;

import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ArgumentParser {

    /**
     * Check the arguments when called main method
     *
     * @param arg: One element in the args array
     * */
    boolean canParse(@NotNull String arg);

    /**
     * Parses arguments. If an invalid argument is entered,
     * throw an ArgumentParseException exception.
     *
     * @param arg: One element in the args array
     * */
    List<ArgumentMetadata> parse(@NotNull String arg) throws ArgumentParseException;

}
