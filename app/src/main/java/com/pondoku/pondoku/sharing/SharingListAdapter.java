package com.pondoku.pondoku.sharing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pondoku.pondoku.R;
import com.pondoku.pondoku.utils.Constants;

import java.util.List;

public class SharingListAdapter extends RecyclerView.Adapter<SharingListAdapter.ViewHolder>{



    private List<FollowingEntity> mData;
    private Context mContext;

    public SharingListAdapter(Context context, List<FollowingEntity> data) {
        mContext = context;
        mData = data;
    }

    public void setData(List<FollowingEntity> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.following_item, parent, false);
        return new ViewHolder(view);
    }
    /**
     * Method to show a sharing habits list by going to another activity
     * @param holder
     * @param position
     * An frequency of habit to be shown
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FollowingEntity following = mData.get(position);
        if (following != null) {
            holder.getTextView().setText(following.getName());
            holder.getLayout().setOnClickListener(view -> {
                //Go to SharingHabitActivity
                Intent intent = new Intent(mContext, SharingHabitActivity.class);
                intent.putExtra(Constants.FOLLOWING_INFO, following);
                mContext.startActivity(intent);
            });
        }
    }
    /**
     * get the size of data
     * @return the size of data
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewGroup layout;
        private final TextView textView;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = (ViewGroup) view.findViewById(R.id.layout);
            textView = (TextView) view.findViewById(R.id.user_name);
        }

        public ViewGroup getLayout() {
            return layout;
        }

        public TextView getTextView() {
            return textView;
        }
    }


}
