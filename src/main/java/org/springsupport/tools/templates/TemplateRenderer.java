package org.springsupport.tools.templates;

import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.templates.files.TemplateContent;

import java.io.FileNotFoundException;
import java.util.List;

public interface TemplateRenderer {

     String CLASS_NAME_KEY = "{name}";

     List<TemplateContent> render(String filePath, ArgumentPipelineContext argumentPipelineContext) throws FileNotFoundException;

     String replaceElement(String content, String key, String value);

}
