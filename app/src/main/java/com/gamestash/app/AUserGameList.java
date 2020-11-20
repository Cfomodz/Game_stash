package com.gamestash.app;

import android.content.Context;
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

import com.gamestash.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AUserGameList extends ArrayAdapter<MGame> {
    private static final String TAG = AUserGameList.class.getSimpleName();

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView name;
        TextView location;
        ImageView gameImage;
        //etc.
    }

    public AUserGameList(Context context, int resource, List<MGame> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getGameName();
        String location = getItem(position).getLocation();
        //etc.

        final View result;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.tv_game_name);
            holder.location = convertView.findViewById(R.id.tv_info2);
            holder.gameImage = convertView.findViewById(R.id.iv_game_thumb);
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
        holder.location.setText(location);
        Picasso.get()
                .load(getItem(position).getThumbURL())
                .resize(100, 100)
                .centerInside()
                .noFade()
                .into(holder.gameImage);
        Log.d(TAG, getItem(position).getGameName() + " " + getItem(position).getThumbURL());
        //etc.

        return convertView;
    }
}
