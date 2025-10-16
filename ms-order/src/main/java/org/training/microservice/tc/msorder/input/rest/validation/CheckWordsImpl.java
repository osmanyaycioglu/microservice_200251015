package org.training.microservice.tc.msorder.input.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class CheckWordsImpl implements ConstraintValidator<CheckWords,String> {
    private CheckWords anno;
    @Override
    public void initialize(final CheckWords constraintAnnotation) {
        anno = constraintAnnotation;
    }

    @Override
    public boolean isValid(final String value,
                           final ConstraintValidatorContext context) {
        return Arrays.stream(anno.value()).noneMatch(value::contains);
    }
}
