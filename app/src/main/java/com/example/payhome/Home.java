package com.example.payhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mess:
                        Intent in = new Intent(Home.this,Mess.class);
                        startActivity(in);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.register_comp:
                        Intent intent = new Intent(Home.this,Register_Complaint.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.laundry:
                        Intent intent1 = new Intent(Home.this,Laundry.class);
                        startActivity(intent1);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.roominfo:
                        Intent intent5 = new Intent(Home.this,RoomInfo.class);
                        startActivity(intent5);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                    case R.id.leavereq:
                        Intent intent8 = new Intent(Home.this,LeaveReq.class);
                        startActivity(intent8);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.viewprofile:
                        Intent intent3 = new Intent(Home.this,viewProfile.class);
                        startActivity(intent3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        Intent intent2 = new Intent(Home.this,MainActivity.class);
                        startActivity(intent2);
                        finish();
                        break;


                }
                return true;
            }
        });

    }
}