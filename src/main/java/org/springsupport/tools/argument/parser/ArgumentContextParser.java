package org.springsupport.tools.argument.parser;

import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentContextMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ArgumentContextParser implements ArgumentParser<ArgumentContextMetadata>{

     private final String matchRegex = "\\s?(-[n|name|e|exclude]\\s[\\S+,?]+[- ]?)+";
     private final String parseRegex = "-([n|name|e|exclude] [\\S,?]+[- ]?)+";

    @Override
    public boolean canParse(String arg) {
        if(arg == null) {
            return false;
        }
        return Pattern.matches(matchRegex, arg);
    }

    @Override
    public ArgumentContextMetadata parse(String arg) throws ArgumentParseException {
        ArgumentContextMetadata contexts = new ArgumentContextMetadata();
        Matcher matcher = Pattern.compile(parseRegex).matcher(arg);

        if(arg == null || !matcher.find() || matcher.groupCount() < 1) {
            throw new ArgumentParseException("context parse error, invalid argument: " + arg, arg);
        }
        for(int i = 0; i < matcher.groupCount(); i++) {
            contexts.getContexts().add(matcher.group(i + 1));
        }
        System.out.println(matcher.groupCount());
        System.out.println(contexts.getContexts());
        return contexts;
    }

}
