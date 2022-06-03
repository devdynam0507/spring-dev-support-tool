package org.springsupport.tools.templates.strategy;

import org.springframework.stereotype.Component;
import org.springsupport.tools.lang.SupportLanguage;

import java.util.Locale;

@Component
public class TemplateWriterNameResolver implements TemplateStrategyNameResolver {

    private final String TEMPLATE_WRITER_CLASS_NAME_SUFFIX = "TemplateWriter";

    @Override
    public String getName(SupportLanguage supportLanguage) {
        return supportLanguage.name().toLowerCase(Locale.ROOT) + TEMPLATE_WRITER_CLASS_NAME_SUFFIX;
    }

}
