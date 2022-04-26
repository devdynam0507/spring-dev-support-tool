package org.springsupport.tools.argument;

import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ArgumentParserImpl implements ArgumentParser {

    private final static String argumentRegex = "-(n|name|e|exclude) ([a-zA-Z,?]+)$";

    @Override
    public boolean canParse(@NotNull String arg) {
        if(arg == null) {
            return false;
        }

        return Pattern.matches(argumentRegex, arg);
    }

    @Override
    public List<ArgumentMetadata> parse(@NotNull String arg) throws ArgumentParseException {
        if(arg == null) {
            throw new ArgumentParseException("Argument is null", null);
        }

        Matcher argsPatternMatcher = getMatcher(arg);
        List<ArgumentMetadata> parsedMetadataResults = new ArrayList<>();
        if(!argsPatternMatcher.find() || argsPatternMatcher.groupCount() < 2) {
            throw new ArgumentParseException(arg + " is invalid argument", arg);
        }
        String argType = argsPatternMatcher.group(1);
        String argParams = argsPatternMatcher.group(2);
        parsedMetadataResults.add(new ArgumentMetadata(argType, argParams));
        return parsedMetadataResults;
    }

    private Matcher getMatcher(String arg) {
        Pattern argumentPattern = Pattern.compile(argumentRegex);
        return argumentPattern.matcher(arg);
    }

}
