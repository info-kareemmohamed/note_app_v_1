package com.example.noteapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.Entity.Note;
import com.example.noteapp.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private List<Note> list = new ArrayList<>();
    private Context context;
    private Recyclerview_Listener listener;

    public Adapter(Context context, Recyclerview_Listener listener) {
        this.context = context;
        this.listener = listener;
    }


    public Adapter(List<Note> list, Context context, Recyclerview_Listener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    public List<Note> getList() {
        return list;
    }

    public void setList(List<Note> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.recyclerview_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.Set_Data_in_Holder(list.get(position));
        holder.CardView_Recyclerview_Listener();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView titel, description, date;
        private ImageView pin;
        private CardView cardView;
        private Note note;

        public Holder(@NonNull View itemView) {
            super(itemView);
            titel = itemView.findViewById(R.id.Recycler_List_titel);
            description = itemView.findViewById(R.id.Recycler_List_textnote);
            date = itemView.findViewById(R.id.Recycler_List_date);
            pin = itemView.findViewById(R.id.Recycler_List_img_pin);
            cardView = itemView.findViewById(R.id.Recycler_List_Cardview);
        }

        //This function is used in onBindViewHolder
        private void CardView_Recyclerview_Listener() {
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(note);
                }
            });
            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.OnLong_Click(note, cardView);

                    return true;
                }
            });
        }

        //add data Holder This function is used in onBindViewHolder
        private void Set_Data_in_Holder(Note note1) {
            note = note1;
            titel.setText(note.getTitle());
            description.setText(note.getDescription());
            date.setText(note.getDate());
            Set_Pin(note.isPin());


        }

        // his function is used in Set_Data_in_Holder
        private void Set_Pin(boolean pin) {
            int imgepin = pin ? R.drawable.icon_pin : 0;
            this.pin.setImageResource(imgepin);
        }


    }
}
