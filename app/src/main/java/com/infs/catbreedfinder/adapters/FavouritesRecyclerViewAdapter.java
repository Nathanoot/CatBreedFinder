package com.infs.catbreedfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.infs.catbreedfinder.R;
import com.infs.catbreedfinder.activities.CatDetailActivity;
import com.infs.catbreedfinder.models.Cat;

import java.util.List;

public class FavouritesRecyclerViewAdapter extends
        RecyclerView.Adapter<FavouritesRecyclerViewAdapter.FavouritesViewHolder> {

    private List<Cat> catsToAdapt;

    public void setData(List<Cat> catsToAdapt) {
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat, parent, false);
        if (view == null) {

        }

        FavouritesViewHolder favouritesViewHolder = new FavouritesViewHolder(view);
        return favouritesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesViewHolder holder, int position) {
        final Cat catAtPosition = catsToAdapt.get(position);

        holder.catNameTextView.setText(catAtPosition.getCatBreed());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatDetailActivity.class);
                //Log.d(TAG, "onClick: ID is " + catAtPosition.getCatID());
                intent.putExtra("id", catAtPosition.getCatID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (catsToAdapt.size() == 0) {
            return 0;
        }
        return catsToAdapt.size();
    }


    public static class FavouritesViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catNameTextView;

        // This constructor is used in onCreateViewHolder
        public FavouritesViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            catNameTextView = v.findViewById(R.id.catName);
        }
    }
}
