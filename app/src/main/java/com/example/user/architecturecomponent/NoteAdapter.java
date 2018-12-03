package com.example.user.architecturecomponent;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25/11/2018.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    OnItemCLickListener listener;

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);


        return new NoteHolder(itemView);
    }

    //take care of getting the data
    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {

        Note currentNote = notes.get(position);
        holder.textView_title.setText(currentNote.getTitle());
        holder.textView_desc.setText(currentNote.getDescription());
        holder.textView_priority.setText(String.valueOf(currentNote.getPriority()));


    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    //for getting the data from the LiveData
    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();

    }

    public Note getNoteAt(int position){
        return notes.get(position);
    }



    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textView_title;
        private TextView textView_desc;
        private TextView textView_priority;

        NoteHolder(View itemId){
            super(itemId);

            textView_title  = (TextView) itemId.findViewById(R.id.text_view_title);
            textView_desc = (TextView) itemId.findViewById(R.id.text_view_desc);
            textView_priority = (TextView) itemId.findViewById(R.id.text_view_priority);

            itemId.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(notes.get(position));
                }
            });
        }
    }

    public interface OnItemCLickListener{
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemCLickListener listener){
        this.listener = listener;

    }
}
