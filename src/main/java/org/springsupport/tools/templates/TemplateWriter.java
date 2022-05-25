package org.springsupport.tools.templates;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springsupport.tools.templates.files.TemplateContent;

public interface TemplateWriter {

	void write(@NotNull @NotEmpty List<TemplateContent> renderedContents);

	void write(@NotNull String rootPath, @NotNull @NotEmpty List<TemplateContent> renderedContents);

}
