package com.infs.catbreedfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.infs.catbreedfinder.FakeDatabase;
import com.infs.catbreedfinder.R;
import com.infs.catbreedfinder.fragments.FavouritesFragment;
import com.infs.catbreedfinder.models.Cat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CatDetailActivity extends AppCompatActivity {


    private TextView catBreedNameTextView;
    private ImageView catImageView;
    private TextView descriptionTextView;
    private TextView weightTextView;
    private TextView temperamentTextView;
    private TextView originTextView;
    private TextView lifeSpanTextView;
    private TextView wikiLinkTextView;
    private TextView dogFriendLevelTextView;
    private ImageView catFavouriteToggle;
    private String imageLink;

//   private static final String TAG = "CatDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        Intent intent = getIntent();

        final String breedId = intent.getStringExtra("id");

        final Cat cat = FakeDatabase.getCatById(breedId);

        catBreedNameTextView = findViewById(R.id.catBreedName);
        catImageView = findViewById(R.id.imageCat);
        descriptionTextView = findViewById(R.id.description);
        weightTextView = findViewById(R.id.weight);
        temperamentTextView = findViewById(R.id.temperament);
        originTextView = findViewById(R.id.origin);
        lifeSpanTextView = findViewById(R.id.lifeSpan);
        wikiLinkTextView = findViewById(R.id.wikiLink);
        dogFriendLevelTextView = findViewById(R.id.dogFriendLevel);
        catFavouriteToggle = findViewById(R.id.imageFavouriteToggle);

        final String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + breedId;

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Obtaining the image
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.d(TAG, "onResponse: Starts and passes:" + response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    if (jsonObject.has("url")) {
                        imageLink = jsonObject.getString("url");
                        if (!imageLink.equals("")) {
                            Glide.with(getApplicationContext()).load(imageLink).into(catImageView);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "onErrorResponse: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);

        // Displaying data in the text views
        catBreedNameTextView.setText(cat.getCatBreed());
        descriptionTextView.setText(cat.getDescription());

        StringBuffer weightString = new StringBuffer("Weight: ");
        weightString.append(cat.getWeight().getMetric());
        weightString.append("kgs");
        weightTextView.setText(weightString);

        StringBuffer temperamentString = new StringBuffer("Temperament: ");
        temperamentString.append(cat.getTemperament());
        temperamentTextView.setText(temperamentString);

        StringBuffer originString = new StringBuffer("Origin: ");
        originString.append(cat.getOrigin());
        originTextView.setText(originString);

        StringBuffer lifeSpanString = new StringBuffer("Life Span: ");
        lifeSpanString.append(cat.getLifeSpan());
        lifeSpanTextView.setText(lifeSpanString);

        StringBuffer wikiLinkString = new StringBuffer("Link: ");
        wikiLinkString.append(cat.getWikiLink());
        wikiLinkTextView.setText(wikiLinkString);

        StringBuffer dogLevel = new StringBuffer("Dog Friendliness: ");
        dogLevel.append(cat.getDogFriendLevel());
        dogFriendLevelTextView.setText(dogLevel);

        // Checks if cat is in Favourites
        if (FakeDatabase.checkDuplicatedFavourite(breedId) > -1) {
            catFavouriteToggle.setImageResource(R.drawable.ic_favorite_red_24dp);
        }


        // Toggling when cat is in favourites and when not in favourites arrayList
        catFavouriteToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duplicate = FakeDatabase.checkDuplicatedFavourite(breedId);

                if (duplicate == -1) {
                    catFavouriteToggle.setImageResource(R.drawable.ic_favorite_red_24dp);
                    FakeDatabase.favouritesCatList.add(cat);
                    Toast.makeText(getApplicationContext(), "You have added " + cat.getCatBreed() + " to your favourites", Toast.LENGTH_SHORT).show();
                    FavouritesFragment.favouritesRecyclerViewAdapter.notifyDataSetChanged();

                } else {
                    catFavouriteToggle.setImageResource(R.drawable.ic_favorite_black_24dp);
                    FakeDatabase.favouritesCatList.remove(duplicate);
                    Toast.makeText(getApplicationContext(), "You have removed " + cat.getCatBreed() + " to your favourites", Toast.LENGTH_SHORT).show();
                    FavouritesFragment.favouritesRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
