package com.yosoftware.musicapp.features.home.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.yosoftware.musicapp.R;
import com.yosoftware.musicapp.adapters.TabAdapter;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTabLayout();

    }

    void setUpTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        tabLayout.addTab(tabLayout.newTab().setText("Folders"));
//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#747474"));
        tabLayout.setTabTextColors(Color.parseColor("#aaaaaa"), Color.parseColor("#747474"));
        tabLayout.addTab(tabLayout.newTab().setText("All Music"));
        tabLayout.addTab(tabLayout.newTab().setText("Play Lists"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}