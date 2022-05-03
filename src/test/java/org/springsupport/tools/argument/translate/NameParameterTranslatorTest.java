package org.springsupport.tools.argument.translate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.InvalidNameArgumentException;

class NameParameterTranslatorTest {

	private ParameterTranslator nameParameterTranslator = new NameParameterTranslator();

	@Test
	void check_arg_type_not_null_valid() {
		String validArgType1 = "-n";
		String validArgType2 = "-name";

		boolean canTranslateArg1 = nameParameterTranslator.canTranslate(validArgType1);
		boolean canTranslateArg2 = nameParameterTranslator.canTranslate(validArgType2);

		assertTrue(canTranslateArg1);
		assertTrue(canTranslateArg2);
	}

	@Test
	void check_arg_type_null() {
		String validArgType1 = null;

		boolean canTranslateArg1 = nameParameterTranslator.canTranslate(validArgType1);

		assertFalse(canTranslateArg1);
	}

	@Test
	void check_arg_type_invalid() {
		String validArgType1 = "oing??";

		boolean canTranslateArg1 = nameParameterTranslator.canTranslate(validArgType1);

		assertFalse(canTranslateArg1);
	}

	@Test
	void check_valid_translate() throws Exception {
		ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
		ArgumentMetadata argumentMetadata = new ArgumentMetadata(
				"-n", "testname"
		);

		nameParameterTranslator.translate(context, argumentMetadata);

		assertEquals("testname", context.getName());
	}

	@Test
	void check_invalid_translate() {
		ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
		ArgumentMetadata argumentMetadata = new ArgumentMetadata(
				"-n", ""
		);

		assertThrows(InvalidNameArgumentException.class, () -> nameParameterTranslator.translate(context, argumentMetadata));
	}

	@Test
	void check_invalid_translate2() {
		ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
		ArgumentMetadata argumentMetadata = new ArgumentMetadata(
				"-n", null
		);

		assertThrows(InvalidNameArgumentException.class, () -> nameParameterTranslator.translate(context, argumentMetadata));
	}

}