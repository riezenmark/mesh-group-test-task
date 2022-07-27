package ru.meshgroup.testtask.validation;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.meshgroup.testtask.exception.InvalidPropertyException;
import ru.meshgroup.testtask.validation.annotation.AllowedSortProperties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AllowedSortPropertiesValidator implements ConstraintValidator<AllowedSortProperties, Pageable> {
    private static final String INVALID_PROPERTY_EXCEPTION_MESSAGE = "Invalid sorting property.";

    private Set<String> allowedSortProperties;

    @Override
    public void initialize(AllowedSortProperties constraintAnnotation) {
        this.allowedSortProperties = new HashSet<>(Arrays.asList(constraintAnnotation.value()));
    }

    @Override
    public boolean isValid(Pageable pageable, ConstraintValidatorContext context) {
        if (pageable == null || allowedSortProperties.isEmpty()) {
            return true;
        }
        for (Sort.Order order : pageable.getSort()) {
            if (!allowedSortProperties.contains(order.getProperty())) {
                throw new InvalidPropertyException(INVALID_PROPERTY_EXCEPTION_MESSAGE);
            }
        }
        return true;
    }
}
