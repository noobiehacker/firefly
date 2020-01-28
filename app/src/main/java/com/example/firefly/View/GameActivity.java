package com.example.firefly.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.firefly.Model.GameResponse;
import com.example.firefly.Model.Gms;
import com.example.firefly.ViewModel.GameViewModel;
import com.example.firefly.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.akarnokd.rxjava3.android.AndroidInteropSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GameActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;
    private GameAdapter gameAdapter = new GameAdapter();
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
                Snackbar.make(view, "Refreshing football data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                refreshFootballData();
        });
        ButterKnife.bind(this);
        recyclerView.setAdapter(gameAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gameViewModel = new GameViewModel(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshFootballData(){
        //1)Get Newest Live Football Game Data
        gameViewModel.getGameResonse()
                .observeOn(AndroidInteropSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<GameResponse>(){

            @Override
            public void onSuccess(@NonNull GameResponse gameResponse) {
                //Update UI
                Gms gms = gameResponse.gms;
                gameAdapter.setGms(gms);
                gameAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                String errorMsg = "Failed To Get Data : " + e.getMessage();
                Toast.makeText(getBaseContext(), errorMsg, Toast.LENGTH_LONG).show();
            }
        });
    }

}
