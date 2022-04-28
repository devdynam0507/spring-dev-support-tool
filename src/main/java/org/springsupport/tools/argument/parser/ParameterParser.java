package org.springsupport.tools.argument.parser;

import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentParamMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class ParameterParser implements ArgumentParser<ArgumentParamMetadata> {

    private final String parameterRegex = "[\\a-zA-Z]+[,?^\\s?|\\s?]*+";

    @Override
    public boolean canParse(@NotNull String arg) {
        if(arg == null) {
            return false;
        }
        return Pattern.matches(parameterRegex, arg);
    }

    @Override
    public ArgumentParamMetadata parse(@NotNull String arg) throws ArgumentParseException {
        ArgumentParamMetadata paramMetadata = new ArgumentParamMetadata();
        String[] paramSplits = arg
                .replaceAll("\\s", "")
                .split(",");

        for(int i = 0; i < paramSplits.length; i++) {
            paramMetadata.addParam(paramSplits[i].toLowerCase(Locale.ROOT));
        }

        return paramMetadata;
    }

}
