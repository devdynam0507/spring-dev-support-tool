package org.springsupport.tools.argument.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentContextParserTest {

    private final ArgumentParser<ArgumentMetadata> argumentParser = new ArgumentContextParser();

    @Test
    @DisplayName("Name argument validation - single character")
    void name_checkValidArgument_singleCharacter() {
        String arg = "-n order";

        boolean canParse = argumentParser.canParse(arg);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Name argument validation - multi character")
    void name_checkValidArgument_multiCharacter() {
        String arg = "-name order";

        boolean canParse = argumentParser.canParse(arg);

        assertTrue(canParse);
    }


    @Test
    @DisplayName("Exclude argument validation - single character")
    void exclude_checkValidArgument_singleCharacter() {
        String arg = "-e controller";

        boolean canParse = argumentParser.canParse(arg);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Exclude argument validation - multi character")
    void exclude_checkValidArgument_multiCharacter() {
        String arg = "-exclude controller";

        boolean canParse = argumentParser.canParse(arg);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Exclude argument validation - multi character contains comma")
    void exclude_checkValidArgument_multiCharacterContainsComma() {
        String arg = "-exclude controller,service,hoo";

        boolean canParse = argumentParser.canParse(arg);

        assertTrue(canParse);
    }

    @Test
    @DisplayName("Input not specified command1")
    void inputNotSpecifiedCommand() {
        String arg = "-notspecified value";

        boolean canParse = argumentParser.canParse(arg);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Input not specified command2")
    void inputNotSpecifiedCommand2() {
        String arg = "notspecified value";

        boolean canParse = argumentParser.canParse(arg);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Input not specified command3")
    void inputNotSpecifiedCommand3() {
        String arg = "";

        boolean canParse = argumentParser.canParse(arg);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Input null")
    void inputNull() {
        String arg = null;

        boolean canParse = argumentParser.canParse(arg);

        assertFalse(canParse);
    }

    @Test
    @DisplayName("Extract -n argument values")
    void extract_singleCharacter_nameArgs_values() throws ArgumentParseException {
        String arg = "-n order";

        ArgumentMetadata parsed = argumentParser.parse(arg);

        assertEquals("n", parsed.getArgType());
        assertEquals("order", parsed.getArgParams());
    }

    @Test
    @DisplayName("Extract -name argument values")
    void extract_multiCharacter_nameArgs_values() throws ArgumentParseException {
        String arg = "-name order";

        ArgumentMetadata parsed = argumentParser.parse(arg);

        assertEquals("name", parsed.getArgType());
        assertEquals("order", parsed.getArgParams());
    }

    @Test
    @DisplayName("Extract -e argument values contains comma")
    void extract_singleCharacter_excludeArgs_values_contains_comma() throws ArgumentParseException {
        String arg = "-e service,controller";

        ArgumentMetadata parsed = argumentParser.parse(arg);

        assertEquals("e", parsed.getArgType());
        assertEquals("service,controller", parsed.getArgParams());
    }

    @Test
    @DisplayName("Extract -e argument values not contains comma")
    void extract_singleCharacter_excludeArgs_values() throws ArgumentParseException {
        String arg = "-e service";

        ArgumentMetadata parsed = argumentParser.parse(arg);

        assertEquals("e", parsed.getArgType());
        assertEquals("service", parsed.getArgParams());
    }

    @Test
    @DisplayName("Extract -exclude argument values contains comma")
    void extract_multiCharacter_excludeArgs_values_contains_comma() throws ArgumentParseException {
        String arg = "-exclude service,controller";

        ArgumentMetadata parsed = argumentParser.parse(arg);

        assertEquals("exclude", parsed.getArgType());
        assertEquals("service,controller", parsed.getArgParams());
    }

    @Test
    @DisplayName("Extract -exclude argument values not contains comma")
    void extract_multiCharacter_excludeArgs_values() throws ArgumentParseException {
        String arg = "-exclude service";

        ArgumentMetadata parsed = argumentParser.parse(arg);

        assertEquals("exclude", parsed.getArgType());
        assertEquals("service", parsed.getArgParams());
    }

    @Test
    @DisplayName("Extract args input null")
    void extractArgsInputNull() {
        String arg = null;

        assertThrows(ArgumentParseException.class, () -> argumentParser.parse(arg));
    }

    @Test
    @DisplayName("Extract invalid args")
    void extractInvalidArgs() {
        String arg = "adadad";

        assertThrows(ArgumentParseException.class, () -> argumentParser.parse(arg));
    }

    @Test
    @DisplayName("Extract invalid(empty) args")
    void extractInvalidEmptyArgs() {
        String arg = "";

        assertThrows(ArgumentParseException.class, () -> argumentParser.parse(arg));
    }

}
