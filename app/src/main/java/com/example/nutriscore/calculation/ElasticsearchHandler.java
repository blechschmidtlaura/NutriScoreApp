package com.example.nutriscore.calculation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ElasticsearchHandler {

    private static final String searchUrl = "https://nutri.wetter.codes/products/_search";

    private static JSONObject sendPostRequest(String jsonBody) throws IOException, JSONException {
        final OkHttpClient client = new OkHttpClient();
        final RequestBody requestBody = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));
        final Request request = new Request.Builder().url(searchUrl).post(requestBody).build();
        final ResponseBody responseBody = client.newCall(request).execute().body();
        assert responseBody != null;
        return new JSONObject(responseBody.string());
    }

    private static boolean hasValue(JSONObject product, String key) throws JSONException {
        return !product.isNull(key) && !product.getString(key).equals("");
    }

    private static String getValue(JSONObject product, String... keys) throws JSONException {
        for (String key : keys) {
            if (hasValue(product, key)) {
                return product.getString(key);
            }
        }
        throw new IllegalArgumentException("The product doesn't have values for the keys " + Arrays.toString(keys));
    }

    public static Food getFoodByEAN(String ean) throws IOException, JSONException {
        ean = ean.replaceAll(" ", "");

        final JSONObject response = sendPostRequest("{ \"query\": { \"term\": { \"code\": \"" + ean + "\" } } }");
        final JSONArray hits = response.getJSONObject("hits").getJSONArray("hits");
        if (hits.length() == 0) {
            throw new IllegalArgumentException("Couldn't find product with the given EAN.");
        }

        final JSONObject product = hits.getJSONObject(0).getJSONObject("_source");

        final int energy_kJ = Integer.parseInt(getValue(product, "energy-kj_100g", "energy_100g"));
        final double sugar_g = Double.parseDouble(product.getString("sugars_100g"));
        final double saturatedFat_g = Double.parseDouble(product.getString("saturated-fat_100g"));
        final double salt_mg = Double.parseDouble(product.getString("salt_100g")) * 1000;
        final int fruitsVegetablesNuts_perc = Integer.parseInt(getValue(product, "fruits-vegetables-nuts_100g", "fruits-vegetables-nuts-estimate_100g"));
        final double dietaryFiber_g = hasValue(product, "fiber_100g") ? Double.parseDouble(product.getString("fiber_100g")) : 0.0;
        final double protein_g = Double.parseDouble(product.getString("proteins_100g"));

        return new Food(energy_kJ, sugar_g, saturatedFat_g, salt_mg, fruitsVegetablesNuts_perc, dietaryFiber_g, protein_g);
    }
}


