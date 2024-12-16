package com.example.sabkasbfa;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sabkasbfa.adapter.ViewAllAdapter;
import com.example.sabkasbfa.ui.models.ViewAllModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {
    List<ViewAllModels> viewAllModelsList;
    ViewAllAdapter viewAllAdapter;
    RecyclerView recyclerView;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_all);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        viewAllModelsList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this,viewAllModelsList);
        recyclerView.setAdapter(viewAllAdapter);

        if ( type != null && type.equalsIgnoreCase("jpg")){
            firestore.collection("AllProducts").whereEqualTo("type","jpg").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModels viewAllModels = documentSnapshot.toObject(ViewAllModels.class);
                        viewAllModelsList.add(viewAllModels);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }
        if ( type != null && type.equalsIgnoreCase("ysl")){
            firestore.collection("AllProducts").whereEqualTo("type","ysl").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModels viewAllModels = documentSnapshot.toObject(ViewAllModels.class);
                        viewAllModelsList.add(viewAllModels);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }
        if ( type != null && type.equalsIgnoreCase("versace")){
            firestore.collection("AllProducts").whereEqualTo("type","versace").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModels viewAllModels = documentSnapshot.toObject(ViewAllModels.class);
                        viewAllModelsList.add(viewAllModels);
                        viewAllAdapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }
}