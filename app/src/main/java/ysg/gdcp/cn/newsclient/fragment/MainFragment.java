package ysg.gdcp.cn.newsclient.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ysg.gdcp.cn.newsclient.R;
import ysg.gdcp.cn.newsclient.adapter.FragmentAdapter;

/**
 * Created by Administrator on 2017/6/22 15:49.
 *
 * @author ysg
 */
public class MainFragment extends Fragment{
    private TabLayout mTabLayout_t;
    private ViewPager mViewPager;
    List<Fragment> fragments = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;
    private List<String> titles;
    /*private List<NewFragment> mNewFragmentList = new ArrayList<NewFragment>();*/

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        initViewPager();

    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentArrayList();
        initViewPager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container,false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTabLayout_t = (TabLayout) view.findViewById(R.id.sliding_tabtop);
        if (mFragmentAdapter == null){
            mFragmentAdapter= new FragmentAdapter(getFragmentManager(),fragments,titles);
        }

        //给ViewPage设置适配器
        mViewPager.setAdapter(mFragmentAdapter);
        //将mViewPage与mTabLayout关联
        mTabLayout_t.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(10);
        return view;
    }

    private void initViewPager() {
        if (titles == null){
            titles = new ArrayList<>();
        }

        titles.add("头条");
        titles.add("社会");
        titles.add("国际");
        titles.add("国内");
        titles.add("娱乐");
        titles.add("体育");
        titles.add("军事");
        titles.add("科技");
        titles.add("财经");
        titles.add("时尚");

        /*for (int i = 0; i < titles.size(); i++) {

            mTabLayout_t.addTab(mTabLayout_t.newTab().setText(titles.get(i)));
        }*/

        /*for (int i = 0; i < titles.size(); i++) {
            fragments.add(fragments.get(i));
        }*/
        /*fragments.add(mNewFragmentList.get(0));
        fragments.add(mNewFragmentList.get(1));
        fragments.add(mNewFragmentList.get(2));
        fragments.add(mNewFragmentList.get(3));*/

        /*//给TabLayout设置适配器
        mTabLayout_t.setTabsFromPagerAdapter(mFragmentAdapter);*/
    }
    private void initFragmentArrayList(){
        NewFragment fa = new NewFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("NEWSTYPE",1);
        fa.setArguments(bundle1);

        NewFragment fb = new NewFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("NEWSTYPE",2);
        fb.setArguments(bundle2);

        NewFragment fc = new NewFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putInt("NEWSTYPE",3);
        fc.setArguments(bundle3);

        NewFragment fd = new NewFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putInt("NEWSTYPE",4);
        fd.setArguments(bundle4);

        NewFragment fe = new NewFragment();
        Bundle bundle5 = new Bundle();
        bundle5.putInt("NEWSTYPE",5);
        fe.setArguments(bundle5);

        NewFragment ff = new NewFragment();
        Bundle bundle6 = new Bundle();
        bundle6.putInt("NEWSTYPE",6);
        ff.setArguments(bundle6);

        NewFragment fg = new NewFragment();
        Bundle bundle7 = new Bundle();
        bundle7.putInt("NEWSTYPE",7);
        fg.setArguments(bundle7);

        NewFragment fh = new NewFragment();
        Bundle bundle8 = new Bundle();
        bundle8.putInt("NEWSTYPE",8);
        fh.setArguments(bundle8);

        NewFragment fi = new NewFragment();
        Bundle bundle9 = new Bundle();
        bundle9.putInt("NEWSTYPE",9);
        fi.setArguments(bundle9);

        NewFragment fj = new NewFragment();
        Bundle bundle10 = new Bundle();
        bundle10.putInt("NEWSTYPE",10);
        fj.setArguments(bundle10);


        fragments.add(fa);
        fragments.add(fb);
        fragments.add(fc);
        fragments.add(fd);
        fragments.add(fe);
        fragments.add(ff);
        fragments.add(fg);
        fragments.add(fh);
        fragments.add(fi);
        fragments.add(fj);
    }

}

