package si.uni_lj.fri.pbd.miniapp3.ui.search;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.util.LinkedList;

import si.uni_lj.fri.pbd.miniapp3.adapter.RecyclerViewAdapter;
import si.uni_lj.fri.pbd.miniapp3.adapter.SpinnerAdapter;
import si.uni_lj.fri.pbd.miniapp3.databinding.FragmentSearchBinding;
import si.uni_lj.fri.pbd.miniapp3.models.dto.IngredientDTO;
import timber.log.Timber;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    private FragmentSearchBinding mBinding;

    private SpinnerAdapter mSpinnerAdapter;
    private RecyclerViewAdapter mRecyclerAdapter;

    private long lastRefreshed = System.currentTimeMillis();

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false);

        // Set up SpinnerAdapter
        Spinner spinner = mBinding.spinnerIngredients;
        mSpinnerAdapter = new si.uni_lj.fri.pbd.miniapp3.adapter.SpinnerAdapter();
        spinner.setAdapter(mSpinnerAdapter);

        // Set up RecyclerViewAdapter
        RecyclerView recyclerView = mBinding.recyclerViewRecipes;
        mRecyclerAdapter = new RecyclerViewAdapter("SearchFragment");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mRecyclerAdapter);

        // When item in the spinner is selected, find all recipes with selected ingredient
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Timber.d("Spinner onItemSelected");
                IngredientDTO ingredientDTO = mSpinnerAdapter.getItem(position);
                String ingredient = ingredientDTO.getStrIngredient();
                mViewModel.findRecipesWithIngredient(ingredient);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        setUpRefreshBehaviour(spinner);

        // Check if internet connection is available, if not inform the user
        if (!isNetworkConnected()) {
            Toast.makeText(getContext(), "Internet connection is not available, please connect to internet and refresh by swiping down.", Toast.LENGTH_LONG).show();
        }

        return mBinding.getRoot();
    }

    private void setUpRefreshBehaviour(Spinner spinner) {
        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {

            if (!isNetworkConnected()) {
                Toast.makeText(getContext(), "Internet connection is not available, please connect to internet and refresh by swiping down.", Toast.LENGTH_LONG).show();
            }

            // If spinner is empty, fetch all ingredients from rest api
            if (spinner.getCount() == 0) {
                mViewModel.findAllIngredients();
            }

            // Set empty list as data set for recycler adapter
            mRecyclerAdapter.setRecipeSummaries(new LinkedList<>());
            mRecyclerAdapter.notifyDataSetChanged();

            new Handler().postDelayed(() -> {
                // A hack to call OnItemSelectedListener
                int position = spinner.getSelectedItemPosition();
                spinner.setSelection(position + 1 >= spinner.getCount() ? position - 1 : position + 1, true);
                spinner.setSelection(position, true);
                mBinding.swipeRefreshLayout.setRefreshing(false);

                // Disable refreshing and then enable it again in five seconds
                mBinding.swipeRefreshLayout.setEnabled(false);
                new Handler().postDelayed(() -> {
                    mBinding.swipeRefreshLayout.setEnabled(true);
                }, 5000);
            }, 100);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create the ViewModel
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        setUpObservers();
    }

    private void setUpObservers() {
        // When the ViewModel gets all ingredients, populate the spinner with them
        mViewModel.getAllIngredients().observe(getViewLifecycleOwner(), ingredients -> {
            if (ingredients != null && !ingredients.isEmpty()) {
                mSpinnerAdapter.setIngredients(ingredients);
                mSpinnerAdapter.notifyDataSetChanged();
                mBinding.materialProgressBar.setVisibility(View.GONE);
            }
        });

        // When ViewModel gets recipes with given ingredient, populate the RecyclerView
        mViewModel.getRecipeSummaries().observe(getViewLifecycleOwner(), recipeSummaries -> {
            if (recipeSummaries.isEmpty()) {
                Toast.makeText(getContext(), "No recipe with selected ingredient found.", Toast.LENGTH_LONG).show();
            }
            mRecyclerAdapter.setRecipeSummaries(recipeSummaries);
            mRecyclerAdapter.notifyDataSetChanged();
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
