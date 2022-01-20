package com.example.pruebanexos.searchbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pruebanexos.R;
import com.example.pruebanexos.detailsearchbook.DetailBookVW;
import com.example.pruebanexos.searchbook.adapter.BookAdapter;
import com.example.pruebanexos.searchbook.model.models.Book;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SearchBookCT.PresenterView {

    SearchBookCT.ViewPresenter presenter;
    EditText editextSearchBook;
    ImageButton imabtn_search;
    RecyclerView recyclerViewBook;
    ProgressBar loadingBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new SearchBookPT(this);
        editextSearchBook = findViewById(R.id.editextSearchBook);
        imabtn_search = findViewById(R.id.imabtn_search);
        imabtn_search.setOnClickListener(this);
        recyclerViewBook = findViewById(R.id.recyclerView);
        loadingBooks = findViewById(R.id.progressBarLoadingBooks);

        onEditorAction();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imabtn_search:
                presenter.onClickSearchButton();
                break;
        }
    }

    @Override
    public void showBooks(List<Book> books) {
        loadingBooks.setVisibility(View.GONE);
        BookAdapter adapter = new BookAdapter(books, this);
        recyclerViewBook.setAdapter(adapter);
        recyclerViewBook.setHasFixedSize(true);
        GridLayoutManager gridLayout = new GridLayoutManager(this, 2);
        recyclerViewBook.setLayoutManager(gridLayout);

        adapter.setOnClickListener(new BookAdapter.OnClickListener() {
            @Override
            public void OnClick(Book book) {
                Intent intent = new Intent(MainActivity.this, DetailBookVW.class);
                intent.putExtra(DetailBookVW.TITLE_EXTRA, book.title);
                intent.putExtra(DetailBookVW.IMAGE_EXTRA, book.image);
                intent.putExtra(DetailBookVW.PRICE_EXTRA, book.price);
                intent.putExtra(DetailBookVW.URL_EXTRA, book.url);
                intent.putExtra(DetailBookVW.SUBTITLE_EXTRA, book.subtitle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showLoading() {
        loadingBooks.setVisibility(View.VISIBLE);
    }

    public void onEditorAction() {
        editextSearchBook.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.onClickKeyboardSearchButton();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void closeTecladoMovil() {
        View view = this.getCurrentFocus();
        InputMethodManager imn = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null) {
            imn.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showtoast() {
        Toast.makeText(this, "No se han encontrado resultados", Toast.LENGTH_LONG).show();
    }

    public String getTextFromEdiTextSearchBook() {
        return editextSearchBook.getText().toString();
    }

    @Override
    public void hideLoading() {
        loadingBooks.setVisibility(View.GONE);
    }
}