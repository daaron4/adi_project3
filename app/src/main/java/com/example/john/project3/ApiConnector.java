package com.example.john.project3;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by EmployYeezy on 5/31/16.
 */
public class ApiConnector {
    private static ApiConnector instance;
    private static ApiResponseHandler responseHandler;

    private ApiConnector() {
    }

    public static ApiConnector getInstance(ApiResponseHandler handler) {
        responseHandler = handler;
        if (instance == null) {
            instance = new ApiConnector();
        }
        return instance;
    }

    public void doRequest() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(
                "https://androidbackend.herokuapp.com/profiles.json",
                null,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        Gson gson = new Gson();
                        PersonModel[] data = gson.fromJson(response.toString(), PersonModel[].class);
                        responseHandler.handleResponse(data);
                    }
                });
    }

    public interface ApiResponseHandler {
        void handleResponse(PersonModel[] data);
    }
}