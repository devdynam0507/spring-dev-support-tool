package org.springsupport.tools.templates;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springsupport.tools.templates.files.TemplateContent;

public interface TemplateWriter {

	void write(@NotNull @NotEmpty List<TemplateContent> renderedContents, @NotNull @NotEmpty String packageName);

	void write(@NotNull @NotEmpty String packageName, @NotNull String rootPath, @NotNull @NotEmpty List<TemplateContent> renderedContents);

}
