package org.springsupport.tools.templates;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.templates.files.TemplateFileReader;

@Component
@RequiredArgsConstructor
public class DefaultTemplateRenderer implements TemplateRenderer {

    private final TemplateFileReader fileReader;

    @Override
    public void render(String filePath, ArgumentPipelineContext argumentPipelineContext) {
    }

    @Override
    public String replaceElement(String content, String key, String value) {
        return content.replace(key, value);
    }

}
