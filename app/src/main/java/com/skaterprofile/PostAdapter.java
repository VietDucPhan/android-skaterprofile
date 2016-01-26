package com.skaterprofile;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.model.Posts;

import java.util.List;

/**
 * Created by Carl on 1/26/2016.
 */
public class PostAdapter extends ArrayAdapter<Posts> {

  private Context context;
  private List<Posts> postlist;

  public PostAdapter(Context context, int resource, List<Posts> objects) {
    super(context, resource, objects);
    this.context = context;
    this.postlist = objects;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.single_post, parent, false);

    Posts post = postlist.get(position);
    TextView tv = (TextView) view.findViewById(R.id.description);
    ImageView iv = (ImageView) view.findViewById(R.id.postImage);
    iv.setImageBitmap(post.getImage());
    tv.setText(post.getDescription());
    //Display flower name in the TextView widget

    return view;
  }
}
