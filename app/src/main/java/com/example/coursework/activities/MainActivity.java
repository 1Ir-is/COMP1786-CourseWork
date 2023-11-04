package com.example.coursework.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.coursework.R;
import com.example.coursework.databinding.ActivityMainBinding;
import com.example.coursework.fragment.AddFragment;
import com.example.coursework.fragment.HomeFragment;
import com.example.coursework.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Replace the fragment with AddFragment initially
        replaceFragment(new AddFragment());

        // Đảm bảo đăng ký onNewIntent
        handleNewIntent(getIntent());

        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.add) {
                replaceFragment(new AddFragment());
            } else if (itemId == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (itemId == R.id.search) {
                replaceFragment(new SearchFragment());
            }
            return true;
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleNewIntent(intent);
    }

    private void handleNewIntent(Intent intent) {
        if (intent != null && intent.hasExtra("fragmentToLoad")) {
            String fragmentToLoad = intent.getStringExtra("fragmentToLoad");
            if (fragmentToLoad != null) {
                if (fragmentToLoad.equals("home_fragment")) {
                    // Display HomeFragment
                    replaceFragment(new HomeFragment());
                }
            }
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
