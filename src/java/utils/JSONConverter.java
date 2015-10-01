package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.City;
import entity.Country;
import java.util.ArrayList;
import java.util.List;

public class JSONConverter {

    public static String GetJSONFromCountry(List<Country> countries) {
        Gson gson = new Gson();
        ArrayList<JsonObject> list = new ArrayList<>();
        for (Country country : countries) {
            JsonObject obj = new JsonObject();
            obj.addProperty("code", country.getCode());
            obj.addProperty("name", country.getName());
            obj.addProperty("continent", country.getContinent());
            if (country.getCapital() != null) {
                obj.addProperty("capital", country.getCapital().getName());

            } else {
                obj.addProperty("capital", "");
            }
            list.add(obj);
        }
        return gson.toJson(list);
    }

    public static String GetJSONFromCity(List<City> cities) {
        Gson gson = new Gson();
        ArrayList<JsonObject> list = new ArrayList<>();
        for (City city : cities) {
            JsonObject obj = new JsonObject();
            obj.addProperty("name", city.getName());
            obj.addProperty("district", city.getDistrict());
            list.add(obj);
        }
        return gson.toJson(list);
    }
    
    public static City GetCityFromJSON(String json){
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();

        City city = new City();
        city.setName(obj.get("name").getAsString());
        city.setDistrict(obj.get("district").getAsString());
        
        return city;
    }
}
