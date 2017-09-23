package com.carselection.carselector.mainActivity.maintype;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carselection.carselector.R;
import com.carselection.carselector.data.model.ResponseModel;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainTypeFragment extends Fragment implements MainTypeContract.View {

    private static final String TAG = "MainTypeFragment";

    @BindView(R.id.maintype_Title_tv)
    TextView searchTitle;

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.maintype_mainList_rv)
    RecyclerView mainList;

    @BindView(R.id.maintype_loading_pb)
    ProgressBar loadingIndicator;

    MainTypeAdapter adapter;

    private String selectedManufacturer;
    private ResponseModel manufacturers;

    private MainTypeFragment.OnMainTypeFragmentInteractionListener mListener;

    private MainTypeContract.Presenter mPresenter;
    private List<String> manufacturersList;

    public MainTypeFragment() {
        // Required empty public constructor
    }

    public static MainTypeFragment newInstance() {
        return new MainTypeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle selectedInformation = this.getArguments();
        if (selectedInformation != null) {
            selectedManufacturer = selectedInformation.getString("manufacturer");
            manufacturers = selectedInformation.getParcelable("manufacturers");
        }

        manufacturersList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.maintype_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        mPresenter.updateTitle(selectedManufacturer);
        mPresenter.loadMainType(selectedManufacturer, manufacturers);

        adapter = new MainTypeAdapter(manufacturersList, selectedItem -> mPresenter.mainTypeClicked(selectedManufacturer, selectedItem, manufacturers));
        mainList.setAdapter(adapter);

        RxSearchView.queryTextChanges(searchView)
                .subscribe(searchedItem -> mPresenter.filter(searchedItem));
    }

    @Override
    public void onResume() {
        super.onResume();
        searchView.setQuery("", false);
        searchView.setIconified(true);
    }

    @Override
    public void setPresenter(MainTypeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar(boolean visible) {
        loadingIndicator.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMainTypeList(List<String> mainTypeList) {
        adapter.updateModel(mainTypeList);
    }

    @Override
    public void showBuiltDate(String manufacturer, String mainType, ResponseModel manufacturers) {
        mListener.showBuildDatePage(manufacturer, mainType, manufacturers);
    }

    @Override
    public void showUpdatedTitle(String title) {
        searchTitle.setText(title);
    }

    public interface OnMainTypeFragmentInteractionListener {
        void showBuildDatePage(String manufacturer, String mainType, ResponseModel manufacturers);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTypeFragment.OnMainTypeFragmentInteractionListener) {
            mListener = (MainTypeFragment.OnMainTypeFragmentInteractionListener) context;
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

