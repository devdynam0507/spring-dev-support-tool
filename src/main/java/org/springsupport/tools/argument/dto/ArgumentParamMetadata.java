package org.springsupport.tools.argument.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ArgumentParamMetadata {

    private List<String> params;

    public ArgumentParamMetadata() {
        this.params = new ArrayList<>();
    }

    public void addParam(@NotNull @NotEmpty String param) {
        params.add(param);
    }

}
