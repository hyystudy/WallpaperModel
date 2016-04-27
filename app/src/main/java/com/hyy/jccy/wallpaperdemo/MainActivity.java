package com.hyy.jccy.wallpaperdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hyy.jccy.wallpaperdemo.fragment.AppFragment;
import com.hyy.jccy.wallpaperdemo.fragment.FavoriteFragment;
import com.hyy.jccy.wallpaperdemo.fragment.HistoryFragment;
import com.hyy.jccy.wallpaperdemo.fragment.LocalFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TOOLBAR_TITLE_APP = "App";
    private static final String TOOLBAR_TITLE_FAVORITE = "Favorite";
    private static final String TOOLBAR_TITLE_HISTORY = "History";
    private static final String TOOLBAR_TITLE_LOCAL = "Local";
    private Menu mNavMenu;
    private ArrayList<String> fragments;
    private AppFragment mAppFragment;
    private FavoriteFragment mFavoriteFragment;
    private HistoryFragment mHistoryFragment;
    private LocalFragment mLocalFragment;
    private Toolbar mToolbar;
    private TabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mNavMenu = navigationView.getMenu();
        mTabs = (TabLayout) findViewById(R.id.tabs);

        fragments = new ArrayList<>();
      /*  mAppFragment =  AppFragment.newInstance(mTabs);
        getSupportFragmentManager().beginTransaction().replace(R.id.root_view,mAppFragment,"App").commit();*/
        turnToFragmentByTag(TOOLBAR_TITLE_APP);
        addFragmentToList("App");
        mNavMenu.getItem(0).setChecked(true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fragments.size() >= 2){
            turnToFragmentByTag(fragments.get(fragments.size() - 2));
            fragments.remove(fragments.size() - 2);
        }else {
            super.onBackPressed();
        }
    }

    private void turnToFragmentByTag(String tag) {
        getSupportActionBar().setTitle(tag);
        switch (tag){
            case TOOLBAR_TITLE_APP:
                if (mAppFragment == null){
                    mAppFragment = AppFragment.newInstance(mTabs);
                }
                mTabs.setVisibility(View.VISIBLE);
                mNavMenu.getItem(0).setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.root_view,mAppFragment,
                        TOOLBAR_TITLE_APP).commit();
                break;
            case TOOLBAR_TITLE_FAVORITE:
                if (mFavoriteFragment == null) {
                    mFavoriteFragment = new FavoriteFragment();
                }
                mTabs.setVisibility(View.GONE);
                mNavMenu.getItem(1).setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.root_view,mFavoriteFragment,
                        TOOLBAR_TITLE_FAVORITE).commit();
                break;
            case TOOLBAR_TITLE_HISTORY:
                if (mHistoryFragment == null){
                    mHistoryFragment = new HistoryFragment();
                }
                mTabs.setVisibility(View.GONE);
                mNavMenu.getItem(2).setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.root_view,mHistoryFragment,
                        TOOLBAR_TITLE_HISTORY).commit();
                break;
            case TOOLBAR_TITLE_LOCAL:
                if (mLocalFragment == null){
                    mLocalFragment = new LocalFragment();
                }
                mTabs.setVisibility(View.GONE);
                mNavMenu.getItem(3).setChecked(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.root_view,mLocalFragment,
                        TOOLBAR_TITLE_LOCAL).commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_app:
                turnToFragmentByTag(TOOLBAR_TITLE_APP);
                addFragmentToList(TOOLBAR_TITLE_APP);
                break;
            case R.id.nav_favorite:
                //mFragmentTransaction.
                turnToFragmentByTag(TOOLBAR_TITLE_FAVORITE);
                addFragmentToList(TOOLBAR_TITLE_FAVORITE);
                break;
            case R.id.nav_history:
                turnToFragmentByTag(TOOLBAR_TITLE_HISTORY);
                addFragmentToList(TOOLBAR_TITLE_HISTORY);
                break;
            case R.id.nav_local:
                turnToFragmentByTag(TOOLBAR_TITLE_LOCAL);
                addFragmentToList(TOOLBAR_TITLE_LOCAL);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addFragmentToList(String app) {
        if (!fragments.contains(app)){
            fragments.add(app);
        }else {
            fragments.remove(fragments.indexOf(app));
            fragments.add(app);
        }
        Log.e("xxxx",fragments.toString());
    }
}
