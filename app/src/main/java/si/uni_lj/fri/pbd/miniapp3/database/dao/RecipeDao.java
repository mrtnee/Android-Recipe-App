package si.uni_lj.fri.pbd.miniapp3.database.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import si.uni_lj.fri.pbd.miniapp3.database.entity.RecipeDetails;
import si.uni_lj.fri.pbd.miniapp3.models.RecipeDetailsIM;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM RecipeDetails WHERE idMeal = :idMeal")
    RecipeDetails getRecipeById(String idMeal);

    // TODO: Add the missing methods
    @Query("DELETE FROM RecipeDetails WHERE idMeal = :idMeal")
    void deleteRecipe(String idMeal);

    @Query("DELETE FROM RecipeDetails")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertRecipe(RecipeDetails rec);

    @Query("SELECT * FROM RecipeDetails")
    LiveData<List<RecipeDetails>> getAllRecipes();

    @Query("SELECT idMeal FROM RecipeDetails")
    LiveData<List<String>> getRecipeMealIds();
}
