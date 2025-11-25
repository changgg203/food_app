package com.example.food_app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_app.FoodDetailActivity;
import com.example.food_app.adapter.FoodAdapter;
import com.example.food_app.databinding.FragmentHomeBinding;
import com.example.food_app.model.Food;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private FoodAdapter foodAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Setup RecyclerView
        RecyclerView rvFoods = binding.rvFoods;
        rvFoods.setLayoutManager(new LinearLayoutManager(getContext()));
        foodAdapter = new FoodAdapter(null);
        rvFoods.setAdapter(foodAdapter);

        // Set click listener for food items
        foodAdapter.setOnFoodClickListener(food -> {
            Intent intent = new Intent(getContext(), FoodDetailActivity.class);
            intent.putExtra("foodName", food.getName());
            intent.putExtra("foodDesc", food.getDescription());
            intent.putExtra("foodRating", food.getRating());
            intent.putExtra("foodTime", food.getTime());
            intent.putExtra("foodImageId", food.getImageResId());
            startActivity(intent);
        });

        // Observe food list from ViewModel
        homeViewModel.getFoodList().observe(getViewLifecycleOwner(), foods -> {
            if (foodAdapter != null) {
                foodAdapter.updateFoodList(foods);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}