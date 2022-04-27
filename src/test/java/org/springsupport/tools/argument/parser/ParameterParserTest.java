package org.springsupport.tools.argument.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParameterParserTest {

    private ArgumentParser parameterParser = new ParameterParser();

    @Test
    @DisplayName("Parse parameters without comma")
    void parse_parameters_without_comma() {
        String param = "service";

        boolean canParse = parameterParser.canParse(param);

        assertEquals(canParse, true);
    }

}