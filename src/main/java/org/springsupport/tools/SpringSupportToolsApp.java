package org.springsupport.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springsupport.tools.dispatcher.AppDispatcher;

public class SpringSupportToolsApp {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppDependencyConfiguration.class);
		AppDispatcher appDispatcher = context.getBean(AppDispatcher.class);

		appDispatcher.doDispatch(args);
	}

}
