package com.demo.su.core.common.utils;

import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@Slf4j
public class ValidationUtils {

    public static <T> boolean validateField(T obj, String fieldName, Class<?> groupClass, ConstraintValidatorContext context) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //验证指定属性
        Set<ConstraintViolation<T>> violations = validator.validateProperty(
                obj,
                fieldName,
                groupClass
        );

        boolean isValid = violations.isEmpty();
        if(!isValid){
            for (ConstraintViolation<T> violation : violations) {
                context.disableDefaultConstraintViolation();
                String messageTemplate = violation.getMessageTemplate();
                context.buildConstraintViolationWithTemplate(fieldName + " " +messageTemplate)
                        .addConstraintViolation();
            }
        }

        factory.close();

        return isValid;
    }

    public static <T> boolean validateWithDefault(List<T> list) {
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        for (T dto : list) {
            // 使用Validator对对象进行验证
            Set<ConstraintViolation<T>> violations = validator.validate(dto);

            if (!violations.isEmpty()) {
                // 处理验证失败的情况
                violations.forEach(c->{
                    String sb = "<" +
                            c.getRootBeanClass().getSimpleName() +
                            "> 中 <" +
                            c.getPropertyPath().toString() +
                            "> 验证错误！错误信息——> " + c.getMessageTemplate();
                    log.error(sb);
                });
                return false;
            }
        }
        return true;
    }

    public static <T> boolean validateWithDefault(T obj) {
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        // 使用Validator对对象进行验证
        Set<ConstraintViolation<T>> violations = validator.validate(obj);
        if (!violations.isEmpty()) {
            // 处理验证失败的情况
            log.warn("valid failed!");
            return false;
        }
        return true;
    }
}
