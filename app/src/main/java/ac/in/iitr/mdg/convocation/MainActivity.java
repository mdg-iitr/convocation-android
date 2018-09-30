package ac.in.iitr.mdg.convocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ac.in.iitr.mdg.convocation.adapters.ChiefGuestAdapter;
import ac.in.iitr.mdg.convocation.adapters.DegreeAdapter;
import ac.in.iitr.mdg.convocation.adapters.HotelAdapter;
import ac.in.iitr.mdg.convocation.adapters.MedalAdapter;
import ac.in.iitr.mdg.convocation.adapters.ScheduleAdapter;
import ac.in.iitr.mdg.convocation.network.ApiClient;
import ac.in.iitr.mdg.convocation.network.ConvoApi;
import ac.in.iitr.mdg.convocation.responsemodels.ChiefGuestResponse;
import ac.in.iitr.mdg.convocation.responsemodels.DegreeBranchModel;
import ac.in.iitr.mdg.convocation.responsemodels.DegreeResponseModel;
import ac.in.iitr.mdg.convocation.responsemodels.HotelProfile;
import ac.in.iitr.mdg.convocation.responsemodels.MedalTypeModel;
import ac.in.iitr.mdg.convocation.responsemodels.MedalsResponseModel;
import ac.in.iitr.mdg.convocation.responsemodels.ScheduleEventModel;
import ac.in.iitr.mdg.convocation.responsemodels.ScheduleModel;
import ac.in.iitr.mdg.convocation.responsemodels.UserResponseModel;
import ac.in.iitr.mdg.convocation.viewmodels.MedalViewModel;
import ac.in.iitr.mdg.convocation.viewmodels.ScheduleViewModel;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String CONVO_CHANNELI_OAUTH_URL = "http://people.iitr.ernet.in/oauth/?client_id=248033f9cc5a67b44777&redirect_url=convoiitr://convo.sdslabs.co.in/";

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    private Button registerButton;

    private boolean isRegisteredFromSharedPrefs = false;

    private ImageView notificationButton;

    private CircleImageView profileButton;

    private String profileImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileButton = findViewById(R.id.profile_open_icon);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
            }
        });

        notificationButton = findViewById(R.id.notification_icon);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(10);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateRegisterAndProfileButtonVisibility();
                if (position == 1 && PlaceholderFragment.isImageExpanded) {
                    PlaceholderFragment.collapseImageView();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        registerButton = findViewById(R.id.button_register_now);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupChromeCustomTab();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        isRegisteredFromSharedPrefs = sharedPreferences.getBoolean(getString(R.string.is_registered_identifier), false);
        profileImageUrl = sharedPreferences.getString(getString(R.string.user_profile_image_identifier), "");

        if (profileImageUrl.isEmpty()) {
            Picasso.get().load("null").placeholder(R.drawable.image_placeholder).into(profileButton);
        } else {
            Picasso.get().load(profileImageUrl).placeholder(R.drawable.image_placeholder).into(profileButton);
        }

        updateRegisterAndProfileButtonVisibility();
    }

    public void updateRegisterAndProfileButtonVisibility() {
        if (mViewPager.getCurrentItem() != 0) {
            registerButton.setVisibility(View.GONE);
        } else {
            if (isRegisteredFromSharedPrefs) {
                registerButton.setVisibility(View.GONE);
            } else {
                registerButton.setVisibility(View.VISIBLE);
            }
        }

        if (isRegisteredFromSharedPrefs) {
            profileButton.setVisibility(View.VISIBLE);
        } else {
            profileButton.setVisibility(View.GONE);
        }

    }

    public void setupChromeCustomTab() {
        final CustomTabsIntent chromeIntent = new CustomTabsIntent.Builder().build();
        chromeIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        chromeIntent.intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        chromeIntent.launchUrl(this, Uri.parse(CONVO_CHANNELI_OAUTH_URL));
    }

    public static class PlaceholderFragment extends Fragment {

        public static boolean isImageExpanded = false;

        private static FrameLayout expandedWrapper;

        private static final String ARG_SECTION_NUMBER = "section_number";
        List<HotelProfile> hotelList = new ArrayList<>();
        RecyclerView recyclerViewHotel;
        HotelAdapter mAdapterHotel;
        Context activityContext;

        // Medals Fragment Attributes
        private int medalLevelSelected = 0;
        private ArrayList<MedalsResponseModel> storedMedalsResponse = new ArrayList<>();
        private RecyclerView medalRecycler;
        private MedalAdapter medalAdapter;
        private ArrayList<MedalViewModel> medalsAdapterArray = new ArrayList<>();

        private int degreeNumberSelected = 0;
        private ArrayList<DegreeResponseModel> storedDegreeResponse = new ArrayList<>();
        private RecyclerView degreeRecycler;
        private DegreeAdapter degreeAdapter;
        private ArrayList<DegreeBranchModel> degreeAdapterArray = new ArrayList<>();

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
                    View view = inflater.inflate(R.layout.fragment_home, container, false);

                    final ProgressBar progressBarHome = view.findViewById(R.id.home_progress_bar);
                    progressBarHome.setVisibility(View.VISIBLE);

                    final LinearLayout homeWrapper = view.findViewById(R.id.home_wrapper);
                    homeWrapper.setVisibility(View.GONE);

                    ImageView fblink = view.findViewById(R.id.fblink);
                    fblink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("https://www.facebook.com/IITRoorkee.ICC/?ref=bookmarks"));
                            startActivity(intent);
                        }
                    });

                    ImageView fblink2 = view.findViewById(R.id.fblink_2);
                    fblink2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("https://www.facebook.com/directoriitr/?ref=bookmarks"));
                            startActivity(intent);
                        }
                    });

                    ImageView twlink = view.findViewById(R.id.twlink);
                    twlink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("https://twitter.com/Director_IITR"));
                            startActivity(intent);
                        }
                    });

                    ImageView twlink2 = view.findViewById(R.id.twlink_2);
                    twlink2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("https://twitter.com/iitr247667"));
                            startActivity(intent);
                        }
                    });

                    ImageView inlink = view.findViewById(R.id.inlink);
                    inlink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("https://www.linkedin.com/school/indian-institute-of-technology-roorkee/"));
                            startActivity(intent);
                        }
                    });

                    RecyclerView recyclerViewGuest = view.findViewById(R.id.chief_guest_recycler);
                    RecyclerView.LayoutManager mLayoutManagerGuest = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerViewGuest.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerViewGuest.setItemAnimator(new DefaultItemAnimator());

                    final ArrayList<ChiefGuestResponse> guestList = new ArrayList<>();
                    final ChiefGuestAdapter mAdapterGuest = new ChiefGuestAdapter(guestList, new ChiefGuestAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ChiefGuestResponse guest) {
                            // TODO : Open Bio Link
                        }
                    });
                    recyclerViewGuest.setAdapter(mAdapterGuest);

                    ApiClient.getClientWithoutAuth(activityContext).create(ConvoApi.class)
                            .getChiefGuests()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ArrayList<ChiefGuestResponse>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(ArrayList<ChiefGuestResponse> chiefGuestResponses) {
                                    progressBarHome.setVisibility(View.GONE);
                                    homeWrapper.setVisibility(View.VISIBLE);
                                    guestList.clear();
                                    guestList.addAll(chiefGuestResponses);
                                    mAdapterGuest.notifyDataSetChanged();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    progressBarHome.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                    return view;

                case 2:
                    View dressCodeView = inflater.inflate(R.layout.fragment_dresscode, container, false);

                    final ImageView expandedImageView = dressCodeView.findViewById(R.id.expanded_image);

                    expandedWrapper = dressCodeView.findViewById(R.id.expanded_image_wrapper);
                    expandedWrapper.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            expandedWrapper.setVisibility(View.GONE);
                            isImageExpanded = false;
                        }
                    });

                    ImageView femaleDressImage = dressCodeView.findViewById(R.id.female_dress_image);
                    femaleDressImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            expandedImageView.setImageResource(R.drawable.female_dress);
                            expandedWrapper.setVisibility(View.VISIBLE);
                            isImageExpanded = true;
                        }
                    });

                    ImageView femaleDressImage2 = dressCodeView.findViewById(R.id.female_dress_image_2);
                    femaleDressImage2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            expandedImageView.setImageResource(R.drawable.female_dress_1);
                            expandedWrapper.setVisibility(View.VISIBLE);
                            isImageExpanded = true;
                        }
                    });

                    ImageView maleDressImage = dressCodeView.findViewById(R.id.male_dress_image);
                    maleDressImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            expandedImageView.setImageResource(R.drawable.male_dress);
                            expandedWrapper.setVisibility(View.VISIBLE);
                            isImageExpanded = true;
                        }
                    });

                    return dressCodeView;

                case 3:
                    View rootView3 = inflater.inflate(R.layout.fragment_accomodation, container, false);

                    Button downloadButton = rootView3.findViewById(R.id.download_button);
                    downloadButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iitr.ac.in/institute/uploads/File/convo2018/Hotels.pdf"));
                            startActivity(browserIntent);
                        }
                    });

                    return setUpAcco(rootView3);

                case 4:

                    View rootView4 = inflater.inflate(R.layout.fragment_schedule, container, false);

                    final ProgressBar progressBarSchedule = rootView4.findViewById(R.id.progress_bar);
                    progressBarSchedule.setVisibility(View.VISIBLE);

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
                                    progressBarSchedule.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    progressBarSchedule.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                    return rootView4;

                case 5:

                    View rootViewMin = inflater.inflate(R.layout.fragment_min, container, false);

                    TextView minLink = rootViewMin.findViewById(R.id.min_link);
                    minLink.setPaintFlags(minLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                    minLink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                            browserIntent.setData(Uri.parse("https://drive.google.com/file/d/1hDVUvi00vDK3uHeetiGxBkuIQwQzpNeE/view?usp=sharing"));
                            startActivity(browserIntent);
                        }
                    });

                    return rootViewMin;

                case 6:
                    final View rootView5 = inflater.inflate(R.layout.fragment_degrees, container, false);
                    final Button bTech = rootView5.findViewById(R.id.Degrees_button_bTech);
                    final Button mTech = rootView5.findViewById(R.id.Degrees_button_mTech);
                    final Button phD = rootView5.findViewById(R.id.Degrees_button_phD);
                    final Button management = rootView5.findViewById(R.id.Degrees_button_Management);

                    final ProgressBar progressBarDegree = rootView5.findViewById(R.id.degree_progress_bar);
                    progressBarDegree.setVisibility(View.VISIBLE);

                    final LinearLayout degreeLabelWrapper = rootView5.findViewById(R.id.degree_label_wrapper);
                    degreeLabelWrapper.setVisibility(View.GONE);

                    final FrameLayout degreeRecyclerWrapper = rootView5.findViewById(R.id.degree_recycler_wrapper);
                    degreeRecyclerWrapper.setVisibility(View.GONE);

                    degreeRecycler = rootView5.findViewById(R.id.degree_recyclerView);
                    degreeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

                    degreeAdapterArray = new ArrayList<>();
                    degreeAdapter = new DegreeAdapter(activityContext, degreeAdapterArray);

                    degreeRecycler.setAdapter(degreeAdapter);

                    ApiClient.getClientWithoutAuth(activityContext).create(ConvoApi.class)
                            .getDegrees()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ArrayList<DegreeResponseModel>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(ArrayList<DegreeResponseModel> degreeResponseModels) {

                                    progressBarDegree.setVisibility(View.GONE);
                                    degreeLabelWrapper.setVisibility(View.VISIBLE);
                                    degreeRecyclerWrapper.setVisibility(View.VISIBLE);

                                    storedDegreeResponse.clear();
                                    storedDegreeResponse.addAll(degreeResponseModels);

                                    setupDegreeRecycler(storedDegreeResponse);

                                }

                                @Override
                                public void onError(Throwable e) {
                                    progressBarDegree.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                    bTech.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                            degreeNumberSelected = 1;
                            bTech.setBackground(getResources().getDrawable(R.drawable.gradient));
                            bTech.setTextColor(getResources().getColor(R.color.white));
                            mTech.setBackgroundColor(getResources().getColor(R.color.white));
                            mTech.setTextColor(getResources().getColor(R.color.textColor));
                            phD.setBackgroundColor(getResources().getColor(R.color.white));
                            phD.setTextColor(getResources().getColor(R.color.textColor));
                            management.setBackgroundColor(getResources().getColor(R.color.white));
                            management.setTextColor(getResources().getColor(R.color.textColor));

                            setupDegreeRecycler(storedDegreeResponse);
                        }
                    });

                    mTech.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                            degreeNumberSelected = 2;
                            mTech.setBackground(getResources().getDrawable(R.drawable.gradient));
                            mTech.setTextColor(getResources().getColor(R.color.white));
                            bTech.setBackgroundColor(getResources().getColor(R.color.white));
                            bTech.setTextColor(getResources().getColor(R.color.textColor));
                            phD.setBackgroundColor(getResources().getColor(R.color.white));
                            phD.setTextColor(getResources().getColor(R.color.textColor));
                            management.setBackgroundColor(getResources().getColor(R.color.white));
                            management.setTextColor(getResources().getColor(R.color.textColor));

                            setupDegreeRecycler(storedDegreeResponse);
                        }
                    });

                    phD.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                            degreeNumberSelected = 3;
                            phD.setBackground(getResources().getDrawable(R.drawable.gradient));
                            phD.setTextColor(getResources().getColor(R.color.white));
                            mTech.setBackgroundColor(getResources().getColor(R.color.white));
                            mTech.setTextColor(getResources().getColor(R.color.textColor));
                            bTech.setBackgroundColor(getResources().getColor(R.color.white));
                            bTech.setTextColor(getResources().getColor(R.color.textColor));
                            management.setBackgroundColor(getResources().getColor(R.color.white));
                            management.setTextColor(getResources().getColor(R.color.textColor));

                            setupDegreeRecycler(storedDegreeResponse);
                        }
                    });

                    management.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onClick(View v) {
                            degreeNumberSelected = 4;
                            management.setBackground(getResources().getDrawable(R.drawable.gradient));
                            management.setTextColor(getResources().getColor(R.color.white));
                            mTech.setBackgroundColor(getResources().getColor(R.color.white));
                            mTech.setTextColor(getResources().getColor(R.color.textColor));
                            phD.setBackgroundColor(getResources().getColor(R.color.white));
                            phD.setTextColor(getResources().getColor(R.color.textColor));
                            bTech.setBackgroundColor(getResources().getColor(R.color.white));
                            bTech.setTextColor(getResources().getColor(R.color.textColor));

                            setupDegreeRecycler(storedDegreeResponse);
                        }
                    });

                    bTech.performClick();

                    return rootView5;

                case 7:
                    View rootView6 = inflater.inflate(R.layout.fragment_medals, container, false);

                    medalRecycler = rootView6.findViewById(R.id.medals_recycler_view);
                    medalRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    medalRecycler.setItemAnimator(new DefaultItemAnimator());

                    medalsAdapterArray = new ArrayList<>();
                    medalAdapter = new MedalAdapter(medalsAdapterArray);
                    medalRecycler.setAdapter(medalAdapter);

                    ApiClient.getClientWithoutAuth(activityContext).create(ConvoApi.class)
                            .getMedals()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ArrayList<MedalsResponseModel>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(ArrayList<MedalsResponseModel> medalsResponseModels) {

                                    storedMedalsResponse.clear();
                                    storedMedalsResponse.addAll(medalsResponseModels);

                                    setupMedalRecycler(storedMedalsResponse);

                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                    final Button instiLevelButton = rootView6.findViewById(R.id.instiLevel);
                    final Button deptLevelButton = rootView6.findViewById(R.id.deptLevel);
                    instiLevelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            medalLevelSelected = 1;
                            instiLevelButton.setBackground(getResources().getDrawable(R.drawable.gradient));
                            deptLevelButton.setBackground(getResources().getDrawable(R.drawable.white_card));
                            instiLevelButton.setTextColor(Color.parseColor("#ffffff"));
                            deptLevelButton.setTextColor(Color.parseColor("#444444"));
                            setupMedalRecycler(storedMedalsResponse);
                        }
                    });
                    deptLevelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            medalLevelSelected = 2;
                            instiLevelButton.setBackground(getResources().getDrawable(R.drawable.white_card));
                            deptLevelButton.setBackground(getResources().getDrawable(R.drawable.gradient));
                            deptLevelButton.setTextColor(Color.parseColor("#ffffff"));
                            instiLevelButton.setTextColor(Color.parseColor("#444444"));
                            setupMedalRecycler(storedMedalsResponse);
                        }
                    });

                    instiLevelButton.performClick();

                    return rootView6;

                case 8:
                    return inflater.inflate(R.layout.fragment_livecast, container, false);

                case 9:
                    View viewInsts = inflater.inflate(R.layout.fragment_instructions, container, false);

//                    TextView feesBody = viewInsts.findViewById(R.id.fees_body);
//                    feesBody.setText(Html.fromHtml(getString(R.string.instructions_fees_body)));

//                    String text = getString(R.string.instructions_details_note_text);
//                    SpannableString ss = new SpannableString(text);
//                    ClickableSpan clickableSpan = new ClickableSpan() {
//                        @Override
//                        public void onClick(View textView) {
//                            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
//                            browserIntent.setData(Uri.parse("https://www.iitr.ac.in/convocation2018/"));
//                            startActivity(browserIntent);
//                        }
//
//                        @Override
//                        public void updateDrawState(TextPaint ds) {
//                            super.updateDrawState(ds);
//                            ds.setUnderlineText(false);
//                        }
//                    };
//                    ss.setSpan(clickableSpan,
//                            text.indexOf("Convocation"),
//                            text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

//                    TextView detailsNote = viewInsts.findViewById(R.id.details_note);
//                    detailsNote.setText(ss);
//                    detailsNote.setMovementMethod(LinkMovementMethod.getInstance());
//                    detailsNote.setHighlightColor(Color.TRANSPARENT);

                    return viewInsts;

                case 10:
                    View rootView10 = inflater.inflate(R.layout.fragment_contact, container, false);

//                    RecyclerView contactView = rootView10.findViewById(R.id.contact_recyclerView);
//                    contactView.setLayoutManager(new LinearLayoutManager(getContext()));
//                    List<Contact> contactList = new ArrayList<>();
//                    ContactAdapter contactAdapter = new ContactAdapter(getContext(), contactList);
//
//                    contactList.add(new Contact(Contact.TYPE_CATEGORY, "Medical Section"));
//                    contactList.add(new Contact(Contact.TYPE_CONTACT, new ContactCard("Jayant Mishra", "Mental Patient", "9876543210")));
//                    contactList.add(new Contact(Contact.TYPE_CONTACT, new ContactCard("Anchit Shukla", "Liver Patient", "8976543210")));
//                    contactList.add(new Contact(Contact.TYPE_CATEGORY, "Technical Section"));
//                    contactList.add(new Contact(Contact.TYPE_CONTACT, new ContactCard("Lakshya Kumawat", "Pro Officials", "7896543210")));
//                    contactList.add(new Contact(Contact.TYPE_CONTACT, new ContactCard("Prasannadeep Das", "Pro Officials", "8796543210")));
//
//                    contactView.setAdapter(contactAdapter);

                    final TextView email1 = rootView10.findViewById(R.id.email_actiir);
                    email1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Uri data = Uri.parse("mailto:?to=" + email1.getText().toString());
                            intent.setData(data);
                            startActivity(intent);
                        }
                    });
                    final TextView email2 = rootView10.findViewById(R.id.email_dracademic);
                    email2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            Uri data = Uri.parse("mailto:?to=" + email2.getText().toString());
                            intent.setData(data);
                            startActivity(intent);
                        }
                    });
                    final TextView phone = rootView10.findViewById(R.id.contact_dracademic);
                    phone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+911332285098"));
                            startActivity(intent);
                        }
                    });

                    return rootView10;

                default:
                    View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                    TextView textView = rootView.findViewById(R.id.section_label);
                    textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                    return rootView;

            }

        }

        private void makeLinks(TextView textView, String[] links, ClickableSpan[] clickableSpans) {
            SpannableString spannableString = new SpannableString(textView.getText());
            for (int i = 0; i < links.length; i++) {
                ClickableSpan clickableSpan = clickableSpans[i];
                String link = links[i];

                int startIndexOfLink = textView.getText().toString().indexOf(link);
                spannableString.setSpan(clickableSpan, startIndexOfLink,
                        startIndexOfLink + link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            textView.setHighlightColor(
                    Color.TRANSPARENT); // prevent TextView change background when highlight
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        }

        private View setUpAcco(View view) {

            recyclerViewHotel = view.findViewById(R.id.hotelListView);

            mAdapterHotel = new HotelAdapter(hotelList, new HotelAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(HotelProfile hotel) {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse(hotel.getMapsUrl()));
                    startActivity(intent);
                }
            });
            RecyclerView.LayoutManager mLayoutManagerHotel = new LinearLayoutManager(activityContext);
            recyclerViewHotel.setLayoutManager(mLayoutManagerHotel);
            recyclerViewHotel.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerViewHotel.setItemAnimator(new DefaultItemAnimator());
            recyclerViewHotel.setAdapter(mAdapterHotel);

            hotelList.add(new HotelProfile("Hometel (A sarovar Hotel)", R.drawable.hometel, "https://maps.google.com/?cid=3613636995944658866"));
            hotelList.add(new HotelProfile("Ambrosia sarovar Portico", R.drawable.sarovar, "https://maps.google.com/?cid=10044551935308580425"));
//            hotelList.add(new HotelProfile("Motel Polaris", R.drawable.polaris, "https://maps.google.com/?cid=3821078255041093716"));
            hotelList.add(new HotelProfile("Hotel Centrum", R.drawable.centrum, "https://maps.google.com/?cid=17119990003797877505"));
            hotelList.add(new HotelProfile("Hotel Godawari", R.drawable.godawari, "https://maps.google.com/?cid=5376462500066981236"));
            hotelList.add(new HotelProfile("Hotel Center Point", R.drawable.centerpoint, "https://maps.google.com/?cid=7390331712734152861"));
            hotelList.add(new HotelProfile("Hotel Dynasty", R.drawable.dynasty, "https://maps.google.com/?cid=7348437769293138179"));
            hotelList.add(new HotelProfile("Hotel Divine", R.drawable.divine, "https://maps.google.com/?cid=3810706295594368849"));
            hotelList.add(new HotelProfile("Hotel Prakash", R.drawable.prakash, "https://maps.google.com/?cid=11895064998211498514"));
            hotelList.add(new HotelProfile("Royal Palace", R.drawable.rp, "https://maps.google.com/?cid=4582136391688479029"));

            mAdapterHotel.notifyDataSetChanged();

            return view;
        }

        private void setupMedalRecycler(ArrayList<MedalsResponseModel> medalsResponseModels) {

            medalsAdapterArray.clear();

            switch (medalLevelSelected) {
                case 1: {

                    for (MedalsResponseModel responseModel : medalsResponseModels) {

                        if (responseModel.getLevel().equals("Institute Level")) {

                            for (MedalTypeModel typeModel : responseModel.getMedals()) {
                                medalsAdapterArray.add(new MedalViewModel(MedalViewModel.TYPE_CATEGORY, typeModel.getType(), null));
                                for (UserResponseModel userModel : typeModel.getStudents()) {
                                    medalsAdapterArray.add(new MedalViewModel(MedalViewModel.TYPE_HOLDER, null, userModel));
                                }
                            }

                        }

                    }

                    medalAdapter.notifyDataSetChanged();

                    break;
                }
                case 2: {

                    for (MedalsResponseModel responseModel : medalsResponseModels) {

                        if (responseModel.getLevel().equals("Department Level")) {

                            for (MedalTypeModel typeModel : responseModel.getMedals()) {
                                medalsAdapterArray.add(new MedalViewModel(MedalViewModel.TYPE_CATEGORY, typeModel.getType(), null));
                                for (UserResponseModel userModel : typeModel.getStudents()) {
                                    medalsAdapterArray.add(new MedalViewModel(MedalViewModel.TYPE_HOLDER, null, userModel));
                                }
                            }

                        }

                    }

                    medalAdapter.notifyDataSetChanged();

                    break;
                }
                default: {
                    break;
                }
            }

        }

        private void setupDegreeRecycler(ArrayList<DegreeResponseModel> degreeResponseModels) {

            degreeAdapterArray.clear();

            switch (degreeNumberSelected) {
                case 1: {

                    for (DegreeResponseModel responseModel : degreeResponseModels) {
                        if (responseModel.getDegree().equals("undergraduate")) {
                            degreeAdapterArray.addAll(responseModel.getBranches());
                        }
                    }

                    degreeAdapter.notifyDataSetChanged();

                    break;
                }
                case 2: {

                    for (DegreeResponseModel responseModel : degreeResponseModels) {
                        if (responseModel.getDegree().equals("postgraduate")) {
                            degreeAdapterArray.addAll(responseModel.getBranches());
                        }
                    }

                    degreeAdapter.notifyDataSetChanged();

                    break;
                }
                case 3: {

                    for (DegreeResponseModel responseModel : degreeResponseModels) {
                        if (responseModel.getDegree().equals("phd")) {
                            degreeAdapterArray.addAll(responseModel.getBranches());
                        }
                    }

                    degreeAdapter.notifyDataSetChanged();

                    break;
                }
                case 4: {

                    for (DegreeResponseModel responseModel : degreeResponseModels) {
                        if (responseModel.getDegree().equals("management")) {
                            degreeAdapterArray.addAll(responseModel.getBranches());
                        }
                    }

                    degreeAdapter.notifyDataSetChanged();

                    break;
                }
            }

        }

        public static void collapseImageView() {
            expandedWrapper.setVisibility(View.GONE);
            isImageExpanded = false;
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
            return 10;
        }
    }

    @Override
    public void onBackPressed() {
        if (PlaceholderFragment.isImageExpanded) {
            PlaceholderFragment.collapseImageView();
            return;
        }
        super.onBackPressed();
    }
}
