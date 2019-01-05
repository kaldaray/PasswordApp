package com.fofilovnikolay.androidregister.Passwords.activity.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fofilovnikolay.androidregister.Passwords.model.Note;
import com.fofilovnikolay.androidregister.R;

import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Note> notes;
    private ItemClickListener itemClickListener;

    public PasswordAdapter(Context context, List<Note> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note,
                parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Note note = notes.get(position);
        holder.tvTittle.setText(note.getTitle());
        holder.tvInfo.setText(note.getInfo());
        holder.tvDate.setText(note.getDate());
        holder.cardItem.setCardBackgroundColor(note.getColor());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTittle, tvInfo, tvDate;
        CardView cardItem;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener = itemClickListener;

            tvTittle = itemView.findViewById(R.id.title);
            tvInfo = itemView.findViewById(R.id.smallInfo);
            tvDate = itemView.findViewById(R.id.date);
            cardItem = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            cardItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
