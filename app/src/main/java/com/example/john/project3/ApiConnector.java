package com.example.john.project3;

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
    String id= new String();
    String title = new String();
    String skills = new String();
    String open = new String();
    String gitHub = new String();
    String ga = new String();
    String linkedIn = new String();
    String other = new String();
    String image= new String();
    String url = new String();
    String name = new String();
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

    public void doRequest () {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(
                "https://androidbackend.herokuapp.com/profiles.json",
                null,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {


                        String[] idArray, nameArray, otherArray, titleArray, skillsArray, openArray, gitHubArray, gaArray, linkedInArray, imageArray, urlArray;

                        try {

                            for (int i=0; i<= response.length(); i++) {

                                JSONObject location = (JSONObject) response.get(i);
                                id += location.getString("id")+"SPACE";
                                name += location.getString("name")+"SPACE";
                                title += location.getString("title")+"SPACE";
                                skills += location.getString("skills")+"SPACE";
                                open += location.getString("open")+"SPACE";
                                gitHub += location.getString("github")+"SPACE";
                                ga += location.getString("ga")+"SPACE";
                                linkedIn += location.getString("linkedin")+"SPACE";
                                other += location.getString("other")+"SPACE";
                                image += location.getString("image")+"SPACE";
                                url += location.getString("url")+"SPACE";

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        idArray = id.split("SPACE");
                        nameArray = name.split("SPACE");
                        titleArray = title.split("SPACE");
                        skillsArray = skills.split("SPACE");
                        openArray = open.split("SPACE");
                        gitHubArray = gitHub.split("SPACE");
                        gaArray = ga.split("SPACE");
                        linkedInArray = linkedIn.split("SPACE");
                        otherArray = other.split("SPACE");
                        imageArray = image.split("SPACE");
                        urlArray = url.split("SPACE");




                        responseHandler.handleResponseName(idArray, nameArray, titleArray, skillsArray,openArray,gitHubArray,gaArray,linkedInArray, otherArray,imageArray,urlArray);

                    }
                });
    }

    public interface ApiResponseHandler {
        void handleResponseName(String[] idArray,String[] nameArray, String[] titleArray, String[] skillsArray, String[] openArray,
                                String[] gitHubArray, String[] gaArray, String[] linkedInArray, String[] otherArray, String[] imageArray, String[] urlArray
//                String[] idArray, , String[] titleArray, String[] skillsArray, String[] openArray,
//                                String[] gitHubArray, String[] gaArray, String[] linkedInArray, String[] otherArray, String[] imageArray, String[] urlArray,
//
                                );
    }
}