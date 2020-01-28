package com.example.firefly.ViewModel;

import android.content.Context;

import androidx.room.Room;

import com.example.firefly.Model.FireflyDatabase;
import com.example.firefly.Model.User;
import com.example.firefly.View.LoginActivity;

import hu.akarnokd.rxjava3.android.AndroidInteropSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel {

    private Context context;

    public LoginViewModel(Context context) {
        this.context = context;
        insertFirstUser();
    }

    public void login(String username, String password, LoginActivity activity) {
        FireflyDatabase fireflyDatabase = Room.databaseBuilder(context, FireflyDatabase.class, "db").build();
        Single.just(fireflyDatabase)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidInteropSchedulers.mainThread())
                .subscribe(new SingleObserver<FireflyDatabase>() {
                    @Override
                    public void onSubscribe(io.reactivex.rxjava3.disposables.@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull FireflyDatabase fireflyDatabase) {
                        User user = fireflyDatabase.userDao().login(username, hashPassword(password));
                        Boolean result = user != null;
                        Single.just(result)
                                .observeOn((AndroidInteropSchedulers.mainThread()))
                                .subscribeOn(Schedulers.io())
                                .subscribe(activity.handleLoginResult());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


    //This is a simplified example of hashing, in the real world, a more secure hashing can be done
    //eg: Add salt, use another sub-system(back-end) ... etc
    private String hashPassword(String password) {
        int oldHashed = password.hashCode();
        int newHashed = (oldHashed * 1234) % 4321;
        String hashedPassword = String.valueOf(newHashed);
        return hashedPassword;
    }

    private void insertFirstUser() {
        FireflyDatabase fireflyDatabase = Room.databaseBuilder(context, FireflyDatabase.class, "db").build();
        Single.just(fireflyDatabase)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidInteropSchedulers.mainThread())
                .subscribe(new SingleObserver<FireflyDatabase>() {
                    @Override
                    public void onSubscribe(io.reactivex.rxjava3.disposables.@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull FireflyDatabase fireflyDatabase) {
                        fireflyDatabase.clearAllTables();
                        User user = new User();
                        user.setUserName("test");
                        user.setHasedPassword(hashPassword("test"));
                        fireflyDatabase.userDao().addUser(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

}
