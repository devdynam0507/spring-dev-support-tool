package org.springsupport.tools.templates.strategy;

import org.junit.jupiter.api.Test;
import org.springsupport.tools.lang.SupportLanguage;

import static org.junit.jupiter.api.Assertions.*;

class TemplateWriterNameResolverTest {

    TemplateWriterNameResolver writerNameResolver = new TemplateWriterNameResolver();

    @Test
    void success_resolved_java_writer_name_test() {
        SupportLanguage language = SupportLanguage.JAVA;

        String templateClassName = writerNameResolver.getName(language);

        assertEquals("javaTemplateWriter", templateClassName);
    }

}