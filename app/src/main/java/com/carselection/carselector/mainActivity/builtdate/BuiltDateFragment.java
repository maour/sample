package com.carselection.carselector.mainActivity.builtdate;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BuiltDateFragment extends Fragment implements BuiltDateContract.View {

    private static final String TAG = "BuiltDateFragment";

    @BindView(R.id.builtdate_Title_tv)
    TextView searchTitle;

    @BindView(R.id.builtdate_mainList_rv)
    RecyclerView mainList;

    @BindView(R.id.builtdate_loading_pb)
    ProgressBar loadingIndicator;

    private String selectedManufacturer, selectedMainType;
    private ResponseModel manufacturers;

    private BuiltDateFragment.OnBuiltDateFragmentInteractionListener mListener;

    private BuiltDateContract.Presenter mPresenter;

    BuiltDateAdapter adapter;
    List<String> builtDateList;

    public BuiltDateFragment() {
        // Required empty public constructor
    }

    public static BuiltDateFragment newInstance() {
        return new BuiltDateFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        builtDateList = new ArrayList<>();

        Bundle selectedInformation = this.getArguments();
        if (selectedInformation != null) {
            selectedManufacturer = selectedInformation.getString("manufacturer");
            selectedMainType = selectedInformation.getString("maintype");
            manufacturers = selectedInformation.getParcelable("manufacturers");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.builtdate_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new BuiltDateAdapter(builtDateList, selectedItem -> mListener.showSummaryPage(selectedManufacturer, selectedMainType, selectedItem));
        mainList.setAdapter(adapter);

        mPresenter.updateTitle(selectedManufacturer, selectedMainType);
        mPresenter.loadBuiltDate(selectedManufacturer, selectedMainType, manufacturers);

    }

    @Override
    public void setPresenter(BuiltDateContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar(boolean visible) {
        loadingIndicator.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showBuiltDateList(List<String> builtDateList) {
        adapter.updateList(builtDateList);
    }

    @Override
    public void showUpdatedTitle(String title) {
        searchTitle.setText(title);
    }

    public interface OnBuiltDateFragmentInteractionListener {
        void showSummaryPage(String manufacturer, String model, String year);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BuiltDateFragment.OnBuiltDateFragmentInteractionListener) {
            mListener = (BuiltDateFragment.OnBuiltDateFragmentInteractionListener) context;
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


