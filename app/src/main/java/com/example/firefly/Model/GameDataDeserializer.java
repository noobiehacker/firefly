package com.example.firefly.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GameDataDeserializer implements JsonDeserializer<GameResponse> {

    @Override
    public GameResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        JsonObject gameData = jsonArray.get(0).getAsJsonObject();
        JsonObject gms = gameData.get("gms").getAsJsonObject();
        GameResponse gameResponse = new GameResponse();
        gameResponse.gms = jsonObjectToGms(gms);
        return gameResponse;
    }

    private Gms jsonObjectToGms(JsonObject input) {
        Gms gms = new Gms();
        JsonObject jsonObject = input.get("@attributes").getAsJsonObject();
        gms.w = jsonObject.get("w").toString();
        gms.y = jsonObject.get("y").toString();
        gms.t = jsonObject.get("t").toString();
        gms.gd = jsonObject.get("gd").toString();
        gms.bph = jsonObject.get("bph").toString();
        List<G> gArrayList = new ArrayList<>();
        JsonArray jsonArray = input.get("g").getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            G g = jsonObjectToG(jsonElement.getAsJsonObject());
            gArrayList.add(g);
        }
        gms.g = gArrayList;
        return gms;
    }

    private G jsonObjectToG(JsonObject input) {
        G g = new G();
        JsonObject jsonObject = input.get("@attributes").getAsJsonObject();
        g.eid = jsonObject.get("eid").toString();
        g.gsis = jsonObject.get("gsis").toString();
        g.d = jsonObject.get("d").toString();
        g.t = jsonObject.get("t").toString();
        g.q = jsonObject.get("q").toString();
        g.h = jsonObject.get("h").toString();
        g.hnn = jsonObject.get("hnn").toString();
        g.hs = jsonObject.get("hs").toString();
        g.v = jsonObject.get("v").toString();
        g.vnn = jsonObject.get("vnn").toString();
        g.vs = jsonObject.get("vs").toString();
        g.rz = jsonObject.get("rz").toString();
        g.ga = jsonObject.get("ga").toString();
        g.gt = jsonObject.get("gt").toString();
        return g;
    }

}