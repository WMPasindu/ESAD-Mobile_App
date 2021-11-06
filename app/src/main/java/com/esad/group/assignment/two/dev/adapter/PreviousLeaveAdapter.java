package com.esad.group.assignment.two.dev.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esad.group.assignment.two.dev.R;
import com.esad.group.assignment.two.dev.modal.LeaveHistory;
import com.esad.group.assignment.two.dev.modal.PassengerLog;

import java.util.List;

public class PreviousLeaveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;

    public PreviousLeaveAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView type;
        private TextView fromDate;
        private TextView toDate;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.type);
            fromDate = (TextView) itemView.findViewById(R.id.from_date);
            toDate = (TextView) itemView.findViewById(R.id.to_date);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:
            default:
                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.leave_history_items, viewGroup, false);
                return new PreviousLeaveAdapter.ItemViewHolder((layoutView));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);
        switch (viewType) {
            case TYPE:
            default:
                PreviousLeaveAdapter.ItemViewHolder itemViewHolder = (PreviousLeaveAdapter.ItemViewHolder) viewHolder;
                LeaveHistory leaveHistory = (LeaveHistory) listRecyclerItem.get(i);

                itemViewHolder.type.setText(leaveHistory.getType());
                itemViewHolder.fromDate.setText(leaveHistory.getFromDate());
                itemViewHolder.toDate.setText(leaveHistory.getToDate());
        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}