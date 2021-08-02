package com.sneydr.roomr_tenant.Entities.ReadJson;

import android.util.JsonReader;


import com.sneydr.roomr_tenant.Entities.Location.HomeownerLocation;
import com.sneydr.roomr_tenant.Entities.Users.Homeowner;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadHomeownerLocationJson;
import com.sneydr.roomr_tenant.Entities.ReadJson.ReadJson;
import com.sneydr.roomr_tenant.Entities.ReadJson.State.JsonFieldTypeState;

import java.io.IOException;
import java.lang.reflect.Field;

public class ReadHomeownerJson extends ReadJson<Homeowner> {
    public ReadHomeownerJson(Class<Homeowner> clazz) {
        super(clazz);
        addState("com.sneydr.roomr_tenant.Entities.Location.HomeownerLocation", new HomeownerLocationState());
    }

    private static class HomeownerLocationState implements JsonFieldTypeState<Homeowner> {

        @Override
        public void setField(Field field, JsonReader reader, Homeowner homeowner) throws IOException {
            reader.beginObject();
            ReadJson<HomeownerLocation> homeownerLocationReadJson = new ReadHomeownerLocationJson(HomeownerLocation.class);
            HomeownerLocation homeownerLocation = homeownerLocationReadJson.read(reader, new HomeownerLocation());
            homeowner.setHomeownerLocation(homeownerLocation);
            reader.endObject();
        }
    }

}
