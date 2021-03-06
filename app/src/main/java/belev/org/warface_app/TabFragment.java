package belev.org.warface_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3 ;
    private static final String BUNDLE_ACHIVMENT = "bundle_achivment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        /**
         *Inflate tab_layout and setup Views.
         */
            View x =  inflater.inflate(R.layout.tab_layout, null);
            tabLayout = (TabLayout) x.findViewById(R.id.tabs);
            viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                    tabLayout.setupWithViewPager(viewPager);
                   }
        });


        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position)
        {

          final AchivmentFragment fragment = new AchivmentFragment();
          final Bundle arguments = new Bundle();

          switch (position){
              case 0 :
                      arguments.putInt(BUNDLE_ACHIVMENT, 2);
                      fragment.setArguments(arguments);
                      return fragment;
              case 1 :
                      arguments.putInt(BUNDLE_ACHIVMENT, 1);
                      fragment.setArguments(arguments);
                      return fragment;

              case 2 :
                      arguments.putInt(BUNDLE_ACHIVMENT, 0);
                      fragment.setArguments(arguments);
                      return fragment;
          }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            String mark = getResources().getString(R.string.menu_mark);
            String badge = getResources().getString(R.string.menu_badge);
            String string = getResources().getString(R.string.menu_string);

            switch (position){
                case 0 :
                    return mark;
                case 1 :
                    return badge;
                case 2 :
                    return string;
            }
                return null;
        }
    }

}
