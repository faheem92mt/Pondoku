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

        private TextView imsak,subuh,syuruk,zuhur,asar,maghrib,isyak,date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imsak = itemView.findViewById(R.id.imsakTv);
            subuh = itemView.findViewById(R.id.subuhTv);

            syuruk = itemView.findViewById(R.id.syurukTv);
            zuhur = itemView.findViewById(R.id.zuhurTv);

            asar = itemView.findViewById(R.id.asarTv);
            maghrib = itemView.findViewById(R.id.maghribTv);

            isyak = itemView.findViewById(R.id.isyakTv);
            date = itemView.findViewById(R.id.dateTv);
        }

        public void bindViews(SolatModel solatModel) {
            imsak.setText(solatModel.getImsak());
            subuh.setText(solatModel.getSubuh());
            syuruk.setText(solatModel.getSyuruk());
            zuhur.setText(solatModel.getZuhur());


            asar.setText(solatModel.getAsar());
            maghrib.setText(solatModel.getMaghrib());
            isyak.setText(solatModel.getIsyak());
            date.setText(solatModel.getDate());




        }
    }

    public int getItemPosition(String eventId) {
        for (int i = 0; i < solatModelList.size(); i++) {
            if (solatModelList.get(i).getDate().equals(eventId)) {
                return i;
            }
        }
        return -1;
    }

}