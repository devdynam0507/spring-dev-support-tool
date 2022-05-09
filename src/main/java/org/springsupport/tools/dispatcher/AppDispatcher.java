package org.springsupport.tools.dispatcher;

import javax.validation.constraints.NotNull;

public interface AppDispatcher {

    void doDispatch(@NotNull String... args);

}
