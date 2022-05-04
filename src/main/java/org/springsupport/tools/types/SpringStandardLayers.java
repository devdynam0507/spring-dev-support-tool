package org.springsupport.tools.types;

import java.util.Locale;

import javax.validation.constraints.NotNull;

public enum SpringStandardLayers {

	SERVICE, CONTROLLER, DOMAIN, REPOSITORY;

	public static boolean isValid(@NotNull String argParam) {
		if(argParam == null) {
			return false;
		}
		SpringStandardLayers[] standardLayers = SpringStandardLayers.values();
		argParam = argParam.toUpperCase(Locale.ROOT);

		for(SpringStandardLayers standardLayer : standardLayers) {
			if(standardLayer.name().equals(argParam)) {
				return true;
			}
		}
		return false;
	}

}
