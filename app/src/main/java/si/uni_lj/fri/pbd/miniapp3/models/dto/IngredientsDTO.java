package si.uni_lj.fri.pbd.miniapp3.models.dto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientsDTO {

    @SerializedName("meals")
    @Expose
    private List<IngredientDTO> ingredients;

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }
}
