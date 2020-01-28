package com.example.firefly.Model;

import retrofit2.http.GET;
import io.reactivex.rxjava3.core.Single;

public interface SportsDbApiService {

    //Get data for g & gms
    @GET("latestamericanfootball.php")
    Single<GameResponse> getFootballData();

}
