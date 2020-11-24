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

import com.squareup.picasso.Picasso;

import java.util.List;

public class AGameListAPI extends ArrayAdapter<DGame> {
    private static final String TAG = AGameListAPI.class.getSimpleName();

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder {
        ImageView gameImage;
        TextView name;
        TextView publisher;
        TextView extraDetails;
        //etc.
    }

    public AGameListAPI(Context context, int resource, List<DGame> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String gameImage = getItem(position).getThumbURL();
        String name = getItem(position).getGameName();
        String publisher = "Publisher: " + getItem(position).getPublisher().getName();
        String extraDetails = "Players: " + getItem(position).getMinPlayers() +
                " - " + getItem(position).getMaxPlayers() +
                "    Age: " + getItem(position).getMinAge() + "+";
        //etc.

        final View result;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.gameImage = convertView.findViewById(R.id.iv_gamelist_item_game_thumb);
            holder.name = convertView.findViewById(R.id.tv_gamelist_item_game_name);
            holder.publisher = convertView.findViewById(R.id.tv_gamelist_publisher);
            holder.extraDetails = convertView.findViewById(R.id.tv_gamelist_item_extra_details);
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

        Picasso.get()
                .load(gameImage)
                .resize(100, 100)
                .centerInside()
                .noFade()
                .into(holder.gameImage);
        holder.name.setText(name);
        holder.publisher.setText(publisher);
        holder.extraDetails.setText(extraDetails);
        //etc.

        Log.d(TAG, getItem(position).getGameName() + " " + getItem(position).getThumbURL());

        return convertView;
    }
}
