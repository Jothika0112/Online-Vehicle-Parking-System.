package com.example.onlinevehicleparking.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinevehicleparking.R;
import com.example.onlinevehicleparking.activities.ParkListItemActivity;
import com.example.onlinevehicleparking.models.parkList;

import java.util.ArrayList;
import java.util.List;

public class ParkListAdapter  extends RecyclerView.Adapter<ParkListAdapter.ParkListViewHolder> {
    private Context mContext;
    private List<parkList> parkList;
    private Fragment fragment;


    public ParkListAdapter(Context mContext, List<parkList> parkList) {
        this.mContext = mContext;
        this.parkList = parkList;
    }

    @NonNull
    @Override
    public ParkListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_park_list, parent, false);
        return new ParkListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ParkListViewHolder holder, final int position) {
        final parkList list = parkList.get(position);
        holder.parkName.setText(list.getPark_name());
        holder.phnNum.setText(list.getPhone());
        holder.add.setText(list.getAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = (int) getItemId(position);
               Intent intent = new Intent(mContext,ParkListItemActivity.class);
                intent.putExtra("parkName", list.getPark_name());
                intent.putExtra("add", list.getAddress());
                intent.putExtra("phn", list.getPhone());
                intent.putExtra("cap", list.getCapacity());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return parkList.size();
    }


    class ParkListViewHolder extends RecyclerView.ViewHolder{

        TextView parkName, phnNum, capacity, add;
        private LinearLayout linearLayout;

        public ParkListViewHolder(@NonNull View itemView) {
            super(itemView);

            parkName = itemView.findViewById(R.id.textView_ParkName);
            phnNum = itemView.findViewById(R.id.textView_Phn_num);
            add = itemView.findViewById(R.id.textView_map_loc);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

    public void setFilter(List<parkList> dataModels){
        parkList = new ArrayList<>();
        parkList.addAll(dataModels);
        notifyDataSetChanged();
    }


}