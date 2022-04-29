package org.springsupport.tools.argument.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentParamMetadata;
import org.springsupport.tools.argument.parser.ArgumentParser;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DefaultArgumentResolver implements ArgumentResolver {

    private final ArgumentParser<ArgumentMetadata> argumentContextParser;
    private final ArgumentParser<ArgumentParamMetadata> parameterParser;

    @Override
    public void resolve(@NotNull String... args) {
        String argsString = argsToString(args);
    }

    public ArgumentMetadata parseContext(String argsString) {
        return null;
    }

    private String argsToString(String... args) {
        return Stream.of(args).collect(Collectors.joining(" "));
    }

}
