package si.uni_lj.fri.pbd.miniapp3.models.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IngredientDTO {

    public IngredientDTO(String name) {
        this.strIngredient = name;
    }

    @SerializedName("idIngredient")
    @Expose
    private String idIngredient;
    @SerializedName("strIngredient")
    @Expose
    private String strIngredient;
    @SerializedName("strDescription")
    @Expose
    private String strDescription;

    public String getIdIngredient() {
        return idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }
}
