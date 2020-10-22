package si.uni_lj.fri.pbd.miniapp3.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipesByIdDTO {
    @Expose
    @SerializedName("meals")
    private List<RecipeDetailsDTO> recipes;

    public List<RecipeDetailsDTO> getRecipes() {
        return recipes;
    }
}
