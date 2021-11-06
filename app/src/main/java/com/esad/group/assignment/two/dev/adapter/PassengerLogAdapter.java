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

import java.util.List;

public class PassengerLogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public PassengerLogAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView date;
        private TextView departure;
        private TextView arrival;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_id);
            date = (TextView) itemView.findViewById(R.id.date);
            departure = (TextView) itemView.findViewById(R.id.departure);
            arrival = (TextView) itemView.findViewById(R.id.arrival);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:
            default:
                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.passenger_log_item, viewGroup, false);
                return new ItemViewHolder((layoutView));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);
        switch (viewType) {
            case TYPE:
            default:
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                PassengerLog passengerLog = (PassengerLog) listRecyclerItem.get(i);

                itemViewHolder.name.setText(passengerLog.getId());
                itemViewHolder.date.setText(passengerLog.getDate());
                itemViewHolder.departure.setText(passengerLog.getDeparture());
                itemViewHolder.arrival.setText(passengerLog.getArrival());
        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}