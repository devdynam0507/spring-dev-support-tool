package org.springsupport.tools.templates;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.templates.files.TemplateContent;
import org.springsupport.tools.templates.files.TemplateFileReader;
import org.springsupport.tools.types.SpringStandardLayers;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

class DefaultTemplateRendererTest {

    TemplateFileReader fileReader = new TemplateFileReader();
    TemplateRenderer templateRenderer = new JavaTemplateRenderer(fileReader);

    @Test
    void get_template_file_test() throws FileNotFoundException {
        ArgumentPipelineContext context = ArgumentPipelineContext.builder()
                        .includeLayers(Arrays.asList(
                                SpringStandardLayers.CONTROLLER, SpringStandardLayers.DOMAIN, SpringStandardLayers.REPOSITORY, SpringStandardLayers.SERVICE
                        ))
                        .language(SupportLanguage.JAVA)
                        .name("Awesome")
                        .build();

        List<TemplateContent> contents = templateRenderer.render("", context);

        assertEquals(contents.size(), 4);
        assertTrue(contents.get(0).getContent().contains("Awesome"));
        assertTrue(contents.get(1).getContent().contains("Awesome"));
        assertTrue(contents.get(2).getContent().contains("Awesome"));
        assertTrue(contents.get(3).getContent().contains("Awesome"));
    }

}