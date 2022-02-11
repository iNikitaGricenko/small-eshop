package com.example.eshop.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;


@Documented
@Target({ METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserOrdersValidator.class)
public @interface UserOrdersConstraint {
    String message() default "No access for this order";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
