package ac.in.iitr.mdg.convocation;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ac.in.iitr.mdg.convocation.adapters.ChiefGuestAdapter;
import ac.in.iitr.mdg.convocation.adapters.ContactAdapter;
import ac.in.iitr.mdg.convocation.adapters.DegreeAdapter;
import ac.in.iitr.mdg.convocation.adapters.GalleryAdapter;
import ac.in.iitr.mdg.convocation.adapters.HotelAdapter;
import ac.in.iitr.mdg.convocation.adapters.MedalAdapter;
import ac.in.iitr.mdg.convocation.adapters.ScheduleAdapter;
import ac.in.iitr.mdg.convocation.network.ApiClient;
import ac.in.iitr.mdg.convocation.network.ConvoApi;
import ac.in.iitr.mdg.convocation.responsemodels.ChiefGuestProfile;
import ac.in.iitr.mdg.convocation.responsemodels.Contact;
import ac.in.iitr.mdg.convocation.responsemodels.ContactCard;
import ac.in.iitr.mdg.convocation.responsemodels.DegreeCard;
import ac.in.iitr.mdg.convocation.responsemodels.HotelProfile;
import ac.in.iitr.mdg.convocation.responsemodels.MedalHolderModel;
import ac.in.iitr.mdg.convocation.responsemodels.MedalModel;
import ac.in.iitr.mdg.convocation.responsemodels.ScheduleEventModel;
import ac.in.iitr.mdg.convocation.responsemodels.ScheduleModel;
import ac.in.iitr.mdg.convocation.responsemodels.SpacesItemDecoration;
import ac.in.iitr.mdg.convocation.viewmodels.ScheduleViewModel;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String CONVO_CHANNELI_OAUTH_URL = "https://channeli.in/oauth/?client_id=248033f9cc5a67b44777&redirect_url=convoiitr://convo.sdslabs.co.in/";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton profileButton = findViewById(R.id.profile_open_icon);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profileIntent);
            }
        });

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(10);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";
        List<ChiefGuestProfile> guestList = new ArrayList<>();
        List<HotelProfile> hotelList = new ArrayList<>();
        RecyclerView recyclerViewGuest, recyclerViewHotel;
        ChiefGuestAdapter mAdapterGuest;
        HotelAdapter mAdapterHotel;
        Context activityContext;
        int k1 = 1;
        int k2 = 1;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            this.activityContext = context;
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (getArguments() == null || !getArguments().containsKey(ARG_SECTION_NUMBER)) {
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                TextView textView = rootView.findViewById(R.id.section_label);
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                return rootView;
            }
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    View rootView1 = inflater.inflate(R.layout.fragment_home, container, false);
                    Button registerNowButton = rootView1.findViewById(R.id.button_register_now);
                    registerNowButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setupChromeCustomTab();
                        }
                    });

                    return setUpHome(rootView1);

                case 2:
                    return inflater.inflate(R.layout.fragment_dresscode, container, false);

                case 3:
                    View rootView3 = inflater.inflate(R.layout.fragment_accomodation, container, false);
                    return setUpAcco(rootView3);

                case 4:

                    View rootView4 = inflater.inflate(R.layout.fragment_schedule, container, false);

                    final ProgressBar progressBar = rootView4.findViewById(R.id.progress_bar);
                    progressBar.setVisibility(View.VISIBLE);

                    final RecyclerView recyclerView = rootView4.findViewById(R.id.schedule_recyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setVisibility(View.GONE);

                    ApiClient.getClientWithoutAuth(activityContext)
                            .create(ConvoApi.class)
                            .getSchedule()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ArrayList<ScheduleModel>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(ArrayList<ScheduleModel> scheduleModels) {
                                    List<ScheduleViewModel> list = new ArrayList<>();
                                    ScheduleAdapter textAdapter = new ScheduleAdapter(activityContext, list);
                                    recyclerView.setAdapter(textAdapter);
                                    for (ScheduleModel model : scheduleModels) {

                                        list.add(new ScheduleViewModel(ScheduleViewModel.TYPE_DATE, model.getDate(), null));

                                        for (ScheduleEventModel eventModel : model.getScheduleEventModels()) {
                                            list.add(new ScheduleViewModel(ScheduleViewModel.TYPE_SCHEDULE, null, eventModel));
                                        }

                                    }
                                    textAdapter.notifyDataSetChanged();
                                    recyclerView.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

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
                            List<DegreeCard> list = new ArrayList<>();
                            DegreeAdapter textAdapter = new DegreeAdapter(getContext(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(textAdapter);

                            for (int i = 0; i < 10; i++) {
                                list.add(new DegreeCard("Chemical Engineering", "Total Students : 120"));
                            }

                            textAdapter.notifyDataSetChanged();
                        }
                    });

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
                            List<DegreeCard> list = new ArrayList<>();
                            DegreeAdapter textAdapter = new DegreeAdapter(getContext(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(textAdapter);

                            for (int i = 0; i < 10; i++) {
                                list.add(new DegreeCard("Electrical Engineering", "Total Students : 120"));
                            }

                            textAdapter.notifyDataSetChanged();
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
                            List<DegreeCard> list = new ArrayList<>();
                            DegreeAdapter textAdapter = new DegreeAdapter(getContext(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(textAdapter);

                            for (int i = 0; i < 10; i++) {
                                list.add(new DegreeCard("Civil Engineering", "Total Students : 120"));
                            }

                            textAdapter.notifyDataSetChanged();
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
                            RecyclerView recyclerView = rootView5.findViewById(R.id.degree_recyclerView);
                            List<DegreeCard> list = new ArrayList<>();
                            DegreeAdapter textAdapter = new DegreeAdapter(getContext(), list);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(textAdapter);

                            for (int i = 0; i < 10; i++) {
                                list.add(new DegreeCard("CSE Engineering", "Total Students : 120"));
                            }

                            textAdapter.notifyDataSetChanged();
                        }
                    });

                    bTech.performClick();

                    return rootView5;

                case 6:
                    View rootView6 = inflater.inflate(R.layout.fragment_medals, container, false);

                    RecyclerView medalView = rootView6.findViewById(R.id.medals_recycler_view);
                    medalView.setLayoutManager(new LinearLayoutManager(getContext()));
                    ArrayList<MedalModel> medalArray = new ArrayList<>();
                    MedalAdapter medalAdapter = new MedalAdapter(medalArray);
                    medalView.setAdapter(medalAdapter);

                    Bitmap bitmap2 = Bitmap.createBitmap(120, 120, Bitmap.Config.ARGB_8888);
                    Canvas c = new Canvas(bitmap2);
                    c.drawColor(Color.LTGRAY);

                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY, "President's Gold Medal"));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY, "Director's Gold Medal"));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_CATEGORY, "Institute Gold Medal"));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));
                    medalArray.add(new MedalModel(MedalModel.TYPE_HOLDER, new MedalHolderModel(bitmap2, "Karthik Iyer", "Chemical Engineering", "17112036")));

                    medalAdapter.notifyDataSetChanged();

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
                    return inflater.inflate(R.layout.fragment_livecast, container, false);

                case 9:
                    return inflater.inflate(R.layout.fragment_instructions, container, false);

                case 10:
                    View rootView10 = inflater.inflate(R.layout.fragment_contact, container, false);

                    RecyclerView contactView = rootView10.findViewById(R.id.contact_recyclerView);
                    contactView.setLayoutManager(new LinearLayoutManager(getContext()));
                    List<Contact> contactList = new ArrayList<>();
                    ContactAdapter contactAdapter = new ContactAdapter(getContext(), contactList);

                    contactList.add(new Contact(Contact.TYPE_CATEGORY, "Medical Section"));
                    contactList.add(new Contact(Contact.TYPE_CONTACT, new ContactCard("Jayant Mishra", "Mental Patient", "9876543210")));
                    contactList.add(new Contact(Contact.TYPE_CONTACT, new ContactCard("Anchit Shukla", "Liver Patient", "8976543210")));
                    contactList.add(new Contact(Contact.TYPE_CATEGORY, "Technical Section"));
                    contactList.add(new Contact(Contact.TYPE_CONTACT, new ContactCard("Lakshya Kumawat", "Pro Officials", "7896543210")));
                    contactList.add(new Contact(Contact.TYPE_CONTACT, new ContactCard("Prasannadeep Das", "Pro Officials", "8796543210")));

                    contactView.setAdapter(contactAdapter);

                    return rootView10;

                default:
                    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    TextView textView = rootView.findViewById(R.id.section_label);
                    textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                    return rootView;

            }

        }

        private View setUpHome(View view) {

            ImageView fblink = view.findViewById(R.id.fblink);
            ImageView twlink = view.findViewById(R.id.twlink);
            ImageView ytlink = view.findViewById(R.id.ytlink);
            ImageView inlink = view.findViewById(R.id.inlink);

            recyclerViewGuest = view.findViewById(R.id.chief_guest_recycler);

            mAdapterGuest = new ChiefGuestAdapter(guestList, new ChiefGuestAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ChiefGuestProfile guest) {
                    Intent intent = new Intent(getActivity(), ChiefGuestDescriptionActivity.class);
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

        private void prepareGuestData() {
            if (k1 == 1) {
                ChiefGuestProfile guest = new ChiefGuestProfile("Shri Ram Nath Kovind", "Hon'ble President of India", "(7 Oct. 2018 , PG)", null, null, null);
                guestList.add(guest);

                guest = new ChiefGuestProfile("Shri Ram Naik", "Hon'ble Governer of U.P.", "(6 Oct. 2018 , UG)", null, null, null);
                guestList.add(guest);
                k1--;
            }
            mAdapterGuest.notifyDataSetChanged();
        }

        private View setUpAcco(View view) {

            recyclerViewHotel = view.findViewById(R.id.hotelListView);

            mAdapterHotel = new HotelAdapter(hotelList, new HotelAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(HotelProfile hotel) {

                }
            });
            RecyclerView.LayoutManager mLayoutManagerHotel = new LinearLayoutManager(activityContext);
            recyclerViewHotel.setLayoutManager(mLayoutManagerHotel);
            recyclerViewHotel.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerViewHotel.setItemAnimator(new DefaultItemAnimator());
            recyclerViewHotel.setAdapter(mAdapterHotel);

            prepareHotelData();

            return view;
        }

        private void prepareHotelData() {
            if (k2 == 1) {
                HotelProfile hotel = new HotelProfile("Hotel Prakash", "21,Civil Lines,Roorkee", null, null, null);
                hotelList.add(hotel);

                hotel = new HotelProfile("Hotel Sungrace", "142,Civil Lines,Roorkee", null, null, null);
                hotelList.add(hotel);

                hotel = new HotelProfile("Hotel Prakash", "21,Civil Lines,Roorkee", null, null, null);
                hotelList.add(hotel);

                hotel = new HotelProfile("Hotel Sungrace", "142,Civil Lines,Roorkee", null, null, null);
                hotelList.add(hotel);

                k2--;
            }

            mAdapterHotel.notifyDataSetChanged();
        }

        public void setupChromeCustomTab() {
            final CustomTabsIntent chromeIntent = new CustomTabsIntent.Builder().build();
            chromeIntent.launchUrl(activityContext, Uri.parse(CONVO_CHANNELI_OAUTH_URL));
        }

    }

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
