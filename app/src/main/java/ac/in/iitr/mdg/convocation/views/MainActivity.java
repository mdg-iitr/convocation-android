package ac.in.iitr.mdg.convocation.views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ac.in.iitr.mdg.convocation.views.Accomodation.hotelAdapter;
import ac.in.iitr.mdg.convocation.views.Accomodation.hotelProfile;
import ac.in.iitr.mdg.convocation.views.home.GuestAdapter;
import ac.in.iitr.mdg.convocation.views.home.GuestDesc;
import ac.in.iitr.mdg.convocation.views.home.chiefGuestsProfile;
import ac.in.iitr.mdg.convocation.views.home.miscellaneousClass;

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
                (min(bitmap.getHeight(), bitmap.getWidth()) / 2), paint);
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
        } else if (id == R.id.feedback) {
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
        List<chiefGuestsProfile> guestList = new ArrayList<>();
        List<miscellaneousClass> miscellaneousList = new ArrayList<>();
        List<hotelProfile> hotelList = new ArrayList<>();
        RecyclerView recyclerViewGuest, recyclerViewMisc, recyclerViewHotel;
        GuestAdapter mAdapterGuest;
        hotelAdapter mAdapterHotel;

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
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {

                case 1:
                    View rootView1 = inflater.inflate(R.layout.activity_home, container, false);
                    return setUpHome(rootView1);

                case 2:
                    View rootView2 = inflater.inflate(R.layout.fragment_dresscode, container, false);
                    return rootView2;

                case 3:
                    View rootViewAcco = inflater.inflate(R.layout.activity_acco, container, false);
                    return setUpAcco(rootViewAcco);

                case 4:

                    View rootView4 = inflater.inflate(R.layout.fragment_schedule, container, false);

                    /*-----------------------------------------------------*/
                    // ScheduleCard Recycler view

                    RecyclerView recyclerView = (RecyclerView) rootView4.findViewById(R.id.schedule_recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    final List<Schedule> list = new ArrayList<>();
                    final ScheduleAdapter textAdapter = new ScheduleAdapter(getContext(), list);

//                    Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.schedule_image_default);
//             /*OR*/
//                    String name = c.getString(str_url);
//                    URL url_value = new URL(name);
//                    Bitmap icon = BitmapFactory.decodeStream(url_value.openConnection().getInputStream());

//                    scheduleCard1.setSchedule_image(getCroppedBitmap(icon));

                    final Schedule schedule_date1 = new Schedule(Schedule.TYPE_DATE, "21 September 2018");
                    list.add(schedule_date1);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule1 = new Schedule(Schedule.TYPE_SCHEDULE, new ScheduleCard(null, "Official Breakfast1", "LBS Ground", "2:00 pm - 3:00 pm"));
                    list.add(schedule1);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule2 = new Schedule(Schedule.TYPE_SCHEDULE, new ScheduleCard(null, "Official Lunch1", "LBS Ground", "2:00 pm - 3:00 pm"));
                    list.add(schedule2);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule3 = new Schedule(Schedule.TYPE_SCHEDULE, new ScheduleCard(null, "Official Dinner1", "LBS Ground", "2:00 pm - 3:00 pm"));
                    list.add(schedule3);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule_date2 = new Schedule(Schedule.TYPE_DATE, "22 September 2018");
                    list.add(schedule_date2);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule4 = new Schedule(Schedule.TYPE_SCHEDULE, new ScheduleCard(null, "Official Breakfast2", "LBS Ground", "2:00 pm - 3:00 pm"));
                    list.add(schedule4);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule5 = new Schedule(Schedule.TYPE_SCHEDULE, new ScheduleCard(null, "Official Lunch2", "LBS Ground", "2:00 pm - 3:00 pm"));
                    list.add(schedule5);
                    textAdapter.notifyDataSetChanged();

                    final Schedule schedule6 = new Schedule(Schedule.TYPE_SCHEDULE, new ScheduleCard(null, "Official Dinner2", "LBS Ground", "2:00 pm - 3:00 pm"));
                    list.add(schedule6);
                    textAdapter.notifyDataSetChanged();

                    recyclerView.setAdapter(textAdapter);


                    /*------------------------------------------------------*/

                    return rootView4;


                case 5:
                    final View rootView5 = inflater.inflate(R.layout.fragment_degrees, container, false);
                    final Button bTech = (Button) rootView5.findViewById(R.id.Degrees_button_bTech);
                    final Button mTech = (Button) rootView5.findViewById(R.id.Degrees_button_mTech);
                    final Button phD = (Button) rootView5.findViewById(R.id.Degrees_button_phD);
                    final Button management = (Button) rootView5.findViewById(R.id.Degrees_button_Management);

                    bTech.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                            bTech.setBackground(getResources().getDrawable(R.drawable.gradient));
                            bTech.setTextColor(getResources().getColor(R.color.white));
                            mTech.setBackgroundColor(getResources().getColor(R.color.white));
                            mTech.setTextColor(getResources().getColor(R.color.textColor));
                            phD.setBackgroundColor(getResources().getColor(R.color.white));
                            phD.setTextColor(getResources().getColor(R.color.textColor));
                            management.setBackgroundColor(getResources().getColor(R.color.white));
                            management.setTextColor(getResources().getColor(R.color.textColor));
                            RecyclerView recyclerView = (RecyclerView) rootView5.findViewById(R.id.degree_recyclerView);
                            final List<DegreeCard> list = new ArrayList<>();
                            final DegreeAdapter textAdapter = new DegreeAdapter(getContext(), list);

                            for (int i = 0; i < 10; i++) {
                                list.add(new DegreeCard("Chemical Engineering", "Total Students : 120"));
                                textAdapter.notifyDataSetChanged();
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(textAdapter);
                        }
                    });

                    bTech.performClick();

                    mTech.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                            mTech.setBackground(getResources().getDrawable(R.drawable.gradient));
                            mTech.setTextColor(getResources().getColor(R.color.white));
                            bTech.setBackgroundColor(getResources().getColor(R.color.white));
                            bTech.setTextColor(getResources().getColor(R.color.textColor));
                            phD.setBackgroundColor(getResources().getColor(R.color.white));
                            phD.setTextColor(getResources().getColor(R.color.textColor));
                            management.setBackgroundColor(getResources().getColor(R.color.white));
                            management.setTextColor(getResources().getColor(R.color.textColor));
                            RecyclerView recyclerView = (RecyclerView) rootView5.findViewById(R.id.degree_recyclerView);
                            final List<DegreeCard> list = new ArrayList<>();
                            final DegreeAdapter textAdapter = new DegreeAdapter(getContext(), list);

                            for (int i = 0; i < 10; i++) {
                                list.add(new DegreeCard("Electrical Engineering", "Total Students : 120"));
                                textAdapter.notifyDataSetChanged();
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(textAdapter);
                        }
                    });

                    phD.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                            phD.setBackground(getResources().getDrawable(R.drawable.gradient));
                            phD.setTextColor(getResources().getColor(R.color.white));
                            mTech.setBackgroundColor(getResources().getColor(R.color.white));
                            mTech.setTextColor(getResources().getColor(R.color.textColor));
                            bTech.setBackgroundColor(getResources().getColor(R.color.white));
                            bTech.setTextColor(getResources().getColor(R.color.textColor));
                            management.setBackgroundColor(getResources().getColor(R.color.white));
                            management.setTextColor(getResources().getColor(R.color.textColor));
                            RecyclerView recyclerView = (RecyclerView) rootView5.findViewById(R.id.degree_recyclerView);
                            final List<DegreeCard> list = new ArrayList<>();
                            final DegreeAdapter textAdapter = new DegreeAdapter(getContext(), list);

                            for (int i = 0; i < 10; i++) {
                                list.add(new DegreeCard("Civil Engineering", "Total Students : 120"));
                                textAdapter.notifyDataSetChanged();
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(textAdapter);
                        }
                    });

                    management.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                            management.setBackground(getResources().getDrawable(R.drawable.gradient));
                            management.setTextColor(getResources().getColor(R.color.white));
                            mTech.setBackgroundColor(getResources().getColor(R.color.white));
                            mTech.setTextColor(getResources().getColor(R.color.textColor));
                            phD.setBackgroundColor(getResources().getColor(R.color.white));
                            phD.setTextColor(getResources().getColor(R.color.textColor));
                            bTech.setBackgroundColor(getResources().getColor(R.color.white));
                            bTech.setTextColor(getResources().getColor(R.color.textColor));
                            RecyclerView recyclerView = (RecyclerView) rootView5.findViewById(R.id.degree_recyclerView);
                            final List<DegreeCard> list = new ArrayList<>();
                            final DegreeAdapter textAdapter = new DegreeAdapter(getContext(), list);

                            for (int i = 0; i < 10; i++) {
                                list.add(new DegreeCard("CSE Engineering", "Total Students : 120"));
                                textAdapter.notifyDataSetChanged();
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(textAdapter);
                        }
                    });

                    return rootView5;


                case 6:
                    View rootView6 = inflater.inflate(R.layout.fragment_medals, container, false);

                    RecyclerView medalView = rootView6.findViewById(R.id.medals_recycler_view);
                    medalView.setLayoutManager(new LinearLayoutManager(getContext()));
                    ArrayList<MedalModel> medalArray = new ArrayList<>();
                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY, "President's Gold Medal"));
                    Bitmap bitmap2 = Bitmap.createBitmap(120, 120, Bitmap.Config.ARGB_8888);
                    Canvas c = new Canvas(bitmap2);
                    c.drawColor(Color.LTGRAY);
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY, "Director's Gold Medal"));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY, "Institute Gold Medal"));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    MedalAdapter medalAdapter = new MedalAdapter(medalArray);
                    medalView.setAdapter(medalAdapter);


                    final Button instiLevelButton = rootView6.findViewById(R.id.instiLevel);
                    final Button deptLevelButton = rootView6.findViewById(R.id.deptLevel);
                    instiLevelButton.setOnClickListener(new View.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(View view) {

                            instiLevelButton.setBackground(getResources().getDrawable(R.drawable.gradient));
                            deptLevelButton.setBackground(getResources().getDrawable(R.drawable.white_card));
                            instiLevelButton.setTextColor(Color.parseColor("#ffffff"));
                            deptLevelButton.setTextColor(Color.parseColor("#444444"));

                        }
                    });
                    deptLevelButton.setOnClickListener(new View.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(View view) {

                            instiLevelButton.setBackground(getResources().getDrawable(R.drawable.white_card));
                            deptLevelButton.setBackground(getResources().getDrawable(R.drawable.gradient));
                            deptLevelButton.setTextColor(Color.parseColor("#ffffff"));
                            instiLevelButton.setTextColor(Color.parseColor("#444444"));

                        }
                    });
                    instiLevelButton.performClick();

                    return rootView6;

                case 7:
                    View rootView7 = inflater.inflate(R.layout.fragment_gallery, container, false);
                    RecyclerView galleryView = rootView7.findViewById(R.id.gallery_recycler_view);
                    galleryView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    Bitmap[] bitmap = new Bitmap[5];
                    bitmap[0] = Bitmap.createBitmap(158 * 2, 182 * 2, Bitmap.Config.ARGB_8888);
                    bitmap[1] = Bitmap.createBitmap(158 * 2, 129 * 2, Bitmap.Config.ARGB_8888);
                    bitmap[2] = Bitmap.createBitmap(158 * 2, 129 * 2, Bitmap.Config.ARGB_8888);
                    bitmap[3] = Bitmap.createBitmap(158 * 2, 182 * 2, Bitmap.Config.ARGB_8888);
                    bitmap[4] = Bitmap.createBitmap(158 * 2, 129 * 2, Bitmap.Config.ARGB_8888);
                    String[] s = new String[5];
                    s[0] = "Old Convocation";
                    s[1] = "Medal Distribution";
                    s[2] = "Chief Guests";
                    s[3] = "Degree Distribution";
                    s[4] = "Old Convocation";


                    for (int i = 0; i < 5; i++) {
                        Canvas c2 = new Canvas(bitmap[i]);
                        c2.drawColor(Color.LTGRAY);
                    }
                    GalleryAdapter galleryAdapter = new GalleryAdapter(bitmap, s);
                    galleryView.setAdapter(galleryAdapter);
                    SpacesItemDecoration decoration = new SpacesItemDecoration(16);
                    galleryView.addItemDecoration(decoration);

                    return rootView7;

                case 8:
                    View rootView8 = inflater.inflate(R.layout.fragment_livecast, container, false);
                    return rootView8;

                case 9:
                    View rootView9 = inflater.inflate(R.layout.fragment_instructions, container, false);
                    return rootView9;

                case 10:
                    View rootView10 = inflater.inflate(R.layout.fragment_contact, container, false);


                    RecyclerView contactView = (RecyclerView) rootView10.findViewById(R.id.contact_recyclerView);
                    contactView.setLayoutManager(new LinearLayoutManager(getContext()));
                    final List<Contact> contactList = new ArrayList<>();
                    final ContactAdapter contactAdapter = new ContactAdapter(getContext(), contactList);

                    final Contact contact_category1 = new Contact(Contact.TYPE_CATEGORY, "Medical Section");
                    contactList.add(contact_category1);
                    contactAdapter.notifyDataSetChanged();

                    final Contact contact1 = new Contact(Contact.TYPE_CONTACT, new ContactCard("Jayant Mishra", "Mental Patient", "9876543210"));
                    contactList.add(contact1);
                    contactAdapter.notifyDataSetChanged();

                    final Contact contact2 = new Contact(Contact.TYPE_CONTACT, new ContactCard("Anchit Shukla", "Liver Patient", "8976543210"));
                    contactList.add(contact2);
                    contactAdapter.notifyDataSetChanged();

                    final Contact contact3 = new Contact(Contact.TYPE_CATEGORY, "Technical Section");
                    contactList.add(contact3);
                    contactAdapter.notifyDataSetChanged();

                    final Contact contact4 = new Contact(Contact.TYPE_CONTACT, new ContactCard("Lakshya Kumawat", "Pro Officials", "7896543210"));
                    contactList.add(contact4);
                    contactAdapter.notifyDataSetChanged();

                    final Contact contact5 = new Contact(Contact.TYPE_CONTACT, new ContactCard("Prasannadeep Das", "Pro Officials", "8796543210"));
                    contactList.add(contact5);
                    contactAdapter.notifyDataSetChanged();

                    contactView.setAdapter(contactAdapter);

                    return rootView10;


                default:
                    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                    textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                    return rootView;

            }

        }

        private Button register;
        private Intent intentFilterIntent;
        private Uri intentUri;
        private static final String CONVO_CHANNELI_OAUTH_URL = "https://channeli.in/oauth/?client_id=248033f9cc5a67b44777&redirect_url=convoiitr://convo.sdslabs.co.in/";

        private View setUpHome(View view) {

            ImageButton fblink = (ImageButton) view.findViewById(R.id.fblink);
            ImageButton twlink = (ImageButton) view.findViewById(R.id.twlink);
            ImageButton ytlink = (ImageButton) view.findViewById(R.id.ytlink);
            ImageButton inlink = (ImageButton) view.findViewById(R.id.inlink);
            register = (Button) view.findViewById(R.id.register_button);


            getIntentFilter();

            setupChromeCustomTab();

            View rootView = View.inflate(getContext(), R.layout.chiefguestcard, null);

            recyclerViewGuest = (RecyclerView) view.findViewById(R.id.chiefguestListView);

            mAdapterGuest = new GuestAdapter(guestList, new GuestAdapter.OnItemClickListener() {
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
            recyclerViewGuest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
            recyclerViewGuest.setItemAnimator(new DefaultItemAnimator());
            recyclerViewGuest.setAdapter(mAdapterGuest);

            prepareGuestData();

            return view;

        }

        private void setupChromeCustomTab() {
            final CustomTabsIntent chromeIntent = new CustomTabsIntent.Builder().build();
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    chromeIntent.launchUrl(getActivity(), Uri.parse(CONVO_CHANNELI_OAUTH_URL));
                    Log.d("checkURi","uri launched");
                }
            });
        }

        private void getIntentFilter() {


            Log.d("checkURi","intent filter called");

            intentFilterIntent = getIntent();
            if (intentFilterIntent != null) {
                intentUri = intentFilterIntent.getData();
                String code;
                if (intentUri != null) {
                    code = intentUri.getQueryParameter("code");
                    Log.d("URI_CODE", code);
                    Toast.makeText(getActivity(), "Machaya : " + code, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), RegsiterActivity.class));
                }
            }
        }

        int k2 =1;

        private void prepareHotelData() {

            if(k2 ==1) {
                hotelProfile hotel = new hotelProfile("Hotel Prakash", "21,Civil Lines,Roorkee", null, null, null);
                hotelList.add(hotel);

                hotel = new hotelProfile("Hotel Sungrace", "142,Civil Lines,Roorkee", null, null, null);
                hotelList.add(hotel);

                hotel = new hotelProfile("Hotel Prakash", "21,Civil Lines,Roorkee", null, null, null);
                hotelList.add(hotel);

                hotel = new hotelProfile("Hotel Sungrace", "142,Civil Lines,Roorkee", null, null, null);
                hotelList.add(hotel);

                k2--;
            }

            mAdapterHotel.notifyDataSetChanged();
        }

        private View setUpAcco(View view) {

            View rootView = View.inflate(getContext(), R.layout.hotelcard, null);

            recyclerViewHotel = (RecyclerView) view.findViewById(R.id.hotelListView);

            mAdapterHotel = new hotelAdapter(hotelList, new hotelAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(hotelProfile hotel) {

                }
            });
            RecyclerView.LayoutManager mLayoutManagerHotel = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerViewHotel.setLayoutManager(mLayoutManagerHotel);
            recyclerViewHotel.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerViewHotel.setItemAnimator(new DefaultItemAnimator());
            recyclerViewHotel.setAdapter(mAdapterHotel);

            prepareHotelData();

            return view;
        }

        int k1 = 1;

        private void prepareGuestData() {

            if(k1==1) {

                chiefGuestsProfile guest = new chiefGuestsProfile("Shri Ram Nath Kovind", "Hon'ble President of India", "(7 Oct. 2018 , PG)", null, null, null);
                guestList.add(guest);

                guest = new chiefGuestsProfile("Shri Ram Naik", "Hon'ble Governer of U.P.", "(6 Oct. 2018 , UG)", null, null, null);
                guestList.add(guest);
                k1--;

            }

                mAdapterGuest.notifyDataSetChanged();
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
            return 10;
        }
    }
}
