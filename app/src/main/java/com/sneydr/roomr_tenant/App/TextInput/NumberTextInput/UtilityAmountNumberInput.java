package com.sneydr.roomr_tenant.App.TextInput.NumberTextInput;

import android.view.View;

import com.sneydr.roomr_tenant.App.Validation.IntTooHighValidator;
import com.sneydr.roomr_tenant.App.Validation.IntTooLowValidator;
import com.sneydr.roomr_tenant.App.Validation.IntZeroValidator;


public class UtilityAmountNumberInput extends NumberTextInput {
    public UtilityAmountNumberInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getValidationFacade().addValidator(new IntZeroValidator("Please enter an amount."));
        getValidationFacade().addValidator(new IntTooHighValidator(
                "Please enter an amount smaller than 100.", 100));
        getValidationFacade().addValidator(new IntTooLowValidator(
                "Please enter an amount larger than 0.", 0));
    }
}
