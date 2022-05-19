package org.springsupport.tools.templates.files;

import org.junit.jupiter.api.Test;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.types.SpringStandardLayers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplateFileReaderTest {

    TemplateFileReader fileReader = new TemplateFileReader();

    @Test
    void get_template_file_test() throws FileNotFoundException {
        File[] files = TemplateFileReader.getTemplateFiles();

        assertEquals(files[0].getName(), "Controller.java");
        assertEquals(files[1].getName(), "Service.java");
        assertEquals(files[2].getName(), "Repository.java");
        assertEquals(files[3].getName(), "Domain.java");
    }

    @Test
    void read_file_success() throws FileNotFoundException {
        List<SpringStandardLayers> layers = Arrays.asList(
                SpringStandardLayers.CONTROLLER, SpringStandardLayers.DOMAIN, SpringStandardLayers.REPOSITORY, SpringStandardLayers.SERVICE
        );
        SupportLanguage language = SupportLanguage.JAVA;

        List<TemplateContent> contents = fileReader.readAllFiles("", layers, language);

        assertEquals(contents.size(), 4);
    }

}