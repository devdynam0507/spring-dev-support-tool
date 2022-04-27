package org.springsupport.tools.argument.parser;

import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

@Component
public class ParameterParser implements ArgumentParser {

    private final String parameterRegex = "";

    @Override
    public boolean canParse(String arg) {
        return false;
    }

    @Override
    public ArgumentMetadata parse(String arg) throws ArgumentParseException {
        return null;
    }

}
