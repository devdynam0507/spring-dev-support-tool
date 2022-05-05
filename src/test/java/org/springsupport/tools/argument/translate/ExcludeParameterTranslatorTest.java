package org.springsupport.tools.argument.translate;

import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.InvalidExcludeParameterException;
import org.springsupport.tools.types.SpringStandardLayers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ExcludeParameterTranslatorTest {

	private ParameterTranslator excludeParameterTranslator = new ExcludeParameterTranslator();

	@Test
	void check_arg_type_not_null_valid() {
		String validArgType1 = "-e";
		String validArgType2 = "-exclude";

		boolean canTranslateArg1 = excludeParameterTranslator.canTranslate(validArgType1);
		boolean canTranslateArg2 = excludeParameterTranslator.canTranslate(validArgType2);

		assertTrue(canTranslateArg1);
		assertTrue(canTranslateArg2);
	}

	@Test
	void check_arg_type_null() {
		String nullArg = null;

		boolean canTranslateArg1 = excludeParameterTranslator.canTranslate(nullArg);

		assertFalse(canTranslateArg1);
	}

	@Test
	void check_arg_type_not_null_invalid() {
		String invalidArg = "ong~?";

		boolean canTranslateArg1 = excludeParameterTranslator.canTranslate(invalidArg);

		assertFalse(canTranslateArg1);
	}

	@Test
	void check_valid_translate() throws Exception {
		ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
		context.setDefaultIncludeLayers();
		ArgumentMetadata argumentMetadata = new ArgumentMetadata(
				"-e", "service"
		);

		excludeParameterTranslator.translate(context, argumentMetadata);

		assertFalse(context.getIncludeLayers().contains(SpringStandardLayers.SERVICE));
	}

	@Test
	void check_valid_translate2() throws Exception {
		ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
		context.setDefaultIncludeLayers();
		ArgumentMetadata argumentMetadata = new ArgumentMetadata(
				"-e", "service, controller"
		);

		excludeParameterTranslator.translate(context, argumentMetadata);

		assertFalse(context.getIncludeLayers().contains(SpringStandardLayers.SERVICE));
		assertFalse(context.getIncludeLayers().contains(SpringStandardLayers.CONTROLLER));
	}

	@Test
	void check_invalid_translate() {
		ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
		ArgumentMetadata argumentMetadata = new ArgumentMetadata(
				"-e", "service, controller, ang?"
		);

		assertThrows(InvalidExcludeParameterException.class, () -> excludeParameterTranslator.translate(context, argumentMetadata));
	}

}