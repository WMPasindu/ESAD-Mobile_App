package com.esad.group.assignment.two.dev.interfaces;

import com.esad.group.assignment.two.dev.modal.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<Results>> getsuperHeroes();
}
