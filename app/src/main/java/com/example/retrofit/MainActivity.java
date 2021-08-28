package com.example.retrofit;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.Adapter.CustomAdapter;
import com.example.retrofit.Model.Data;
import com.example.retrofit.retrofit.ApiClient;
import com.example.retrofit.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<Data> DataList;
    RecyclerView recyclerView;
    CustomAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new CustomAdapter(DataList,getApplicationContext());
        recyclerView.setAdapter(recyclerAdapter);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Data>> call = apiService.getData();
    try {


        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                DataList = response.body();
                Log.d("TAG","Response = "+ DataList);
                recyclerAdapter.SetdataList(DataList);
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {

                Log.d("TAG","Response = "+t.toString());
            }
        });
    }catch (Exception e){
        Toast.makeText(getApplicationContext(),"exception "+e.getMessage(),Toast.LENGTH_SHORT).show();
    }
    }
}
