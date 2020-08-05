package demo.projects.test.framework.annotations;

import org.springframework.context.annotation.Bean;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Convenience annotation for @Bean @ThreadScope.
 */
@Target(value = {METHOD, ANNOTATION_TYPE})
@Retention(value = RUNTIME)
@Bean
@ThreadScope
public @interface ThreadBean {
}
