package com.example.reservation;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<DentistItem> itemList;
    private Context context;

    public ItemAdapter(Context context, List<DentistItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override

    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        DentistItem item = itemList.get(position);
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText("$" + item.getPrice());

        // Set the image for the item
        holder.itemImageView.setImageResource(item.getImageResourceId());

        // Handle Add to Cart action
        holder.addToCartButton.setOnClickListener(v -> {
            Toast.makeText(context, item.getName() + " added to cart", Toast.LENGTH_SHORT).show();
        });

        // Handle Buy Now action
        holder.buyNowButton.setOnClickListener(v -> {
            Toast.makeText(context, "Buying " + item.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView;
        ImageView itemImageView;
        Button addToCartButton, buyNowButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            itemImageView = itemView.findViewById(R.id.itemImageView); // Image view for item
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
            buyNowButton = itemView.findViewById(R.id.buyNowButton);
        }
    }
}
