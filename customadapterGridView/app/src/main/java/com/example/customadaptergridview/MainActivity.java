package com.example.customadaptergridview;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<LastProduct> arrayList;
    LastProductAdapter adapter;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview1);
        arrayList = new ArrayList<>();
        adapter = new LastProductAdapter(this, R.layout.row_monhoc, arrayList);
        gridView.setAdapter(adapter);

        apiService = RetrofitClient.getClient().create(ApiService.class);
        fetchImages();
    }

    private void fetchImages() {
        apiService.getImageUrls().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API_RESPONSE", "Images: " + response.body()); // Kiểm tra log dữ liệu
                    for (String url : response.body()) {
                        arrayList.add(new LastProduct("Image", "Description", url));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e("API_ERROR", "Response failed: " + response.code());
                    Toast.makeText(MainActivity.this, "API Response Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("API_ERROR", "Failed to connect: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to load images", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
