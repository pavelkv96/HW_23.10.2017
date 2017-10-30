package com.github.pavelkv96.hw_23102017;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.github.pavelkv96.hw_23102017.fragments.CameraFragment;
import com.github.pavelkv96.hw_23102017.fragments.GalleryFragment;
import com.github.pavelkv96.hw_23102017.fragments.SlideshowFragment;

import java.util.HashMap;

public class SecondActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CameraFragment cameraFragment;
    private GalleryFragment galleryFragment;
    private SlideshowFragment slideshowFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        HashMap<String, String> hashMapValueEditTexts = (HashMap<String, String>) getIntent().getExtras().get("hash_map_value_edit_texts");
        Log.e("myLogs", hashMapValueEditTexts != null ? hashMapValueEditTexts.get("email") : null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cameraFragment = new CameraFragment();
        galleryFragment = new GalleryFragment();
        slideshowFragment = new SlideshowFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                Intent firstIntent = new Intent(SecondActivity.this, FirstActivity.class);
                startActivity(firstIntent);
                finish();
            }
            break;
            case R.id.my_broadcast: {
                Intent firstIntent = new Intent(SecondActivity.this, MyBroadcastReceiver.class);
                startActivity(firstIntent);
                finish();
            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.nav_camera: {
                    if (!cameraFragment.isAdded()) {
                        fragmentTransaction.add(R.id.container, cameraFragment);
                    }
                    fragmentTransaction.show(cameraFragment);
                    fragmentTransaction.hide(galleryFragment);
                    fragmentTransaction.hide(slideshowFragment);
                }
                break;
                case R.id.nav_gallery: {
                    if (!galleryFragment.isAdded()) {
                        fragmentTransaction.add(R.id.container, galleryFragment);
                    }
                    fragmentTransaction.show(galleryFragment);
                    fragmentTransaction.hide(cameraFragment);
                    fragmentTransaction.hide(slideshowFragment);
                }
                break;
                case R.id.nav_slideshow: {
                    if (!slideshowFragment.isAdded()) {
                        fragmentTransaction.add(R.id.container, slideshowFragment);
                    }
                    fragmentTransaction.show(slideshowFragment);
                    fragmentTransaction.hide(cameraFragment);
                    fragmentTransaction.hide(galleryFragment);
                }
                break;
            }
            fragmentTransaction.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }