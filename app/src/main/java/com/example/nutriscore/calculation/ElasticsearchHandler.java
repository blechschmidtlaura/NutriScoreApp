package com.example.nutriscore.calculation;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Der ElasticsearchHandler
 * Ruft die auf Torben Wetters Server gehostete API auf, welche für die Barcodes
 * die in der Datenbank eingetragenen Nahrung zurückliefert
 */
public class ElasticsearchHandler {

    // Die URL der API auf torbens Server gehostet
    private static final String searchUrl = "https://nutri.wetter.codes/products/_search";

    /**
     * Die Methode sendet eine Anfrage an die API
     * @param jsonBody Das JSON Object der Anfrage
     * @return gibt die Nahrung zurück
     * @throws IOException
     * @throws JSONException
     */
    private static JSONObject sendPostRequest(String jsonBody) throws IOException, JSONException {
        final OkHttpClient client = new OkHttpClient();
        final RequestBody requestBody = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));
        final Request request = new Request.Builder().url(searchUrl).post(requestBody).build();
        final ResponseBody responseBody = client.newCall(request).execute().body();
        assert responseBody != null;
        return new JSONObject(responseBody.string());
    }

    /**
     * Testet ob das gelieferte Nahrungsmittel einen bestimmten Wert im JSON Object hat
     * @param product Das Nahrungsmittel Ergebnis
     * @param key Der geprüfte Schlüssel
     * @return
     * @throws JSONException
     */
    private static boolean hasValue(JSONObject product, String key) throws JSONException {
        return !product.isNull(key) && !product.getString(key).equals("");
    }

    /**
     * Gibt für einen gesuchten Schlüssel einen Wert zurück
     * @param product Das Nahrungsmittel
     * @param keys die Schlüssel welche Abgefragt werden
     * @return
     * @throws JSONException
     */
    private static String getValue(JSONObject product, String... keys) throws JSONException {
        for (String key : keys) {
            if (hasValue(product, key)) {
                return product.getString(key);
            }
        }
        return "0";
        // TODO ich habe das geändert weil das Produkt nicht die FrüchteGemüseNüsse hat
    }

    /**
     * Gibt für einen Barcode das Nahrungsmittel aus der Datenbank zurück
     * @param ean Die ean des angefragten Barcodes
     * @return Gibt ein Optionales Food Objekt zurück, da manchmal die gesuchte ean nicht in der Datenbank ist.
     * @throws IOException
     * @throws JSONException
     * @throws IllegalArgumentException
     */
    public static Optional<Product> getFoodByEAN(String ean, Context c) throws IOException, JSONException, IllegalArgumentException {
        ean = ean.replaceAll(" ", "");

        final JSONObject response = sendPostRequest("{ \"query\": { \"term\": { \"code\": \"" + ean + "\" } } }");
        final JSONArray hits = response.getJSONObject("hits").getJSONArray("hits");
        if (hits.length() == 0) {
            return Optional.empty();
        }

        final JSONObject product = hits.getJSONObject(0).getJSONObject("_source");
        System.out.println(product.toString());
        final int energy_kJ = Integer.parseInt(getValue(product, "energy-kj_100g", "energy_100g"));
        final double sugar_g = Double.parseDouble(product.getString("sugars_100g"));
        final double saturatedFat_g = Double.parseDouble(product.getString("saturated-fat_100g"));
        final double salt_mg = Double.parseDouble(product.getString("salt_100g")) * 1000;
        final int fruitsVegetablesNuts_perc = Integer.parseInt(getValue(product, "fruits-vegetables-nuts_100g", "fruits-vegetables-nuts-estimate_100g"));
        final double dietaryFiber_g = hasValue(product, "fiber_100g") ? Double.parseDouble(product.getString("fiber_100g")) : 0.0;
        final double protein_g = Double.parseDouble(product.getString("proteins_100g"));

        final String category = getValue(product, "quantity".split()); //Todo: regex
        if(category.equals("ml")) {
            return Optional.of(new Drink(c, energy_kJ, sugar_g, saturatedFat_g, salt_mg, fruitsVegetablesNuts_perc, dietaryFiber_g, protein_g));
        } else{
            return Optional.of(new Food(c, energy_kJ, sugar_g, saturatedFat_g, salt_mg, fruitsVegetablesNuts_perc, dietaryFiber_g, protein_g));
        }
    }
}


