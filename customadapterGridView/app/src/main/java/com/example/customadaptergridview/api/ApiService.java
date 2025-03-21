    package com.example.customadaptergridview.api;

    import com.example.customadaptergridview.model.Category;

    import java.util.List;

    import retrofit2.Call;
    import retrofit2.http.Body;
    import retrofit2.http.GET;
    import retrofit2.http.POST;
    import retrofit2.http.Query;

    public interface ApiService {
        @POST("api/product/getProductsByCategoryID")  // Dùng POST thay vì GET
        Call<ApiResponseProduct> getProductsByCategory(@Query("categoryID") int categoryId, @Body Object emptyBody);
        @GET("api/category")
        Call<ApiResponseCategory> getCategories();

    }
