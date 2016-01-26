package com.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.skaterprofile.ServerRequest;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Carl on 1/26/2016.
 */
public class Posts {
  private String description;

  public Bitmap getImage() {
    return image;
  }

  public void setImage(Bitmap image) {
    this.image = image;
  }

  private Bitmap image;



  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


}
