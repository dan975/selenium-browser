package demo.projects.test.framework.annotations;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Convenience annotation for @Scope("thread") annotation.
 */
@Target(value = {TYPE, METHOD})
@Retention(value = RUNTIME)
@Scope("thread")
public @interface ThreadScope {
}
