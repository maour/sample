package com.carselection.carselector.mainActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.carselection.carselector.CarApplication;
import com.carselection.carselector.R;
import com.carselection.carselector.data.model.ResponseModel;
import com.carselection.carselector.data.repository.CarSelectorRepository;
import com.carselection.carselector.mainActivity.builtdate.BuiltDateFragment;
import com.carselection.carselector.mainActivity.builtdate.BuiltDatePresenter;
import com.carselection.carselector.mainActivity.builtdate.BuiltDatePresenterModule;
import com.carselection.carselector.mainActivity.maintype.MainTypeFragment;
import com.carselection.carselector.mainActivity.maintype.MainTypePresenter;
import com.carselection.carselector.mainActivity.maintype.MainTypePresenterModule;
import com.carselection.carselector.mainActivity.manufacturer.ManufacturerFragment;
import com.carselection.carselector.mainActivity.manufacturer.ManufacturerPresenter;
import com.carselection.carselector.mainActivity.manufacturer.ManufacturerPresenterModule;
import com.carselection.carselector.mainActivity.summary.SummaryFragment;
import com.carselection.carselector.mainActivity.summary.SummaryPresenter;
import com.carselection.carselector.mainActivity.summary.SummaryPresenterModule;
import com.carselection.carselector.util.ActivityUtil;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity
        implements ManufacturerFragment.OnManufacturerFragmentInteractionListener
        , MainTypeFragment.OnMainTypeFragmentInteractionListener
        , BuiltDateFragment.OnBuiltDateFragmentInteractionListener {

    ManufacturerFragment manufacturerFragment;
    MainTypeFragment mainTypeFragment;
    BuiltDateFragment builtDateFragment;

    SummaryFragment summaryFragment;

    @Inject
    CarSelectorRepository carSelectorRepository;

    @Inject
    ManufacturerPresenter manufacturerPresenter;

    @Inject
    MainTypePresenter mainTypePresenter;

    @Inject
    BuiltDatePresenter builtDatePresenter;

    @Inject
    SummaryPresenter summaryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            manufacturerFragment = (ManufacturerFragment) getSupportFragmentManager().findFragmentByTag("MANUFACTURER");
            mainTypeFragment = (MainTypeFragment) getSupportFragmentManager().findFragmentByTag("MAINTYPE");
            builtDateFragment = (BuiltDateFragment) getSupportFragmentManager().findFragmentByTag("BUILTDATE");
            summaryFragment = (SummaryFragment) getSupportFragmentManager().findFragmentByTag("SUMMARY");
        }

        if (manufacturerFragment == null) {
            manufacturerFragment = ManufacturerFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), manufacturerFragment, R.id.main_content, "MANUFACTURER");
        }

        if (mainTypeFragment == null)
            mainTypeFragment = MainTypeFragment.newInstance();

        if (builtDateFragment == null)
            builtDateFragment = BuiltDateFragment.newInstance();

        if (summaryFragment == null)
            summaryFragment = SummaryFragment.newInstance();

        DaggerMainComponent.builder()
                .repoComponent(((CarApplication) getApplication()).getComponent())
                .manufacturerPresenterModule(new ManufacturerPresenterModule(manufacturerFragment))
                .mainTypePresenterModule(new MainTypePresenterModule(mainTypeFragment))
                .builtDatePresenterModule(new BuiltDatePresenterModule(builtDateFragment))
                .summaryPresenterModule(new SummaryPresenterModule(summaryFragment))
                .build()
                .inject(this);
    }

    @Override
    public void showMainTypePage(String manufacturer, ResponseModel manufacturers) {

        Bundle selectedInfo = new Bundle();
        selectedInfo.putString("manufacturer", manufacturer);
        selectedInfo.putParcelable("manufacturers", manufacturers);

        mainTypeFragment.setArguments(selectedInfo);
        ActivityUtil.replaceFragment(getSupportFragmentManager(), mainTypeFragment, R.id.main_content, "MAINTYPE");
    }

    @Override
    public void showBuildDatePage(String manufacturer, String mainType, ResponseModel manufacturers) {

        Bundle selectedInfo = new Bundle();
        selectedInfo.putString("manufacturer", manufacturer);
        selectedInfo.putString("maintype", mainType);
        selectedInfo.putParcelable("manufacturers", manufacturers);

        builtDateFragment.setArguments(selectedInfo);
        ActivityUtil.replaceFragment(getSupportFragmentManager(), builtDateFragment, R.id.main_content, "BUILTDATE");
    }

    @Override
    public void showSummaryPage(String manufacturer, String model, String year) {

        Bundle selectedInfo = new Bundle();
        selectedInfo.putString("manufacturer", manufacturer);
        selectedInfo.putString("maintype", model);
        selectedInfo.putString("builtdate", year);

        summaryFragment.setArguments(selectedInfo);
        ActivityUtil.replaceFragment(getSupportFragmentManager(), summaryFragment, R.id.main_content, "SUMMARY");
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else
            finish();
    }
}

