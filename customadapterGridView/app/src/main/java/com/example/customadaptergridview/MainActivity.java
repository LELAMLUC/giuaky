package com.example.customadaptergridview;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.customadaptergridview.adapter.CategoryAdapter;
import com.example.customadaptergridview.adapter.ProductAdapter;
import com.example.customadaptergridview.api.ApiClient;
import com.example.customadaptergridview.api.ApiResponseCategory;
import com.example.customadaptergridview.api.ApiResponseProduct;
import com.example.customadaptergridview.api.ApiService;
import com.example.customadaptergridview.model.Category;
import com.example.customadaptergridview.model.Product;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements CategoryAdapter.OnCategoryClickListener {
    private RecyclerView recyclerViewCategories;
    private GridView gridViewProducts;
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        gridViewProducts = findViewById(R.id.gridView);

        apiService = ApiClient.getClient().create(ApiService.class);

        loadCategories();
    }
    private void setupCategoryRecyclerView(List<Category> categories) {
        categoryAdapter = new CategoryAdapter(this, categories, this);
        // Truyền đủ 3 tham số
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategories.setAdapter(categoryAdapter);
    }


    private void loadCategories() {
        apiService.getCategories().enqueue(new Callback<ApiResponseCategory>() {
            @Override
            public void onResponse(Call<ApiResponseCategory> call, Response<ApiResponseCategory> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body().getBody();
                    if (categories != null && !categories.isEmpty()) {
                        setupCategoryRecyclerView(categories);
                        loadProducts(categories.get(0).getId()); // Tải sản phẩm của danh mục đầu tiên
                    } else {
                        Toast.makeText(MainActivity.this, "Không có danh mục!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi tải danh mục!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseCategory> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi kết nối", t);
                Toast.makeText(MainActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void loadProducts(int categoryId) {
        apiService.getProductsByCategory(categoryId, new Object()).enqueue(new Callback<ApiResponseProduct>() {
            @Override
            public void onResponse(Call<ApiResponseProduct> call, Response<ApiResponseProduct> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> productList = response.body().getBody();
                    if (productList != null && !productList.isEmpty()) {
                        productAdapter = new ProductAdapter(MainActivity.this, productList);
                        gridViewProducts.setAdapter(productAdapter);
                    } else {
                        Toast.makeText(MainActivity.this, "Không có sản phẩm!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi tải sản phẩm!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseProduct> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int categoryId) {
        loadProducts(categoryId);
    }
}
