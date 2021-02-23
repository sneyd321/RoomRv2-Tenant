package com.sneydr.roomr_tenant.App.TextInput.AutoCompleteTextView;

import android.view.View;

import com.sneydr.roomr_tenant.App.Validation.EmptyStringValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooLongValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooShortValidator;

public class AddressAutoCompleteTextInput extends AutoCompleteTextInput {


    public AddressAutoCompleteTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getValidationFacade().addValidator(new EmptyStringValidator("Please enter an address."));
        getValidationFacade().addValidator(new StringTooLongValidator(
                "Please enter a password shorter than 50 characters.", 50));
        getValidationFacade().addValidator(new StringTooShortValidator(
                "Please enter a password longer than 3 characters.", 3));



    }

    @Override
    protected void setAdapter() {

    }




}
