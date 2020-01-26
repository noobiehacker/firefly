package com.example.firefly.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.firefly.Model.GameResponse;
import com.example.firefly.Model.GameScore;
import com.example.firefly.ViewModel.GameViewModel;
import com.example.firefly.R;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class GameActivity extends AppCompatActivity {

    private GameViewModel gameViewModel = new GameViewModel();
    private GameAdapter gameAdapter = new GameAdapter();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refreshing football data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                refreshFootballData();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(gameAdapter);
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
        Single<GameResponse> response = gameViewModel.getGameResonse();
        response.subscribe(new SingleObserver<GameResponse>(){

            @Override
            public void onSuccess(@NonNull GameResponse gameResponse) {
                //Update UI
                List<GameScore> gameScoreList = gameResponse.scores;
                gameAdapter.setGameScoreList(gameScoreList);
                gameAdapter.notifyDataSetChanged();
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                //Propagate Error to user
                String errorMsg = e.getMessage();
                Toast.makeText(getBaseContext(), errorMsg, Toast.LENGTH_LONG);
            }
        });
    }
}
