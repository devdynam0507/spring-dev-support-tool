package org.springsupport.tools.argument.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.types.SpringStandardLayers;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class ArgumentPipelineContext {

	private String name;
	private SupportLanguage language;
	private List<SpringStandardLayers> includeLayers;

	public void exclude(@NotNull SpringStandardLayers standardLayer) {
		if(includeLayers != null && includeLayers.size() > 0) {
			includeLayers.removeIf(i -> i == standardLayer);
		}
	}

	public void setDefaultIncludeLayers() {
		this.includeLayers = new ArrayList<>(
				Arrays.asList(
						SpringStandardLayers.CONTROLLER,
						SpringStandardLayers.DOMAIN,
						SpringStandardLayers.REPOSITORY,
						SpringStandardLayers.SERVICE
				)
		);
	}

}
