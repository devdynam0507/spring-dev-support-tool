package org.springsupport.tools.templates;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springsupport.tools.templates.files.TemplateContent;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@RequiredArgsConstructor
public class JavaTemplateWriter implements TemplateWriter {

	@Override
	public void write(List<TemplateContent> renderedContents, String packageName) {
		String currentDir = System.getProperty("user.dir");
		write(packageName, currentDir, renderedContents);
	}

	@Override
	public void write(String packageName, String rootPath, List<TemplateContent> renderedContents) {
		try {
			for(TemplateContent templateContent : renderedContents) {
				FileWriter fileWriter = getFileWriter(rootPath + "/" + packageName, templateContent.getTemplateName());
				fileWriter.write(templateContent.getContent());
				fileWriter.flush();
				fileWriter.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FileWriter getFileWriter(@NotNull final String rootDirectory, String fileNameWithDirectory) throws IOException {
		FileWriter fileWriter = new FileWriter(rootDirectory + fileNameWithDirectory);
		return fileWriter;
	}

}
