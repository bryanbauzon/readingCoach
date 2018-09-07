package sebastian.devmonkey.capstoneproject.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import sebastian.devmonkey.capstoneproject.R;
import sebastian.devmonkey.capstoneproject.activity.Journal.JournalActivity;
import sebastian.devmonkey.capstoneproject.fragments.AboutFragment;
import sebastian.devmonkey.capstoneproject.fragments.BookmarksFragment;
import sebastian.devmonkey.capstoneproject.fragments.HelpFragment;
import sebastian.devmonkey.capstoneproject.fragments.HomeFragment;
import sebastian.devmonkey.capstoneproject.fragments.ReadingPlansFragment;
import sebastian.devmonkey.capstoneproject.fragments.SettingsFragment;
import sebastian.devmonkey.capstoneproject.fragments.TerminologiesFragment;


public class MainActivity extends AppCompatActivity implements
        AboutFragment.OnFragmentInteractionListener, BookmarksFragment.OnFragmentInteractionListener
, ReadingPlansFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener
, HomeFragment.OnFragmentInteractionListener, TerminologiesFragment.OnFragmentInteractionListener
        , HelpFragment.OnFragmentInteractionListener{

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private TextView txtName;
    private Toolbar toolbar;


    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_READING_PLANS = "reading_plans";
    private static final String TAG_BOOKMARKS = "bookmarks";
    private static final String TAG_JOURNAL = "journal";
    private static final String TAG_ABOUT = "about";
    private static final String TAG_TERMINOLOGIES = "terminologies";
    private static final String TAG_SETTINGS = "settings";
    private static final String TAG_HELP = "help";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;

    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Navigation view header
        navHeader = navigationView.getHeaderView(0);
       txtName = (TextView) navHeader.findViewById(R.id.name);
        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }


    /***
     * Load navigation menu header information
     * like background image, profile image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
       txtName.setText("BRAINIAC");

    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // show or hide the fab button
            //toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {

            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;

            case 1:
                // reading fragments
                ReadingPlansFragment readingPlansFragment = new ReadingPlansFragment();
                return readingPlansFragment;
            case 2:
                // bookmarks
                BookmarksFragment bookmarksFragment = new BookmarksFragment();
                return bookmarksFragment;

            case 3:
                // terminologiews fragment
                TerminologiesFragment terminologiesFragment = new TerminologiesFragment();
                return terminologiesFragment;

            case 5:
                // about fragment
                AboutFragment aboutFragment = new AboutFragment();
                return aboutFragment;

            case 6:
                HelpFragment helpFragment = new HelpFragment();
                return helpFragment;

            case 7:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;


            default:
                return new ReadingPlansFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;

                    case R.id.home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;

                    case R.id.reading_plans:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_READING_PLANS;
                        break;

                    case R.id.bookmarks:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_BOOKMARKS;
                        break;


                    case R.id.terminologies:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_TERMINOLOGIES;
                        break;

                    case R.id.about:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_ABOUT;
                        break;

                    case R.id.help:
                        navItemIndex = 6;
                        CURRENT_TAG = TAG_HELP;
                        break;

                    case R.id.nav_settings:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;


                    case R.id.journal:
                        startActivity(new Intent(MainActivity.this, JournalActivity.class));
                        finish();
                        return true;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG =  TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        if (navItemIndex == 0) {
            DoubleClickBack();
        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    private void DoubleClickBack() {

        //Custom Dialog
        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        final View confirmationMessageView = factory.inflate(R.layout.customized_confirmation_message,null);
        final AlertDialog confirmationDialog = new AlertDialog.Builder(MainActivity.this).create();
        final Button yes = (Button)confirmationMessageView.findViewById(R.id.btnYes);
        final Button no = (Button)confirmationMessageView.findViewById(R.id.btnNo);
        confirmationDialog.setView(confirmationMessageView);
        confirmationDialog.show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               System.exit(0);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmationDialog.dismiss();
            }
        });

        //tl
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
