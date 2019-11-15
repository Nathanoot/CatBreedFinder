package com.infs.catbreedfinder.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.infs.catbreedfinder.R;
import com.infs.catbreedfinder.fragments.FavouritesFragment;
import com.infs.catbreedfinder.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new SearchFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.nav_search) {
                    Fragment fragment = new SearchFragment();
                    swapFragment(fragment);
                    setFragmentTitle("Cat breeds");
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_favourites) {
                    Fragment fragment = new FavouritesFragment();
                    swapFragment(fragment);
                    setFragmentTitle("Favourites");
                    return true;
                }
                return false;
            }
        });
    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void setFragmentTitle(String title) {
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(title);
    }

}
