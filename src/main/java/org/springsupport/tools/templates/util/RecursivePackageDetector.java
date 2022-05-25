package org.springsupport.tools.templates.util;

import java.io.File;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class RecursivePackageDetector {

	public String getRootPackage(@NotNull String path) {
		File file = new File(path);
		File[] subFiles = file.listFiles();

		if(subFiles.length == 1 && subFiles[0].isDirectory()) {
			return getRootPackage(path + "/" + subFiles[0].getName());
		}
		else {
			return path;
		}
	}

}
