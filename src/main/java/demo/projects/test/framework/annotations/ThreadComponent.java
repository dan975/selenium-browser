package demo.projects.test.framework.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Convenience annotation for @Component @ThreadScope.
 */
@Target(value = TYPE)
@Retention(value = RUNTIME)
@Component
@ThreadScope
public @interface ThreadComponent {
}
