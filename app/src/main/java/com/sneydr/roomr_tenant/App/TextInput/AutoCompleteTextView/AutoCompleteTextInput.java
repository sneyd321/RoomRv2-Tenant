package com.sneydr.roomr_tenant.App.TextInput.AutoCompleteTextView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.sneydr.roomr_tenant.App.TextInput.TextInput;


public abstract class AutoCompleteTextInput extends TextInput {


    protected AutoCompleteTextView editText;


    public AutoCompleteTextInput(View view, int layoutId, int editTextId) {
        super(view, layoutId, editTextId);
        this.editText = view.findViewById(editTextId);
        editText.setOnItemClickListener(onAutoCompleteItemSelected);
    }


    AdapterView.OnItemClickListener onAutoCompleteItemSelected = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };

    protected abstract void setAdapter();


}
