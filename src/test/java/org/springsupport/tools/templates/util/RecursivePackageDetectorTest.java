package org.springsupport.tools.templates.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecursivePackageDetectorTest {

	RecursivePackageDetector recursivePackageDetector = new RecursivePackageDetector();

	@Test
	void get_package_name() {
		String currentDir = System.getProperty("user.dir") + "/src/test/java";

		String packageName = recursivePackageDetector.getRootPackage(currentDir);

		assertEquals(currentDir + "/org/springsupport/tools", packageName);
	}

}