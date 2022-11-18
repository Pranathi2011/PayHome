package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdp extends FragmentStateAdapter {


    public MyViewPagerAdp(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Veg_Mess();
            case 1:
                return new NVeg_Mess();
            case 2:
                return new Spl_Mess();
            default:
                return new Veg_Mess();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
