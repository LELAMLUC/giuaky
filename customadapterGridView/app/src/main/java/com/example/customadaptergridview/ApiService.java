package com.example.customadaptergridview;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products/images") // Endpoint trả về danh sách URL ảnh
    Call<List<String>> getImageUrls();
}
