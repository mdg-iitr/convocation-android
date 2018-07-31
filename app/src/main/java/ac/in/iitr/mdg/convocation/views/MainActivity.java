package ac.in.iitr.mdg.convocation.views;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ac.in.iitr.mdg.convocation.views.home.GuestAdapter;
import ac.in.iitr.mdg.convocation.views.home.GuestDesc;
import ac.in.iitr.mdg.convocation.views.home.MiscDesc;
import ac.in.iitr.mdg.convocation.views.home.chiefGuestsProfile;
import ac.in.iitr.mdg.convocation.views.home.miscellaneousAdapter;
import ac.in.iitr.mdg.convocation.views.home.miscellaneousClass;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerButton = findViewById(R.id.registerButton);
        String registerText = "<big><font color='#000000'>"+"REGISTER NOW"+"</font></big>"+"<br/>"+"<small><font color='#000000'>"+"(To get early bird facilities)"+"</font></small>";
        registerButton.setText((Html.fromHtml(registerText)));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.maps) {
            return true;
        }else if(id == R.id.feedback){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        List<chiefGuestsProfile> guestList = new ArrayList<>();
        List<miscellaneousClass> miscellaneousList = new ArrayList<>();
        RecyclerView recyclerViewGuest,recyclerViewMisc;
        GuestAdapter mAdapterGuest;
        miscellaneousAdapter mAdapterMisc;
        
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            switch (getArguments().getInt(ARG_SECTION_NUMBER)){

                case 1:
                    View rootView6 = inflater.inflate(R.layout.activity_home, container, false);
                    return setUpHome(rootView6);

                case 7:
                    View rootView7 = inflater.inflate(R.layout.fragment_livecast, container, false);
                    return rootView7;

                    default:
                        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                        return rootView;

            }

        }

        private View setUpHome(View view) {
            
            ImageButton fblink = (ImageButton) view.findViewById(R.id.fblink);
            ImageButton twlink = (ImageButton) view.findViewById(R.id.twlink);
            ImageButton ytlink = (ImageButton) view.findViewById(R.id.ytlink);
            ImageButton inlink = (ImageButton) view.findViewById(R.id.inlink);
            View rootView = View.inflate(getContext(), R.layout.chiefguestcard, null);
            ImageButton cg_next = (ImageButton) rootView.findViewById(R.id.cg_next_button);
            fblink.setImageResource(R.drawable.facebook);
            twlink.setImageResource(R.drawable.twitter);
            ytlink.setImageResource(R.drawable.youtube);
            inlink.setImageResource(R.drawable.linkedin);
            cg_next.setImageResource(R.drawable.baseline_keyboard_arrow_right_24);

            recyclerViewGuest = (RecyclerView) view.findViewById(R.id.chiefguestListView);

            mAdapterGuest = new GuestAdapter(guestList, new GuestAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(chiefGuestsProfile guest) {
                    Intent intent = new Intent(getActivity(), GuestDesc.class);
                    intent.putExtra("headingName", guest.getName());
                    intent.putExtra("headingDesig", guest.getDesignation());
                    intent.putExtra("data", guest.getData());
                    startActivity(intent);
                }
            });
            RecyclerView.LayoutManager mLayoutManagerGuest = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerViewGuest.setLayoutManager(mLayoutManagerGuest);
            recyclerViewGuest.setItemAnimator(new DefaultItemAnimator());
            recyclerViewGuest.setAdapter(mAdapterGuest);

            recyclerViewMisc = (RecyclerView) view.findViewById(R.id.miscellaneousListView);

            mAdapterMisc = new miscellaneousAdapter(miscellaneousList, new miscellaneousAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(miscellaneousClass miscellaneous) {
                    Intent intent = new Intent(getActivity(), MiscDesc.class);
                    intent.putExtra("headingName", miscellaneous.getMainHead());
                    intent.putExtra("data", miscellaneous.getData());
                    startActivity(intent);
                }
            });
            RecyclerView.LayoutManager mLayoutManagerMisc = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerViewMisc.setLayoutManager(mLayoutManagerMisc);
            recyclerViewMisc.setItemAnimator(new DefaultItemAnimator());
            recyclerViewMisc.setAdapter(mAdapterMisc);

            recyclerViewMisc.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,true));

            prepareGuestData();
            prepareMiscData();

            return view;

        }

        private void prepareMiscData() {
            chiefGuestsProfile guest = new chiefGuestsProfile(1,"Shri Ram Nath Kovind","Hon'ble President of India",null,null,null);
            guestList.add(guest);

            guest = new chiefGuestsProfile(2,"Shri Ram Naik","Hon'ble Governer of U.P.",null,null,null);
            guestList.add(guest);

            mAdapterGuest.notifyDataSetChanged();
        }

        private void prepareGuestData() {
            miscellaneousClass miscellaneous = new miscellaneousClass("About IIT Roorkee",null,null,null,null);
            miscellaneousList.add(miscellaneous);

            miscellaneous = new miscellaneousClass("IITR Guru Wandana",null,null,null,null);
            miscellaneousList.add(miscellaneous);

            mAdapterMisc.notifyDataSetChanged();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 7;
        }
    }
}
