package org.springsupport.tools.argument;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArgumentMetadata {

    @NotNull
    private String argType;

    @NotNull
    private String argParams;

}
