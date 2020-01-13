package com.nonsense.retro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitApi {

    @GET("/users")
    Call<List<GitModel>> reposForuser(@Query("since") Integer since);
}
