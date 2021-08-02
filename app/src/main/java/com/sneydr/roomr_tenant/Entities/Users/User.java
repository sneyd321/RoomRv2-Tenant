package com.sneydr.roomr_tenant.Entities.Users;



import androidx.annotation.Nullable;

import java.lang.reflect.AccessibleObject;
import java.util.Objects;


public abstract class User extends AccessibleObject {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phoneNumber;
    protected String authToken;
    @Nullable
    private String imageURL;


    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAuthToken() { return authToken; }
    @Nullable
    public String getImageURL() { return imageURL; }
}
