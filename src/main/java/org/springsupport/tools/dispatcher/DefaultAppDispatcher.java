package org.springsupport.tools.dispatcher;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.resolver.ArgumentResolver;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.templates.TemplateRenderer;
import org.springsupport.tools.templates.TemplateWriter;
import org.springsupport.tools.templates.files.TemplateContent;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class DefaultAppDispatcher implements AppDispatcher {

	private static final String TEMPLATE_RENDERER_NAME = "TemplateRenderer";
    private static final String TEMPLATE_WRITER_NAME = "TemplateWriter";

	private final ArgumentResolver argumentResolver;
    private final Map<String, TemplateRenderer> templateRendererMap;
    private final Map<String, TemplateWriter> templateWriterMap;

    @Override
    public void doDispatch(@NotNull String... args) {
        try {
            ArgumentPipelineContext argumentPipelineContext = argumentResolver.resolve(args);
            TemplateRenderer renderer = getTemplateRendererStrategy(argumentPipelineContext.getLanguage());
            TemplateWriter writer = getTemplateWriterStrategy(argumentPipelineContext.getLanguage());
            List<TemplateContent> renderedContents = renderer.render("", argumentPipelineContext);

            writer.write(renderedContents, argumentPipelineContext.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TemplateRenderer getTemplateRendererStrategy(SupportLanguage language) {
    	final String templateClassName = language.name().toLowerCase(Locale.ROOT) + TEMPLATE_RENDERER_NAME;
    	return templateRendererMap.get(templateClassName);
	}

    public TemplateWriter getTemplateWriterStrategy(SupportLanguage language) {
        final String templateClassName = language.name().toLowerCase(Locale.ROOT) + TEMPLATE_WRITER_NAME;
        return templateWriterMap.get(templateClassName);
    }

}
