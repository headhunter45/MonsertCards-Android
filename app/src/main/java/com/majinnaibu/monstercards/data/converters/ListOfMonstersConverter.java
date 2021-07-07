package com.majinnaibu.monstercards.data.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.majinnaibu.monstercards.models.Monster;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListOfMonstersConverter {
    @TypeConverter
    public static String fromListOfMonsters(List<Monster> monsters) {
        Gson gson = new Gson();
        return gson.toJson(monsters);
    }

    @TypeConverter
    public static List<Monster> listOfMonstersFromString(String string) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Monster>>() {
        }.getType();
        return gson.fromJson(string, listType);
    }
}
