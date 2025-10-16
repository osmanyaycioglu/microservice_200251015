package org.training.microservice.tc.msorder.input.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CheckWordsImpl.class)
public @interface CheckWords {
    String[] value();

    String message() default "yasaklı kelime var. Bunları giremezsiniz $value";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
