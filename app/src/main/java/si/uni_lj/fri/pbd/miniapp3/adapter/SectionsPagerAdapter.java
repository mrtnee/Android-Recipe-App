package si.uni_lj.fri.pbd.miniapp3.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import si.uni_lj.fri.pbd.miniapp3.ui.favorites.FavoritesFragment;
import si.uni_lj.fri.pbd.miniapp3.ui.search.SearchFragment;

public class SectionsPagerAdapter extends FragmentStateAdapter {

    private int mTabCount;

    public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity, int numberOfTabs) {
        super(fragmentActivity);
        mTabCount = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                // Return search fragment
                return new SearchFragment();
            case 1:
                // Return favourites fragment
                return new FavoritesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return mTabCount;
    }
}
