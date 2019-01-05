package com.fofilovnikolay.androidregister.Passwords.activity.main;

import com.fofilovnikolay.androidregister.Passwords.model.Note;

import java.util.List;

public interface PasswordInterface {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);
}
