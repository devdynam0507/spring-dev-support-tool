package org.springsupport.tools.argument.translate;

import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.InvalidNameArgumentException;

import org.springframework.stereotype.Component;

@Component
public class NameParameterTranslator implements ParameterTranslator {

	@Override
	public void translate(ArgumentPipelineContext argumentPipelineContext, ArgumentMetadata argumentMetadata) throws Exception {
		if(argumentMetadata.getArgParams() == null || argumentMetadata.getArgParams().length() <= 0) {
			throw new InvalidNameArgumentException("invalid input name: " + argumentMetadata.getArgParams(), argumentMetadata.getArgParams());
		}
		argumentPipelineContext.setName(argumentMetadata.getArgParams());
	}

}
