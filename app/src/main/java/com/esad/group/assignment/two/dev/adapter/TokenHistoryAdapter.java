package com.esad.group.assignment.two.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.PassengerLog;
import com.esad.group.assignment.two.dev.modal.PreviousTokens;

import java.util.List;

public class TokenHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public TokenHistoryAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView date;
        private TextView amount;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.item_id);
            date = (TextView) itemView.findViewById(R.id.date);
            amount = (TextView) itemView.findViewById(R.id.credit_Amount);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:
            default:
                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.previous_token_items, viewGroup, false);
                return new TokenHistoryAdapter.ItemViewHolder((layoutView));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);
        switch (viewType) {
            case TYPE:
            default:
                TokenHistoryAdapter.ItemViewHolder itemViewHolder = (TokenHistoryAdapter.ItemViewHolder) viewHolder;
                PreviousTokens previousTokens = (PreviousTokens) listRecyclerItem.get(i);

                itemViewHolder.id.setText(previousTokens.getId());
                itemViewHolder.date.setText(previousTokens.getDate());
                itemViewHolder.amount.setText(previousTokens.getAmount());
        }
    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}