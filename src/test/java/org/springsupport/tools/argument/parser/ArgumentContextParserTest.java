package org.springsupport.tools.argument.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentContextMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentContextParserTest {

    private ArgumentParser<ArgumentContextMetadata> argumentContextParser = new ArgumentContextParser();

    @Test
    @DisplayName("Check parse context")
    void check_parse_context() {
        String context = "-n name -e service,controller";

        boolean canParse = argumentContextParser.canParse(context);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Check parse context single")
    void check_parse_context_single() {
        String context = "-n name";

        boolean canParse = argumentContextParser.canParse(context);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Check parse context single")
    void check_parse_context_null() {
        String context = null;

        boolean canParse = argumentContextParser.canParse(context);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Check parse invalid command name")
    void check_parse_context_invalid_command_name() {
        String context = "-aaaa vascasca";

        boolean canParse = argumentContextParser.canParse(context);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Check parse invalid command name2")
    void check_parse_context_invalid_command_name2() {
        String context = "-n Order -bbbb avasawe";

        boolean canParse = argumentContextParser.canParse(context);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("parse single argument")
    void parse_single_argument() throws ArgumentParseException {
        String context = "-n Order";

        ArgumentContextMetadata metadata = argumentContextParser.parse(context);

        assertEquals(1, metadata.getContexts().size());
        assertEquals("n Order", metadata.getContexts().get(0));
    }

    @Test
    @DisplayName("parse two argument")
    void parse_two_argument() throws ArgumentParseException {
        String context = "-n Order -e abc";

        ArgumentContextMetadata metadata = argumentContextParser.parse(context);

        assertEquals(2, metadata.getContexts().size());
        assertEquals("-n Order", metadata.getContexts().get(0));
        assertEquals("-e abc", metadata.getContexts().get(1));
    }

}