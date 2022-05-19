package org.springsupport.tools.templates;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.templates.exception.TemplateFileNotFoundException;
import org.springsupport.tools.templates.files.TemplateContent;
import org.springsupport.tools.templates.files.TemplateFileReader;

import java.io.FileNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JavaTemplateRenderer implements TemplateRenderer {

    private final TemplateFileReader fileReader;

    @Override
    public List<TemplateContent> render(String filePath, ArgumentPipelineContext argumentPipelineContext) throws FileNotFoundException {
        List<TemplateContent> contents;
        try {
            contents = fileReader.readAllFiles("", argumentPipelineContext.getIncludeLayers(), argumentPipelineContext.getLanguage());

            for(TemplateContent content : contents) {
                String contentStr = content.getContent();

                contentStr = replaceElement(contentStr, CLASS_NAME_KEY, argumentPipelineContext.getName());
                content.setContent(contentStr);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        return contents;
    }

    @Override
    public String replaceElement(String content, String key, String value) {
        return content.replace(key, value);
    }

}
