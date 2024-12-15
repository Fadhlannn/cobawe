package com.example.sabkasbfa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.sabkasbfa.ui.models.ViewAllModels;

public class activity_detail extends AppCompatActivity {
    ImageView img_detail,image_minus,image_plus;
    TextView price;
    Button button_addtocart;
    ViewAllModels viewAllModels = null;
    TextView quantity;
    int totalquantity = 1;
    int totalprice = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModels){
            viewAllModels = (ViewAllModels) object;
        }
        quantity = findViewById(R.id.quantity);
        img_detail = findViewById(R.id.img_detail);
        image_plus = findViewById(R.id.image_plus);
        image_minus = findViewById(R.id.image_minus);
        price = findViewById(R.id.price_detail);
        button_addtocart = findViewById(R.id.button_addtocart);

        if(viewAllModels != null){
            Glide.with(getApplicationContext()).load(viewAllModels.getImg_url()).into(img_detail);
            price.setText("Price: "+viewAllModels.getPrice());
        }
        image_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity < 10){
                    totalquantity++;
                    quantity.setText(String.valueOf(totalquantity));
                }
            }
        });
        image_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity >0 ){
                    totalquantity--;
                    quantity.setText(String.valueOf(totalquantity));
                }
            }
        });
    }
}