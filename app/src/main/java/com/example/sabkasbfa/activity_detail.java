package com.example.sabkasbfa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.sabkasbfa.ui.models.ViewAllModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class activity_detail extends AppCompatActivity {
    ImageView img_detail,image_minus,image_plus;
    TextView price;
    Button button_addtocart;
    ViewAllModels viewAllModels = null;
    TextView quantity;
    int totalquantity = 1;
    int totalprice = 0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

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

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

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
        button_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_addedtocart();
            }
        });

        if(viewAllModels != null){
            Glide.with(getApplicationContext()).load(viewAllModels.getImg_url()).into(img_detail);
            price.setText(String.format("Price:Rp%d", viewAllModels.getPrice()));
            //price.setText("Price: Rp. "+viewAllModels.getPrice());
            totalprice = viewAllModels.getPrice() * totalquantity;
        }
        image_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity < 10){
                    totalquantity++;
                    quantity.setText(String.valueOf(totalquantity));
                    totalprice = viewAllModels.getPrice() * totalquantity;
                }
            }
        });
        image_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalquantity >0 ){
                    totalquantity--;
                    quantity.setText(String.valueOf(totalquantity));
                    totalprice = viewAllModels.getPrice() * totalquantity;
                }
            }
        });
    }

    private void button_addedtocart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd MM, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put("productName", viewAllModels.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("productDate", saveCurrentDate);
        cartMap.put("productTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalprice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddtoCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(activity_detail.this, "Added To A Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

    }
}