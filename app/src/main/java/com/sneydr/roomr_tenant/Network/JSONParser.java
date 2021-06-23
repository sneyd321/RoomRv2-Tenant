package com.sneydr.roomr_tenant.Network;

import com.google.gson.stream.JsonReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sneydr.roomr_tenant.Entities.House.Document;
import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Entities.House.Lease;
import com.sneydr.roomr_tenant.Entities.Login.Login;
import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Entities.RentDetails.RentDetails;
import com.sneydr.roomr_tenant.Entities.Users.Homeowner;
import com.sneydr.roomr_tenant.Entities.Users.Tenant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private static final Object lock = new Object();
    private static volatile JSONParser instance;
    private Gson gson;

    public static synchronized JSONParser getInstance() {
        JSONParser r = instance;
        if (r == null) {
            synchronized (lock) {    // While we were waiting for the lock, another
                r = instance;        // thread may have instantiated the object.
                if (r == null) {
                    r = new JSONParser();
                    instance = r;
                }
            }
        }
        return r;
    }

    public JSONParser() {
        gson = new Gson();
    }



    public String loginToJson(Login login) {
        return gson.toJson(login, Login.class);
    }

    private JsonReader getReader(InputStreamReader inputStream) {
        JsonReader reader = new JsonReader(inputStream);
        reader.setLenient(true);
        return reader;
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

    public Tenant parseTenant(InputStreamReader inputStream) {
        try {
            JsonReader reader = getReader(inputStream);
            Tenant tenant = gson.fromJson(reader, Tenant.class);
            reader.close();
            return tenant;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Tenant> parseTenants(String response) {
        Type tenantType = new TypeToken<ArrayList<Tenant>>(){}.getType();
        return gson.fromJson(response, tenantType);
    }

    public List<Tenant> parseTenants(Reader inputStream) {
        Type tenantType = new TypeToken<ArrayList<Tenant>>(){}.getType();
        return gson.fromJson(inputStream, tenantType);
    }



    public House parseHouse(String response) {
        return gson.fromJson(response, House.class);
    }

    public House parseHouse(InputStreamReader inputStream) {
        try {
            JsonReader reader = getReader(inputStream);
            House house = gson.fromJson(reader, House.class);
            reader.close();
            return house;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String problemToJson(Problem problem) {
        return gson.toJson(problem, Problem.class);
    }

    public List<Problem> parseProblems(String response) {
        Type problemType = new TypeToken<ArrayList<Problem>>(){}.getType();
        return gson.fromJson(response, problemType);
    }

    public List<Problem> parseProblems(InputStreamReader inputStream) {
        try {
            JsonReader reader = getReader(inputStream);
            Type problemType = new TypeToken<ArrayList<Problem>>(){}.getType();
            List<Problem> problems = gson.fromJson(reader, problemType);
            reader.close();
            return problems;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Problem parseProblem(String response) {
        return gson.fromJson(response, Problem.class);
    }

    public Problem parseProblem(InputStreamReader inputStream) {
        try {
            JsonReader reader = getReader(inputStream);
            Problem problem = gson.fromJson(reader, Problem.class);
            reader.close();
            return problem;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public List<Document> parseDocuments(String response) {
        Type documentType = new TypeToken<ArrayList<Document>>(){}.getType();
        return gson.fromJson(response, documentType);
    }

    public List<Document> parseDocuments(InputStreamReader inputStream) {
        try {
            JsonReader reader = getReader(inputStream);
            Type documentType = new TypeToken<ArrayList<Document>>(){}.getType();
            List<Document> documents = gson.fromJson(reader, documentType);
            reader.close();
            return documents;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public RentDetails parseRentDetails(String response) {
        return gson.fromJson(response, RentDetails.class);
    }

    public RentDetails parseRentDetails(InputStreamReader inputStream) {
        try {
            JsonReader reader = getReader(inputStream);
            RentDetails rentDetails = gson.fromJson(reader, RentDetails.class);
            reader.close();
            return rentDetails;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Homeowner parseHomeowner(String response) {
        return gson.fromJson(response, Homeowner.class);
    }

    public Homeowner parseHomeowner(InputStreamReader inputStream) {
        try {
            JsonReader reader = getReader(inputStream);
            Homeowner homeowner = gson.fromJson(reader, Homeowner.class);
            reader.close();
            return homeowner;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }



}
