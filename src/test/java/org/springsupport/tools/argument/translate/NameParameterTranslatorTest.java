package org.springsupport.tools.argument.translate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.InvalidNameArgumentException;

class NameParameterTranslatorTest {

	private ParameterTranslator nameParameterTranslator = new NameParameterTranslator();

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