package org.springsupport.tools.templates.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.lang.SupportLanguage;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class TemplateRendererNameResolver implements TemplateStrategyNameResolver {

    private final String TEMPLATE_RENDERER_CLASS_NAME_SUFFIX = "TemplateRenderer";

    @Override
    public String getName(SupportLanguage supportLanguage) {
        return supportLanguage.name().toLowerCase(Locale.ROOT) + TEMPLATE_RENDERER_CLASS_NAME_SUFFIX;
    }

}
