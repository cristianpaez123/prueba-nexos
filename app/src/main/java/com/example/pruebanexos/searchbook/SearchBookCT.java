package com.example.pruebanexos.searchbook;

import com.example.pruebanexos.searchbook.model.models.Book;

import java.util.List;

public interface SearchBookCT {

    interface PresenterView {
        void showBooks(List<Book> books);
        void showLoading();
        String getTextFromEdiTextSearchBook();
        void hideLoading();
        void closeTecladoMovil();
        void showtoast();
    }

    interface ViewPresenter {
        void onClickSearchButton();
        void onClickKeyboardSearchButton();
    }

    interface RepositoryPresenter {
        void sendListBooks(List<Book> books);
    }

    interface Model {
        void getSearchBooks(String book);
    }
}
