package com.example.food_app.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.food_app.adapter.OrderAdapter;
import com.example.food_app.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;
    private com.example.food_app.adapter.FoodAdapter foodAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Thiết lập RecyclerView cho danh sách món ăn
        binding.rvFood.setLayoutManager(new LinearLayoutManager(getContext()));
        foodAdapter = new com.example.food_app.adapter.FoodAdapter(null);
        binding.rvFood.setAdapter(foodAdapter);

        // Quan sát danh sách món ăn từ ViewModel
        dashboardViewModel.getFoodList().observe(getViewLifecycleOwner(), foods -> {
            if (foods != null) {
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