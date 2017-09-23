package com.carselection.carselector.mainActivity.maintype;


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

public class MainTypeAdapter extends RecyclerView.Adapter<MainTypeAdapter.ViewHolder> {

    private List<String> model;
    private ModelItemListener mListener;

    public interface ModelItemListener {
        void itemClicked(String selectedItem);
    }

    MainTypeAdapter(List<String> modelList, ModelItemListener listener) {
        model = modelList;
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
    public MainTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainTypeAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carinfo_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MainTypeAdapter.ViewHolder holder, int position) {
        holder.info.setText(model.get(position));
    }

    @Override
    public int getItemCount() {
        return model != null ? model.size() : 0;
    }

    void updateModel(List<String> updatedModel) {
        model.clear();
        model.addAll(updatedModel);
        notifyDataSetChanged();
    }
}

