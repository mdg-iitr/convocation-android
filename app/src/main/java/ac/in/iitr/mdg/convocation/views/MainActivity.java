package ac.in.iitr.mdg.convocation.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


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
        String registerText = "<b><big><font color='#000000'>"+"REGISTER NOW"+"</font></big>"+"<br/>"+"<small><font color='#000000'>"+"(To get early bird facilities)"+"</font></small></b>";
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

                case 5:
                    View rootView5 = inflater.inflate(R.layout.fragment_medals,container,false);

                    RecyclerView medalView = rootView5.findViewById(R.id.medals_recycler_view);
                    medalView.setLayoutManager(new LinearLayoutManager(getContext()));
                    ArrayList<MedalModel> medalArray = new ArrayList<>();
                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY,"President's Gold Medal"));
                    Bitmap bitmap2 = Bitmap.createBitmap(120,120, Bitmap.Config.ARGB_8888);
                    Canvas c = new Canvas(bitmap2);
                    c.drawColor(Color.LTGRAY);
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER,new MedalHolderModel(bitmap2,"Karthik Iyer","Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER,new MedalHolderModel(bitmap2,"Karthik Iyer","Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY,"Director's Gold Medal"));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER,new MedalHolderModel(bitmap2,"Karthik Iyer","Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER,new MedalHolderModel(bitmap2,"Karthik Iyer","Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY,"Institute Gold Medal"));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER,new MedalHolderModel(bitmap2,"Karthik Iyer","Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER,new MedalHolderModel(bitmap2,"Karthik Iyer","Chemical Engineering", "17112036")));
                    MedalAdapter medalAdapter = new MedalAdapter(medalArray);
                    medalView.setAdapter(medalAdapter);

                    return rootView5;

                case 6:
                    View rootView6 = inflater.inflate(R.layout.fragment_gallery,container,false);

                    RecyclerView galleryView = rootView6.findViewById(R.id.gallery_recycler_view);
                    galleryView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    Bitmap[] bitmap = new Bitmap[7];
                    bitmap[0] = Bitmap.createBitmap(250,250, Bitmap.Config.ARGB_8888);
                    bitmap[1] = Bitmap.createBitmap(500,600, Bitmap.Config.ARGB_8888);
                    bitmap[2] = Bitmap.createBitmap(400,250, Bitmap.Config.ARGB_8888);
                    bitmap[3] = Bitmap.createBitmap(300,300, Bitmap.Config.ARGB_8888);
                    bitmap[4] = Bitmap.createBitmap(450,270, Bitmap.Config.ARGB_8888);
                    bitmap[5] = Bitmap.createBitmap(350,150, Bitmap.Config.ARGB_8888);
                    bitmap[6] = Bitmap.createBitmap(200,250, Bitmap.Config.ARGB_8888);

                    for (int i = 0; i<7;i++){
                        Canvas c2 = new Canvas(bitmap[i]);
                        c2.drawColor(Color.LTGRAY);
                    }
                    GalleryAdapter galleryAdapter = new GalleryAdapter(bitmap);
                    galleryView.setAdapter(galleryAdapter);
                    SpacesItemDecoration decoration = new SpacesItemDecoration(16);
                    galleryView.addItemDecoration(decoration);

                    return rootView6;

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
            // Show 3 total pages.
            return 7;
        }
    }
}
