package com.example.pruebanexos.searchbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pruebanexos.R;
import com.example.pruebanexos.searchbook.model.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolderDatos> {

    List<Book> items;
    private Context context;
    private OnClickListener listener;


    public BookAdapter(List<Book> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_books, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        Book book = items.get(position);

        holder.title.setText(book.title);

        Picasso.with(context)
                .load(book.image)
                .into(holder.frontPage);

        holder.cardViewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.OnClick(book);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();

    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView title;
        ImageView frontPage;
        CardView cardViewBook;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txtTitle);
            frontPage = itemView.findViewById(R.id.imageFrontPage);
            cardViewBook = itemView.findViewById(R.id.cardViewBook);
        }
    }

    public interface OnClickListener {
        void OnClick(Book book);
    }
}
