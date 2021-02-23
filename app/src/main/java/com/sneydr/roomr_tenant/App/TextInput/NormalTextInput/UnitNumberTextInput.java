package com.sneydr.roomr_tenant.App.TextInput.NormalTextInput;

import android.view.View;

import com.sneydr.roomr_tenant.App.TextInput.TextInput;
import com.sneydr.roomr_tenant.App.Validation.EmptyStringValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooLongValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooShortValidator;


public class UnitNumberTextInput extends TextInput {
    public UnitNumberTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getValidationFacade().addValidator(new EmptyStringValidator("Please enter a unit number."));
        getValidationFacade().addValidator(new StringTooLongValidator(
                "Please enter a unit number shorter than 50 characters.", 50));
        getValidationFacade().addValidator(new StringTooShortValidator(
                "Please enter a unit number longer than 1 characters.", 1));

    }

}
