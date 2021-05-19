package com.example.notes_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Note> notes;
    Context context;
    Activity activity;

    Adapter(Context context, Activity activity, List<Note> notes)
    {
        this.inflater = LayoutInflater.from(context);
        this.activity = activity;
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String title = notes.get(position ).getTitle();
        holder.note_title.setText(title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,my_note.class);
                intent.putExtra("iid",notes.get(position).getID());
                intent.putExtra("title",notes.get(position).getTitle());
                intent.putExtra("content",notes.get(position).getContent());
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView note_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note_title = itemView.findViewById(R.id.noteTitle);
        }
    }
}
