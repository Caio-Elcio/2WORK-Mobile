package com.example.plans;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class SlideActivity extends AppCompatActivity {

    ViewPager viewPager;
    SlideViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        viewPager=findViewById(R.id.viewpager);
        adapter=new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
    }
}