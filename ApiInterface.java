package com.fofilovnikolay.androidregister.Passwords;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("save.php")
    Call<Note> saveNote(
      @Field("title") String title,
      @Field("email") String email,
      @Field("pass") String password,
      @Field("info") String info,
      @Field("color") int color
    );

}
