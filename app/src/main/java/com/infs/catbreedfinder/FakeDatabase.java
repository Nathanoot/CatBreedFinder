package com.infs.catbreedfinder;

import com.infs.catbreedfinder.models.Cat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FakeDatabase {
    public static HashMap<String, Cat> savedCatList = new HashMap<>();

    public static ArrayList<Cat> favouritesCatList = new ArrayList<>();

    public static Cat getCatById(String id) {
        return savedCatList.get(id);
    }

    public static void saveCatList(List<Cat> breedList) {
        for (int i = 0; i < breedList.size(); i++) {
            Cat cat = breedList.get(i);
            savedCatList.put(cat.getCatID(), cat);
        }
    }

    public static List<Cat> filterList(String query) {
        List<Cat> currentList = new ArrayList<>(savedCatList.values());
        List<Cat> list = new ArrayList<>();
        for (int i = 0; i < currentList.size(); i++) {
            String breedName = currentList.get(i).getCatBreed().toLowerCase();
            if (breedName.contains(query.toLowerCase())) {
                list.add(currentList.get(i));
            }
        }
        return list;
    }

    public static int checkDuplicatedFavourite(String id) {
        for (int i = 0; i < favouritesCatList.size(); i++) {
            Cat breedList = favouritesCatList.get(i);
            if (breedList.getCatID().equals(id)) {
                return i;
            }
        }

        return -1;
    }
}
