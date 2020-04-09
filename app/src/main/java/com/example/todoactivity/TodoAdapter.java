package com.example.todoactivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
    private Context mContext;

    private ArrayList<String> namesList;
    private ActionListener mActionListener;

    public TodoAdapter(Context mContext, ArrayList<String> namesList) {
        this.mContext = mContext;
        this.namesList = namesList;
        mActionListener = (ActionListener) mContext;
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_recycler_item, parent, false);
        TodoViewHolder todoViewHolder = new TodoViewHolder(view);
        return todoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TodoViewHolder holder, final int position) {
        holder.mListName.setText(namesList.get(position));

        final int orientation = mContext.getResources().getConfiguration().orientation;


        holder.mListName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = namesList.get(position);

                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("nameKey", name);
                    mContext.startActivity(intent);
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    mActionListener.respond(name);

                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }

}
