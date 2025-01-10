package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserHelper {
    public static JsonObject parseRates(String jsonResponse) {
        return JsonParser.parseString(jsonResponse).getAsJsonObject();
    }
}
