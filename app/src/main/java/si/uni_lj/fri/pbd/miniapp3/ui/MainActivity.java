package si.uni_lj.fri.pbd.miniapp3.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.amitshekhar.DebugDB;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import si.uni_lj.fri.pbd.miniapp3.R;
import si.uni_lj.fri.pbd.miniapp3.adapter.SectionsPagerAdapter;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private static final int NUMBER_OF_TABS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        configureTabLayout();

        Timber.d("Database Address: " + DebugDB.getAddressLog());
    }

    private void configureTabLayout() {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, NUMBER_OF_TABS);
        viewPager.setAdapter(sectionsPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                        switch (position) {
                            case 0:
                                tab.setText("SEARCH RECIPE BY INGREDIENT");
                                break;
                            case 1:
                                tab.setText("FAVORITE RECIPES");
                                break;
                        }
                }).attach();
    }
}
