package com.example.pruebanexos.searchbook.model.repositories;

import android.util.Log;

import com.example.pruebanexos.searchbook.SearchBookCT;
import com.example.pruebanexos.searchbook.model.api.Api;
import com.example.pruebanexos.searchbook.model.api.ApiService;
import com.example.pruebanexos.searchbook.model.models.BooksResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository implements SearchBookCT.Model {

    SearchBookCT.RepositoryPresenter presenter;

    public BookRepository(SearchBookCT.RepositoryPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getSearchBooks(String book) {
        Api.getInstance();
        ApiService service = Api.getInstance().create(ApiService.class);
        final Call<BooksResponse> modelsCall = service.ListBooks(book);

        modelsCall.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                presenter.sendListBooks(response.body().books);

            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {

            }
        });
    }
}
