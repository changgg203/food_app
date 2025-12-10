package com.example.food_app.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.food_app.model.Food;
import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Food>> foodList;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        
        foodList = new MutableLiveData<>();
        loadFoodData();
    }

    private void loadFoodData() {
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Burger Bò Phô Mai", "Bánh burger bò với phô mai tan chảy", 4.5f, "20 phút", android.R.drawable.ic_menu_camera));
        foods.add(new Food("Phở Bò Hà Nội", "Phở bò truyền thống Hà Nội", 4.8f, "15 phút", android.R.drawable.ic_menu_gallery));
        foods.add(new Food("Bánh Mì Thịt", "Bánh mì thịt đặc biệt", 4.2f, "10 phút", android.R.drawable.ic_menu_manage));
        foods.add(new Food("Cơm Tấm Sườn Trứng", "Cơm tấm sườn trứng thơm ngon", 4.7f, "18 phút", android.R.drawable.ic_menu_slideshow));
        foodList.setValue(foods);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Food>> getFoodList() {
        return foodList;
    }
}