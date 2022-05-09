package org.springsupport.tools.dispatcher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.resolver.ArgumentResolver;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class DefaultAppDispatcher implements AppDispatcher {

    private final ArgumentResolver argumentResolver;

    @Override
    public void doDispatch(@NotNull String... args) {
        try {
            ArgumentPipelineContext argumentPipelineContext = argumentResolver.resolve(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
