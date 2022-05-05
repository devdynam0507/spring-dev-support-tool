package org.springsupport.tools.argument.translate;

import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.ArgumentResolveException;
import org.springsupport.tools.argument.exception.InvalidNameArgumentException;

import org.springframework.stereotype.Component;

@Component
public class NameParameterTranslator extends ParameterTranslatorTemplate {

	@Override
	public boolean canTranslate(String argType) {
		return argType != null && (argType.equals("-n") || argType.equals("-name"));
	}

	@Override
	public void translate(ArgumentPipelineContext argumentPipelineContext, ArgumentMetadata argumentMetadata) throws ArgumentResolveException {
		if(argumentMetadata.getArgParams() == null || argumentMetadata.getArgParams().length() <= 0) {
			throw new InvalidNameArgumentException("invalid input name: " + argumentMetadata.getArgParams(), argumentMetadata.getArgParams());
		}
		argumentPipelineContext.setName(argumentMetadata.getArgParams());
	}

}
