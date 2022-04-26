package org.springsupport.tools;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanDefinitionTest {

    @Test
    @DisplayName("Spring Bean이 정상적으로 등록되는지")
    void beanRegisterSuccess() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfiguration.class);
        TestBean testBean = (TestBean) ac.getBean("testBean");

        Assertions.assertEquals(testBean.getClass(), TestBean.class);
    }

    @Data
    static class TestBean { }

    @Configuration
    public static class TestConfiguration {
        @Bean
        public TestBean testBean() {
            return new TestBean();
        }
    }

}
