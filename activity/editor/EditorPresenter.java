package com.fofilovnikolay.androidregister.Passwords.activity.editor;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.fofilovnikolay.androidregister.Passwords.api.ApiClient;
import com.fofilovnikolay.androidregister.Passwords.api.ApiInterface;
import com.fofilovnikolay.androidregister.Passwords.model.Note;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    public void saveNote(final String title, final String email, final String password, final String info, final int color) {

        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title, email, password, info, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
              view.hideProgress();

                if (response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if(success){
                        view.onAddSuccess(response.body().getMessage());
                        /**/
                    }
                    else{
                        view.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, Throwable t) {
                view.hideProgress();
                view.onAddError("ok");
            }
        });
    }
}
