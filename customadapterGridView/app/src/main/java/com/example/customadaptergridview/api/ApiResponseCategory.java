package com.example.customadaptergridview.api;
import com.example.customadaptergridview.model.Category;
import java.util.List;

public class ApiResponseCategory {
    private boolean status;
    private String message;
    private List<Category> body; // Trùng với JSON API

    public List<Category> getBody() {
        return body;
    }
}