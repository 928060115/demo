package com.example.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author: rogue
 * @Description: 自定义参数验证器
 * @Package: com.example.demo.validator
 * @Date: 2017/12/6
 * @Time: 15:54
 */
@Documented
@Retention(
        RetentionPolicy.RUNTIME
)
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Constraint(validatedBy = DefinedValidatorClass.class)
public @interface DefinedValidator {
    //flag的有效值多个使用“,”隔开
    String values();
    //提示内容
    String message() default "flag不存在";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
