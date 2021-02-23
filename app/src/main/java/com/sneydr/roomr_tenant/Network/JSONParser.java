package com.sneydr.roomr_tenant.Network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Entities.House.Lease;
import com.sneydr.roomr_tenant.Entities.Login.Login;
import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {


    private static JSONParser instance = null;
    private Gson gson;

    public static JSONParser getInstance() {
        if (instance == null){
            instance = new JSONParser();
        }
        return instance;
    }

    public JSONParser() {
        gson = new Gson();
    }

    public String leaseToJson(Lease lease) {
        return gson.toJson(lease, Lease.class);
    }


    public String parseToken(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getString("token");
    }



    public String loginToJson(Login login) {
        return gson.toJson(login, Login.class);
    }


    public List<House> parseHouses(String response) {
        Type houseType = new TypeToken<ArrayList<House>>(){}.getType();
        return gson.fromJson(response, houseType);
    }

    public String houseToJson(House house) {
        return gson.toJson(house, House.class);
    }

    public String tenantToJson(Tenant tenant) {
        return gson.toJson(tenant, Tenant.class);
    }

    public Tenant parseTenant(String response) {
        return gson.fromJson(response, Tenant.class);
    }

    public List<Tenant> parseTenants(String response) {
        Type tenantType = new TypeToken<ArrayList<Tenant>>(){}.getType();
        return gson.fromJson(response, tenantType);
    }

    public House parseHouse(String response) {
        return gson.fromJson(response, House.class);
    }

    public String problemToJson(Problem problem) {
        return gson.toJson(problem, Problem.class);
    }

    public List<Problem> parseProblems(String response) {
        Type problemType = new TypeToken<ArrayList<Problem>>(){}.getType();
        return gson.fromJson(response, problemType);
    }

    public Problem parseProblem(String response) {
        return gson.fromJson(response, Problem.class);
    }

    public Message parseMessage(String response) {
        return gson.fromJson(response, Message.class);
    }


    public String messageToJson(Message message) {
        return gson.toJson(message, Message.class);
    }

    public List<Message> parseMessages(String response) {
        Type messageType = new TypeToken<ArrayList<Message>>(){}.getType();
        return gson.fromJson(response, messageType);
    }



}
