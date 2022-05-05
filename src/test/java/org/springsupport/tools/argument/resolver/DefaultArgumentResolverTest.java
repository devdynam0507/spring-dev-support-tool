package org.springsupport.tools.argument.resolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springsupport.tools.AppDependencyConfiguration;
import org.springsupport.tools.argument.dto.ArgumentPipelineContext;
import org.springsupport.tools.argument.exception.InvalidExcludeParameterException;
import org.springsupport.tools.lang.SupportLanguage;
import org.springsupport.tools.types.SpringStandardLayers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultArgumentResolverTest {

    private ArgumentResolver resolver;

    @BeforeEach
    public void beforeEach() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppDependencyConfiguration.class);
        resolver = applicationContext.getBean(DefaultArgumentResolver.class);
    }

    @Test
    public void success_resolve() throws Exception {
        String[] args = new String[] {
                "-n", "Order", "-l", "java"
        };

        ArgumentPipelineContext context = resolver.resolve(args);

        assertEquals("Order", context.getName());
        assertEquals(SupportLanguage.JAVA, context.getLanguage());
    }

    @Test
    public void failed_resolve() throws Exception {
        String[] args = new String[] {
                "-n", "Order", "-l", "java", "-e", "java, service"
        };

        assertThrows(InvalidExcludeParameterException.class, () -> resolver.resolve(args));
    }

    @Test
    public void default_name() throws Exception {
        String[] args = new String[] { };

        ArgumentPipelineContext context = resolver.resolve(args);

        assertEquals("Some", context.getName());
    }

    @Test
    public void default_include_layers() throws Exception {
        String[] args = new String[] { };
        List<SpringStandardLayers> defaultIncludeLayers = Arrays.asList(
                SpringStandardLayers.CONTROLLER,
                SpringStandardLayers.DOMAIN,
                SpringStandardLayers.REPOSITORY,
                SpringStandardLayers.SERVICE
        );

        ArgumentPipelineContext context = resolver.resolve(args);
        List<SpringStandardLayers> includedLayers = context.getIncludeLayers();

        assertEquals(defaultIncludeLayers.size(), includedLayers.size());
        for(int i = 0; i < defaultIncludeLayers.size(); i++) {
            assertEquals(defaultIncludeLayers.get(i), includedLayers.get(i));
        }
    }

    @Test
    public void exclude_layers_service() throws Exception {
        String[] args = new String[] { "-e", "service" };

        ArgumentPipelineContext context = resolver.resolve(args);
        List<SpringStandardLayers> includedLayers = context.getIncludeLayers();

        assertFalse(includedLayers.contains(SpringStandardLayers.SERVICE));
    }

    @Test
    public void exclude_layers_controller() throws Exception {
        String[] args = new String[] { "-e", "service" };

        ArgumentPipelineContext context = resolver.resolve(args);
        List<SpringStandardLayers> includedLayers = context.getIncludeLayers();

        assertFalse(includedLayers.contains(SpringStandardLayers.CONTROLLER));
    }

    @Test
    public void exclude_layers_domain() throws Exception {
        String[] args = new String[] { "-e", "domain" };

        ArgumentPipelineContext context = resolver.resolve(args);
        List<SpringStandardLayers> includedLayers = context.getIncludeLayers();

        assertFalse(includedLayers.contains(SpringStandardLayers.DOMAIN));
    }

    @Test
    public void exclude_layers_repository() throws Exception {
        String[] args = new String[] { "-e", "repository" };

        ArgumentPipelineContext context = resolver.resolve(args);
        List<SpringStandardLayers> includedLayers = context.getIncludeLayers();

        assertFalse(includedLayers.contains(SpringStandardLayers.REPOSITORY));
    }

    @Test
    public void exclude_layers_multiple() throws Exception {
        String[] args = new String[] { "-e", "service, domain, repository" };

        ArgumentPipelineContext context = resolver.resolve(args);
        List<SpringStandardLayers> includedLayers = context.getIncludeLayers();

        assertFalse(includedLayers.contains(SpringStandardLayers.SERVICE));
        assertFalse(includedLayers.contains(SpringStandardLayers.DOMAIN));
        assertFalse(includedLayers.contains(SpringStandardLayers.REPOSITORY));
    }

    @Test
    public void exclude_layers_empty() throws Exception {
        String[] args = new String[] { "-e", "service, domain, repository, controller" };

        ArgumentPipelineContext context = resolver.resolve(args);
        List<SpringStandardLayers> includedLayers = context.getIncludeLayers();

        assertTrue(includedLayers.isEmpty());
        assertFalse(includedLayers.contains(SpringStandardLayers.SERVICE));
        assertFalse(includedLayers.contains(SpringStandardLayers.DOMAIN));
        assertFalse(includedLayers.contains(SpringStandardLayers.REPOSITORY));
        assertFalse(includedLayers.contains(SpringStandardLayers.CONTROLLER));
    }

}