package com.fofilovnikolay.androidregister.Passwords;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fofilovnikolay.androidregister.R;

public class PasswordActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        floatingActionButton = findViewById(R.id.add);
        floatingActionButton.setOnClickListener(view ->
            startActivity(new Intent(this, EditorActivity.class))
        );
    }
}
