package org.springsupport.tools.argument.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.ArgumentParseException;
import org.springsupport.tools.argument.parser.ArgumentParser;
import org.springsupport.tools.argument.translate.ParameterTranslator;
import org.springsupport.tools.argument.translate.ParameterTranslatorTemplate;
import org.springsupport.tools.types.SpringStandardLayers;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultArgumentResolver implements ArgumentResolver {

	private final ArgumentParser<ArgumentMetadata> loopArgumentParser;
	private final List<ParameterTranslatorTemplate> translators;

    @Override
    public ArgumentPipelineContext resolve(@NotNull String... args) throws ArgumentParseException {
		ArgumentPipelineContext argumentPipelineContext = ArgumentPipelineContext.builder()
				.name("Some")
				.build();
		argumentPipelineContext.setDefaultIncludeLayers();
		List<ArgumentMetadata> argumentMetadataList = loopArgumentParser.parse(args);

		translators.stream().forEach(i -> {
			try {
				i.translateMany(argumentPipelineContext, argumentMetadataList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return argumentPipelineContext;
    }

}
