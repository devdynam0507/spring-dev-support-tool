package org.springsupport.tools.templates.files;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.types.SpringStandardLayers;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TemplateFileReader {

    public static File[] getTemplateFiles() throws FileNotFoundException {
        return new File[] {
                ResourceUtils.getFile("classpath:templates/Controller.java"),
                ResourceUtils.getFile("classpath:templates/Service.java"),
                ResourceUtils.getFile("classpath:templates/Repository.java"),
                ResourceUtils.getFile("classpath:templates/Domain.java")
        };
    }

    public List<TemplateContent> readAllFiles(@NotNull final String rootPath, @NotEmpty List<SpringStandardLayers> layers, @NotNull SupportLanguage supportLanguage) throws FileNotFoundException {
        File[] files = getTemplateFiles();
        List<TemplateContent> templateContent = new ArrayList<>();
        try {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String extension = getExtension(file.getName());
                if(!extension.equalsIgnoreCase(supportLanguage.name()) || !isContainsLayer(file.getName(), layers)) {
                    continue;
                }

                String lines = readAllLines(file);
                templateContent.add(new TemplateContent(getFileNameWithoutExtension(file.getName()), lines));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return templateContent;
    }

    private boolean isContainsLayer(String fileName, List<SpringStandardLayers> layers) {
        String fileNameWithoutExtension = getFileNameWithoutExtension(fileName);
        for(SpringStandardLayers layer : layers) {
            if(layer.name().equalsIgnoreCase(fileNameWithoutExtension)) {
                return true;
            }
        }
        return false;
    }

    private String readAllLines(File file) throws IOException {
        BufferedReader bufferedReader = getFileReader(file);
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = bufferedReader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        bufferedReader.close();

        return sb.toString();
    }

    private String getExtension(String fileName) {
        int i = fileName.lastIndexOf(".");
        if(i > 0) {
            return fileName.substring(i + 1);
        }

        return fileName;
    }

    private String getFileNameWithoutExtension(String fileName) {
        return fileName.split("[.]")[0];
    }

    private BufferedReader getFileReader(File file) throws FileNotFoundException {
        final int BUFFER_SIZE = 32 * 1024; // 32 KB
        return new BufferedReader(new FileReader(file), BUFFER_SIZE);
    }

}
