package com.example.heychat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.heychat.Fragment.ConfigFragment;
import com.example.heychat.Fragment.HomeFragment;
import com.example.heychat.Fragment.ProfileFragment;
import com.example.heychat.Fragment.VideoCallFragment;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottom_nav_view);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_chat));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_video_call));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_config));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_user));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener(){
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new VideoCallFragment();
                        break;
                    case 3:
                        fragment = new ConfigFragment();
                        break;
                    case 4:
                        fragment = new ProfileFragment();
                        break;
                }
                moveToFragement(fragment);
            }
        });
        bottomNavigation.show(1, true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener(){

            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener(){

            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });

    }

    private void moveToFragement(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}