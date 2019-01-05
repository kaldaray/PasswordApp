package com.fofilovnikolay.androidregister.Passwords.activity.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fofilovnikolay.androidregister.Passwords.activity.editor.EditorActivity;
import com.fofilovnikolay.androidregister.Passwords.model.Note;
import com.fofilovnikolay.androidregister.R;

import java.util.List;

public class PasswordActivity extends AppCompatActivity implements PasswordInterface{

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    PasswordPresenter presenter;
    PasswordAdapter adapter;
    PasswordAdapter.ItemClickListener itemClickListener;
    List<Note> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.add);
        floatingActionButton.setOnClickListener(view ->
            startActivity(new Intent(this, EditorActivity.class))
        );

        presenter = new PasswordPresenter(this);
        presenter.getDate();

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getDate()
        );

        itemClickListener = ((view, position) -> {
           String tittle = note.get(position).getTitle();
            Toast.makeText(this, tittle , Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Note> notes) {
        adapter = new PasswordAdapter(this, notes, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        note = notes;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
