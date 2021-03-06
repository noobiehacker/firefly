package com.example.firefly.ViewModel;

import android.content.Context;

import com.example.firefly.Model.GameDataDeserializer;
import com.example.firefly.Model.GameResponse;
import com.example.firefly.Model.SportsDbApiService;
import com.example.firefly.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameViewModel {

    private Single<GameResponse> response;
    private Context context;

    public Single<GameResponse> getGameResonse(){
        SportsDbApiService service = getApiService();
        response = service.getFootballData();
        return response;
    }

    private SportsDbApiService getApiService() {
        //Build APIService Object
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(GameResponse.class, new GameDataDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(SportsDbApiService.class);
    }

    private String getBaseUrl(){
        return context.getResources().getString(R.string.sportsDbBaseUrl);
    }

    public GameViewModel(Context context) {
        this.context = context;
    }
}
