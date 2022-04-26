package org.springsupport.tools.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

public class ComponentScanTest {

    @Test
    @DisplayName("컴포넌트 스캔이 잘 되는지")
    void componentScanSuccess() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentScanConfiguration.class);

        Object testComponent = ac.getBean("componentScanTest.TestComponent");

        Assertions.assertEquals(testComponent.getClass(), TestComponent.class);
    }

    @ComponentScan(basePackageClasses = {ComponentScanTest.class})
    static class ComponentScanConfiguration { }

    @Component
    static class TestComponent { }

}
