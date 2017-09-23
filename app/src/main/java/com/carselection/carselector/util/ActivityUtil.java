package com.carselection.carselector.util;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ActivityUtil {

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameID, String tag) {
        FragmentTransaction frg = fragmentManager.beginTransaction();
        frg.add(frameID, fragment, tag);
        frg.commit();
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameID, String tag) {

        FragmentTransaction frg = fragmentManager.beginTransaction();
        frg.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.fade_out);
        frg.replace(frameID, fragment, tag).addToBackStack(null);
        frg.commit();
    }
}
