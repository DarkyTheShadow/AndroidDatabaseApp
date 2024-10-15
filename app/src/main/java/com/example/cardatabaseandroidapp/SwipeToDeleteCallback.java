package com.example.cardatabaseandroidapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

// Class to handle swipe gestures for deleting items
public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private CarAdapter carAdapter;

    public SwipeToDeleteCallback(CarAdapter carAdapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT); // Allow swiping left and right
        this.carAdapter = carAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false; // Not implementing item move functionality
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        carAdapter.deleteItem(position); // Delete item using the adapter
    }
}
