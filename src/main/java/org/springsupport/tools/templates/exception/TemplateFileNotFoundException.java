package org.springsupport.tools.templates.exception;

import java.io.FileNotFoundException;

public class TemplateFileNotFoundException extends FileNotFoundException {

    private final String rootPath;

    public TemplateFileNotFoundException(String message, String rootPath) {
        super(message);
        this.rootPath = rootPath;
    }

    public String getRootPath() {
        return rootPath;
    }

}
