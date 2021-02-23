package com.sneydr.roomr_tenant.App.Validation;

public class EmptyStringValidator extends Validator<String> {

    public EmptyStringValidator(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean validate(String s) {
        return !s.isEmpty();
    }
}
