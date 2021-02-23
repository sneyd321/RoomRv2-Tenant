package com.sneydr.roomr_tenant.App.TextInput.NormalTextInput;

import android.view.View;

import com.sneydr.roomr_tenant.App.TextInput.TextInput;
import com.sneydr.roomr_tenant.App.Validation.EmptyStringValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooLongValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooShortValidator;


public class RentMadePayableTextInput extends TextInput {
    public RentMadePayableTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        getValidationFacade().addValidator(new EmptyStringValidator("Please enter a payee name."));
        getValidationFacade().addValidator(new StringTooLongValidator(
                "Please enter a payee name shorter than 50 characters.", 50));
        getValidationFacade().addValidator(new StringTooShortValidator(
                "Please enter a payee name longer than 1 characters.", 1));

    }

    @Override
    public void invokeValidation() {
        if (getLayout().isEnabled()){
            super.invokeValidation();
            return;
        }
        getLayout().setError(null);
    }
}
