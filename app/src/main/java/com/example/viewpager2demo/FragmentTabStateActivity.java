package com.example.viewpager2demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabStateActivity extends AppCompatActivity {
    private final int PAGE_HOME = 0;
    private final int PAGE_CATE = 1;
    private final int PAGE_ONLINE = 2;
    private final int PAGE_REDEEM = 3;
    private final int PAGE_SHOP = 4;
    private final int PAGE_USER = 5;
    private ViewPagerFragmentStateAdapter mAdapter;
    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private TabLayout.Tab tab1;
    private TabLayout.Tab tab2;
    private TabLayout.Tab tab3;
    private List<Fragment> fragmentList = new ArrayList();
    private List<Long> fragmentIdList = new ArrayList();
    private int mSelectedTab = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_fragment_tab_state);
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager2 = findViewById(R.id.viewpager2);

        fragmentInit();
        tabInit();

        mAdapter = new ViewPagerFragmentStateAdapter(this, fragmentList, fragmentIdList);
        mViewPager2.setAdapter(mAdapter);
//        mViewPager2.setUserInputEnabled(false);
        mViewPager2.setOffscreenPageLimit(3);
        mTabLayout.getTabAt(mSelectedTab).select();

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.equals(mTabLayout.getTabAt(0))) {
                    mSelectedTab = 0;
                    mViewPager2.setCurrentItem(0, false);
                } else if (tab.equals(mTabLayout.getTabAt(1))) {
                    mSelectedTab = 1;
                    mViewPager2.setCurrentItem(1, false);
                }else if (tab.equals(mTabLayout.getTabAt(2))) {
                    mSelectedTab = 2;
                    mViewPager2.setCurrentItem(2, false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position != mTabLayout.getSelectedTabPosition()) {
                    mTabLayout.getTabAt(position).select();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    private void tabInit() {
        mTabLayout.removeAllTabs();
        View view1 = LayoutInflater.from(this).inflate(R.layout.tabone_layout, mViewPager2, false);
        tab1 = mTabLayout.newTab();
        tab1.setCustomView(view1);
        mTabLayout.addTab(tab1);
        View view2 = LayoutInflater.from(this).inflate(R.layout.tabtwo_layout, mViewPager2, false);
        tab2 = mTabLayout.newTab();
        tab2.setCustomView(view2);
        mTabLayout.addTab(tab2);
        View view3 = LayoutInflater.from(this).inflate(R.layout.tabthree_layout, mViewPager2, false);
        tab3 = mTabLayout.newTab();
        tab3.setCustomView(view3);
        mTabLayout.addTab(tab3);
    }

    private void fragmentInit() {
        Fragment1 fragemnt1 = new Fragment1();
        Fragment2 fragemnt2 = new Fragment2();
        Fragment3 fragemnt3 = new Fragment3();
        fragmentList.clear();
        fragmentIdList.clear();
        fragmentList.add(fragemnt1);
        fragmentIdList.add((long) PAGE_HOME);
        fragmentList.add(fragemnt2);
        fragmentIdList.add((long) PAGE_CATE);
        fragmentList.add(fragemnt3);
        fragmentIdList.add((long) PAGE_ONLINE);
    }


    class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {
        private List<Fragment> list;
        private List<Long> fragmentIds;

        ViewPagerFragmentStateAdapter(@NonNull AppCompatActivity fragmentManager, List fragmentList, List fragmentIdList) {
            super(fragmentManager);
            this.list = fragmentList;
            this.fragmentIds = fragmentIdList;
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }

        public List getFragmentIds() {
            return fragmentIds;
        }

        @Override
        public boolean containsItem(long itemId) {
            return fragmentIds.contains(itemId);
        }

        @Override
        public long getItemId(int position) {
            return fragmentIdList.get(position);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return list.get(position);
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentTabStateActivity.class);
        context.startActivity(starter);
    }
}
