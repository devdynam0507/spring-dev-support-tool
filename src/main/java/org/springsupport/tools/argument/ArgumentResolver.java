package org.springsupport.tools.argument;

import javax.validation.constraints.NotNull;

public interface ArgumentResolver {

    void resolve(@NotNull final String... args);

}
