package com.sneydr.roomr_tenant.App.Validation;

public abstract class Validator<T> {


    protected int threshold;
    protected String errorMessage;


    public Validator() {
        this.threshold = 0;
    }

    public abstract boolean validate(T t);

    public String getErrorMessage() {
        return errorMessage;
    }

    protected boolean tryParse(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }


}
