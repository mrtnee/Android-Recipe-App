package si.uni_lj.fri.pbd.miniapp3.rest;

import androidx.lifecycle.LiveData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import si.uni_lj.fri.pbd.miniapp3.models.dto.IngredientDTO;
import si.uni_lj.fri.pbd.miniapp3.models.dto.IngredientsDTO;
import si.uni_lj.fri.pbd.miniapp3.models.dto.RecipesByIdDTO;
import si.uni_lj.fri.pbd.miniapp3.models.dto.RecipesByIngredientDTO;

public interface RestAPI {

    @GET("list.php?i=list")
    Call<IngredientsDTO> getAllIngredients();

    @GET("filter.php?")
    Call<RecipesByIngredientDTO> getAllRecipesWithIngredient(@Query("i") String ingredient);

    @GET("lookup.php?")
    Call<RecipesByIdDTO> getRecipeDetailsById(@Query("i") String id);

}