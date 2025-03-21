package com.example.customadaptergridview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.customadaptergridview.R;
import com.example.customadaptergridview.model.LastProduct;

import java.util.List;

public class LastProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<LastProduct> lastProductList;

    public LastProductAdapter(Context context, int layout, List<LastProduct> lastProductList) {
        this.context = context;
        this.layout = layout;
        this.lastProductList = lastProductList;
    }

    @Override
    public int getCount() {
        return lastProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return lastProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        ImageView imagePic = view.findViewById(R.id.imagePic);

        LastProduct lastProduct = lastProductList.get(i);

        // Load ảnh từ URL bằng Glide
        Glide.with(context)
                .load(lastProduct.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(android.R.color.darker_gray) // Màu xám thay vì ảnh chờ
                .error(android.R.drawable.stat_notify_error) // Icon lỗi mặc định
                .into(imagePic);

        return view;
    }
}
