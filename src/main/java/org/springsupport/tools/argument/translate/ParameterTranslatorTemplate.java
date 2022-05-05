package org.springsupport.tools.argument.translate;

import org.springsupport.tools.argument.dto.ArgumentMetadata;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;

import java.util.List;

public abstract class ParameterTranslatorTemplate implements ParameterTranslator {

    @Override
    public void translateMany(ArgumentPipelineContext argumentPipelineContext, List<ArgumentMetadata> argumentMetadataList) throws Exception {
        for(ArgumentMetadata argumentMetadata : argumentMetadataList) {
            if(canTranslate(argumentMetadata.getArgType())) {
                translate(argumentPipelineContext, argumentMetadata);
            }
        }
    }

}
