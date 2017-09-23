package com.carselection.carselector.mainActivity.summary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carselection.carselector.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SummaryFragment extends Fragment implements SummaryContract.View {

    private final static String TAG = "SummaryFragment";

    @BindView(R.id.summary_manufacturer_tv)
    TextView manufacturer_tv;

    @BindView(R.id.summary_maintype_tv)
    TextView maintype_tv;

    @BindView(R.id.summary_builtdate_tv)
    TextView builtdate_tv;

    private String selectedManufacturer, selectedMainType, selectedBuiltDate;

    SummaryContract.Presenter mPresenter;

    public SummaryFragment() {
        // Required empty public constructor
    }

    public static SummaryFragment newInstance() {
        return new SummaryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle selectedInformation = this.getArguments();
        if (selectedInformation != null) {
            loadArguments(selectedInformation);
        }
    }

    private void loadArguments(Bundle selectedInformation) {
        selectedManufacturer = selectedInformation.getString("manufacturer");
        selectedMainType = selectedInformation.getString("maintype");
        selectedBuiltDate = selectedInformation.getString("builtdate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter.loadSummary(selectedManufacturer, selectedMainType, selectedBuiltDate);
    }

    @Override
    public void setPresenter(SummaryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar(boolean visible) {

    }

    @Override
    public void showSummaryInformation(String manufacturer, String maintype, String builtdate) {
        manufacturer_tv.setText(manufacturer);
        maintype_tv.setText(maintype);
        builtdate_tv.setText(builtdate);
    }
}
