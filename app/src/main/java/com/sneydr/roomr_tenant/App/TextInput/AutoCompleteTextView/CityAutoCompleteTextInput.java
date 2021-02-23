package com.sneydr.roomr_tenant.App.TextInput.AutoCompleteTextView;

import android.view.View;
import android.widget.ArrayAdapter;

import com.sneydr.roomr_tenant.App.Validation.EmptyStringValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooLongValidator;
import com.sneydr.roomr_tenant.App.Validation.StringTooShortValidator;
import com.sneydr.roomr_tenant.R;

public class CityAutoCompleteTextInput extends AutoCompleteTextInput {

    public CityAutoCompleteTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        setAdapter();
        getValidationFacade().addValidator(new EmptyStringValidator("Please enter a city."));
        getValidationFacade().addValidator(new StringTooLongValidator(
                "Please enter a password shorter than 50 characters.", 50));
        getValidationFacade().addValidator(new StringTooShortValidator(
                "Please enter a password longer than 1 characters.", 1));
    }

    @Override
    protected void setAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getView().getContext(),android.R.layout.select_dialog_item, getView().getContext().getResources().getStringArray(R.array.ON_cities));
        this.editText.setAdapter(adapter);
    }



}
