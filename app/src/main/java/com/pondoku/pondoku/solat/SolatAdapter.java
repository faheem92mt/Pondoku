package com.pondoku.pondoku.solat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pondoku.pondoku.R;

import java.util.ArrayList;
import java.util.List;

public class SolatAdapter extends RecyclerView.Adapter<SolatAdapter.MyViewHolder>{

    private Context context;
    private List<SolatModel> solatModelList;

    public SolatAdapter(Context context) {
        this.context = context;
        solatModelList = new ArrayList<>();
    }

    public void add(SolatModel solatModel) {
        solatModelList.add(solatModel);
        notifyDataSetChanged();
    }

    public void clear() {
        solatModelList.clear();
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SolatModel solatModel = solatModelList.get(position);
        holder.bindViews(solatModel);
    }

    @Override
    public int getItemCount() {
        return solatModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView fajr,zuhr,asr,maghrib,isha,date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            fajr = itemView.findViewById(R.id.fajrTv);
            zuhr = itemView.findViewById(R.id.zuhrTv);
            asr = itemView.findViewById(R.id.asrTv);
            maghrib = itemView.findViewById(R.id.maghribTv);
            isha = itemView.findViewById(R.id.ishaTv);
            date = itemView.findViewById(R.id.dateTv);
        }

        public void bindViews(SolatModel solatModel) {
            fajr.setText(solatModel.getFajr());
            zuhr.setText(solatModel.getZuhr());
            asr.setText(solatModel.getAsr());
            maghrib.setText(solatModel.getMaghrib());
            isha.setText(solatModel.getIsha());
            date.setText(solatModel.getDate());




        }
    }

}