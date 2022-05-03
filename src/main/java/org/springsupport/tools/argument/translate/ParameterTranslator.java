package org.springsupport.tools.argument.translate;

import javax.validation.constraints.NotNull;

import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;

public interface ParameterTranslator {

	/**
	 * @param argumentPipelineContext The context data storage class of the argument processing pipeline
	 * @param targetArgumentMetadata Information dto class to be contained in context
	 * */
	void translate(@NotNull ArgumentPipelineContext argumentPipelineContext, ArgumentMetadata targetArgumentMetadata) throws Exception;

}
