package org.springsupport.tools.dispatcher;

import org.springsupport.tools.AppDependencyConfiguration;
import org.springsupport.tools.argument.resolver.DefaultArgumentResolver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springsupport.tools.templates.strategy.TemplateStrategySupport;


class DefaultAppDispatcherTest {

	ApplicationContext ac = new AnnotationConfigApplicationContext(AppDependencyConfiguration.class);
	DefaultAppDispatcher defaultAppDispatcher = new DefaultAppDispatcher(
		ac.getBean(DefaultArgumentResolver.class),
		ac.getBean(TemplateStrategySupport.class)
	);

}