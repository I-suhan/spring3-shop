package com.demo.su.core.common.annotation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;

public class RangeValidator implements ConstraintValidator<Range, Object> {

    private double min;
    private double max;
    private boolean required;
    private boolean includeMin;
    private boolean includeMax;

    @Override
    public void initialize(Range constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.required = constraintAnnotation.required();
        this.includeMin = constraintAnnotation.includeMin();
        this.includeMax = constraintAnnotation.includeMax();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (required && value == null) {
            return true; // Allow null values
        }

        if (value instanceof Integer || value instanceof Double || value instanceof Long) {
            double numericValue = ((Number) value).doubleValue();
            return checkInRange(numericValue);
        } else if (value instanceof Collection<?> collectionValue) {
            return checkInRange(collectionValue.size());
        }

        return false; // Invalid value type
    }

    private boolean checkInRange(double value) {
        boolean minValid = includeMin ? value >= min : value > min;
        boolean maxValid = includeMax ? value <= max : value < max;
        return minValid && maxValid;
    }

}
