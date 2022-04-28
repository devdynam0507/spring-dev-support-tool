package org.springsupport.tools.argument.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentParamMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import static org.junit.jupiter.api.Assertions.*;

class ParameterParserTest {

    private ArgumentParser<ArgumentParamMetadata> parameterParser = new ParameterParser();

    @Test
    @DisplayName("Parse parameters without comma")
    void parse_parameters_without_comma() {
        String param = "service";

        boolean canParse = parameterParser.canParse(param);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Parse parameters with comma")
    void parse_parameters_with_comma() {
        String param = "service,controller";

        boolean canParse = parameterParser.canParse(param);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Parse parameters with comma and whitespace")
    void parse_parameters_with_comma_and_whitespace() {
        String param = "service, controller , entity";

        boolean canParse = parameterParser.canParse(param);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Parse parameters invalid character")
    void parse_parameters_invalid_character() {
        String param = "service, controller , 한글";

        boolean canParse = parameterParser.canParse(param);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Parse parameters single invalid character")
    void parse_parameters_single_invalid_character() {
        String param = "한글";

        boolean canParse = parameterParser.canParse(param);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Parse parameters null")
    void parse_parameters_null() {
        String param = null;

        boolean canParse = parameterParser.canParse(param);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Parse parameter single character")
    void parse_parameter_single_character() throws ArgumentParseException {
        String param = "service";

        ArgumentParamMetadata paramMetadata = parameterParser.parse(param);

        assertTrue(parameterParser.canParse(param));
        assertEquals(1, paramMetadata.getParams().size());
        assertEquals("service", paramMetadata.getParams().get(0));
    }

    @Test
    @DisplayName("Parse parameter multi character")
    void parse_parameter_multi_character() throws ArgumentParseException {
        String param = "service,controller, entity";

        ArgumentParamMetadata paramMetadata = parameterParser.parse(param);

        assertTrue(parameterParser.canParse(param));
        assertEquals(3, paramMetadata.getParams().size());
        assertEquals("service", paramMetadata.getParams().get(0));
        assertEquals("controller", paramMetadata.getParams().get(1));
        assertEquals("entity", paramMetadata.getParams().get(2));
    }

    @Test
    @DisplayName("Verify that capitalized inputs return well in lowercase")
    void parse_parameter_multi_character_lowercase() throws ArgumentParseException {
        String param = "SERVICE,Controller, enTity";

        ArgumentParamMetadata paramMetadata = parameterParser.parse(param);

        assertTrue(parameterParser.canParse(param));
        assertEquals(3, paramMetadata.getParams().size());
        assertEquals("service", paramMetadata.getParams().get(0));
        assertEquals("controller", paramMetadata.getParams().get(1));
        assertEquals("entity", paramMetadata.getParams().get(2));
    }

}