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

/**
 * <h1>AGameList API</h1>
 * This class is used to attach the game list returned from the board game atlas api to the
 * listview in VGAmeListAPI.
 */
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

    /**
     * AGameListAPI requires a List of DGame as objects.
     * @param context
     * @param resource
     * @param objects
     */
    public AGameListAPI(Context context, int resource, List<DGame> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Set up incoming values to display
        String gameImage = getItem(position).getThumbURL();
        String name = getItem(position).getGameName();
        String publisher = "Publisher: " + getItem(position).getPublisher().getName();
        String extraDetails = "Players: " + getItem(position).getMinPlayers() +
                " - " + getItem(position).getMaxPlayers() +
                "    Age: " + getItem(position).getMinAge() + "+";
        //etc.

        // Viewholder is used to:
        // a) contain all needed layout items
        // b) only hold the items that are on screen.
        final View result;
        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();

            // Set viewholder to layout items
            holder.gameImage = convertView.findViewById(R.id.iv_gamelist_item_game_thumb);
            holder.name = convertView.findViewById(R.id.tv_gamelist_item_game_name);
            holder.publisher = convertView.findViewById(R.id.tv_gamelist_item_publisher);
            holder.extraDetails = convertView.findViewById(R.id.tv_gamelist_item_extra_details);
            //etc.

            result = convertView;
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        // Sets up the animation for the items loading into view.
        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        // Set the values of the layout items to the values of the list items.
        if(gameImage.trim().length() > 0) {
            Picasso.get()
                    .load(gameImage)
                    .resize(100, 100)
                    .error(R.drawable.main_menu_img_00)
                    .centerInside()
                    .noFade()
                    .into(holder.gameImage);
        } else {
            Picasso.get()
                    .load(R.drawable.error_404)
                    .resize(100, 100)
                    .centerInside()
                    .noFade()
                    .into(holder.gameImage);
        }
        holder.name.setText(name);
        holder.publisher.setText(publisher);
        holder.extraDetails.setText(extraDetails);
        //etc.

        //Log.d(TAG, getItem(position).getGameName() + " " + getItem(position).getThumbURL());
        return convertView;
    }
}
