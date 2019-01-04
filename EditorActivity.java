package com.fofilovnikolay.androidregister.Passwords;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fofilovnikolay.androidregister.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity {

    EditText etName, etPassword, etEmail, etInfo;
    ProgressDialog progressDialog;

    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        etName = findViewById(R.id.title);
        etPassword = findViewById(R.id.passwordPass);
        etEmail = findViewById(R.id.emailPass);
        etInfo = findViewById(R.id.infoPassword);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                //save
                String title = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String info = etInfo.getText().toString().trim();
                int color = -2184710;
                if (title.isEmpty())
                    etName.setError("Please enter Name");
                else if (email.isEmpty())
                    etEmail.setError("Please enter Email");
                else if (password.isEmpty())
                    etPassword.setError("Please enter Password");
                else
                    saveNote(title, email, password, info, color);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote(final String title, final String email, final String password, final String info, final int color) {

        progressDialog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.saveNote(title, email, password, info, color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {
                progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if(success){
                        Toast.makeText(EditorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        Toast.makeText(EditorActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditorActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
