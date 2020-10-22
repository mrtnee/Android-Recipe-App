package si.uni_lj.fri.pbd.miniapp3.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import si.uni_lj.fri.pbd.miniapp3.database.Database;
import si.uni_lj.fri.pbd.miniapp3.database.dao.RecipeDao;
import si.uni_lj.fri.pbd.miniapp3.database.entity.RecipeDetails;
import si.uni_lj.fri.pbd.miniapp3.models.Mapper;
import si.uni_lj.fri.pbd.miniapp3.models.RecipeDetailsIM;
import si.uni_lj.fri.pbd.miniapp3.models.RecipeSummaryIM;
import si.uni_lj.fri.pbd.miniapp3.models.dto.IngredientDTO;
import si.uni_lj.fri.pbd.miniapp3.models.dto.IngredientsDTO;
import si.uni_lj.fri.pbd.miniapp3.models.dto.RecipeDetailsDTO;
import si.uni_lj.fri.pbd.miniapp3.models.dto.RecipeSummaryDTO;
import si.uni_lj.fri.pbd.miniapp3.models.dto.RecipesByIdDTO;
import si.uni_lj.fri.pbd.miniapp3.models.dto.RecipesByIngredientDTO;
import si.uni_lj.fri.pbd.miniapp3.rest.RestAPI;
import si.uni_lj.fri.pbd.miniapp3.rest.ServiceGenerator;
import timber.log.Timber;

@SuppressWarnings("NullableProblems")
public class RecipeRepository {

    public enum DataSource {
        DATABASE,
        REST_API
    }

    private MutableLiveData<List<IngredientDTO>> mAllIngredients = new MutableLiveData<>();
    private MutableLiveData<List<RecipeSummaryIM>> mRecipeSummaries = new MutableLiveData<>();
    private MutableLiveData<List<RecipeSummaryIM>> mFavoriteRecipes = new MutableLiveData<>();
    private MutableLiveData<RecipeDetailsIM> mRecipeDetailsIntermediate = new MutableLiveData<>();
    private MutableLiveData<RecipeDetailsIM> mRecipeDetails = new MutableLiveData<>();
    private String mRecipeId;

    private RecipeDao mRecipeDao;

    public RecipeRepository(Application application) {
        Database db = Database.getDatabase(application);
        mRecipeDao = db.recipeDao();

        // Create an observer for recipe details, if a recipe is found in the database, we return
        // that value, otherwise we fetch the recipe from the REST API
        mRecipeDetailsIntermediate.observeForever(mRecipeDetailsIntermediateObserver);

        // Get all recipes from the database and transform them into RecipeDetailsIM, push them forward with LiveData
        mRecipeDao.getAllRecipes().observeForever(recipeDetails -> {
            if (recipeDetails != null && !recipeDetails.isEmpty()) {
                List<RecipeSummaryIM> recipeDetailsIm = new LinkedList<>();
                for (RecipeDetails rec : recipeDetails)
                    recipeDetailsIm.add(Mapper.mapRecipeDetailsToRecipeSummaryIm(rec));
                mFavoriteRecipes.setValue(recipeDetailsIm);
            }
            else {
                // TODO: there are no favorites, inform the user
                mFavoriteRecipes.setValue(new LinkedList<>());
            }
        });
    }

    public void insertRecipe(RecipeDetailsIM rec) {
        RecipeDetails recipeDetails = Mapper.mapRecipeDetailsImToRecipeDetails(rec, true);
        Database.databaseWriteExecutor.execute(() -> {
            mRecipeDao.insertRecipe(recipeDetails);
        });
    }

    public void deleteRecipe(RecipeDetailsIM rec) {
        Database.databaseWriteExecutor.execute(() -> {
            mRecipeDao.deleteRecipe(rec.getIdMeal());
        });
    }

    public void findAllIngredients() {
        // Call the thefooddb API and populate the Spinner with ingredients
        RestAPI client = ServiceGenerator.createService(RestAPI.class);
        Call<IngredientsDTO> ingredientsDtoCall = client.getAllIngredients();
        ingredientsDtoCall.enqueue(new Callback<IngredientsDTO>() {
            @Override
            public void onResponse(@NotNull Call<IngredientsDTO> call, @NotNull Response<IngredientsDTO> response) {
                if (response.body() != null) {
                    mAllIngredients.setValue(response.body().getIngredients());
                }
            }

            @Override
            public void onFailure(@NotNull Call<IngredientsDTO> call, @NotNull Throwable t) {
                Timber.d("API call getAllIngredients failed");
            }
        });
    }

    public void findRecipesWithIngredient(String ingredient) {
        // Call the API for recipes with ingredient and populate the array
        RestAPI client = ServiceGenerator.createService(RestAPI.class);
        Call<RecipesByIngredientDTO> recipesByIngredientDTOCall = client.getAllRecipesWithIngredient(ingredient);
        recipesByIngredientDTOCall.enqueue(new Callback<RecipesByIngredientDTO>() {
            @Override
            public void onResponse(Call<RecipesByIngredientDTO> call, Response<RecipesByIngredientDTO> response) {
                List<RecipeSummaryDTO> recipeSummaryDTOs =  response.body().getRecipeSummaries();
                if (recipeSummaryDTOs != null && !recipeSummaryDTOs.isEmpty()) {
                    List<RecipeSummaryIM> recipeSummaryIMs = new LinkedList<>();
                    for (RecipeSummaryDTO rec : recipeSummaryDTOs) {
                        recipeSummaryIMs.add(Mapper.mapRecipeSummaryDtoToRecipeSummaryIm(rec));
                    }
                    mRecipeSummaries.setValue(recipeSummaryIMs);
                }
                else {
                    // TODO: Notify user that there are no recipes with specified ingredient
                    mRecipeSummaries.setValue(new LinkedList<>());
                }
            }

            @Override
            public void onFailure(@NotNull Call<RecipesByIngredientDTO> call, @NotNull Throwable t) {
                Timber.d("Could not find all recipes by ingredient");
            }
        });
    }

    private Observer<RecipeDetailsIM> mRecipeDetailsIntermediateObserver = recipeDetails -> {
        if (recipeDetails != null) {
            mRecipeDetails.setValue(recipeDetails);
        }
        else {
            // Recipe is not in the database, so fetch it from REST API
            RestAPI client = ServiceGenerator.createService(RestAPI.class);
            Call<RecipesByIdDTO> recipesByIdDTOCall = client.getRecipeDetailsById(mRecipeId);
            recipesByIdDTOCall.enqueue(new Callback<RecipesByIdDTO>() {
                @Override
                public void onResponse(Call<RecipesByIdDTO> call, Response<RecipesByIdDTO> response) {
                    if (response.isSuccessful()) {
                        List<RecipeDetailsDTO> recipeDetailsDTOs = response.body().getRecipes();

                        if (recipeDetailsDTOs != null && !recipeDetailsDTOs.isEmpty()) {
                            RecipeDetailsDTO recDto = recipeDetailsDTOs.get(0);
                            RecipeDetailsIM recipeDetails = Mapper.mapRecipeDetailsDtoToRecipeDetailsIm(false, recDto);

                            mRecipeDetails.setValue(recipeDetails);
                        }
                    }
                }

                @Override
                public void onFailure(Call<RecipesByIdDTO> call, Throwable t) {
                    // TODO: inform user that there has been a problem when accessing data
                }
            });
        }
    };

    public void findRecipeById(String id) {
        mRecipeId = id;
        // First let's check if the recipe with the given id already exists in the database,
        // if it does, load it, else load recipe from rest api
        Database.databaseWriteExecutor.execute(() -> {
            RecipeDetails rec = mRecipeDao.getRecipeById(mRecipeId);
            if (rec != null) {
                RecipeDetailsIM recipeDetailsIm = Mapper.mapRecipeDetailsToRecipeDetailsIm(true, rec);
                mRecipeDetailsIntermediate.postValue(recipeDetailsIm);
            }
            else {
                mRecipeDetailsIntermediate.postValue(null);
            }
        });
    }

    public MutableLiveData<List<IngredientDTO>> getAllIngredients() {
        return mAllIngredients;
    }

    public MutableLiveData<List<RecipeSummaryIM>> getRecipeSummaries() {
        return mRecipeSummaries;
    }

    public MutableLiveData<RecipeDetailsIM> getRecipeDetails() {
        return mRecipeDetails;
    }

    public LiveData<List<RecipeSummaryIM>> getFavoriteRecipes() {
        return mFavoriteRecipes;
    }
}
