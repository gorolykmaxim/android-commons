package com.gorolykmaxim.android.commons.bottomnavigation;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gorolykmaxim.android.commons.R;

public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String SELECTED_ITEM_ID = "selected item ID";

    private SparseArray<Fragment> menuItemIdToFragment;
    private BottomNavigationView navigationView;
    private int menuResourceId;
    private int selectedItemId;

    public BottomNavigationActivity(int menuResourceId, int selectedItemId) {
        super();
        this.menuResourceId = menuResourceId;
        this.selectedItemId = selectedItemId;
        menuItemIdToFragment = new SparseArray<>();
    }

    protected void addFragment(int menuItemId, Fragment fragment) {
        menuItemIdToFragment.put(menuItemId, fragment);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation_activity);
        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.inflateMenu(menuResourceId);
        navigationView.setOnNavigationItemSelectedListener(this);
        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_ITEM_ID)) {
            navigationView.setSelectedItemId(savedInstanceState.getInt(SELECTED_ITEM_ID));
        } else {
            navigationView.setSelectedItemId(selectedItemId);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SELECTED_ITEM_ID, navigationView.getSelectedItemId());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = menuItemIdToFragment.get(item.getItemId());
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.bottom_navigation_fragment_container, fragment)
                    .commit();
            return true;
        } else {
            return false;
        }
    }
}
