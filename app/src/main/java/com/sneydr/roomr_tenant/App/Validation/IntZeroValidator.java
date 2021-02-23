package com.sneydr.roomr_tenant.App.Validation;

public class IntZeroValidator extends Validator<Integer> {

    public IntZeroValidator(String errorMessage)  {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean validate(Integer integer) {
        return integer != 0;
    }
}
