package com.cat.couriertracker.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cat.couriertracker.R;

import java.util.ArrayList;

public class GetAllTrackingDetails extends RecyclerView.Adapter<GetAllTrackingDetails.GetViewHolder>{
    ArrayList<Long> orderId;

    ArrayList<String> name,status;
    public GetAllTrackingDetails(ArrayList<Long> orderId, ArrayList<String> name, ArrayList<String> status)
    {
        this.orderId=orderId;
        this.name=name;
        this.status=status;

    }


    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public GetViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_sample, parent, false);

        return new GetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull GetAllTrackingDetails.GetViewHolder holder, int position) {


        holder.txtOrderId.setText(orderId.get(position).toString());
        holder.txtName.setText(name.get(position));
         holder.txtStatus.setText(status.get(position));
    }

    @Override
    public int getItemCount() {
        return orderId.size();
    }

    public  class GetViewHolder extends RecyclerView.ViewHolder{
        TextView txtOrderId,txtName,txtStatus;

        public GetViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            txtOrderId=itemView.findViewById(R.id.txtOrderId);
            txtName=itemView.findViewById(R.id.txtName);
            txtStatus=itemView.findViewById(R.id.txtStatus);


        }
    }
}
