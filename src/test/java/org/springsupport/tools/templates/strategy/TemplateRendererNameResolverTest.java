package org.springsupport.tools.templates.strategy;

import org.junit.jupiter.api.Test;
import org.springsupport.tools.lang.SupportLanguage;

import static org.junit.jupiter.api.Assertions.*;

class TemplateRendererNameResolverTest {

    TemplateRendererNameResolver nameResolver = new TemplateRendererNameResolver();

    @Test
    void success_resolved_java_renderer_name_test() {
        SupportLanguage language = SupportLanguage.JAVA;

        String templateClassName = nameResolver.getName(language);

        assertEquals("javaTemplateRenderer", templateClassName);
    }

}