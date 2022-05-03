package org.springsupport.tools.argument.dto;

import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.types.SpringStandardLayers;

@Data
@Builder
public class ArgumentPipelineContext {

	private String name;
	private SupportLanguage language;
	private List<SpringStandardLayers> includeLayers;

}
