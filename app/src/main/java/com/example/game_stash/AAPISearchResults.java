package com.example.game_stash;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AAPISearchResults extends ArrayAdapter<MGame> {
    private static final String TAG = AAPISearchResults.class.getSimpleName();

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView name;
        TextView publisher;
        ImageView gameImage;
        //etc.
    }

    public AAPISearchResults(Context context, int resource, List<MGame> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getGameName();
        String publisher = getItem(position).getPublisher().getName();
        //etc.

        final View result;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.publisher = (TextView) convertView.findViewById(R.id.tv_primarypublisher);
            holder.gameImage = (ImageView) convertView.findViewById(R.id.iv_game_thumb);
            //etc.

            result = convertView;
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(name);
        holder.publisher.setText(publisher);
        Picasso.get()
                .load(getItem(position).getThumbURL())
                .resize(100, 100)
                .centerInside()
                .into(holder.gameImage);
        Log.d(TAG, getItem(position).getGameName() + " " + getItem(position).getThumbURL());
        //etc.

        return convertView;
    }

    public Drawable loadImageFromWeb(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

}
