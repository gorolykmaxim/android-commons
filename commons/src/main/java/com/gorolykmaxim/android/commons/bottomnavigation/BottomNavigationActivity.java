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

/**
 * Activity, that displays {@link BottomNavigationView} and a currently selected {@link Fragment}.
 *
 * <p>Use this activity in the following way:
 *
 * <pre>
 * create your menu.xml:
 * &lt;?xml version="1.0" encoding="utf-8"?&gt;
 * &lt;menu xmlns:android="http://schemas.android.com/apk/res/android"&gt;
 *     &lt;item
 *         android:id="@+id/first_item_id"
 *         android:icon="@drawable/ic_list_black_24dp"
 *         android:title="First item" /&gt;
 *     &lt;item
 *         android:id="@+id/second_item_id"
 *         android:icon="@drawable/ic_timer_black_24dp"
 *         android:title="Second item" /&gt;
 * &lt;/menu&gt;
 * create your activity, that inherits this activity:
 * public class MainActivity extends BottomNavigationActivity {
 *  public MainActivity() {
 *      super(R.menu.navigation, R.id.first_item_id);
 *  }
 *  @Override
 *  protected void onCreate(Bundle savedInstanceState){
 *      addFragment(R.id.first_item_id, new FirstFragment());
 *      addFragment(R.id.second_item_id, new SecondFragment());
 *      super.onCreate(savedInstanceState);
 *  }
 * }
 * </pre>
 *
 * <p>When the user will select the first menu item, the FirstFragment will get displayed by this
 * activity. Same applies to the second menu item and the SecondFragment. As per this example,
 * the first menu item / FirstFragment will be selected / displayed by default.
 */
public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String SELECTED_ITEM_ID = "selected item ID";

    private SparseArray<Fragment> menuItemIdToFragment;
    private BottomNavigationView navigationView;
    private int menuResourceId;
    private int selectedItemId;

    /**
     * Construct an activity.
     *
     * @param menuResourceId ID of the menu resource file to display in the bottom navigation bar
     *                       of this activity
     * @param selectedItemId ID of the item in the specified menu file, that should be selected
     *                       by default in the bottom navigation bar
     */
    public BottomNavigationActivity(int menuResourceId, int selectedItemId) {
        super();
        this.menuResourceId = menuResourceId;
        this.selectedItemId = selectedItemId;
        menuItemIdToFragment = new SparseArray<>();
    }

    /**
     * Display specified fragment in the activity's fragment container, when the bottom navigation
     * menu item with the specified ID is selected.
     *
     * @param menuItemId ID of the bottom navigation menu item, to bound the specified fragment to
     * @param fragment fragment to display when the specified menu item is selected
     */
    protected void addFragment(int menuItemId, Fragment fragment) {
        menuItemIdToFragment.put(menuItemId, fragment);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SELECTED_ITEM_ID, navigationView.getSelectedItemId());
        super.onSaveInstanceState(outState);
    }

    /**
     * {@inheritDoc}
     */
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
