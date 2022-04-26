package org.springsupport.tools.argument.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class ArgumentMetadata {

    @NotNull
    private String argType;

    @NotNull
    private String argParams;

}
