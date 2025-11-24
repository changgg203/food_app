package com.example.food_app.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.food_app.R;
import com.example.food_app.model.Notification;
import java.util.ArrayList;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Notification>> notificationList;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
        
        notificationList = new MutableLiveData<>();
        loadNotificationData();
    }

    private void loadNotificationData() {
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification("Đơn hàng của bạn đang được chuẩn bị", "Nhà hàng đang chuẩn bị đơn hàng của bạn", "5 phút trước", R.drawable.ic_star));
        notifications.add(new Notification("Tài xế đang trên đường", "Tài xế của bạn sẽ đến trong 15 phút", "10 phút trước", R.drawable.ic_star));
        notifications.add(new Notification("Khuyến mãi mới", "Giảm 20% cho đơn hàng tiếp theo", "1 giờ trước", R.drawable.ic_star));
        notifications.add(new Notification("Đơn hàng đã giao", "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi", "2 giờ trước", R.drawable.ic_star));
        
        notificationList.setValue(notifications);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Notification>> getNotificationList() {
        return notificationList;
    }
}