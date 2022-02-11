package com.example.eshop.validator;

import com.example.eshop.dto.OrderDto;
import com.example.eshop.model.CustomUserDetails;
import com.example.eshop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class UserOrdersValidator implements ConstraintValidator<UserOrdersConstraint, Object[]> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void initialize(UserOrdersConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext constraintValidatorContext) {

        if (objects[0] == null || objects[1] == null) {
            return true;
        }

        if (!(objects[0] instanceof Long) && !(objects[0] instanceof OrderDto)) {
            throw new IllegalArgumentException(
              "Illegal method signature, expected two parameters of types Long and Authentication.");
        }

        if (!(objects[1] instanceof Authentication)) {
            throw new IllegalArgumentException(
              "Illegal method signature, expected two parameters of types Long and Authentication.");
        }

        Long id;
        if (objects[0] instanceof Long) {
            id = ((Long) objects[0]);
        } else {
            id = ((OrderDto) objects[0]).getId();
        }

        Authentication authentication = (Authentication) objects[1];
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        boolean equals = orderRepository.existsOrderByIdAndUserEquals(id, userDetails.getUser());
        return equals;
    }
}
