package si.uni_lj.fri.pbd.miniapp3.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import si.uni_lj.fri.pbd.miniapp3.models.RecipeDetailsIM;
import si.uni_lj.fri.pbd.miniapp3.repository.RecipeRepository;
import timber.log.Timber;

public class DetailsViewModel extends AndroidViewModel {

    public static final String RECIPE_ID = "recipe_id";
    public static final String INSTANTIATED_FROM = "instantiated_from";

    private RecipeRepository mRepository;
    private MutableLiveData<RecipeDetailsIM> mRecipe = new MutableLiveData<>();

    private Observer<RecipeDetailsIM> mObserver = recipeDetails -> {
        if (recipeDetails != null) {
            Timber.d("Recipe \"%s\" has favorite set to: %s", recipeDetails.getStrMeal(), recipeDetails.getFavorite());
            mRecipe.setValue(recipeDetails);
        }
    };

    public DetailsViewModel(@NonNull Application application) {
        super(application);

        // Create the repository and connect to RecipeDetailsIM LiveData
        mRepository = new RecipeRepository(application);
        mRepository.getRecipeDetails().observeForever(mObserver);
    }

    @Override
    protected void onCleared() {
        mRepository.getRecipeDetails().removeObserver(mObserver);
        super.onCleared();
    }

    void findRecipeById(String id) {
        mRepository.findRecipeById(id);
    }

    public LiveData<RecipeDetailsIM> getRecipe() {
        return mRecipe;
    }

    void toggleFavorite() {
        if (mRecipe.getValue() != null) {
            if (mRecipe.getValue().getFavorite()) {
                // Set favorite to false and remove recipe from database
                mRecipe.getValue().setFavorite(false);
                mRepository.deleteRecipe(mRecipe.getValue());
            }
            else {
                // Toggle favorite to true and insert recipe to database
                mRecipe.getValue().setFavorite(true);
                mRepository.insertRecipe(mRecipe.getValue());
            }
        }
    }
}
