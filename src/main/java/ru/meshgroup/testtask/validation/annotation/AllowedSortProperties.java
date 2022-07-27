package ru.meshgroup.testtask.validation.annotation;

import ru.meshgroup.testtask.validation.AllowedSortPropertiesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {AllowedSortPropertiesValidator.class})
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface AllowedSortProperties {
    String message() default "{ru.meshgroup.testtask.validation.annotation.AllowedSortProperties.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();
}
