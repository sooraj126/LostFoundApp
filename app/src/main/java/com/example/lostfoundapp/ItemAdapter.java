package com.example.lostfoundapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context;
    List<ItemModel> list;

    public ItemAdapter(Context context, List<ItemModel> list) {
        this.context = context;
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtType, txtCategory, txtLocation, txtTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtType = itemView.findViewById(R.id.txtType);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            txtTimestamp = itemView.findViewById(R.id.txtTimestamp);
        }
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        ItemModel item = list.get(position);

        holder.txtName.setText(item.getName());
        holder.txtType.setText("Type: " + item.getType());
        holder.txtCategory.setText("Category: " + item.getCategory());
        holder.txtLocation.setText("Location: " + item.getLocation());
        holder.txtTimestamp.setText("Posted: " + item.getTimestamp());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.putExtra("id", item.getId());
            intent.putExtra("name", item.getName());
            intent.putExtra("type", item.getType());
            intent.putExtra("phone", item.getPhone());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("date", item.getDate());
            intent.putExtra("location", item.getLocation());
            intent.putExtra("category", item.getCategory());
            intent.putExtra("image", item.getImage());
            intent.putExtra("timestamp", item.getTimestamp());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}