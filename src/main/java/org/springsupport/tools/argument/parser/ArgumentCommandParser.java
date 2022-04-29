package org.springsupport.tools.argument.parser;

import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ArgumentCommandParser implements ArgumentParser<ArgumentMetadata> {

    private final static String argumentRegex = "-(n|name|e|exclude) ([a-zA-Z,?]+)$";

    @Override
    public boolean canParse(@NotNull String arg) {
        if(arg == null) {
            return false;
        }
        return Pattern.matches(argumentRegex, arg);
    }

    @Override
    public ArgumentMetadata parse(@NotNull String arg) throws ArgumentParseException {
        if(arg == null) {
            throw new ArgumentParseException("Argument is null", null);
        }
        Matcher argsPatternMatcher = Pattern.compile(argumentRegex)
                                            .matcher(arg);
        ArgumentMetadata argumentMetadata = new ArgumentMetadata(arg, null);

        if(!argsPatternMatcher.find() || argsPatternMatcher.groupCount() < 2) {
            throw new ArgumentParseException(arg + " is invalid argument", arg);
        }
        String argType = argsPatternMatcher.group(1);
        String argParams = argsPatternMatcher.group(2);
        argumentMetadata.setArgType(argType);
        argumentMetadata.setArgParams(argParams);

        return argumentMetadata;
    }

}
