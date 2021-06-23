package com.sneydr.roomr_tenant.Entities.Login;


public class Login {
    private transient String email;
    private transient String password;
    private int houseId;

    public Login(String email, String password, int houseId){
        this.email = email;
        this.password = password;
        this.houseId = houseId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public int getHouseId() {
        return houseId;
    }
}
