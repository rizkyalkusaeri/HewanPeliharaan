package com.rizkyalkus.mebel1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rizkyalkus.mebel1.fragment.AnjingFragment;
import com.rizkyalkus.mebel1.fragment.BurungFragment;
import com.rizkyalkus.mebel1.fragment.IkanFragment;
import com.rizkyalkus.mebel1.fragment.KucingFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new IkanFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.ikan_menu:
                fragment = new IkanFragment();
                break;
            case R.id.kucing_menu:
                fragment = new KucingFragment();
                break;
            case R.id.anjing_menu:
                fragment = new AnjingFragment();
                break;
            case R.id.burung_menu:
                fragment = new BurungFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
