package com.example.eksamensprojekt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mActivitynames = new ArrayList<>();
    private ArrayList<String> mWeekdays = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> activityNames, ArrayList<String> weekdays, Context mContext) {
        this.mActivitynames = activityNames;
        this.mWeekdays = weekdays;
        this.mContext = mContext;
    }

    public void clear() {
        int size = mActivitynames.size();
        mActivitynames.clear();
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

        holder.textView1.setText(mActivitynames.get(position));
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

                                mActivitynames.remove(position);
                                mWeekdays.remove(position);
                                notifyDataSetChanged();
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
                Log.d(TAG, "onClick: Clicked on "+mActivitynames.get(position));

                Toast.makeText(mContext,mActivitynames.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mActivitynames.size();
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


}
