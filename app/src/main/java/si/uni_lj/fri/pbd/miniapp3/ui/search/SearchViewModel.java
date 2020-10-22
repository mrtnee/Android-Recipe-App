package si.uni_lj.fri.pbd.miniapp3.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import si.uni_lj.fri.pbd.miniapp3.models.RecipeSummaryIM;
import si.uni_lj.fri.pbd.miniapp3.models.dto.IngredientDTO;
import si.uni_lj.fri.pbd.miniapp3.repository.RecipeRepository;

public class SearchViewModel extends AndroidViewModel {

    private RecipeRepository mRepository;

    private MutableLiveData<List<IngredientDTO>> mAllIngredients;
    private MutableLiveData<List<RecipeSummaryIM>> mRecipeSummaries;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        mRepository = new RecipeRepository(application);

        // Get references to ingredients and recipes with given ingredient
        mAllIngredients = mRepository.getAllIngredients();
        mRecipeSummaries = mRepository.getRecipeSummaries();

        // Immediately when SearchViewModel is created, fetch all ingredients from rest api
        mRepository.findAllIngredients();
    }

    void findRecipesWithIngredient(String ingredient) {
        mRepository.findRecipesWithIngredient(ingredient);
    }

    void findAllIngredients() {
        mRepository.findAllIngredients();
    }

    LiveData<List<IngredientDTO>> getAllIngredients() {
        return mAllIngredients;
    }

    LiveData<List<RecipeSummaryIM>> getRecipeSummaries() {
        return mRecipeSummaries;
    }
}
