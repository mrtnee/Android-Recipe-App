package si.uni_lj.fri.pbd.miniapp3.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import si.uni_lj.fri.pbd.miniapp3.adapter.RecyclerViewAdapter;
import si.uni_lj.fri.pbd.miniapp3.databinding.FragmentFavoritesBinding;
import si.uni_lj.fri.pbd.miniapp3.repository.RecipeRepository;
import timber.log.Timber;

public class FavoritesFragment extends Fragment {

    @SuppressWarnings("FieldCanBeLocal")
    private FavoritesViewModel mViewModel;
    @SuppressWarnings("FieldCanBeLocal")
    private FragmentFavoritesBinding mBinding;

    private RecyclerViewAdapter mRecyclerAdapter;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentFavoritesBinding.inflate(inflater, container, false);

        // Create recycler view for favorite recipes
        RecyclerView recyclerView = mBinding.recyclerViewRecipes;
        mRecyclerAdapter = new RecyclerViewAdapter("FavoritesFragment");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mRecyclerAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create the ViewModel
        mViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        // Wait for the ViewModel to push you favorite recipes in form of RecipeSummaryIM
        mViewModel.getFavoriteRecipes().observe(getViewLifecycleOwner(), recipeSummaries -> {
            if (recipeSummaries != null) {
                if (recipeSummaries.isEmpty())
                    Toast.makeText(getContext(), "No favorite recipes found.", Toast.LENGTH_LONG).show();
                mRecyclerAdapter.setRecipeSummaries(recipeSummaries);
                mRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }
}
