package org.springsupport.tools.argument.resolver;

import org.springsupport.tools.argument.dto.ArgumentPipelineContext;

import javax.validation.constraints.NotNull;

public interface ArgumentResolver {

    ArgumentPipelineContext resolve(@NotNull final String... args) throws Exception;

}
