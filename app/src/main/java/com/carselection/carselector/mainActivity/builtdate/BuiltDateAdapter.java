package com.carselection.carselector.mainActivity.builtdate;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carselection.carselector.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuiltDateAdapter extends RecyclerView.Adapter<BuiltDateAdapter.ViewHolder> {

    private List<String> model;
    private BuiltDateAdapter.BuiltDateItemListener mListener;


    public interface BuiltDateItemListener {
        void itemClicked(String selectedItem);
    }

    BuiltDateAdapter(List<String> builtDateList, BuiltDateAdapter.BuiltDateItemListener listener) {
        model = builtDateList;
        mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.row_info_tv)
        TextView info;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        @OnClick(R.id.row_info_tv)
        public void onClick(View view) {
            mListener.itemClicked(model.get(getAdapterPosition()));
        }
    }

    @Override
    public BuiltDateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BuiltDateAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carinfo_row, parent, false));
    }

    @Override
    public void onBindViewHolder(BuiltDateAdapter.ViewHolder holder, int position) {
        holder.info.setText(model.get(position));
    }

    @Override
    public int getItemCount() {
        return model != null ? model.size() : 0;
    }

    void updateList(List<String> updatedModel) {
        model.clear();
        model.addAll(updatedModel);
        notifyDataSetChanged();
    }
}
