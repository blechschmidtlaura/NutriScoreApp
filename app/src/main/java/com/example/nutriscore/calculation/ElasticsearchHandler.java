package com.example.nutriscore.calculation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collections;

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

    public static JSONObject getProductByEAN(String ean) throws IOException, JSONException {
        ean = ean.replaceAll(" ", "");

        final JSONObject response = sendPostRequest("{ \"query\": { \"term\": { \"code\": \"" + ean + "\" } } }");
        final JSONObject hit = response.getJSONObject("hits").getJSONArray("hits").getJSONObject(0);
        return hit.getJSONObject("_source");
    }

    public static JSONArray searchProductsByName(String name) throws IOException, JSONException {
        final JSONObject response = sendPostRequest("{ \"query\": { \"match\": { \"product_name\": \"" + name + "\" } } }");
        final JSONArray hits = response.getJSONObject("hits").getJSONArray("hits");
        for (int i = 0; i < hits.length(); i++) {
            hits.put(i, hits.getJSONObject(i).getJSONObject("_source"));
        }
        return hits;
    }
}


