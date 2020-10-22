package si.uni_lj.fri.pbd.miniapp3.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipeSummaryDTO {
    @Expose
    @SerializedName("strMeal")
    private String strMeal;
    @Expose
    @SerializedName("strMealThumb")
    private String strMealThumb;
    @Expose
    @SerializedName("idMeal")
    private String idMeal;

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getIdMeal() {
        return idMeal;
    }
}
