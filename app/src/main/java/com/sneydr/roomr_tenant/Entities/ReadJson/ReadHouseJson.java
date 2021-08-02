package com.sneydr.roomr_tenant.Entities.ReadJson;

import android.util.JsonReader;


import com.sneydr.roomr_tenant.Entities.House.House;
import com.sneydr.roomr_tenant.Entities.Location.RentalUnitLocation;
import com.sneydr.roomr_tenant.Entities.ReadJson.State.JsonFieldTypeState;

import java.io.IOException;
import java.lang.reflect.Field;

public class ReadHouseJson extends ReadJson<House> {
    public ReadHouseJson(Class<House> clazz) {
        super(clazz);
        addState("com.sneydr.roomr_tenant.Entities.Location.RentalUnitLocation", new RentalUnitLocationTypeState());
    }

    private static class RentalUnitLocationTypeState implements JsonFieldTypeState<House> {

        @Override
        public void setField(Field field, JsonReader reader, House house) throws IOException {
            reader.beginObject();
            ReadJson<RentalUnitLocation> readRentalUnitLocationJson = new ReadRentalUnitLocationJson(RentalUnitLocation.class);
            RentalUnitLocation rentalUnitLocation = readRentalUnitLocationJson.read(reader, new RentalUnitLocation());
            house.setRentalUnitLocation(rentalUnitLocation);
            reader.endObject();
        }
    }




}
