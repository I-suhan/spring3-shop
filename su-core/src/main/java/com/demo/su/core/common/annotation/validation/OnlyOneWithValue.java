package com.demo.su.core.common.annotation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.GroupSequence;
import jakarta.validation.Payload;
import jakarta.validation.groups.Default;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {OnlyOneWithValueValidator.class})
@GroupSequence({OnlyOneWithValue.class, Default.class})
public @interface OnlyOneWithValue {

    String message() default "系统验证不通过";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String[] properties() default {};    //需要判断的属性名
}
