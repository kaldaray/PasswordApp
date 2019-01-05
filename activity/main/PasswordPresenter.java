package com.fofilovnikolay.androidregister.Passwords.activity.main;

import android.support.annotation.NonNull;

import com.fofilovnikolay.androidregister.Passwords.api.ApiClient;
import com.fofilovnikolay.androidregister.Passwords.api.ApiInterface;
import com.fofilovnikolay.androidregister.Passwords.model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordPresenter {

    private PasswordInterface view;

    public PasswordPresenter(PasswordInterface view) {
        this.view = view;
    }

    void getDate(){
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getNotes();
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(@NonNull Call<List<Note>> call,@NonNull Response<List<Note>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Note>> call,@NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
