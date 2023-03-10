package com.pondoku.pondoku.habits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pondoku.pondoku.R;

import java.util.ArrayList;

public class HabitYearAdapter extends ArrayAdapter<String> {




    private ArrayList<String> years;
    private Context context;

    public HabitYearAdapter(Context context, ArrayList<String> years) {
        super(context, 0, years);
        this.years = years;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content_card_view, parent,false);
        }
        String year = years.get(position);

        // sets the habit list names
        TextView yearTitle = view.findViewById(R.id.card_view_text);
        yearTitle.setText(year);

        return view;
    }


}
