package ac.in.iitr.mdg.convocation.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;


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

    public static Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                (min(bitmap.getHeight(),bitmap.getWidth()) / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
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

                case 3:

                    View rootView3 = inflater.inflate(R.layout.fragment_schedule,container,false);

                     /*-----------------------------------------------------*/
                    // Schedule Recycler view

                    RecyclerView recyclerView1 = (RecyclerView)rootView3.findViewById(R.id.schedule_date1_recyclerView);
                    final List<Schedule> list = new ArrayList<>();
                    final ScheduleAdapter textAdapter = new ScheduleAdapter(getContext(),list);
                    final Schedule schedule1 = new Schedule();


//                    Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.test);
//             /*OR*/
//                    String name = c.getString(str_url);
//                    URL url_value = new URL(name);
//                    Bitmap icon = BitmapFactory.decodeStream(url_value.openConnection().getInputStream());

//                    schedule1.setSchedule_image(getCroppedBitmap(icon));

                    schedule1.setSchedule_event("Official Lunch");
                    schedule1.setSchedule_venue("LBS Ground");
                    schedule1.setSchedule_time("2:00 pm - 3:00 pm");
                    list.add(schedule1);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule2 = new Schedule();
                    schedule2.setSchedule_event("Official Dinner");
                    schedule2.setSchedule_venue("LBS Ground");
                    schedule2.setSchedule_time("2:00 pm - 3:00 pm");
                    list.add(schedule2);
                    textAdapter.notifyDataSetChanged();

                    RecyclerView.LayoutManager recycler = new LinearLayoutManager(getContext());
                    recyclerView1.setLayoutManager(recycler);
                    recyclerView1.setAdapter(textAdapter);


                    RecyclerView recyclerView2 = (RecyclerView)rootView3.findViewById(R.id.schedule_date2_recyclerView);
                    final List<Schedule> list2 = new ArrayList<>();
                    final ScheduleAdapter textAdapter2 = new ScheduleAdapter(getContext(),list);
                    final Schedule schedule3 = new Schedule();
                    schedule3.setSchedule_event("Official Lunch");
                    schedule3.setSchedule_venue("LBS Ground");
                    schedule3.setSchedule_time("2:00 pm - 3:00 pm");
                    list2.add(schedule3);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule4 = new Schedule();
                    schedule4.setSchedule_event("Official Dinner");
                    schedule4.setSchedule_venue("LBS Ground");
                    schedule4.setSchedule_time("2:00 pm - 3:00 pm");
                    list2.add(schedule4);
                    textAdapter.notifyDataSetChanged();

                    RecyclerView.LayoutManager recycler2 = new LinearLayoutManager(getContext());
                    recyclerView2.setLayoutManager(recycler2);
                    recyclerView2.setAdapter(textAdapter2);

                     /*------------------------------------------------------*/

                    return rootView3;

                case 4:
                    View rootView4 = inflater.inflate(R.layout.fragment_degrees,container,false);
                    return rootView4;

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
