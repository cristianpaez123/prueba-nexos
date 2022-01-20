package com.example.pruebanexos.searchbook.model.api;

import com.example.pruebanexos.searchbook.model.models.BooksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("search/{id}")
    public Call<BooksResponse> ListBooks(@Path("id") String id);
}
