package si.uni_lj.fri.pbd.miniapp3.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import timber.log.Timber;

public class RecipeDetailsIM extends BaseObservable {

    private Boolean isFavorite;
    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;
    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure9;
    private String strMeasure10;
    private String strMeasure11;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure14;
    private String strMeasure15;
    private String strMeasure16;
    private String strMeasure17;
    private String strMeasure18;
    private String strMeasure19;
    private String strMeasure20;
    private String strSource;

    public RecipeDetailsIM() {}

    public RecipeDetailsIM(Boolean isFavorite, String idMeal, String strMeal, String strCategory, String strArea,
                           String strInstructions, String strMealThumb, String strYoutube, String strIngredient1,
                           String strIngredient2, String strIngredient3, String strIngredient4, String strIngredient5,
                           String strIngredient6, String strIngredient7, String strIngredient8, String strIngredient9,
                           String strIngredient10, String strIngredient11, String strIngredient12, String strIngredient13,
                           String strIngredient14, String strIngredient15, String strIngredient16, String strIngredient17,
                           String strIngredient18, String strIngredient19, String strIngredient20, String strMeasure1,
                           String strMeasure2, String strMeasure3, String strMeasure4, String strMeasure5, String strMeasure6,
                           String strMeasure7, String strMeasure8, String strMeasure9, String strMeasure10, String strMeasure11,
                           String strMeasure12, String strMeasure13, String strMeasure14, String strMeasure15, String strMeasure16,
                           String strMeasure17, String strMeasure18, String strMeasure19, String strMeasure20, String strSource) {
        this.isFavorite = isFavorite;
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.strMeasure1 = strMeasure1;
        this.strMeasure2 = strMeasure2;
        this.strMeasure3 = strMeasure3;
        this.strMeasure4 = strMeasure4;
        this.strMeasure5 = strMeasure5;
        this.strMeasure6 = strMeasure6;
        this.strMeasure7 = strMeasure7;
        this.strMeasure8 = strMeasure8;
        this.strMeasure9 = strMeasure9;
        this.strMeasure10 = strMeasure10;
        this.strMeasure11 = strMeasure11;
        this.strMeasure12 = strMeasure12;
        this.strMeasure13 = strMeasure13;
        this.strMeasure14 = strMeasure14;
        this.strMeasure15 = strMeasure15;
        this.strMeasure16 = strMeasure16;
        this.strMeasure17 = strMeasure17;
        this.strMeasure18 = strMeasure18;
        this.strMeasure19 = strMeasure19;
        this.strMeasure20 = strMeasure20;
        this.strSource = strSource;
    }

    private boolean isNullOrEmpty(String str) {
        if (str == null) return true;
        return str.length() == 0;
    }

    public String getIngredientsListString() {
        StringBuilder builder = new StringBuilder();
        if (!isNullOrEmpty(strIngredient1)) builder.append(strIngredient1);
        if (!isNullOrEmpty(strIngredient2)) builder.append(", " + strIngredient2);
        if (!isNullOrEmpty(strIngredient3)) builder.append(", " + strIngredient3);
        if (!isNullOrEmpty(strIngredient4)) builder.append(", " + strIngredient4);
        if (!isNullOrEmpty(strIngredient5)) builder.append(", " + strIngredient5);
        if (!isNullOrEmpty(strIngredient6)) builder.append(", " + strIngredient6);
        if (!isNullOrEmpty(strIngredient7)) builder.append(", " + strIngredient7);
        if (!isNullOrEmpty(strIngredient8)) builder.append(", " + strIngredient8);
        if (!isNullOrEmpty(strIngredient9)) builder.append(", " + strIngredient9);
        if (!isNullOrEmpty(strIngredient10)) builder.append(", " + strIngredient10);
        if (!isNullOrEmpty(strIngredient11)) builder.append(", " + strIngredient11);
        if (!isNullOrEmpty(strIngredient12)) builder.append(", " + strIngredient12);
        if (!isNullOrEmpty(strIngredient13)) builder.append(", " + strIngredient13);
        if (!isNullOrEmpty(strIngredient14)) builder.append(", " + strIngredient14);
        if (!isNullOrEmpty(strIngredient15)) builder.append(", " + strIngredient15);
        if (!isNullOrEmpty(strIngredient16)) builder.append(", " + strIngredient16);
        if (!isNullOrEmpty(strIngredient17)) builder.append(", " + strIngredient17);
        if (!isNullOrEmpty(strIngredient18)) builder.append(", " + strIngredient18);
        if (!isNullOrEmpty(strIngredient19)) builder.append(", " + strIngredient19);
        if (!isNullOrEmpty(strIngredient20)) builder.append(", " + strIngredient20);
        return builder.toString();
    }

    public String getMeasuresListString() {
        StringBuilder builder = new StringBuilder();
        if (!isNullOrEmpty(strMeasure1)) builder.append(strMeasure1);
        if (!isNullOrEmpty(strMeasure2)) builder.append(", " + strMeasure2);
        if (!isNullOrEmpty(strMeasure3)) builder.append(", " + strMeasure3);
        if (!isNullOrEmpty(strMeasure4)) builder.append(", " + strMeasure4);
        if (!isNullOrEmpty(strMeasure5)) builder.append(", " + strMeasure5);
        if (!isNullOrEmpty(strMeasure6)) builder.append(", " + strMeasure6);
        if (!isNullOrEmpty(strMeasure7)) builder.append(", " + strMeasure7);
        if (!isNullOrEmpty(strMeasure8)) builder.append(", " + strMeasure8);
        if (!isNullOrEmpty(strMeasure9)) builder.append(", " + strMeasure9);
        if (!isNullOrEmpty(strMeasure10)) builder.append(", " + strMeasure10);
        if (!isNullOrEmpty(strMeasure11)) builder.append(", " + strMeasure11);
        if (!isNullOrEmpty(strMeasure12)) builder.append(", " + strMeasure12);
        if (!isNullOrEmpty(strMeasure13)) builder.append(", " + strMeasure13);
        if (!isNullOrEmpty(strMeasure14)) builder.append(", " + strMeasure14);
        if (!isNullOrEmpty(strMeasure15)) builder.append(", " + strMeasure15);
        if (!isNullOrEmpty(strMeasure16)) builder.append(", " + strMeasure16);
        if (!isNullOrEmpty(strMeasure17)) builder.append(", " + strMeasure17);
        if (!isNullOrEmpty(strMeasure18)) builder.append(", " + strMeasure18);
        if (!isNullOrEmpty(strMeasure19)) builder.append(", " + strMeasure19);
        if (!isNullOrEmpty(strMeasure20)) builder.append(", " + strMeasure20);
        return builder.toString();
    }

    @Bindable
    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
        notifyPropertyChanged(BR.favorite);
    }

    public String getIdMeal() {
        return idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public String getStrIngredient6() {
        return strIngredient6;
    }

    public String getStrIngredient7() {
        return strIngredient7;
    }

    public String getStrIngredient8() {
        return strIngredient8;
    }

    public String getStrIngredient9() {
        return strIngredient9;
    }

    public String getStrIngredient10() {
        return strIngredient10;
    }

    public String getStrIngredient11() {
        return strIngredient11;
    }

    public String getStrIngredient12() {
        return strIngredient12;
    }

    public String getStrIngredient13() {
        return strIngredient13;
    }

    public String getStrIngredient14() {
        return strIngredient14;
    }

    public String getStrIngredient15() {
        return strIngredient15;
    }

    public String getStrIngredient16() {
        return strIngredient16;
    }

    public String getStrIngredient17() {
        return strIngredient17;
    }

    public String getStrIngredient18() {
        return strIngredient18;
    }

    public String getStrIngredient19() {
        return strIngredient19;
    }

    public String getStrIngredient20() {
        return strIngredient20;
    }

    public String getStrMeasure1() {
        return strMeasure1;
    }

    public String getStrMeasure2() {
        return strMeasure2;
    }

    public String getStrMeasure3() {
        return strMeasure3;
    }

    public String getStrMeasure4() {
        return strMeasure4;
    }

    public String getStrMeasure5() {
        return strMeasure5;
    }

    public String getStrMeasure6() {
        return strMeasure6;
    }

    public String getStrMeasure7() {
        return strMeasure7;
    }

    public String getStrMeasure8() {
        return strMeasure8;
    }

    public String getStrMeasure9() {
        return strMeasure9;
    }

    public String getStrMeasure10() {
        return strMeasure10;
    }

    public String getStrMeasure11() {
        return strMeasure11;
    }

    public String getStrMeasure12() {
        return strMeasure12;
    }

    public String getStrMeasure13() {
        return strMeasure13;
    }

    public String getStrMeasure14() {
        return strMeasure14;
    }

    public String getStrMeasure15() {
        return strMeasure15;
    }

    public String getStrMeasure16() {
        return strMeasure16;
    }

    public String getStrMeasure17() {
        return strMeasure17;
    }

    public String getStrMeasure18() {
        return strMeasure18;
    }

    public String getStrMeasure19() {
        return strMeasure19;
    }

    public String getStrMeasure20() {
        return strMeasure20;
    }

    public String getStrSource() {
        return strSource;
    }

    @Override
    public String toString() {
        return "RecipeDetailsIM {" +
                "idMeal='" + idMeal + '\'' +
                ", strMeal='" + strMeal + '\'' +
                ", strCategory='" + strCategory + '\'' +
                ", strArea='" + strArea + '\'' +
                ", strInstructions='" + strInstructions + '\'' +
                ", strMealThumb='" + strMealThumb + '\'' +
                ", strYoutube='" + strYoutube + '\'' +
                ", strIngredient1='" + strIngredient1 + '\'' +
                ", strIngredient2='" + strIngredient2 + '\'' +
                ", strIngredient3='" + strIngredient3 + '\'' +
                ", strIngredient4='" + strIngredient4 + '\'' +
                ", strIngredient5='" + strIngredient5 + '\'' +
                ", strIngredient6='" + strIngredient6 + '\'' +
                ", strIngredient7='" + strIngredient7 + '\'' +
                ", strIngredient8='" + strIngredient8 + '\'' +
                ", strIngredient9='" + strIngredient9 + '\'' +
                ", strIngredient10='" + strIngredient10 + '\'' +
                ", strIngredient11='" + strIngredient11 + '\'' +
                ", strIngredient12='" + strIngredient12 + '\'' +
                ", strIngredient13='" + strIngredient13 + '\'' +
                ", strIngredient14='" + strIngredient14 + '\'' +
                ", strIngredient15='" + strIngredient15 + '\'' +
                ", strIngredient16='" + strIngredient16 + '\'' +
                ", strIngredient17='" + strIngredient17 + '\'' +
                ", strIngredient18='" + strIngredient18 + '\'' +
                ", strIngredient19='" + strIngredient19 + '\'' +
                ", strIngredient20='" + strIngredient20 + '\'' +
                ", strMeasure1='" + strMeasure1 + '\'' +
                ", strMeasure2='" + strMeasure2 + '\'' +
                ", strMeasure3='" + strMeasure3 + '\'' +
                ", strMeasure4='" + strMeasure4 + '\'' +
                ", strMeasure5='" + strMeasure5 + '\'' +
                ", strMeasure6='" + strMeasure6 + '\'' +
                ", strMeasure7='" + strMeasure7 + '\'' +
                ", strMeasure8='" + strMeasure8 + '\'' +
                ", strMeasure9='" + strMeasure9 + '\'' +
                ", strMeasure10='" + strMeasure10 + '\'' +
                ", strMeasure11='" + strMeasure11 + '\'' +
                ", strMeasure12='" + strMeasure12 + '\'' +
                ", strMeasure13='" + strMeasure13 + '\'' +
                ", strMeasure14='" + strMeasure14 + '\'' +
                ", strMeasure15='" + strMeasure15 + '\'' +
                ", strMeasure16='" + strMeasure16 + '\'' +
                ", strMeasure17='" + strMeasure17 + '\'' +
                ", strMeasure18='" + strMeasure18 + '\'' +
                ", strMeasure19='" + strMeasure19 + '\'' +
                ", strMeasure20='" + strMeasure20 + '\'' +
                ", strSource='" + strSource + '\'' +
                '}';
    }



}
