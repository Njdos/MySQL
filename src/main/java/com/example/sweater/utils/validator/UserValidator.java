package com.example.sweater.utils.validator;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

public interface UserValidator {

    void validate(Object o, Errors errors);

    void bindingResultErrors(BindingResult bindingResult, Model model);
}
