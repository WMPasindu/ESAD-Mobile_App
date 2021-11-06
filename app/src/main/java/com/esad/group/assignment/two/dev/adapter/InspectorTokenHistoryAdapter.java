package com.esad.group.assignment.two.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.TokenHistory;

import java.util.List;

public class InspectorTokenHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public InspectorTokenHistoryAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView date;
        private TextView busNumber;
        private TextView status;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.item_id);
            date = (TextView) itemView.findViewById(R.id.date);
            busNumber = (TextView) itemView.findViewById(R.id.bus_number);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:
            default:
                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.ticket_history_items, viewGroup, false);
                return new InspectorTokenHistoryAdapter.ItemViewHolder((layoutView));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);
        switch (viewType) {
            case TYPE:
            default:
                InspectorTokenHistoryAdapter.ItemViewHolder itemViewHolder = (InspectorTokenHistoryAdapter.ItemViewHolder) viewHolder;
                TokenHistory tokenHistory = (TokenHistory) listRecyclerItem.get(i);

                itemViewHolder.id.setText(""+tokenHistory.getId());
                itemViewHolder.date.setText(tokenHistory.getDate());
                itemViewHolder.busNumber.setText(tokenHistory.getBusNumber());
                itemViewHolder.status.setText(tokenHistory.getStatus());

                //adding data to the view ---
                if(tokenHistory.getStatus().equalsIgnoreCase("Approved")) {
                    itemViewHolder.status.setBackgroundResource(R.color.red);
                } else if(tokenHistory.getStatus().equalsIgnoreCase("Canceled")) {
                    itemViewHolder.status.setBackgroundResource(R.color.orange);
                }
        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}