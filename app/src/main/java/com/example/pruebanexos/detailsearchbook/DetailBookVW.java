package com.example.pruebanexos.detailsearchbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pruebanexos.R;
import com.squareup.picasso.Picasso;

public class DetailBookVW extends AppCompatActivity {

    TextView txtTitle, txtSubTitle, txtUrl, txtPrice;
    ImageView imageViewDetail;

    public static final String TITLE_EXTRA = "TITLE_EXTRA";
    public static final String SUBTITLE_EXTRA = "SUBTITLE_EXTRA";
    public static final String IMAGE_EXTRA = "IMAGE_EXTRA";
    public static final String PRICE_EXTRA = "PRICE_EXTRA";
    public static final String URL_EXTRA = "URL_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);


        txtTitle = findViewById(R.id.txtTitle);
        txtSubTitle = findViewById(R.id.txtsubTitle);
        txtUrl = findViewById(R.id.txtUrl);
        txtPrice = findViewById(R.id.txtPrice);
        txtPrice = findViewById(R.id.txtPrice);
        imageViewDetail = findViewById(R.id.imageViewDetail);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString(TITLE_EXTRA);
        String subTitle = bundle.getString(SUBTITLE_EXTRA);
        String image = bundle.getString(IMAGE_EXTRA);
        String url = bundle.getString(URL_EXTRA);
        String price = bundle.getString(PRICE_EXTRA);

        setText(title, txtTitle);
        setText(subTitle, txtSubTitle);
        setText(url, txtUrl);
        setText(price, txtPrice);
        setText(title, txtTitle);


        if (image == null || image.isEmpty()){
            imageViewDetail.setVisibility(View.GONE);
        }else{
            Picasso.with(this)
                    .load(image)
                    .into(imageViewDetail);
        }

    }

    public void setText(String text, TextView textView) {
        if (text == null || text.isEmpty()) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(text);
    }

}