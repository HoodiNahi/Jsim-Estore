package com.hoodinahi.store.User;

import com.hoodinahi.store.User.Lowercase;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.val;

public class LowerCaseValidator implements ConstraintValidator<Lowercase, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub
        if(value == null) return true;

        return value.equals(value.toLowerCase());
    }
    
}
