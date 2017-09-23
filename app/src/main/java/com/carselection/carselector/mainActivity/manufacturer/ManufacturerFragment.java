package com.carselection.carselector.mainActivity.manufacturer;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carselection.carselector.R;
import com.carselection.carselector.data.model.ResponseModel;
import com.paginate.Paginate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManufacturerFragment extends Fragment implements ManufacturerContract.View, Paginate.Callbacks {

    private static final String TAG = "ManufacturerFragment";

    @BindView(R.id.manufacturer_Title_tv)
    TextView searchTitle;

    @BindView(R.id.manufacturer_mainList_rv)
    RecyclerView mainList;

    @BindView(R.id.manufacturer_loading_pb)
    ProgressBar loadingIndicator;

    ManufacturerAdapter adapter;

    private ManufacturerFragment.OnManufacturerFragmentInteractionListener mListener;

    private ManufacturerContract.Presenter mPresenter;
    private boolean listIsLoading = false;
    private boolean listHasLoadedAll = false;

    public ManufacturerFragment() {
        // Required empty public constructor
    }

    public static ManufacturerFragment newInstance() {
        return new ManufacturerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.manufacturer_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mainList.setHasFixedSize(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(ManufacturerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar(boolean visible) {
        loadingIndicator.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        listIsLoading = isLoading;
    }

    @Override
    public void setHasLoadedAll(boolean hasLoaded) {
        listHasLoadedAll = hasLoaded;
    }

    @Override
    public void showManufacturerList(List<String> manufacturerList, final ResponseModel manufacturers) {

        searchTitle.setText(R.string.ManufaturePageTitle);

        adapter = new ManufacturerAdapter(manufacturerList, selectedItem -> mPresenter.manufacturerClicked(selectedItem));
        mainList.setAdapter(adapter);

        Paginate.with(mainList, this)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .build();
    }

    @Override
    public void showUpdatedManufacturerList(List<String> newList) {
        adapter.addAll(newList);
    }

    @Override
    public void showMainTypePage(String manufacturer, ResponseModel manufacturers) {
        mListener.showMainTypePage(manufacturer, manufacturers);
    }

//==========Paginate Callback==============

    @Override
    public void onLoadMore() {
        mPresenter.loadManufacturers();
    }

    @Override
    public boolean isLoading() {
        return listIsLoading;
    }

    @Override
    public boolean hasLoadedAllItems() {
        return listHasLoadedAll;
    }

//=======================================

    public interface OnManufacturerFragmentInteractionListener {
        void showMainTypePage(String manufacturer, ResponseModel manufacturers);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ManufacturerFragment.OnManufacturerFragmentInteractionListener) {
            mListener = (ManufacturerFragment.OnManufacturerFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
