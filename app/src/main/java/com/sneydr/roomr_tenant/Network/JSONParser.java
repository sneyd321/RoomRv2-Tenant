package com.sneydr.roomr_tenant.Network;



import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sneydr.roomr_tenant.Entities.House.Document;
import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Entities.House.Lease;
import com.sneydr.roomr_tenant.Entities.Login.Login;
import com.sneydr.roomr_tenant.Entities.Message.Message;
import com.sneydr.roomr_tenant.Entities.Problem.Problem;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadDocumentJson;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadHomeownerJson;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadHouseJson;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadJson;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadProblemJson;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadRentDetails;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadTenantJson;
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
import java.nio.charset.StandardCharsets;
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

    public Tenant parseTenant(InputStream inputStream) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.beginObject();
            ReadJson<Tenant> readJson = new ReadTenantJson(Tenant.class);
            Tenant tenant = readJson.read(reader, new Tenant());
            reader.endObject();
            reader.close();
            return tenant;
        }
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

    public House parseHouse(InputStream inputStream) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.beginObject();
            ReadJson<House> readJson = new ReadHouseJson(House.class);
            House house = readJson.read(reader, new House());
            reader.endObject();
            reader.close();
            return house;
        }
    }


    public String problemToJson(Problem problem) {
        return gson.toJson(problem, Problem.class);
    }

    public List<Problem> parseProblems(String response) {
        Type problemType = new TypeToken<ArrayList<Problem>>(){}.getType();
        return gson.fromJson(response, problemType);
    }

    public List<Problem> parseProblems(InputStream inputStream) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            List<Problem> problems = new ArrayList<>();
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                ReadJson<Problem> readJson = new ReadProblemJson(Problem.class);
                Problem problem = readJson.read(reader, new Problem());
                reader.endObject();
                problems.add(problem);
            }
            reader.endArray();
            reader.close();
            return problems;
        }
    }

    public Problem parseProblem(String response) {
        return gson.fromJson(response, Problem.class);
    }

    public Problem parseProblem(InputStream inputStream) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            reader.beginObject();
            ReadJson<Problem> readJson = new ReadProblemJson(Problem.class);
            Problem problem = readJson.read(reader, new Problem());
            reader.endObject();
            reader.close();
            return problem;
        }
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

    public List<Document> parseDocuments(InputStream inputStream) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            List<Document> documents = new ArrayList<>();
            reader.beginArray();
            while (reader.hasNext()) {
                reader.beginObject();
                ReadJson<Document> readJson = new ReadDocumentJson(Document.class);
                Document document = readJson.read(reader, new Document());
                reader.endObject();
                documents.add(document);
            }
            reader.endArray();
            reader.close();
            return documents;
        }

    }

    public RentDetails parseRentDetails(String response) {
        return gson.fromJson(response, RentDetails.class);
    }

    public RentDetails parseRentDetails(InputStream inputStream) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            ReadJson<RentDetails> readJson = new ReadRentDetails(RentDetails.class);
            reader.beginObject();
            RentDetails rentDetails = readJson.read(reader, new RentDetails());
            reader.endObject();
            reader.close();
            return rentDetails;
        }
    }


    public Homeowner parseHomeowner(String response) {
        return gson.fromJson(response, Homeowner.class);
    }

    public Homeowner parseHomeowner(InputStream inputStream) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            ReadJson<Homeowner> readJson = new ReadHomeownerJson(Homeowner.class);
            reader.beginObject();
            Homeowner homeowner = readJson.read(reader, new Homeowner());
            reader.endObject();
            reader.close();
            return homeowner;
        }
    }



}
