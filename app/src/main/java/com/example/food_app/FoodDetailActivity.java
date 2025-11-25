package com.example.food_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailActivity extends AppCompatActivity {

    private ImageView btnBack, btnFavorite, imgProduct;
    private TextView tvProductName, tvRestaurantName, tvDescription, tvCookingMethod;
    private TextView tvQuantity, btnMinus, btnPlus;
    private Button btnAddToCart;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        // Hide ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        btnBack = findViewById(R.id.btnBack);
        btnFavorite = findViewById(R.id.btnFavorite);
        imgProduct = findViewById(R.id.imgProduct);
        tvProductName = findViewById(R.id.tvProductName);
        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        tvDescription = findViewById(R.id.tvDescription);
        tvCookingMethod = findViewById(R.id.tvCookingMethod);
        tvQuantity = findViewById(R.id.tvQuantity);
        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // Back button
        btnBack.setOnClickListener(v -> onBackPressed());

        // Favorite button
        btnFavorite.setOnClickListener(v -> {
            Toast.makeText(this, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
            btnFavorite.setImageTintList(getColorStateList(R.color.white));
        });

        // Minus button
        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        // Plus button
        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        // Add to cart button
        btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(this, "Đã thêm " + quantity + " sản phẩm vào giỏ", Toast.LENGTH_SHORT).show();
            // TODO: Add to cart logic
        });

        // Load product details (from intent or API)
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
                tvProductName.setText(foodName);
                tvRestaurantName.setText("NGUYỄN LIỄU");
                tvDescription.setText(foodDesc != null ? foodDesc : "");
                tvCookingMethod.setText("Nước dùng tinh về hương vị, được nấu từ xương gà, xương bò, hành, gừng, quế, hồi...");
                if (foodImageId > 0) {
                    imgProduct.setImageResource(foodImageId);
                }
            }
        } else {
            // Fallback to mock data
            tvProductName.setText("Phở Bò Nam Định");
            tvRestaurantName.setText("NGUYỄN LIỄU");
            tvDescription.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
            tvCookingMethod.setText("Nước dùng tinh về hương vị, được nấu từ xương gà, xương bò, hành, gừng, quế, hồi...");
        }
    }
}
