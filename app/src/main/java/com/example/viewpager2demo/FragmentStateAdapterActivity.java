package com.example.viewpager2demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @author wdx
 * @date 2020/6/3
 * 新闻栏目样式（热点，新闻，体育，生活等等）
 */
public class FragmentStateAdapterActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private List<Integer> images = new ArrayList<>();
    private ViewPagerFragmentStateAdapter mAdapter;
    {
        images.add(R.drawable.bg1);
        images.add(R.drawable.bg2);
        images.add(R.drawable.bg3);
        images.add(R.drawable.bg4);
    }
    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentStateAdapterActivity.class);
        context.startActivity(starter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            if (TextUtils.equals(item.getTitle(), getString(R.string.action_add))) {
                images.add(R.drawable.bg5);
                mTabLayout.addTab(mTabLayout.newTab().setText("汽车"));
                mAdapter.notifyItemInserted(images.size() - 1);
                item.setIcon(R.drawable.ic_action_remove);
                item.setTitle(R.string.action_remove);
            } else {
                item.setIcon(R.drawable.ic_action_add);
                item.setTitle(R.string.action_add);
                int last = images.size() - 1;
                images.remove(last);
                mTabLayout.removeTabAt(last);
                mAdapter.notifyItemRemoved(last);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_state_adapter);
        mTabLayout = findViewById(R.id.tablayout);
        mViewPager2 = findViewById(R.id.viewpager2);
        mAdapter = new ViewPagerFragmentStateAdapter(this);
        mViewPager2.setAdapter(mAdapter);
        mViewPager2.setUserInputEnabled(true);//true:滑动，false：禁止滑动
        mTabLayout.addTab(mTabLayout.newTab().setText("生活"));
        mTabLayout.addTab(mTabLayout.newTab().setText("体育"));
        mTabLayout.addTab(mTabLayout.newTab().setText("美食"));
        mTabLayout.addTab(mTabLayout.newTab().setText("头条"));
        //tab点击选中
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager2.setCurrentItem(tab.getPosition());
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
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTabLayout.setScrollPosition(position,0,false);
            }
        });
    }

    class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {

        ViewPagerFragmentStateAdapter(@NonNull AppCompatActivity fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return CommontPageFragment.newInstance(images, position);
        }
    }
}
