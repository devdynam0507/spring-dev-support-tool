package org.springsupport.tools.argument.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.parser.ArgumentParser;
import org.springsupport.tools.argument.translate.ParameterTranslator;
import org.springsupport.tools.argument.translate.ParameterTranslatorTemplate;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultArgumentResolver implements ArgumentResolver {

	private final ArgumentParser<ArgumentMetadata> loopArgumentParser;
	private final List<ParameterTranslatorTemplate> translators;

    @Override
    public ArgumentPipelineContext resolve(@NotNull String... args) throws Exception {
		ArgumentPipelineContext argumentPipelineContext = ArgumentPipelineContext.builder()
				.name("Some")
				.build();
		argumentPipelineContext.setDefaultIncludeLayers();
		List<ArgumentMetadata> argumentMetadataList = loopArgumentParser.parse(args);

		for(ParameterTranslator translator : translators) {
			translator.translateMany(argumentPipelineContext, argumentMetadataList);
		}

		return argumentPipelineContext;
    }

}
