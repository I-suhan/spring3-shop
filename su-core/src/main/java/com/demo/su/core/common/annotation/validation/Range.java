package com.demo.su.core.common.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RangeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    String message() default "输入值超出范围！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    double min() default Double.NEGATIVE_INFINITY;

    double max() default Double.POSITIVE_INFINITY;

    boolean required() default false;

    boolean includeMin() default true;//如果为 true [min false (min

    boolean includeMax() default true;//如果为 true  max] false  max)

}
