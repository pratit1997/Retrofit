package com.example.retrofit.retrofit;
import com.example.retrofit.Model.Data;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {

    @GET("photos")
    Call<List<Data>> getData();


}
