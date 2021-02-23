package com.sneydr.roomr_tenant.App.Validation;

public class StringTooShortValidator extends Validator<String> {

    public StringTooShortValidator(String errorMessage, int threshold){
        this.errorMessage = errorMessage;
        this.threshold = threshold;
    }

    @Override
    public boolean validate(String s) {
        return s.length() >= threshold;
    }
}
