package com.example.food_app.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.food_app.LoginActivity;
import com.example.food_app.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Hiển thị tên người dùng
        profileViewModel.getUserName().observe(getViewLifecycleOwner(), name -> {
            if (name != null) {
                binding.tvName.setText(name);
            }
        });

        // Hiển thị mô tả/bio
        profileViewModel.getUserBio().observe(getViewLifecycleOwner(), bio -> {
            if (bio != null) {
                binding.tvBio.setText(bio);
            }
        });

        // Quay lại
        binding.btnBack.setOnClickListener(v -> requireActivity().onBackPressed());

        // Thông tin cá nhân
        binding.itemPersonalInfo.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Thông tin cá nhân - chưa có chức năng", Toast.LENGTH_SHORT).show();
        });

        // Yêu thích
        binding.itemFavourite.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Yêu thích - chưa có chức năng", Toast.LENGTH_SHORT).show();
        });

        // Đăng xuất
        binding.itemLogout.setOnClickListener(v -> {
            Context ctx = requireContext();
            SharedPreferences prefs = ctx.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            prefs.edit().clear().apply();
            Toast.makeText(ctx, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ctx, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
