package com.example.sabkasbfa.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sabkasbfa.R;
import com.example.sabkasbfa.adapter.NavCategoryAdapter;
import com.example.sabkasbfa.adapter.PopularAdapter;
import com.example.sabkasbfa.ui.models.NavCategoryModels;
import com.example.sabkasbfa.ui.models.PopularModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    RecyclerView recyclerView;
    List<NavCategoryModels> categoryModelsList;
    NavCategoryAdapter navCategoryAdapter;
    FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = root.findViewById(R.id.rec_cat);
        db = FirebaseFirestore.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryModelsList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(),categoryModelsList);
        recyclerView.setAdapter(navCategoryAdapter);

        db.collection("NavCategory").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){

                                NavCategoryModels navCategoryModels = document.toObject(NavCategoryModels.class);
                                categoryModelsList.add(navCategoryModels);
                                navCategoryAdapter.notifyDataSetChanged();
                            }
                        }else {

                            Toast.makeText(getActivity(),"Error:"+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return root;
    }

}