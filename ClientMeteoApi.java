package com.SAEJavaMeteo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClientMeteoApi {
	  protected static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(Map.class.getName());
	    protected static HttpURLConnection conn;
	    public JsonObject getData(String Ville){
	        BufferedReader reader;
	        String line;
	        StringBuilder responseContent = new StringBuilder();
	        
	        try {
	        	

	            URL url = new URL("http://api.worldweatheronline.com/premium/v1/weather.ashx?key=6cd5a761c5db4c989c1185034230101&q="+Ville+"&format=json&aqi=yes\r\n"
	            		+ "");
	            conn = (HttpURLConnection) url.openConnection();

	            // Organisation du Request
	            conn.setRequestMethod("GET");
	            conn.setConnectTimeout(5000);// 5000 (en ms), cela correspond � 5 seconds
	            conn.setReadTimeout(5000);


	            // Test du fonctionnement du serveur, si'il y a bien réponse.
	            int status = conn.getResponseCode();

	if (status >= 300) {
	                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	                while ((line = reader.readLine()) != null) {
	                    responseContent.append(line);
	                }
	                reader.close();
	            } else {
	                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	                while ((line = reader.readLine()) != null) {
	                    responseContent.append(line);
	                }
	                reader.close();
	            }
	           
	            //System.out.println(responseContent.toString());
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            conn.disconnect();
	        }

	        String json = responseContent.toString();
	        JsonParser parser = new JsonParser();
	        JsonObject jsTree = parser.parse(json).getAsJsonObject();

	        //R�cuperation de data
	      
	        JsonObject data = jsTree.get("data").getAsJsonObject();
	        JsonArray current_condition = data.get("current_condition").getAsJsonArray();
	        JsonObject  current_condition_Object= current_condition.get(0).getAsJsonObject();
	        // recuperation de data pendant une heure  
	        JsonArray wetherDesc = current_condition_Object.get("weatherDesc").getAsJsonArray();
	        JsonObject  wether_desc_Object= wetherDesc.get(0).getAsJsonObject();
	        String wether = wether_desc_Object.get("value").getAsString();
	        JsonArray wetherHourlyDesc = data.get("weather").getAsJsonArray();
	        JsonObject wetherHourlyObject = wetherHourlyDesc.get(0).getAsJsonObject();
	        JsonArray wetherHourlyArray = wetherHourlyObject.get("hourly").getAsJsonArray();
	        JsonObject wetherHourly = wetherHourlyArray.get(0).getAsJsonObject();
	        String humidité_h= wetherHourly.get("humidity").getAsString()+" g.m-3";
	        String temperature_h = wetherHourly.get("tempC").getAsString() + " °C";
	        JsonArray wetherDesc_h = wetherHourly.get("weatherDesc").getAsJsonArray();
	        JsonObject  wether_desc_Object_h= wetherDesc_h.get(0).getAsJsonObject();
	        String wether_h = wether_desc_Object.get("value").getAsString();
	     // calculating the air quality hourly
	        JsonObject air_quality_h = wetherHourly.get("air_quality").getAsJsonObject();
	        Float pm_h = air_quality_h.get("pm10").getAsFloat();
	        Float so2_h = air_quality_h.get("so2").getAsFloat();
	        Float no2_h = air_quality_h.get("no2").getAsFloat();
	        Float IQA_h = Math.max(pm_h,Math.max(so2_h,no2_h));
	        String air_Q_h = IQA_h.toString() +"AQI";
	        //// ajouter l image a partir de l Api hourly
	        JsonArray weatherIconUrlArray_h = wetherHourly.get("weatherIconUrl").getAsJsonArray();
	        JsonObject  weatherIconUrlObj_h = weatherIconUrlArray_h.get(0).getAsJsonObject();  
	        String weatherIconUrl_h = weatherIconUrlObj_h.get("value").getAsString();
	       
	        //////////////////////////////////////////////////////////////////////////////////////
	        String humidité= current_condition_Object.get("humidity").getAsString()+" g.m-3";
	        String temperature = current_condition_Object.get("temp_C").getAsString() + " °C";
	        // calculating the air quality
	        JsonObject air_quality = current_condition_Object.get("air_quality").getAsJsonObject();
	        Float pm = air_quality.get("pm10").getAsFloat();
	        Float so2 = air_quality.get("so2").getAsFloat();
	        Float no2 = air_quality.get("no2").getAsFloat();
	        Float IQA = Math.max(pm,Math.max(so2,no2));
	        String air_Q = IQA.toString() +"AQI";
	        //// ajouter l image a partir de l Api
	        JsonArray weatherIconUrlArray = current_condition_Object.get("weatherIconUrl").getAsJsonArray();
	        JsonObject  weatherIconUrlObj = weatherIconUrlArray.get(0).getAsJsonObject();  
	        String weatherIconUrl = weatherIconUrlObj.get("value").getAsString();
	        JsonObject donneesFineaux = new JsonObject();
	        donneesFineaux.addProperty("wether", wether);
	        donneesFineaux.addProperty("humidité", humidité);
	        donneesFineaux.addProperty("temperature", temperature);
	        donneesFineaux.addProperty("weatherIconUrl", weatherIconUrl);
	        donneesFineaux.addProperty("qualitéAir", air_Q);
	        donneesFineaux.addProperty("wether_h", wether_h);
	        donneesFineaux.addProperty("humidité_h", humidité_h);
	        donneesFineaux.addProperty("temperature_h", temperature_h);
	        donneesFineaux.addProperty("weatherIconUrl_h", weatherIconUrl_h);
	        donneesFineaux.addProperty("qualitéAir_h", air_Q_h);
	        

	        return donneesFineaux;
	       
	        ////////////////
}

}
