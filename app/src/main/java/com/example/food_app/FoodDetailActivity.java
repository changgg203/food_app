package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailActivity extends AppCompatActivity {

    private ImageButton btnBack, btnFavorite, btnFavoriteFilled;
    private ImageView imgFoodCover;
    private TextView tvFoodName, tvIngredients, tvDescription, tvHowToCook;
    private int quantity = 1;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        btnBack = findViewById(R.id.btn_back);
        btnFavorite = findViewById(R.id.btn_favorite);
        btnFavoriteFilled = findViewById(R.id.btn_favorite_filled);
        imgFoodCover = findViewById(R.id.imgFoodCover);
        tvFoodName = findViewById(R.id.tvFoodName);
        tvIngredients = findViewById(R.id.tvIngredients);
        tvDescription = findViewById(R.id.tvDescription);
        tvHowToCook = findViewById(R.id.tvHowToCook);

        // Back button
        btnBack.setOnClickListener(v -> onBackPressed());

        // Favorite button - toggle between empty and filled
        btnFavorite.setOnClickListener(v -> {
            isFavorite = true;
            btnFavorite.setVisibility(ImageView.GONE);
            btnFavoriteFilled.setVisibility(ImageView.VISIBLE);
            Toast.makeText(this, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
        });

        // Favorite filled button - toggle back to empty
        btnFavoriteFilled.setOnClickListener(v -> {
            isFavorite = false;
            btnFavoriteFilled.setVisibility(ImageView.GONE);
            btnFavorite.setVisibility(ImageView.VISIBLE);
            Toast.makeText(this, "Đã xóa khỏi yêu thích", Toast.LENGTH_SHORT).show();
        });

        // Load product details
        loadProductDetails();
    }

    private void loadProductDetails() {
        // Fetch product details from intent
        Intent intent = getIntent();
        if (intent != null) {
            String foodName = intent.getStringExtra("foodName");
            String foodDesc = intent.getStringExtra("foodDesc");
            float foodRating = intent.getFloatExtra("foodRating", 0);
            String foodTime = intent.getStringExtra("foodTime");
            int foodImageId = intent.getIntExtra("foodImageId", 0);

            if (foodName != null) {
                tvFoodName.setText(foodName);
                tvIngredients.setText(foodDesc != null ? foodDesc : "Bánh phở tươi, thịt bò tái, nạm, gầu, hành lá, rau thơm, giá đỗ, nước dùng ninh xương bò 12 tiếng...");
                tvDescription.setText("Phở Nam Định chuẩn vị với nước dùng trong veo, thơm lừng hành gừng và thịt bò tươi ngon. Được nấu theo công thức truyền thống của người Nam Định.");
                tvHowToCook.setText("Xương bò rửa sạch, ninh lửa nhỏ 10-12 tiếng cùng hành, gừng, quế, hồi, đinh hương. Chế phẩm phở vào bát nóng, thêm thịt bò tái và nước dùng nóng.");
                if (foodImageId > 0) {
                    imgFoodCover.setImageResource(foodImageId);
                }
            }
        } else {
            // Fallback to mock data
            tvFoodName.setText("Phở Bò Nam Định");
            tvIngredients.setText("Bánh phở tươi, thịt bò tái, nạm, gầu, hành lá, rau thơm, giá đỗ, nước dùng ninh xương bò 12 tiếng...");
            tvDescription.setText("Phở Nam Định chuẩn vị với nước dùng trong veo, thơm lừng hành gừng và thịt bò tươi ngon.");
            tvHowToCook.setText("Xương bò rửa sạch, ninh lửa nhỏ 10-12 tiếng cùng hành, gừng, quế, hồi, đinh hương.");
        }
    }
}
