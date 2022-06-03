package org.springsupport.tools.dispatcher;

import org.junit.jupiter.api.Test;
import org.springsupport.tools.AppDependencyConfiguration;
import org.springsupport.tools.argument.parser.LoopArgumentParser;
import org.springsupport.tools.argument.resolver.DefaultArgumentResolver;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.templates.JavaTemplateRenderer;
import org.springsupport.tools.templates.JavaTemplateWriter;
import org.springsupport.tools.templates.TemplateRenderer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springsupport.tools.templates.TemplateWriter;

import static org.junit.jupiter.api.Assertions.*;

class DefaultAppDispatcherTest {

	ApplicationContext ac = new AnnotationConfigApplicationContext(AppDependencyConfiguration.class);
	DefaultAppDispatcher defaultAppDispatcher = new DefaultAppDispatcher(
		ac.getBean(DefaultArgumentResolver.class),
		ac.getBeansOfType(TemplateRenderer.class),
		ac.getBeansOfType(TemplateWriter.class)
	);

	@Test
	void get_template_strategy_java() {
		SupportLanguage javaLang = SupportLanguage.JAVA;

		TemplateRenderer templateRenderer = defaultAppDispatcher.getTemplateRendererStrategy(javaLang);

		assertTrue(templateRenderer instanceof JavaTemplateRenderer);
	}

	@Test
	void get_template_writer_strategy_java() {
		SupportLanguage javaLang = SupportLanguage.JAVA;

		TemplateWriter templateWriter = defaultAppDispatcher.getTemplateWriterStrategy(javaLang);

		assertTrue(templateWriter instanceof JavaTemplateWriter);
	}

}