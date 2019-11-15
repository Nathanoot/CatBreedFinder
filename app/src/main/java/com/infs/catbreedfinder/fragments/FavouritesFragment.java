package com.infs.catbreedfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.infs.catbreedfinder.FakeDatabase;
import com.infs.catbreedfinder.R;
import com.infs.catbreedfinder.adapters.FavouritesRecyclerViewAdapter;

public class FavouritesFragment extends Fragment {

    private RecyclerView recyclerView;
    public static FavouritesRecyclerViewAdapter favouritesRecyclerViewAdapter = new FavouritesRecyclerViewAdapter();

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = view.findViewById(R.id.rvFavourites);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        favouritesRecyclerViewAdapter.setData(FakeDatabase.favouritesCatList);
        recyclerView.setAdapter(favouritesRecyclerViewAdapter);
        return view;
    }
}
