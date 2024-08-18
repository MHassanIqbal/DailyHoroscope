package t.hkb.designhoroscope.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private static int position;

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public int getCount() {
        this.position = position;
        return mFragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
//        return mFragmentTitleList.get(position)+" "+fetchData.current_date+"\n"+"";
        return mFragmentTitleList.get(position);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}