package org.springsupport.tools.templates;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springsupport.tools.templates.files.TemplateContent;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JavaTemplateWriter implements TemplateWriter {

	@Override
	public void write(List<TemplateContent> renderedContents) {
		String currentDir = System.getProperty("user.dir");
		write(currentDir, renderedContents);
	}

	@Override
	public void write(String rootPath, List<TemplateContent> renderedContents) {
		System.out.println("");
	}

}
