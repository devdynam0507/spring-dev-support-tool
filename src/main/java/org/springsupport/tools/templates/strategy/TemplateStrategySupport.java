package org.springsupport.tools.templates.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.templates.TemplateRenderer;
import org.springsupport.tools.templates.TemplateWriter;

import javax.validation.constraints.NotNull;
import java.util.Locale;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateStrategySupport {

    private final Map<String, TemplateRenderer> templateRendererMap;
    private final Map<String, TemplateWriter> templateWriterMap;

    private final Map<String, TemplateStrategyNameResolver> templateStrategyNameResolverMap;


    public TemplateRenderer getTemplateRenderer(SupportLanguage supportLanguage) {
        String templateClassName = resolveTemplateName("templateRenderer", supportLanguage);
        return templateRendererMap.get(templateClassName);
    }

    public TemplateWriter getTemplateWriter(SupportLanguage supportLanguage) {
        String templateClassName = resolveTemplateName("templateWriter", supportLanguage);
        return templateWriterMap.get(templateClassName);
    }

    public String resolveTemplateName(@NotNull String templateActionType, @NotNull SupportLanguage supportLanguage) {
        final String nameResolverName = templateActionType + "NameResolver";
        TemplateStrategyNameResolver nameResolver = templateStrategyNameResolverMap.get(nameResolverName);

        return nameResolver.getName(supportLanguage);
    }

}
