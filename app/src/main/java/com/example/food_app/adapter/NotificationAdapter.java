package com.example.food_app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.food_app.databinding.ItemNotificationBinding;
import com.example.food_app.model.Notification;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    
    private List<Notification> notificationList;

    public NotificationAdapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification = notificationList.get(position);
        holder.bind(notification);
    }

    @Override
    public int getItemCount() {
        return notificationList != null ? notificationList.size() : 0;
    }

    public void updateNotificationList(List<Notification> newNotificationList) {
        this.notificationList = newNotificationList;
        notifyDataSetChanged();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        private ItemNotificationBinding binding;

        public NotificationViewHolder(@NonNull ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Notification notification) {
            binding.tvNotificationTitle.setText(notification.getTitle());
            binding.tvNotificationMessage.setText(notification.getMessage());
            binding.tvNotificationTime.setText(notification.getTime());
            binding.ivNotificationIcon.setImageResource(notification.getIcon());
        }
    }
}
