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
                "https://androidbackend.herokuapp.com/profiles.json",
                null,
                new JsonHttpResponseHandler() {
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        String address = new String();

                        try {

                            for (int i=0; i<= response.length(); i++) {
                                JSONObject location = (JSONObject) response.get(String.valueOf(i));
                                address += location.getString("name");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        responseHandler.handleResponseName(address);

                    }
                });
    }

    public interface ApiResponseHandler {
        void handleResponseName(String address);
    }
}
