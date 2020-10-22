package si.uni_lj.fri.pbd.miniapp3.ui.details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import si.uni_lj.fri.pbd.miniapp3.R;
import si.uni_lj.fri.pbd.miniapp3.databinding.ActivityDetailsBinding;
import si.uni_lj.fri.pbd.miniapp3.repository.RecipeRepository;
import timber.log.Timber;

public class DetailsActivity extends AppCompatActivity {

    private DetailsViewModel mViewModel;
    private ActivityDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate layout with binding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        // Retrieve recipe id and data source, from which to fetch recipe details
        String recipeId = getIntent().getExtras().getString(DetailsViewModel.RECIPE_ID);

        // Find the recipe with given id and from given data source
        mViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        mViewModel.findRecipeById(recipeId);

        // When the recipe is found, update the binding
        mViewModel.getRecipe().observe(this, recipeDetails -> {
            if (recipeDetails != null) {
                mBinding.setRecipe(recipeDetails);
            }
        });

        // onClickListener for setting a recipe favorite
        mBinding.buttonFavorite.setOnClickListener(v -> {
            mViewModel.toggleFavorite();
            mBinding.executePendingBindings();
        });
    }
}
