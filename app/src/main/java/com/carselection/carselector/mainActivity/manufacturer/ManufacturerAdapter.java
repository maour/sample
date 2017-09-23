package com.carselection.carselector.mainActivity.manufacturer;

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


public class ManufacturerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ODD = 1, EVEN = 2;

    private List<String> model;
    private BuiltDateItemListener mListener;

    public interface BuiltDateItemListener {
        void itemClicked(String selectedItem);
    }

    ManufacturerAdapter(List<String> manufacturerList, BuiltDateItemListener listener) {
        model = manufacturerList;
        mListener = listener;
    }

    public void addAll(List<String> newItems) {
        model.addAll(newItems);
        notifyDataSetChanged();
    }

    public class ViewHolderEven extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.row_info_tv)
        TextView info;

        ViewHolderEven(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        @OnClick(R.id.row_info_tv)
        public void onClick(View view) {
            mListener.itemClicked(model.get(getAdapterPosition()));
        }
    }

    public class ViewHolderOdd extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.row_info_tv)
        TextView info;

        ViewHolderOdd(View itemView) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 1:
                View oddView = inflater.inflate(R.layout.carinfo_odd_row, parent, false);
                viewHolder = new ViewHolderOdd(oddView);
                break;
            case 2:
                View evenView = inflater.inflate(R.layout.carinfo_row, parent, false);
                viewHolder = new ViewHolderEven(evenView);
                break;
            default:
                View elseView = inflater.inflate(R.layout.carinfo_row, parent, false);
                viewHolder = new ViewHolderEven(elseView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case EVEN:
                ViewHolderEven eHolder = (ViewHolderEven) holder;
                eHolder.info.setText(model.get(position));
                break;
            case ODD:
                ViewHolderOdd oHolder = (ViewHolderOdd) holder;
                oHolder.info.setText(model.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? EVEN : ODD;

    }
}
