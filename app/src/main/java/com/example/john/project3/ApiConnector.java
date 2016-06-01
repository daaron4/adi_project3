package com.example.john.project3;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by EmployYeezy on 5/31/16.
 */

public class ApiConnector {

    private static ApiConnector instance;
    private static ApiResponseHandler responseHandler;

    private ApiConnector () {}

    public static ApiConnector getInstance(ApiResponseHandler handler) {
        responseHandler = handler;
        if (instance == null) {
            instance = new ApiConnector();
        }
        return instance;
    }

    public void doRequest (int idNumber) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(
                "https://androidbackend.herokuapp.com/profiles" + idNumber + ".json",
                null,
                new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            responseHandler.handleResponseName(response.getString("ID"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public interface ApiResponseHandler {
        void handleResponseName(String response);
    }
}
