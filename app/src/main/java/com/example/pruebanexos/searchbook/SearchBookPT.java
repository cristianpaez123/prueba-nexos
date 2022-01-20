package com.example.pruebanexos.searchbook;

import com.example.pruebanexos.searchbook.model.models.Book;
import com.example.pruebanexos.searchbook.model.repositories.BookRepository;

import java.util.List;


public class SearchBookPT implements SearchBookCT.ViewPresenter, SearchBookCT.RepositoryPresenter {
    SearchBookCT.PresenterView view;
    SearchBookCT.Model model;

    public SearchBookPT(SearchBookCT.PresenterView presenterView) {
        this.view = presenterView;
        this.model = new BookRepository(this);
    }

    private void getBooks(String book) {
        view.showLoading();
        view.closeTecladoMovil();
        this.model.getSearchBooks(book);
    }

    @Override
    public void sendListBooks(List<Book> books) {
        view.hideLoading();
        if (books.size() == 0) {
            view.showtoast();
        } else {
            view.showBooks(books);
        }
    }

    @Override
    public void onClickSearchButton() {
        getBooks(view.getTextFromEdiTextSearchBook());
    }

    @Override
    public void onClickKeyboardSearchButton() {
        getBooks(view.getTextFromEdiTextSearchBook());
    }

}
