package org.springsupport.tools.templates.strategy;

import org.springsupport.tools.lang.SupportLanguage;

import javax.validation.constraints.NotNull;

public interface TemplateStrategyNameResolver {

    String getName(@NotNull SupportLanguage supportLanguage);

}
