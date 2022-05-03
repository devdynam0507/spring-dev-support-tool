package org.springsupport.tools.argument.resolver;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.RequiredArgsConstructor;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.ArgumentParseException;
import org.springsupport.tools.argument.parser.ArgumentParser;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.types.SpringStandardLayers;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultArgumentResolver implements ArgumentResolver {

	private ArgumentParser<ArgumentMetadata> loopArgumentParser;

    @Override
    public void resolve(@NotNull String... args) {
    	try {
			ArgumentPipelineContext argumentPipelineContext = ArgumentPipelineContext.builder()
					.name("Some")
					.includeLayers(Arrays.asList(
							SpringStandardLayers.CONTROLLER,
							SpringStandardLayers.DOMAIN,
							SpringStandardLayers.REPOSITORY,
							SpringStandardLayers.SERVICE
					))
					.language(SupportLanguage.JAVA)
					.build();
			List<ArgumentMetadata> argumentMetadataList = loopArgumentParser.parse(args);

			for(ArgumentMetadata argumentMetadata : argumentMetadataList) {

			}
		}
    	catch (ArgumentParseException e) {

		}
    }

    public ArgumentMetadata parseContext(String argsString) {
        return null;
    }

}
