package com.fofilovnikolay.androidregister.Passwords.activity.editor;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.fofilovnikolay.androidregister.Passwords.api.ApiClient;
import com.fofilovnikolay.androidregister.Passwords.api.ApiInterface;
import com.fofilovnikolay.androidregister.Passwords.model.Note;
import com.fofilovnikolay.androidregister.R;
import com.thebluealliance.spectrum.SpectrumPalette;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity implements EditorView{

    EditText etName, etPassword, etEmail, etInfo;
    ProgressDialog progressDialog;
    SpectrumPalette palette;

    EditorPresenter presenter;

    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        etName = findViewById(R.id.title);
        etPassword = findViewById(R.id.passwordPass);
        etEmail = findViewById(R.id.emailPass);
        etInfo = findViewById(R.id.infoPassword);
        palette = findViewById(R.id.palette);

        palette.setOnColorSelectedListener(
                clr -> color = clr
        );
        //Default Color Setup
        palette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        presenter = new EditorPresenter(this);

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
                int color = this.color;
                if (title.isEmpty())
                    etName.setError("Please enter Name");
                else if (email.isEmpty())
                    etEmail.setError("Please enter Email");
                else if (password.isEmpty())
                    etPassword.setError("Please enter Password");
                else
                    presenter.saveNote(title, email, password, info, color);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(EditorActivity.this, message, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(EditorActivity.this, "ok", Toast.LENGTH_SHORT).show();
    }
}
