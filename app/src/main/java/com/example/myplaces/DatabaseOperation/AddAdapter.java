package com.example.myplaces.DatabaseOperation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myplaces.R;

import java.util.ArrayList;
import java.util.List;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.AddHolder> {

    private List<EntityTable> entityTables = new ArrayList<>();

    @NonNull
    @Override
    public AddHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_item, parent , false);
        return new AddHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddHolder holder, int position) {

        EntityTable entityTable = entityTables.get(position);
        holder.textViewAddress.setText(entityTable.getAddress());
        holder.textViewDescription.setText(entityTable.getDescription());
        holder.textViewPriority.setText(String.valueOf(entityTable.getPriority()));

    }

    @Override
    public int getItemCount() {
        return entityTables.size();
    }

    public void setEntityTables(List<EntityTable> entityTables){
        this.entityTables = entityTables;
        notifyDataSetChanged();
    }

    public EntityTable getEntityAt(int position){
        return entityTables.get(position);
    }

    class AddHolder extends RecyclerView.ViewHolder{
        private TextView textViewAddress;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public AddHolder(@NonNull View itemView) {
            super(itemView);
            textViewAddress = itemView.findViewById(R.id.address);
            textViewDescription = itemView.findViewById(R.id.des);
            textViewPriority = itemView.findViewById(R.id.priority);
        }
    }


}
