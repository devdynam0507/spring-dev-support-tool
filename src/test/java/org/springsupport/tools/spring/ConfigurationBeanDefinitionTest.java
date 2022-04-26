package org.springsupport.tools.spring;

import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationBeanDefinitionTest {

    @Test
    @DisplayName("Spring Bean이 정상적으로 등록되는지")
    void beanRegisterSuccess() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfiguration.class);

        TestBean testBean = (TestBean) ac.getBean("testBean");

        assertEquals(testBean.getClass(), TestBean.class);
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
