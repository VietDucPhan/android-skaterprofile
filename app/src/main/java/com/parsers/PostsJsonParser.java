package com.parsers;

import com.model.Posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carl on 1/26/2016.
 */
public class PostsJsonParser {
  public static List<Posts> parseFeed(JSONArray content) {

    try {
      List<Posts> postList = new ArrayList<>();

      for (int i = 0; i < content.length(); i++) {

        JSONObject obj = content.getJSONObject(i);
        Posts post = new Posts();

        post.setDesc(obj.getString("desc").toString());
        post.setName(obj.getString("name").toString());
        post.setImage(obj.getString("source").toString());


        postList.add(post);
      }

      return postList;
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

  }
}
