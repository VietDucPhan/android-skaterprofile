package com.skaterprofile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

  MainActivity THIS = this;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
      }
    });
    ServerRequest.post("posts/get", null, new JsonHttpResponseHandler() {
      @Override
      public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        // If the response is JSONObject instead of expected JSONArray
        MainAsync ma = new MainAsync();
        ma.execute(response, null, null);
      }

      @Override
      public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        super.onFailure(statusCode, headers, responseString, throwable);

        Toast.makeText(MainActivity.this, "Could not access to server, please try again latter", Toast.LENGTH_LONG).show();
      }
    });
    //new MainAsyncTask().execute(url, "", "");

  }

  private class MainAsync extends AsyncTask<JSONObject ,String ,JSONArray>{

    @Override
    protected JSONArray doInBackground(JSONObject... response) {
      try {
        JSONArray respnoseArray = response[0].getJSONArray("response");
        return  respnoseArray;
      } catch (JSONException e ){
        e.printStackTrace();
      }

      return null;
    }

    @Override
    protected void onPostExecute(JSONArray respnoseArray) {
      super.onPostExecute(respnoseArray);
      try {

        for (int i = 0; i < respnoseArray.length(); i++){
          String urlStr = null;
          JSONObject contentObj = respnoseArray.getJSONObject(i);
          LinearLayout ll = (LinearLayout) findViewById(R.id.viewScrollContent);
          urlStr = contentObj.get("source").toString();
          InputStream is = (InputStream) new URL(urlStr).openConnection().getInputStream();
          Bitmap bit = BitmapFactory.decodeStream(is);
          ImageView iv = new ImageView(THIS);
          iv.setImageBitmap(bit);

          TextView tv = new TextView(THIS);
          tv.setText(urlStr);
          ll.addView(tv);
          ll.addView(iv);


          Log.i("source", urlStr);
        }
      } catch(JSONException e){
        e.printStackTrace();
      } catch (MalformedURLException e){
        e.printStackTrace();
      } catch (IOException e){
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onResume() {
    super.onResume();
    checkNetworkConnection();
  }

  public boolean isNetworkConnected() {
    ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    if (networkInfo != null && networkInfo.isConnected()) {
      return true;
    } else {
      return false;
    }
  }

  public void checkNetworkConnection() {
    if (!isNetworkConnected()) {
      Toast toast = Toast.makeText(MainActivity.this, "No network connection available.", Toast.LENGTH_LONG);
      toast.show();
    }
  }

}
