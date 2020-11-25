package com.example.quiz.MainActivity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quiz.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class StartFragment extends Fragment {

    private ModFragment modFragment;
    private RecordFragment recordFragment;
    private FragmentTransaction ft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_fragment, null);

        MobileAds.initialize(v.getContext(), initializationStatus -> {
        });

        AdView mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);

        Button btnPlay = (Button) v.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(v12 -> {
            modFragment = new ModFragment();

            ft = getFragmentManager().beginTransaction();
            ft.add(R.id.frgmCont, modFragment);
            ft.commit();
        });

        Button btnRecords = (Button) v.findViewById(R.id.btnRecords);
        btnRecords.setOnClickListener(v1 -> {
            recordFragment = new RecordFragment();
            Bundle bundle = new Bundle();

            recordFragment.setArguments(bundle);
            ft = getFragmentManager().beginTransaction();
            ft.add(R.id.frgmCont, recordFragment);
            ft.commit();
        });

        Button btnExit = v.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Выход")
                    .setMessage("Вы уверены что хотите выйти?")
                    .setPositiveButton("Да", (dialog, id) -> {
                        finish();
                    })
                    .setNegativeButton("Нет", (dialog, id) -> {
                    })
                    .create()
                    .show();
        });

        return v;
    }

    private void finish() {
        getActivity().finish();
    }

}