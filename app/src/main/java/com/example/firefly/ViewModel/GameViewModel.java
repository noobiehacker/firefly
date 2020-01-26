package com.example.firefly.ViewModel;

import android.content.res.Resources;

import com.example.firefly.Model.GameResponse;
import com.example.firefly.R;
import com.example.firefly.Model.SportsDbApiService;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;

public class GameViewModel {

    private String baseUrl = Resources.getSystem().getString(R.string.sportsDbBaseUrl);

    public Single<GameResponse> getGameResonse(){
        return getApiService().getFootballData();
    }

    private SportsDbApiService getApiService() {
        //Build APIService Object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(SportsDbApiService.class);
    }

}
