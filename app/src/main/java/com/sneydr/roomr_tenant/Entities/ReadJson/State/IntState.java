package com.sneydr.roomr_tenant.Entities.ReadJson.State;

import android.util.JsonReader;

import java.io.IOException;
import java.lang.reflect.Field;

public class IntState<T> implements JsonFieldTypeState<T> {

    @Override
    public void setField(Field field, JsonReader reader, T t) throws IOException {
        try {
            field.set(t, reader.nextInt());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
