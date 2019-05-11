package com.test2.tstuff.toolbar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.test2.tstuff.List.Lists;
import com.test2.tstuff.MainActivity;
import com.test2.tstuff.R;
import com.test2.tstuff.Water;

public class Tmenu extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
   protected ActionBarDrawerToggle mToggle;
   protected DrawerLayout mdrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmenu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       ActionBar actionBar = getSupportActionBar();
       actionBar.setDisplayHomeAsUpEnabled(true);
       actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black);

        //handling when items are clicked in drawer
       drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        //item keeps highlight when clicked
                        menuItem.setChecked(true);
                        //close drawer when item is selected
                        drawerLayout.closeDrawers();
                        Intent i;
                        switch (menuItem.getItemId()) {
                            case R.id.home: // Home button
                                i = new Intent(Tmenu.this, MainActivity.class);
                                startActivity(i);
                                break;

                            case R.id.drink: // Water
                                i = new Intent(Tmenu.this, Water.class);
                                startActivity(i);
                                break;
                            case R.id.back: //Close
                                drawerLayout.closeDrawers();
                                break;

                            case R.id.slist: // Shopping list
                                i = new Intent(Tmenu.this, Lists.class);
                                startActivity(i);
                                break;

                        }


                        return true;
                    }
                }

        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);


    }

}
