package com.example.sd6501assignment1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.sd6501assignment1.Adapter.ToDoAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements HomeFragment.RecyclerViewItemClickListener {
//    Toolbar toolbar = findViewById(R.id.toolbar);
    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;

    private RecyclerView recyclerView;
    private ToDoAdapter toDoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //setSupportActionBar(toolbar);

//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
//                this, drawerLayout, R.string.open_nav,R.string.close_nav);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
        
        //floating bar
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);

            }
        });

        // Open menu drawer

//            //drawer view
//            navigationView = findViewById(R.id.bottomNavigationView);
//            navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//            if (savedInstanceState == null) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
//                navigationView.setCheckedItem(R.id.nav_home);
//            }
            bottomNavigationView = findViewById(R.id.bottomNavigationView);

            bottomNavigationView.setBackground(null);
            bottomNavigationView.setOnItemSelectedListener(item -> {

                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.profile:
                        replaceFragment(new ProfileFragment());
                        break;
                    case R.id.settings:
                        replaceFragment(new SettingsFragment());
                        break;
                    case R.id.calendar:
                        replaceFragment(new CalendarFragment());
                        break;
                }

                return true;

        });

        replaceFragment(new CalendarFragment());
}
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private static ToDoAdapter taskAdapter;

    public static ToDoAdapter getTaskAdapter() {
        Log.e("MenuActivity", "setAdapter");
        return taskAdapter;
    }

//    @Override
//    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//
//    }

    @Override
    public void onItemClick(int position) {

    }
}