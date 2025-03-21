package com.example.customadaptergridview.api;
import com.example.customadaptergridview.model.Product;

import java.util.List;

public class ApiResponseProduct {
    private boolean status;
    private String message;
    private List<Product> body;

    public boolean isStatus() { return status; }
    public String getMessage() { return message; }
    public List<Product> getBody() { return body; }
}
