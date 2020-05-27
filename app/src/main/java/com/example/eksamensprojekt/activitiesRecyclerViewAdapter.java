package com.example.eksamensprojekt;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eksamensprojekt.databasecomp.Activity;
import com.example.eksamensprojekt.databasecomp.AppDatabase;

import java.util.ArrayList;


public class activitiesRecyclerViewAdapter extends RecyclerView.Adapter<activitiesRecyclerViewAdapter.ViewHolderActivity>{
    private static final String TAG = "RecyclerViewAdapter";


    private Context mContext;
    private AppDatabase db;
    private Activity[] mActivities;

    public activitiesRecyclerViewAdapter(Context mContext) {

        this.mContext = mContext;
        db = AppDatabase.getAppDatabase(mContext);
    }

    public void resetView(){
        GetDataFromDatabase getDataFromDatabase = new GetDataFromDatabase();
        getDataFromDatabase.execute();
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

        holder.activityTextView.setText(mActivities[position].activityName);
        holder.weekdayTextView.setText(mActivities[position].weekday);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Clicked on "+mActivities[position].activityName);

                Toast.makeText(mContext,mActivities[position].activityName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mActivities.length;
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

    private class GetDataFromDatabase extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // start loading icon

        }

        @Override
        protected Void doInBackground(Void... Voids) {




            mActivities = db.activityDao().loadAllActivities();






            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //load ui
            notifyDataSetChanged();
        }
    }
}