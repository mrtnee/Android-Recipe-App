package si.uni_lj.fri.pbd.miniapp3.ui.favorites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import si.uni_lj.fri.pbd.miniapp3.models.RecipeSummaryIM;
import si.uni_lj.fri.pbd.miniapp3.repository.RecipeRepository;
import timber.log.Timber;

public class FavoritesViewModel extends AndroidViewModel {

    @SuppressWarnings("FieldCanBeLocal")
    private RecipeRepository mRepository;

    private LiveData<List<RecipeSummaryIM>> mFavoriteRecipes;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        Timber.d("Starting FavoritesViewModel");

        // Create repository object and immediately get reference to favorite recipes
        mRepository = new RecipeRepository(application);
        mFavoriteRecipes = mRepository.getFavoriteRecipes();
    }

    LiveData<List<RecipeSummaryIM>> getFavoriteRecipes() {
        return mFavoriteRecipes;
    }
}
