package com.sneydr.roomr_tenant.App.TextInput.NumberTextInput;

import android.view.View;

import com.sneydr.roomr_tenant.App.Validation.IntTooHighValidator;
import com.sneydr.roomr_tenant.App.Validation.IntTooLowValidator;

public class HouseIdNumberTextInput extends NumberTextInput {

    public HouseIdNumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getValidationFacade().addValidator(new IntTooHighValidator(
                "Please enter a valid house id.", Integer.MAX_VALUE));
        getValidationFacade().addValidator(new IntTooLowValidator(
                "Please enter a house id greater than 0.", 0));
    }
}
