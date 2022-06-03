package org.springsupport.tools.templates.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springsupport.tools.AppDependencyConfiguration;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.templates.JavaTemplateRenderer;
import org.springsupport.tools.templates.JavaTemplateWriter;
import org.springsupport.tools.templates.TemplateRenderer;
import org.springsupport.tools.templates.TemplateWriter;

import static org.junit.jupiter.api.Assertions.*;

class TemplateStrategySupportTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AppDependencyConfiguration.class);

    private TemplateStrategySupport templateRendererStrategySupport = new TemplateStrategySupport(
            ac.getBeansOfType(TemplateRenderer.class),
            ac.getBeansOfType(TemplateWriter.class),
            ac.getBeansOfType(TemplateStrategyNameResolver.class)
    );

    @Test
    void template_writer_name_resolve_test() {
        final String action = "templateWriter";
        SupportLanguage supportLanguage = SupportLanguage.JAVA;

        String templateName = templateRendererStrategySupport.resolveTemplateName(action, supportLanguage);

        assertEquals("javaTemplateWriter", templateName);
    }

    @Test
    void template_renderer_name_resolve_test() {
        final String action = "templateRenderer";
        SupportLanguage supportLanguage = SupportLanguage.JAVA;

        String templateName = templateRendererStrategySupport.resolveTemplateName(action, supportLanguage);

        assertEquals("javaTemplateRenderer", templateName);
    }

    @Test
    void return_successfully_template_writer_test() {
        SupportLanguage supportLanguage = SupportLanguage.JAVA;

        TemplateWriter templateWriter = templateRendererStrategySupport.getTemplateWriter(supportLanguage);

        assertTrue(templateWriter instanceof JavaTemplateWriter);
    }

    @Test
    void return_successfully_template_renderer_test() {
        SupportLanguage supportLanguage = SupportLanguage.JAVA;

        TemplateRenderer templateRenderer = templateRendererStrategySupport.getTemplateRenderer(supportLanguage);

        assertTrue(templateRenderer instanceof JavaTemplateRenderer);
    }

}