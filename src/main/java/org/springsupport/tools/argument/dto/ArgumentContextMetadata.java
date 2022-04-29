package org.springsupport.tools.argument.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArgumentContextMetadata {

    private List<String> contexts;

    public ArgumentContextMetadata() {
        this.contexts = new ArrayList<>();
    }

}
