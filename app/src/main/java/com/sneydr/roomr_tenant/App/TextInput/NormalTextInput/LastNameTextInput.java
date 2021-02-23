package com.sneydr.roomr_tenant.App.TextInput.NormalTextInput;

import android.view.View;

import com.sneydr.roomr_tenant.App.TextInput.TextInput;
import com.sneydr.roomr_tenant.App.Validation.EmptyStringValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooLongValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooShortValidator;


public class LastNameTextInput extends TextInput {
    public LastNameTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getValidationFacade().addValidator(new EmptyStringValidator("Please enter a last name."));
        getValidationFacade().addValidator(new StringTooLongValidator("Please enter a last name shorter than 50 characters.", 50));
        getValidationFacade().addValidator(new StringTooShortValidator("Please enter a last name longer than 1 characters.", 1));

    }
}
