package com.example.food_app.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.food_app.model.Order;
import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Order>> orderList;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        
        orderList = new MutableLiveData<>();
        loadOrderData();
    }

    private void loadOrderData() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Đơn #12345", "Rose Garden Restaurant", "2x Burger, 1x Fries", "150.000đ", "Đang giao"));
        orders.add(new Order("Đơn #12346", "Phở Bò Hà Nội", "1x Phở Bò, 2x Trà đá", "120.000đ", "Đang chuẩn bị"));
        orders.add(new Order("Đơn #12347", "Bánh Mì Huế", "3x Bánh mì thịt", "180.000đ", "Đã giao"));
        orders.add(new Order("Đơn #12348", "Cơm Tấm Sài Gòn", "1x Cơm tấm, 1x Trứng", "95.000đ", "Đang giao"));
        
        orderList.setValue(orders);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Order>> getOrderList() {
        return orderList;
    }
}