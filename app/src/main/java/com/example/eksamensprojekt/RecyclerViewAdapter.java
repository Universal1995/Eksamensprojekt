package com.example.eksamensprojekt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eksamensprojekt.databasecomp.Activity;
import com.example.eksamensprojekt.databasecomp.AppDatabase;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mActivityNames = new ArrayList<>();
    private ArrayList<String> mWeekdays = new ArrayList<>();
    private Context mContext;
    private AppDatabase db;
    private Activity[] mActivities;

    public RecyclerViewAdapter(Context mContext) {

        this.mContext = mContext;
        db = AppDatabase.getAppDatabase(mContext);

    }

    public void resetView(){
        GetDataFromDatabase getDataFromDatabase = new GetDataFromDatabase();
        getDataFromDatabase.execute();
    }

    public void clear() {
        int size = mActivityNames.size();
        mActivityNames.clear();
        mWeekdays.clear();
        notifyItemRangeRemoved(0, size);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //Glide.with(mContext).asBitmap().load(mWeekdays.get(position)).into(holder.image);

        holder.textView1.setText(mActivityNames.get(position));
        holder.textView2.setText(mWeekdays.get(position));
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(true);
                builder.setTitle("Finished ativity?");
                builder.setMessage("If you finished activity, click confirm otherwise click no and go back");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                db.activityDao().updateActivityDone(mActivities[position].uid);
                                resetView();
                                holder.checkBox.setChecked(false);
                            }
                        });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        holder.checkBox.setChecked(false);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();




            }

        });




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

    public class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        TextView textView1;
        TextView textView2;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.checkBox = itemView.findViewById(R.id.mainRCVCheckbox);
            this.textView1 = itemView.findViewById(R.id.layoutActivityTextView1);
            this.textView2 = itemView.findViewById(R.id.layoutActivitesWeekdayTextView1);
            this.relativeLayout = itemView.findViewById(R.id.par_layout);
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


            mActivityNames.clear();
            mWeekdays.clear();

            mActivities = db.activityDao().loadAllActivities();


            for(int i = 0; i < mActivities.length; i++){
                mActivityNames.add(mActivities[i].activityName);
                mWeekdays.add(mActivities[i].weekday);

            }



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
