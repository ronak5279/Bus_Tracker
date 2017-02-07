package com.example.bus_tracker.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bus_tracker.R;
import com.example.bus_tracker.Step1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harsh on 06-02-2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepViewHolder> {

    private List<Step1> steps = new ArrayList<>();
    private int rowLayout;
    private Context context;

    OnItemClickListener mItemClickListener;


    public  class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        CardView ssnLayout;
        TextView busNum;
        TextView start;
        TextView end;
        TextView dist;
        TextView duration;
        LinearLayout Holder;
        // private OnItemClickListener mItemClickListener;


        public StepViewHolder(View v) {
            super(v);
            //ssnLayout = (CardView) v.findViewById(R.id.busNo);
            busNum = (TextView) v.findViewById(R.id.busNo);
            start = (TextView)v.findViewById(R.id.from2);
            end = (TextView)v.findViewById(R.id.to2);
            dist = (TextView)v.findViewById(R.id.stepDistance);
            duration = (TextView)v.findViewById(R.id.stepDuration);
            Holder=(LinearLayout)v.findViewById(R.id.mainHolder);
            Holder.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getPosition());
            }

        }
        // Holder.setOnClickListener(this);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener =  mItemClickListener;
    }

    public StepsAdapter(List<Step1> steps, int rowLayout, Context context) {
        this.steps = steps;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public StepsAdapter.StepViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new StepViewHolder(view);
    }


    @Override
    public void onBindViewHolder(StepViewHolder holder, final int position) {



        holder.busNum.setText(( steps.get(position).getBusNum()).toString());
       // holder.start.setText(( steps.get(position).getStart()).toString());

        holder.end.setText(( steps.get(position).getEnd()).toString());

        holder.dist.setText(( steps.get(position).getDist()).toString());

        holder.duration.setText(( steps.get(position).getDuration()).toString());



    }


    @Override
    public int getItemCount() {
        return steps.size() ;
    }

}
