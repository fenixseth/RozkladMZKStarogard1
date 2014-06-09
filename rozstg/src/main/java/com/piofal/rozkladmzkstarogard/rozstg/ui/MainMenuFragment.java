package com.piofal.rozkladmzkstarogard.rozstg.ui;

/**
 * Created by Piotr on 2014-05-30.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.piofal.rozkladmzkstarogard.rozstg.R;

public class MainMenuFragment extends Fragment
        implements View.OnClickListener
{
    public void onClick(View paramView)
    {
        HomeActivity localHomeActivity = (HomeActivity)getActivity();
        switch (paramView.getId())
        {
            default:
                return;
            case R.id.main_menu_przystanki:
                localHomeActivity.addFragment(new BusstopsFragment());
                return;
            case R.id.main_menu_linie:
                localHomeActivity.addFragment(new BuslinesFragment());
                return;
            case R.id.main_menu_ulubione:
        }
        localHomeActivity.addFragment(new FavouritesFragment());
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        View localView = paramLayoutInflater.inflate(R.layout.main_menu, null);
        localView.findViewById(R.id.main_menu_przystanki).setOnClickListener(this);
        localView.findViewById(R.id.main_menu_linie).setOnClickListener(this);
        localView.findViewById(R.id.main_menu_ulubione).setOnClickListener(this);
        return localView;
    }
}

