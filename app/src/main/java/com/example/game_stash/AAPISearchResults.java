package com.example.game_stash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class AAPISearchResults extends ArrayAdapter<MGame> {
    private static final String fName = VMainMenu.class.getSimpleName();
    private static final String TAG = fName + ":";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView name;
        TextView publisher;
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
        String name = getItem(position).getName();
        String publisher = getItem(position).getPrimaryPublisher();
        //etc.

        final View result;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.publisher = (TextView) convertView.findViewById(R.id.tv_primarypublisher);
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
        //etc.

        return convertView;
    }

}
