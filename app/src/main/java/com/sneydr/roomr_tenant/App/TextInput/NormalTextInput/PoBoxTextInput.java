package com.sneydr.roomr_tenant.App.TextInput.NormalTextInput;

import android.view.View;

import com.sneydr.roomr_tenant.App.TextInput.TextInput;
import com.sneydr.roomr_tenant.App.Validation.EmptyStringValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooLongValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooShortValidator;


public class PoBoxTextInput extends TextInput {
    public PoBoxTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getValidationFacade().addValidator(new EmptyStringValidator("Please enter a po box number."));
        getValidationFacade().addValidator(new StringTooLongValidator("Please enter a po box number shorter than 10 characters.", 10));
        getValidationFacade().addValidator(new StringTooShortValidator("Please enter a po box number longer than 1 characters.", 1));


    }



}
