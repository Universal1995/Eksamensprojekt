package com.example.eksamensprojekt;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class activitiesRecyclerViewAdapter extends RecyclerView.Adapter<activitiesRecyclerViewAdapter.ViewHolderActivity>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mActivityNames = new ArrayList<>();
    private ArrayList<String> mWeekdays = new ArrayList<>();
    private Context mContext;

    public activitiesRecyclerViewAdapter(ArrayList<String> mActivityNames, ArrayList<String> mWeekdays, Context mContext) {
        this.mActivityNames = mActivityNames;
        this.mWeekdays = mWeekdays;
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public ViewHolderActivity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_activitylist, parent,false);
        ViewHolderActivity viewHolder = new ViewHolderActivity(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderActivity holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //Glide.with(mContext).asBitmap().load(mImages.get(position)).into(holder.image);

        holder.activityTextView.setText(mActivityNames.get(position));
        holder.weekdayTextView.setText(mWeekdays.get(position));

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked on "+mActivityNames.get(position));

                Toast.makeText(mContext,mActivityNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mActivityNames.size();
    }

    public class ViewHolderActivity extends RecyclerView.ViewHolder{

        TextView activityTextView;
        TextView weekdayTextView;
        RelativeLayout relativeLayout;

        public ViewHolderActivity(@NonNull View itemView) {
            super(itemView);
            this.activityTextView = itemView.findViewById(R.id.layoutActivityTextView);
            this.weekdayTextView = itemView.findViewById(R.id.layoutActivitesWeekdayTextView);
            this.relativeLayout = itemView.findViewById(R.id.layoutActivityListParrent);
        }
    }
}