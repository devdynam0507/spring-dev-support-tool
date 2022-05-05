package org.springsupport.tools.argument.translate;

import javax.validation.constraints.NotNull;

import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;

import java.util.List;

public interface ParameterTranslator {

	 boolean canTranslate(@NotNull String argType);

	/**
	 * @param argumentPipelineContext The context data storage class of the argument processing pipeline
	 * @param targetArgumentMetadata Information dto class to be contained in context
	 * */
	void translate(@NotNull ArgumentPipelineContext argumentPipelineContext, ArgumentMetadata targetArgumentMetadata) throws Exception;

	void translateMany(@NotNull ArgumentPipelineContext argumentPipelineContext, List<ArgumentMetadata> argumentMetadataList) throws Exception;

}
