package com.example.sabkasbfa.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sabkasbfa.R;
import com.example.sabkasbfa.adapter.HomeAdapter;
import com.example.sabkasbfa.adapter.PopularAdapter;
import com.example.sabkasbfa.databinding.FragmentHomeBinding;
import com.example.sabkasbfa.ui.models.HomeCategory;
import com.example.sabkasbfa.ui.models.PopularModels;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView popularRecycle,exploreCatRecycle;
    FirebaseFirestore db;
    List<PopularModels> popularModelsList;
    PopularAdapter popularAdapter;

    List<HomeCategory> homeCategoryList;
    HomeAdapter homeAdapter;

    ScrollView scrollView;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home,container,false);
        db = FirebaseFirestore.getInstance();
        popularRecycle = root.findViewById(R.id.pop_rec);
        exploreCatRecycle = root.findViewById(R.id.explor_rec);
        scrollView = root.findViewById(R.id.scrollidhome);
        progressBar = root.findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        popularRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelsList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getActivity(),popularModelsList);
        popularRecycle.setAdapter(popularAdapter);

        db.collection("PopularProducts").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){

                                PopularModels popularModels = document.toObject(PopularModels.class);
                                popularModelsList.add(popularModels);
                                popularAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        }else {

                            Toast.makeText(getActivity(),"Error:"+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        exploreCatRecycle.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeCategoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(homeCategoryList, getActivity());
        exploreCatRecycle.setAdapter(homeAdapter);

        db.collection("HomeCategory").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){

                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                homeCategoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        }else {

                            Toast.makeText(getActivity(),"Error:"+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        return root;
    }

}