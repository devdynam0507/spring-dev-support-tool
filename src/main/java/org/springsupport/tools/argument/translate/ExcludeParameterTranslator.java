package org.springsupport.tools.argument.translate;

import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.ArgumentResolveException;
import org.springsupport.tools.argument.exception.InvalidExcludeParameterException;
import org.springsupport.tools.types.SpringStandardLayers;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class ExcludeParameterTranslator extends ParameterTranslatorTemplate {

	@Override
	public boolean canTranslate(String argType) {
		return argType != null && (argType.equals("-e") || argType.equals("-exclude"));
	}

	@Override
	public void translate(ArgumentPipelineContext argumentPipelineContext, ArgumentMetadata targetArgumentMetadata) throws ArgumentResolveException {
		final String argParams = targetArgumentMetadata.getArgParams();

		if(argParams == null) {
			throw new InvalidExcludeParameterException("Invalid exclude params: " + null, null);
		}
		if(argParams.length() == 0) {
			return;
		}
		List<String> splitParams = Arrays.asList(
				argParams
				.replaceAll("\\s", "")
				.split(",")
		);
		checkInvalidSpringLayersIfNotThrows(splitParams);
		splitParams.forEach(s -> argumentPipelineContext.exclude(
				SpringStandardLayers.valueOf(s.toUpperCase(Locale.ROOT))
		));
	}

	protected void checkInvalidSpringLayersIfNotThrows(List<String> splitParams) throws InvalidExcludeParameterException {
		for(String param : splitParams) {
			if(!SpringStandardLayers.isValid(param)) {
				throw new InvalidExcludeParameterException("The layer types of parameters to be excluded do not match: " + param, param);
			}
		}
	}

}
