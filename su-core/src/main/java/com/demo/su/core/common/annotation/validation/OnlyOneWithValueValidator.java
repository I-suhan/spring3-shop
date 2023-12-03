package com.demo.su.core.common.annotation.validation;

import com.demo.su.core.common.annotation.validation.group.OnlyOne;
import com.demo.su.core.common.utils.SuStringUtils;
import com.demo.su.core.common.utils.ValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Arrays;

@Slf4j
public class OnlyOneWithValueValidator implements ConstraintValidator<OnlyOneWithValue, Object> {

    private String[] properties;

    @Override
    public void initialize(OnlyOneWithValue annotation) {
        properties = annotation.properties();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object == null) {
            return false;
        }

        HibernateConstraintValidatorContext hibernateContext = context.unwrap(HibernateConstraintValidatorContext.class);
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        int count = 0;
        String onlyPropertyName = null;

        for (String propertyName : properties) {
            Object value = beanWrapper.getPropertyValue(propertyName);

            if (value != null) {
                count ++;
                if (count == 1) {
                    onlyPropertyName = propertyName;
                } else {
                    String onlyProperties = SuStringUtils.listToStringWithSeparator(Arrays.asList(properties),"，");
                    hibernateContext.disableDefaultConstraintViolation(); // Disable default constraint violation
                    hibernateContext.buildConstraintViolationWithTemplate(onlyProperties + "必须且只能有一个")
                            .addConstraintViolation();
                    return false;  // Multiple properties have values
                }
            }
        }

        if (count == 1) {
            return ValidationUtils.validateField(object, onlyPropertyName, OnlyOne.class, context);
        }

        return false;  // No properties have values
    }
}