package com.sneydr.roomr_tenant.App.TextInput.NormalTextInput;

import android.view.View;

import com.sneydr.roomr_tenant.App.TextInput.TextInput;
import com.sneydr.roomr_tenant.App.Validation.EmptyStringValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooLongValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooShortValidator;


public class PostalCodeTextInput extends TextInput {
    public PostalCodeTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getValidationFacade().addValidator(new EmptyStringValidator("Please enter a postal code."));
        getValidationFacade().addValidator(new StringTooLongValidator("Please enter a postal code shorter than 8 characters.", 8));
        getValidationFacade().addValidator(new StringTooShortValidator("Please enter a postal code longer than 6 characters.", 6));


    }





}
