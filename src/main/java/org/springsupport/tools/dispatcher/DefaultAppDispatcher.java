package org.springsupport.tools.dispatcher;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.resolver.ArgumentResolver;
import org.springsupport.tools.templates.TemplateRenderer;
import org.springsupport.tools.templates.TemplateWriter;
import org.springsupport.tools.templates.files.TemplateContent;
import org.springsupport.tools.templates.strategy.TemplateStrategySupport;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class DefaultAppDispatcher implements AppDispatcher {

	private final ArgumentResolver argumentResolver;
    private final TemplateStrategySupport templateStrategySupport;

    @Override
    public void doDispatch(@NotNull String... args) {
        try {
            ArgumentPipelineContext argumentPipelineContext = argumentResolver.resolve(args);
            TemplateRenderer renderer = templateStrategySupport.getTemplateRenderer(argumentPipelineContext.getLanguage());
            TemplateWriter writer = templateStrategySupport.getTemplateWriter(argumentPipelineContext.getLanguage());
            List<TemplateContent> renderedContents = renderer.render("", argumentPipelineContext);

            writer.write(renderedContents, argumentPipelineContext.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
