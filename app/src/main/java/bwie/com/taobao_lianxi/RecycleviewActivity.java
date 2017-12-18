package bwie.com.taobao_lianxi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import fragment.Fragment01;
import fragment.Fragment02;
import fragment.Fragment03;
import fragment.Fragment04;

public class RecycleviewActivity extends AppCompatActivity{

    private List<Fragment> listFrag = new ArrayList<>();
    private Fragment01 fragment01 = new Fragment01();
    private Fragment02 fragment02 = new Fragment02();
    private Fragment03 fragment03 = new Fragment03();
    private Fragment04 fragment04 = new Fragment04();
    private ViewPager pager;
    private RadioGroup rg;
    private RadioButton rb1,rb2,rb3,rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        listFrag.add(fragment01);
        listFrag.add(fragment02);
        listFrag.add(fragment03);
        listFrag.add(fragment04);
        pager = (ViewPager) findViewById(R.id.pager);
        rg = (RadioGroup) findViewById(R.id.rg);

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFrag.get(position);
            }
            @Override
            public int getCount() {
                return listFrag.size();
            }
        });
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg.check(R.id.rb1);
                        break;
                    case 1:
                        rg.check(R.id.rb2);
                        break;
                    case 2:
                        rg.check(R.id.rb3);
                        break;
                    case 3:
                        rg.check(R.id.rb4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                         pager.setCurrentItem(1);
                         break;
                    case R.id.rb3:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        pager.setCurrentItem(3);
                        break;
                }
            }
        });
    }
}