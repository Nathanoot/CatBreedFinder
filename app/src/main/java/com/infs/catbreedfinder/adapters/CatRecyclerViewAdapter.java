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

public class CatRecyclerViewAdapter extends
        RecyclerView.Adapter<CatRecyclerViewAdapter.CatViewHolder> {
    //private static final String TAG = "CatRecyclerViewAdapter";

    private List<Cat> catsToAdapt;


    public void setData(List<Cat> cats) {
        this.catsToAdapt = cats;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat, parent, false);

        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {

        final Cat catAtPosition = catsToAdapt.get(position);
        //Log.d(TAG, "bind: Cat list: Name= " + catAtPosition.getCatBreed() + " catID= " + catAtPosition.getCatID());

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
        if (catsToAdapt.isEmpty()) {
            return 0;
        }
        return catsToAdapt.size();
    }


    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catNameTextView;

        // This constructor is used in onCreateViewHolder
        public CatViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            catNameTextView = v.findViewById(R.id.catName);
        }
    }
}
