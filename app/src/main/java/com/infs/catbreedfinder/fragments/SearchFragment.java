package com.infs.catbreedfinder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.infs.catbreedfinder.FakeDatabase;
import com.infs.catbreedfinder.R;
import com.infs.catbreedfinder.adapters.CatRecyclerViewAdapter;
import com.infs.catbreedfinder.models.Cat;

import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {
//    private static final String TAG = "SearchFragment";


    private RecyclerView recyclerView;
    private SearchView searchView;
    private CatRecyclerViewAdapter catRecyclerViewAdapter = new CatRecyclerViewAdapter();


    public SearchFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.rvSearch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);


        final String url = "https://api.thecatapi.com/v1/breeds";

        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cat[] objectArray = gson.fromJson(response, Cat[].class);
                List catList = Arrays.asList(objectArray);
                FakeDatabase.saveCatList(catList);
                catRecyclerViewAdapter.setData(catList);
                recyclerView.setAdapter(catRecyclerViewAdapter);
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);

        searchView = view.findViewById(R.id.searchBar);
        searchView.setQueryHint("Enter a cat breed.");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                catRecyclerViewAdapter.setData(FakeDatabase.filterList(newText));
                recyclerView.setAdapter(catRecyclerViewAdapter);

                return false;
            }
        });

        return view;
    }
}
