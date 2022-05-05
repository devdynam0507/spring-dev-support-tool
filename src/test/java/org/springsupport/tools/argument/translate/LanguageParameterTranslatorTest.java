package org.springsupport.tools.argument.translate;

import org.junit.jupiter.api.Test;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.lang.LanguageNotSupportException;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.types.SpringStandardLayers;

import static org.junit.jupiter.api.Assertions.*;

class LanguageParameterTranslatorTest {

    private ParameterTranslator languageParameterTranslator = new LanguageParameterTranslator();

    @Test
    void check_arg_type_not_null_valid() {
        String validArgType1 = "-l";
        String validArgType2 = "-lang";

        boolean canTranslateArg1 = languageParameterTranslator.canTranslate(validArgType1);
        boolean canTranslateArg2 = languageParameterTranslator.canTranslate(validArgType2);

        assertTrue(canTranslateArg1);
        assertTrue(canTranslateArg2);
    }

    @Test
    void check_arg_type_null() {
        String validArgType1 = null;

        boolean canTranslate = languageParameterTranslator.canTranslate(validArgType1);

        assertFalse(canTranslate);
    }

    @Test
    void check_arg_type_invalid() {
        String validArgType1 = "ong?";

        boolean canTranslate = languageParameterTranslator.canTranslate(validArgType1);

        assertFalse(canTranslate);
    }

    @Test
    void check_valid_translate_lang_java() throws Exception {
        ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
        ArgumentMetadata argumentMetadata = new ArgumentMetadata(
                "-l", "java"
        );

        languageParameterTranslator.translate(context, argumentMetadata);

        assertEquals(SupportLanguage.JAVA, context.getLanguage());
    }

    @Test
    void check_valid_translate_lang_kotlin() throws Exception {
        ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
        ArgumentMetadata argumentMetadata = new ArgumentMetadata(
                "-l", "kotlin"
        );

        languageParameterTranslator.translate(context, argumentMetadata);

        assertEquals(SupportLanguage.KOTLIN, context.getLanguage());
    }

    @Test
    void check_valid_translate_jagged_lang() throws Exception {
        ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
        ArgumentMetadata argumentMetadata = new ArgumentMetadata(
                "-l", "JaVa"
        );

        languageParameterTranslator.translate(context, argumentMetadata);

        assertEquals(SupportLanguage.JAVA, context.getLanguage());
    }

    @Test
    void check_valid_translate_empty_string() throws Exception {
        ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
        ArgumentMetadata argumentMetadata = new ArgumentMetadata(
                "-l", ""
        );

        languageParameterTranslator.translate(context, argumentMetadata);

        assertEquals(SupportLanguage.JAVA, context.getLanguage());
    }

    @Test
    void check_valid_translate_invalid() throws Exception {
        ArgumentPipelineContext context = ArgumentPipelineContext.builder().build();
        ArgumentMetadata argumentMetadata = new ArgumentMetadata(
                "-l", "aaa"
        );

        assertThrows(LanguageNotSupportException.class, () -> languageParameterTranslator.translate(context, argumentMetadata));
    }

}