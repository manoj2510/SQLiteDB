package com.example.todoactivity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoViewHolder extends RecyclerView.ViewHolder
{
    TextView mListName;
    LinearLayout mParent;

    public TodoViewHolder(@NonNull View itemView) {
        super(itemView);
        mListName = itemView.findViewById(R.id.tv_recycler_item);
        mParent = itemView.findViewById(R.id.parent_layout);
    }
}
