package com.example.lifequote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lifequote.fragments.PageFragment;
import com.example.lifequote.fragments.PageFragment10;
import com.example.lifequote.fragments.PageFragment11;
import com.example.lifequote.fragments.PageFragment12;
import com.example.lifequote.fragments.PageFragment13;
import com.example.lifequote.fragments.PageFragment14;
import com.example.lifequote.fragments.PageFragment15;
import com.example.lifequote.fragments.PageFragment2;
import com.example.lifequote.fragments.PageFragment3;
import com.example.lifequote.fragments.PageFragment4;
import com.example.lifequote.fragments.PageFragment5;
import com.example.lifequote.fragments.PageFragment6;
import com.example.lifequote.fragments.PageFragment7;
import com.example.lifequote.fragments.PageFragment8;
import com.example.lifequote.fragments.PageFragment9;
import com.example.lifequote.ui.SlidePageAdapter;

import java.util.ArrayList;
import java.util.List;

public class quote1_Activity extends AppCompatActivity {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote1_);
        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragment());
        list.add(new PageFragment2());
        list.add(new PageFragment3());
        list.add(new PageFragment4());
        list.add(new PageFragment5());
        list.add(new PageFragment6());
        list.add(new PageFragment7());
        list.add(new PageFragment8());
        list.add(new PageFragment9());
        list.add(new PageFragment10());
        list.add(new PageFragment11());
        list.add(new PageFragment12());
        list.add(new PageFragment13());
        list.add(new PageFragment14());
        list.add(new PageFragment15());
        pager = findViewById(R.id.pager);
        pagerAdapter =  new SlidePageAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(pagerAdapter);
    }
    public void onDefautToggleClick(View view) {
        Toast.makeText(this,"DefaultToggle", Toast.LENGTH_SHORT).show();
    }
    public void onCustomToggleClick(View view){
        Toast.makeText(this,"Liked",Toast.LENGTH_SHORT).show();
    }
}