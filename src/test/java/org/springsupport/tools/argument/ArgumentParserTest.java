package org.springsupport.tools.argument;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArgumentParserTest {

    private ArgumentParser argumentParser = new ArgumentParserImpl();

    @Test
    @DisplayName("Name argument validation - single character")
    void name_checkValidArgument_singleCharacter() {
        String arg = "-n order";

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, true);
    }

    @Test
    @DisplayName("Name argument validation - multi character")
    void name_checkValidArgument_multiCharacter() {
        String arg = "-name order";

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, true);
    }


    @Test
    @DisplayName("Exclude argument validation - single character")
    void exclude_checkValidArgument_singleCharacter() {
        String arg = "-e controller";

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, true);
    }

    @Test
    @DisplayName("Exclude argument validation - multi character")
    void exclude_checkValidArgument_multiCharacter() {
        String arg = "-exclude controller";

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, true);
    }

    @Test
    @DisplayName("Exclude argument validation - multi character contains comma")
    void exclude_checkValidArgument_multiCharacterContainsComma() {
        String arg = "-exclude controller,service,hoo";

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, true);
    }

    @Test
    @DisplayName("Input not specified command1")
    void inputNotSpecifiedCommand() {
        String arg = "-notspecified value";

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, false);
    }

    @Test
    @DisplayName("Input not specified command2")
    void inputNotSpecifiedCommand2() {
        String arg = "notspecified value";

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, false);
    }

    @Test
    @DisplayName("Input not specified command3")
    void inputNotSpecifiedCommand3() {
        String arg = "";

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, false);
    }

    @Test
    @DisplayName("Input null")
    void inputNull() {
        String arg = null;

        boolean canParse = argumentParser.canParse(arg);

        assertEquals(canParse, false);
    }

    @Test
    @DisplayName("Extract -n argument values")
    void extract_singleCharacter_nameArgs_values() throws ArgumentParseException {
        String arg = "-n order";

        List<ArgumentMetadata> parsed = argumentParser.parse(arg);

        assertEquals(parsed.size(), 1);
        assertEquals(parsed.get(0).getArgType(), "n");
        assertEquals(parsed.get(0).getArgParams(), "order");
    }

    @Test
    @DisplayName("Extract -name argument values")
    void extract_multiCharacter_nameArgs_values() throws ArgumentParseException {
        String arg = "-name order";

        List<ArgumentMetadata> parsed = argumentParser.parse(arg);

        assertEquals(parsed.size(), 1);
        assertEquals(parsed.get(0).getArgType(), "name");
        assertEquals(parsed.get(0).getArgParams(), "order");
    }

    @Test
    @DisplayName("Extract -e argument values contains comma")
    void extract_singleCharacter_excludeArgs_values_contains_comma() throws ArgumentParseException {
        String arg = "-e service,controller";

        List<ArgumentMetadata> parsed = argumentParser.parse(arg);

        assertEquals(parsed.size(), 1);
        assertEquals(parsed.get(0).getArgType(), "e");
        assertEquals(parsed.get(0).getArgParams(), "service,controller");
    }

    @Test
    @DisplayName("Extract -e argument values not contains comma")
    void extract_singleCharacter_excludeArgs_values() throws ArgumentParseException {
        String arg = "-e service";

        List<ArgumentMetadata> parsed = argumentParser.parse(arg);

        assertEquals(parsed.size(), 1);
        assertEquals(parsed.get(0).getArgType(), "e");
        assertEquals(parsed.get(0).getArgParams(), "service");
    }

    @Test
    @DisplayName("Extract -exclude argument values contains comma")
    void extract_multiCharacter_excludeArgs_values_contains_comma() throws ArgumentParseException {
        String arg = "-exclude service,controller";

        List<ArgumentMetadata> parsed = argumentParser.parse(arg);

        assertEquals(parsed.size(), 1);
        assertEquals(parsed.get(0).getArgType(), "exclude");
        assertEquals(parsed.get(0).getArgParams(), "service,controller");
    }

    @Test
    @DisplayName("Extract -exclude argument values not contains comma")
    void extract_multiCharacter_excludeArgs_values() throws ArgumentParseException {
        String arg = "-exclude service";

        List<ArgumentMetadata> parsed = argumentParser.parse(arg);

        assertEquals(parsed.size(), 1);
        assertEquals(parsed.get(0).getArgType(), "exclude");
        assertEquals(parsed.get(0).getArgParams(), "service");
    }

    @Test
    @DisplayName("Extract args input null")
    void extractArgsInputNull() throws ArgumentParseException {
        String arg = null;

        assertThrows(ArgumentParseException.class, () -> argumentParser.parse(arg));
    }

    @Test
    @DisplayName("Extract invalid args")
    void extractInvalidArgs() throws ArgumentParseException {
        String arg = "adadad";

        assertThrows(ArgumentParseException.class, () -> argumentParser.parse(arg));
    }

    @Test
    @DisplayName("Extract invalid(empty) args")
    void extractInvalidEmptyArgs() throws ArgumentParseException {
        String arg = "";

        assertThrows(ArgumentParseException.class, () -> argumentParser.parse(arg));
    }

}
