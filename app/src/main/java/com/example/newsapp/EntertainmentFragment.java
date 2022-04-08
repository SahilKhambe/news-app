package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntertainmentFragment extends Fragment {

    String apiKey = "1170889f3a16417d8e92bb03417dbd90";

    ArrayList<Model> modelArrayList;
    Adapter adapter;
    String country = "in";
    private RecyclerView recyclerViewOfEntertainment;
    private String category = "entertainment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.entertainmentfragment, null);

        recyclerViewOfEntertainment = view.findViewById(R.id.recyclerViewOfEntertainment);
        modelArrayList = new ArrayList<>();
        recyclerViewOfEntertainment.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), modelArrayList);
        recyclerViewOfEntertainment.setAdapter(adapter);

        findNews();

        return view;

    }

    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country, category, 100, apiKey).enqueue(new Callback<Array>() {
            @Override
            public void onResponse(Call<Array> call, Response<Array> response) {

                if (response.isSuccessful()) {

                    modelArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<Array> call, Throwable t) {

            }
        });
    }
}