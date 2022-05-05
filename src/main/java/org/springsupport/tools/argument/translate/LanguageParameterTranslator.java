package org.springsupport.tools.argument.translate;

import org.springframework.stereotype.Component;
import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.lang.LanguageNotSupportException;
import org.springsupport.tools.lang.SupportLanguage;

import javax.validation.constraints.NotNull;
import java.util.Locale;

@Component
public class LanguageParameterTranslator implements ParameterTranslator {

    @Override
    public boolean canTranslate(String argType) {
        return argType != null && (argType.equals("-l") || argType.equals("-lang"));
    }

    @Override
    public void translate(ArgumentPipelineContext argumentPipelineContext, ArgumentMetadata targetArgumentMetadata) throws Exception {
        if(targetArgumentMetadata == null || targetArgumentMetadata.getArgParams().length() == 0) {
            argumentPipelineContext.setLanguage(SupportLanguage.JAVA);
            return;
        }
        String param = targetArgumentMetadata.getArgParams().toUpperCase(Locale.ROOT);
        checkInvalidLangTypeIfNotThrows(param);
        argumentPipelineContext.setLanguage(SupportLanguage.valueOf(param));
    }

    protected void checkInvalidLangTypeIfNotThrows(@NotNull String languageName) throws LanguageNotSupportException {
        SupportLanguage[] supportLanguages = SupportLanguage.values();

        for(SupportLanguage language : supportLanguages) {
            if(language.name().equals(languageName)) {
               return;
            }
        }

        throw new LanguageNotSupportException("Language not support: " + languageName, languageName);
    }

}
