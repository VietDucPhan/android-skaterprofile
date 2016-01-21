package com.skaterprofile;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;

/**
 * Created by Carl on 1/20/2016.
 */

class ServerRequest {
  private static final String BASE_URL = "https://skaterprofile.herokuapp.com/api/";

  private static AsyncHttpClient client = new AsyncHttpClient();

  public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.get(getAbsoluteUrl(url), params, responseHandler);
  }

  public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
    client.post(getAbsoluteUrl(url), params, responseHandler);
  }

  private static String getAbsoluteUrl(String relativeUrl) {
    return BASE_URL + relativeUrl;
  }

}
