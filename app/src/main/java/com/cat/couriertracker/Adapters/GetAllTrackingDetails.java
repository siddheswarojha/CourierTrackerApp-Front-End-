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

    ArrayList<String> name, itemType,startLocation, currentLocation,expectedDeliveryDate,status, deliveryLocation;
    public GetAllTrackingDetails( ArrayList<Long> orderId, ArrayList<String> name, ArrayList<String> itemType , ArrayList<String> startLocation,
                                  ArrayList<String> currentLocation, ArrayList<String> expectedDeliveryDate,ArrayList<String> status, ArrayList<String> deliveryLocation)
    {

        this.orderId=orderId;
        this.name=name;
        this.itemType=itemType;
        this.currentLocation=currentLocation;
        this.startLocation=startLocation;
        this.expectedDeliveryDate=expectedDeliveryDate;
        this.status=status;
        this.deliveryLocation=deliveryLocation;
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
        holder.txtItemType.setText(itemType.get(position));
        holder.txtCurrentLocation.setText(currentLocation.get(position));
         holder.txtStartLocation.setText(startLocation.get(position));
         holder.txtExpectedDeliveryDate.setText(expectedDeliveryDate.get(position));
         holder.txtDeliveryLocation.setText(deliveryLocation.get(position));
         holder.txtStatus.setText(status.get(position));
    }

    @Override
    public int getItemCount() {
        return orderId.size();
    }

    public  class GetViewHolder extends RecyclerView.ViewHolder{
        TextView txtOrderId,txtName,txtItemType,txtStartLocation,
                txtCurrentLocation,txtExpectedDeliveryDate,txtStatus,txtDeliveryLocation;

        public GetViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            txtOrderId=itemView.findViewById(R.id.txtOrderId);
            txtName=itemView.findViewById(R.id.txtName);
            txtItemType=itemView.findViewById(R.id.txtItemType);
            txtStartLocation=itemView.findViewById(R.id.txtStartLocation);
            txtCurrentLocation=itemView.findViewById(R.id.txtCurrentLocation);
            txtExpectedDeliveryDate=itemView.findViewById(R.id.txtExpectedDeliveryDate);
            txtStatus=itemView.findViewById(R.id.txtStatus);
            txtDeliveryLocation=itemView.findViewById(R.id.txtDeliveryLocation);

        }
    }
}
