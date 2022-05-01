package org.springsupport.tools.argument.parser;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.exception.ArgumentParseException;

import static org.junit.jupiter.api.Assertions.*;

class LoopArgumentParserTest {

	private ArgumentParser<ArgumentMetadata> argumentParser = new LoopArgumentParser();

	@Test
	public void check_argument_parse_valid() {
		String[] args = new String[] {
				"-n", "Order", "-e", "service,controller"
		};

		boolean canParse = argumentParser.canParse(args);

		assertTrue(canParse);
	}

	@Test
	public void check_argument_parse_one_param() {
		String[] args = new String[] {
				"-n"
		};

		boolean canParse = argumentParser.canParse(args);

		assertFalse(canParse);
	}

	@Test
	public void check_argument_parse_three_param() {
		String[] args = new String[] {
				"-n", "Order", "-e"
		};

		boolean canParse = argumentParser.canParse(args);

		assertFalse(canParse);
	}

	@Test
	public void check_argument_parse_zero_param() {
		String[] args = new String[] { };

		boolean canParse = argumentParser.canParse(args);

		assertFalse(canParse);
	}

	@Test
	public void parse_arguments_valid() throws ArgumentParseException {
		String[] args = new String[] {
				"-n", "Order", "-e", "service,controller"
		};

		List<ArgumentMetadata> argumentMetadata = argumentParser.parse(args);

		assertEquals(2, argumentMetadata.size());
		assertEquals("-n", argumentMetadata.get(0).getArgType());
		assertEquals("Order", argumentMetadata.get(0).getArgParams());
		assertEquals("-e", argumentMetadata.get(1).getArgType());
		assertEquals("service,controller", argumentMetadata.get(1).getArgParams());
	}

	@Test
	public void parse_arguments_one_params() {
		String[] args = new String[] {
				"-n"
		};

		assertThrows(ArgumentParseException.class, () -> argumentParser.parse(args));
	}

	@Test
	public void parse_arguments_three_params() {
		String[] args = new String[] {
				"-n", "Order", "-e"
		};

		assertThrows(ArgumentParseException.class, () -> argumentParser.parse(args));
	}

	@Test
	public void parse_arguments_zero_params() {
		String[] args = new String[] { };

		assertThrows(ArgumentParseException.class, () -> argumentParser.parse(args));
	}
}