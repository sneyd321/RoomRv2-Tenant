package com.sneydr.roomr_tenant.App.Validation;

public class IntTooHighValidator extends Validator<Integer> {


    public IntTooHighValidator(String errorMessage, int threshold) {
        this.errorMessage = errorMessage;
        this.threshold = threshold;
    }

    @Override
    public boolean validate(Integer integer) {
        return integer <= threshold;
    }
}
