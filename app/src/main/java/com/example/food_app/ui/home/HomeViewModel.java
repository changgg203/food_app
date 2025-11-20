package com.example.food_app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.food_app.R;
import com.example.food_app.model.Food;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Food>> foodList;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        
        foodList = new MutableLiveData<>();
        loadFoodData();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Food>> getFoodList() {
        return foodList;
    }

    private void loadFoodData() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Rose Garden Restaurant", "Burger - Chicken - Riche - Wings", 4.7f, "20 min", R.drawable.ic_launcher_background));
        foods.add(new Food("Pizza Palace", "Pizza - Pasta - Italian Cuisine", 4.5f, "25 min", R.drawable.ic_launcher_background));
        foods.add(new Food("Sushi Express", "Sushi - Rolls - Japanese Food", 4.9f, "30 min", R.drawable.ic_launcher_background));
        foods.add(new Food("Burger King", "Burger - Fries - Drinks", 4.3f, "15 min", R.drawable.ic_launcher_background));
        foods.add(new Food("Pho Vietnam", "Pho - Banh Mi - Vietnamese", 4.8f, "22 min", R.drawable.ic_launcher_background));
        foodList.setValue(foods);
    }
}